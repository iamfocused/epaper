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
@Table(name = "screen_device_pl")
//production line screen device
public class ScreenDevicePL {
    @Id
    @Column(name = "device_ip")
    private String deviceIp;
    @Column(name = "service_ip")
    private String serviceIp;
    @Column(name = "device_mac")
    private String deviceMac;
    @Column(name = "device_desc")
    private String deviceDesc;
    @Column(name = "device_voltage")
    private Integer deviceVoltage;
    @Column(name = "refresh_time")
    private Integer refreshTime;

    public ScreenDevicePL() {
    }

}
