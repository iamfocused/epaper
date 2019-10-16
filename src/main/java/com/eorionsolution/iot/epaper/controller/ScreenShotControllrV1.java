package com.eorionsolution.iot.epaper.controller;

import com.eorionsolution.iot.epaper.service.ScreenshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/v1/screenshot")
public class ScreenShotControllrV1 {
    @Autowired
    private ScreenshotService screenshotService;

//    @PostMapping("/url2picture")
//    @ResponseBody
//    public byte[] setPLDevice(@RequestBody Map<String, String> requestBody) throws Exception {
//        return screenshotService.url2Picture(requestBody.get("metabaseUrl"));
//    }
}
