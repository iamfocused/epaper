package com.eorionsolution.iot.epaper.controller;

import com.eorionsolution.iot.epaper.domain.ScreenDevice;
import com.eorionsolution.iot.epaper.domain.ScreenDevicePL;
import com.eorionsolution.iot.epaper.service.ScreenDevicePLService;
import com.eorionsolution.iot.epaper.service.ScreenDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/screen_device")
public class ScreenDeviceControllerV1 {
    @Autowired
    private ScreenDeviceService screenDeviceService;

    @Autowired
    private ScreenDevicePLService screenDevicePLService;

    @GetMapping("")
    @ResponseBody
    public List<ScreenDevice> getAllDevice() {
        return screenDeviceService.getAllScreenDevice();
    }

    @GetMapping("/pl")
    @ResponseBody
    public List<ScreenDevicePL> getAllPLDevice() {
        return screenDevicePLService.getAllScreenDevice();
    }

    @PostMapping("/pl/set")
    @ResponseBody
    public boolean setPLDevice(@RequestBody Map<String, String> requestBody) throws Exception {
        boolean b = screenDevicePLService.setDevice(requestBody.get("deviceIp"), Integer.valueOf(requestBody.get("refreshInterval")), requestBody.get("metabaseUrl"));
        return b;
    }
}
