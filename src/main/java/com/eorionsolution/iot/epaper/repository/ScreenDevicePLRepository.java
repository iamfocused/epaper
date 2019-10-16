package com.eorionsolution.iot.epaper.repository;

import com.eorionsolution.iot.epaper.domain.ScreenDevicePL;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScreenDevicePLRepository  extends CrudRepository<ScreenDevicePL, Long> {
    Optional<ScreenDevicePL> findFirstByDeviceIp(String deviceIp);

}
