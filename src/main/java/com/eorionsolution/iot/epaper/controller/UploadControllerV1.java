package com.eorionsolution.iot.epaper.controller;

import com.eorionsolution.iot.epaper.service.ScreenDeviceService;
import com.eorionsolution.iot.epaper.service.UploadPictureToRSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/v1/upload")
public class UploadControllerV1 {
    @Autowired
    private UploadPictureToRSService uploadPictureToRSService;
    @Autowired
    private ScreenDeviceService screenDeviceService;
    @GetMapping("")
    public String loadAll(Model model) {
        model.addAttribute("screenDeviceList", screenDeviceService.getAllScreenDevice());
        return "kanpod_upload";
    }

    @PostMapping("/{ip}/{emptyFolders}/{refresh_interval}")
    @ResponseBody
    public boolean handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable(value = "ip") String ip, @PathVariable(value = "emptyFolders") Boolean emptyFolders, @PathVariable(value = "refresh_interval") Integer refresh_interval) throws Exception {
        return  uploadPictureToRSService.upload(file, ip, emptyFolders, refresh_interval);
    }
}
