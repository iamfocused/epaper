package com.eorionsolution.iot.epaper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="screen_device")
public class ScreenDevice {
    @Id
    @Column(name = "device_ip")
    private String deviceIp;
    @Column(name = "device_mac")
    private String deviceMac;
    @Column(name = "device_desc")
    private String deviceDesc;
    @Column(name = "device_voltage")
    private Integer deviceVoltage;

    public ScreenDevice() {
    }

}
