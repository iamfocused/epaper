package com.eorionsolution.iot.epaper.service;

import com.eorionsolution.iot.epaper.config.PdfProperties;
import com.eorionsolution.iot.epaper.repository.ScreenDeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class UploadPictureToRSService {

    @Autowired
    ScreenDeviceRepository screenDeviceRepository;
    @Autowired
    PdfProperties pdfProperties;

    public boolean upload(MultipartFile file, String ip, Boolean emptyFolders, Integer refresh_interval) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Optional<String> fileExtensionOptional = Optional.ofNullable(filename).filter(f -> f.contains(".")).map(f -> f.substring(filename.lastIndexOf(".") + 1));
        String fileExtension = fileExtensionOptional.orElse("unknown");

        List<String> fileType = Arrays.asList("pdf", "png");
        List<String> picType = Arrays.asList("png");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        if (fileType.stream().anyMatch(str -> str.trim().equals(fileExtension))) {
            if (picType.stream().anyMatch(str -> str.trim().equals(fileExtension))) {
                InputStream inputStream = file.getInputStream();
                BufferedImage image = ImageIO.read(inputStream);
                //convert image to black and white picture and zip
                byte[] greyArray = picToGreyArrey(image);

                ZipEntry entry = new ZipEntry("png_" + Long.toString(System.currentTimeMillis()) + ".bitmap");
                entry.setSize(greyArray.length);
                zos.putNextEntry(entry);
                zos.write(greyArray);
                zos.closeEntry();
            } else {//处理pdf
                String currentEpoch = Long.toString(System.currentTimeMillis());
                final PDDocument document = PDDocument.load(file.getInputStream());
                try {
                    PDFRenderer pdfRenderer = new PDFRenderer(document);
                    for (int page = 0; page < document.getNumberOfPages(); ++page) {

                        BufferedImage bim = pdfRenderer.renderImageWithDPI(page, pdfProperties.getDpi(), ImageType.RGB);

                        byte[] greyArray = picToGreyArrey(bim);

                        ZipEntry entry = new ZipEntry("pdf_" + currentEpoch + "_" + page + ".bitmap");
                        entry.setSize(greyArray.length);
                        zos.putNextEntry(entry);
                        zos.write(greyArray);
                        zos.closeEntry();
                    }
                    document.close();
                } catch (IOException e) {
                    throw new Exception("Exception while trying to create pdf document - " + e);
                }
            }
        } else {
            throw new Exception("File suffix error,Upload pdf or png only");
        }
        zos.close();
        byte[] input = baos.toByteArray();

        //send to Raspberry Pi
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<byte[]> entity = new HttpEntity<>(input, null);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://" + ip + ":5000/api/upload")
                    .queryParam("empty_folders", emptyFolders)
                    .queryParam("refresh_interval", refresh_interval)
                    .queryParam("voltage", screenDeviceRepository.findFirstByDeviceIp(ip).get().getDeviceVoltage());

            ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
            HttpStatus status = responseEntity.getStatusCode();
            if (!status.is2xxSuccessful()) {
                throw new Exception("Send to Raspberry Pi error,Please contact the administrator");
            }
        } catch (Exception e) {
            throw new Exception("Connect Raspberry Pi error,Please contact the administrator");
        }
        return true;
    }

    public static byte[] picToGreyArrey(BufferedImage bufferedImage) throws Exception{
        //Image tmp = bufferedImage.getScaledInstance(1872, 1404, Image.SCALE_SMOOTH);
        //BufferedImage copImage = new BufferedImage(1872, 1404, BufferedImage.TYPE_INT_ARGB);
        Image tmp = bufferedImage.getScaledInstance(1200, 825, Image.SCALE_SMOOTH);
        BufferedImage copImage = new BufferedImage(1200, 825, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = copImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();


        javax.imageio.ImageIO.write(copImage, "png", new File("D:\\zipzip\\test\\"+ Long.toString(System.currentTimeMillis())+".png"));


        byte[] greyArray = new byte[copImage.getHeight() * copImage.getWidth()];
        for (int i = 0; i < copImage.getHeight(); i++)
            for (int j = 0; j < copImage.getWidth(); j++) {
                try {
                    java.awt.Color color = new Color(copImage.getRGB(j, i));
                    greyArray[copImage.getWidth() * i + j] = (byte) (((color.getBlue() + color.getGreen() + color.getRed()) / 3) & 0xFF);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.format("i=%d, j=%d\n", i, j);
                }
            }
        return greyArray;
    }

}
