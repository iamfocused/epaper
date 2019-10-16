package com.eorionsolution.iot.epaper.service;

import com.eorionsolution.iot.epaper.domain.ScreenDevice;
import com.eorionsolution.iot.epaper.repository.ScreenDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ScreenDeviceService {
    @Autowired
    ScreenDeviceRepository screenDeviceRepository;

    public List<ScreenDevice> getAllScreenDevice() {
        Iterable<ScreenDevice> source = screenDeviceRepository.findAll();
        List<ScreenDevice> screenDeviceList = new ArrayList<>();
        source.forEach(screenDeviceList::add);
        return screenDeviceList;
    }
}
