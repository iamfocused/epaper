package com.eorionsolution.iot.epaper.service;

import com.eorionsolution.iot.epaper.domain.ScreenDevicePL;
import com.eorionsolution.iot.epaper.repository.ScreenDevicePLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScreenDevicePLService {
    @Autowired
    ScreenDevicePLRepository screenDevicePLRepository;

    public List<ScreenDevicePL> getAllScreenDevice() {
        Iterable<ScreenDevicePL> source = screenDevicePLRepository.findAll();
        List<ScreenDevicePL> screenDevicePLList = new ArrayList<ScreenDevicePL>();
        source.forEach(screenDevicePLList::add);
        return screenDevicePLList;
    }

    public boolean setDevice(String deviceIp, Integer refresh_interval, String metabaseUrl) throws Exception {
        Integer voltage = screenDevicePLRepository.findFirstByDeviceIp(deviceIp).get().getDeviceVoltage();
        String serviceIp = screenDevicePLRepository.findFirstByDeviceIp(deviceIp).get().getServiceIp();
        try {
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://" + deviceIp + ":5000/api/set");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map req_payload = new HashMap();
            req_payload.put("refresh_interval", refresh_interval);
            req_payload.put("voltage", voltage);
            req_payload.put("metabase_url", metabaseUrl);
            req_payload.put("service_ip", serviceIp);

            //HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
            HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, String.class);
            HttpStatus status = responseEntity.getStatusCode();
            if (!status.is2xxSuccessful()) {
                throw new Exception("Send to Raspberry Pi error,Please contact the administrator");
            }
        } catch (Exception e) {
            throw new Exception("Connect Raspberry Pi error,Please contact the administrator");
        }
        return true;
    }
}
