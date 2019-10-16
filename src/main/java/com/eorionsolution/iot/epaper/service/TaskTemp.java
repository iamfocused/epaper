package com.eorionsolution.iot.epaper.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TaskTemp {
    private JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "0 */2 * * * ?")
    public void updateFlag() {
        log.info("task start.");
        jdbcTemplate.execute("UPDATE intermediate_data set flag = 0  ");
    }
}
