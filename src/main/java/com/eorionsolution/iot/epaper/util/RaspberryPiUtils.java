package com.eorionsolution.iot.epaper.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
@AllArgsConstructor
@Slf4j
public class RaspberryPiUtils {
    public void sendDataToRaspberryPi(byte[] input,String url, Integer freshTime, Integer voltage) throws Exception {

        assert input != null && url != null;

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<byte[]> entity = new HttpEntity<>(input, null);
        UriComponentsBuilder builder;
        if(freshTime == null || voltage == null) {
            builder = UriComponentsBuilder.fromHttpUrl(url);
        } else {
            builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("empty_folders", false)
                    .queryParam("refresh_interval", freshTime)
                    .queryParam("voltage", voltage);
        }

        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
        HttpStatus status = responseEntity.getStatusCode();
        if (!status.is2xxSuccessful()) {
            log.error("send failed. return status is not 200");
            throw new Exception("send failed. return status is not 200");
        }

    }

    public byte[] convertImageToBitmap(BufferedImage bufferedImage, String pictureName) throws Exception{
        return this.convertGreyArrayToBitmap(this.convertPicToGreyArray(bufferedImage), pictureName);
    }

    public byte[] convertPicToGreyArray(BufferedImage bufferedImage) throws Exception{
        Image tmp = bufferedImage.getScaledInstance(1200, 825, Image.SCALE_SMOOTH);
        BufferedImage copImage = new BufferedImage(1200, 825, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = copImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        byte[] greyArray = new byte[copImage.getHeight() * copImage.getWidth()];
        for (int i = 0; i < copImage.getHeight(); i++)
            for (int j = 0; j < copImage.getWidth(); j++) {
                java.awt.Color color = new Color(copImage.getRGB(j, i));
                greyArray[copImage.getWidth() * i + j] = (byte) (((color.getBlue() + color.getGreen() + color.getRed()) / 3) & 0xFF);
            }
        return greyArray;
    }

    public byte[] convertGreyArrayToBitmap(byte[] greyArray, String bitmapName) throws Exception{
        ByteArrayOutputStream baos = null;
        ZipOutputStream zos = null;
        try {
            baos = new ByteArrayOutputStream();
            zos = new ZipOutputStream(baos);
            ZipEntry entry = new ZipEntry(bitmapName + ".bitmap");
            entry.setSize(greyArray.length);
            zos.putNextEntry(entry);
            zos.write(greyArray);
            zos.closeEntry();
        } finally {
            if(baos != null)
                baos.close();
            if(zos != null)
                zos.close();
        }
        return Optional.of(baos).map(ByteArrayOutputStream::toByteArray).orElseThrow(()-> new Exception("bitmap convert failed."));
    }
}
