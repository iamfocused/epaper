package com.eorionsolution.iot.epaper.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.eorionsolution.iot.epaper.config.PdfProperties;
import com.eorionsolution.iot.epaper.domain.IntermediateData;
import com.eorionsolution.iot.epaper.util.RaspberryPiUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JdbcMessageHandler {
    @Autowired
    private PdfProperties pdfProperties;
    @Autowired
    private RaspberryPiUtils raspberryPiUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void handleJdbcMessage(List<IntermediateData> messages) {
        log.info("message handle start.");
        ObjectMapper mapper = new ObjectMapper();

        messages.forEach(message -> {
            int errorLevel = 5;
            PDDocument document = null;
            try {
                Map<String, Object> jsonDataMap = mapper.readValue(message.getJsonData(), new TypeReference<Map<String, Object>>() {
                });
                errorLevel -= 1;
                JasperReport jasperReport = JasperCompileManager.compileReport(pdfProperties.getTemplatePath() + "" + message.getTemplateName());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRMapArrayDataSource(new Map[]{jsonDataMap}));
                document = PDDocument.load(JasperExportManager.exportReportToPdf(jasperPrint));
                errorLevel -= 1;
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                BufferedImage bim = pdfRenderer.renderImageWithDPI(0, pdfProperties.getDpi(), ImageType.RGB);
                byte[] bitmap = raspberryPiUtils.convertImageToBitmap(bim, message.getPictureName());
                errorLevel -= 1;
                raspberryPiUtils.sendDataToRaspberryPi(bitmap,"http://" + message.getScreenIp() + ":5000/api/upload/pl", null, null);
                errorLevel -= 1;
            }catch (Exception e) {
                log.error("message handle error. [screenIp:" + message.getScreenIp() + "][templateName:" + message.getTemplateName() + "][pictureName:" + message.getPictureName() + "]");
                log.error("trace is ", e);
            } finally {
                Optional.ofNullable(document).ifPresent(doc -> {
                    try {
                        doc.close();
                    } catch (IOException e) {
                        log.error("PDDocument close IOException.");
                    }
                });
            }
            message.setErrorLevel(errorLevel);
        });

        jdbcTemplate.batchUpdate("UPDATE intermediate_data SET error_level=?,handle_time=GETDATE() WHERE screen_ip=? AND template_name=? AND picture_name=?", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                IntermediateData msg = messages.get(i);
                ps.setInt(1, msg.getErrorLevel());
                ps.setString(2, msg.getScreenIp());
                ps.setString(3, msg.getTemplateName());
                ps.setString(4, msg.getPictureName());
            }

            @Override
            public int getBatchSize() {
                return messages.size();
            }
        });

        log.info("execute finish.");
    }

}
