package com.eorionsolution.iot.epaper.service;

import com.eorionsolution.iot.epaper.domain.IntermediateData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntermediateDataRowMapper implements RowMapper<IntermediateData> {
    @Override
    public IntermediateData mapRow(ResultSet rs, int rowNum) throws SQLException {
        IntermediateData data = new IntermediateData();
        data.setScreenIp(rs.getString("screen_ip"))
            .setPictureName(rs.getString("picture_name"))
            .setTemplateName(rs.getString("template_name"))
            .setJsonData(rs.getString("json_data"))
            .setFlag(rs.getInt("flag"));
        return data;
    }
}
