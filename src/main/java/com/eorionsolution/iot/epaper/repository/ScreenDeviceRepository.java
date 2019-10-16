package com.eorionsolution.iot.epaper.repository;

import com.eorionsolution.iot.epaper.domain.ScreenDevice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ScreenDeviceRepository extends CrudRepository<ScreenDevice, Long> {
    Optional<ScreenDevice> findFirstByDeviceIp(String deviceIp);

}
