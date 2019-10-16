package com.eorionsolution.iot.epaper.service;

import java.awt.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;

@Service
public class ScreenshotService {


//    public static void main(String args[])throws Exception {
//        System.setProperty("webdriver.firefox.bin", "E:\\software\\fireFox\\firefox.exe");
//        System.setProperty("webdriver.gecko.driver", "E:\\software\\fireFox\\geckodriver.exe");
//
//        FirefoxProfile profile= new FirefoxProfile();
//        //profile.setPreference( "layout.css.devPixelsPerPx", "1.4 " );
//        //WebDriver driver = new FirefoxDriver(profile);
//
//        FirefoxOptions options = new FirefoxOptions();
//        //options.setHeadless(true);
//        options.setProxy(null);
//        options.setProfile(profile);
//        WebDriver driver = new FirefoxDriver(options);
//
//        driver.manage().window().setSize(new Dimension(1200, 825));
//        //driver.manage().window().maximize();
//
//
//
//        driver.get("https://www.baidu.com");
//        //driver.get("https://metabase.eorionsolution.com/public/question/8402761b-f78e-4e08-8abb-3f3d4a2b3777");
//        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//
//        ((FirefoxDriver) driver).findElementByCssSelector("anthing").sendKeys(Keys.chord(Keys.CONTROL,"a"));
////driver.findElement(By.)
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        BufferedImage image = ImageIO.read(new ByteArrayInputStream(takesScreenshot.getScreenshotAs(OutputType.BYTES)));
//
//        BufferedImage copImage = new BufferedImage(1200, 825, image.getType());
//        Graphics2D g2d = copImage.createGraphics();
//        g2d.drawImage(image, 0, 0, 1200, 825, null);
//        g2d.dispose();
//
//        ImageIO.write(copImage, "png", new File("D:\\zipzip\\1200825(2).png"));
//    }
//
//    public byte[] url2Picture(String url) throws Exception {
//
//        System.setProperty("webdriver.firefox.bin", "E:\\software\\fireFox\\firefox.exe");
//        System.setProperty("webdriver.gecko.driver", "E:\\software\\fireFox\\geckodriver.exe");
//
//        FirefoxOptions options = new FirefoxOptions();
//        options.setHeadless(true);
//        options.setProxy(null);
//        WebDriver driver = new FirefoxDriver(options);
//
//        driver.manage().window().setSize(new Dimension(1200, 825));
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        driver.get(url);
//        System.out.println(url);
//
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        BufferedImage image = ImageIO.read(new ByteArrayInputStream(takesScreenshot.getScreenshotAs(OutputType.BYTES)));
//
//        BufferedImage copImage = new BufferedImage(1200, 825, image.getType());
//        Graphics2D g2d = copImage.createGraphics();
//        g2d.drawImage(image, 0, 0, 1200, 825, null);
//        g2d.dispose();
//
//        ImageIO.write(copImage, "png", new File("D:\\zipzip\\1200825.png"));
//
//
//        driver.quit();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ZipOutputStream zos = new ZipOutputStream(baos);
//
//        //convert image to black and white picture and zip
//        byte[] greyArray = picToGreyArray(copImage);
//
//        ZipEntry entry = new ZipEntry("png_" + Long.toString(System.currentTimeMillis()) + ".bitmap");
//        entry.setSize(greyArray.length);
//        zos.putNextEntry(entry);
//        zos.write(greyArray);
//        zos.closeEntry();
//        zos.close();
//
//        byte[] input = baos.toByteArray();
//        return input;
//    }
//
//    public static byte[] picToGreyArray(BufferedImage bufferedImage) throws Exception {
//        //Image tmp = bufferedImage.getScaledInstance(1872, 1404, Image.SCALE_SMOOTH);
//        //BufferedImage copImage = new BufferedImage(1872, 1404, BufferedImage.TYPE_INT_ARGB);
//        Image tmp = bufferedImage.getScaledInstance(1200, 825, Image.SCALE_SMOOTH);
//        BufferedImage copImage = new BufferedImage(1200, 825, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = copImage.createGraphics();
//        g2d.drawImage(tmp, 0, 0, null);
//        g2d.dispose();
//
//        byte[] greyArray = new byte[copImage.getHeight() * copImage.getWidth()];
//        for (int i = 0; i < copImage.getHeight(); i++)
//            for (int j = 0; j < copImage.getWidth(); j++) {
//                try {
//                    java.awt.Color color = new Color(copImage.getRGB(j, i));
//                    greyArray[copImage.getWidth() * i + j] = (byte) (((color.getBlue() + color.getGreen() + color.getRed()) / 3) & 0xFF);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.format("i=%d, j=%d\n", i, j);
//                }
//            }
//        return greyArray;
//    }
}
