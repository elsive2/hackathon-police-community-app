package com.hackathon.police_community_app.backend.mapper;

import com.hackathon.police_community_app.backend.dto.response.SosAlertResponse;
import com.hackathon.police_community_app.backend.entity.SosAlert;
import org.springframework.stereotype.Component;

@Component
public class SosAlertMapper {
    public SosAlertResponse toResponse(SosAlert sosAlert) {
        SosAlertResponse response = new SosAlertResponse();
        response.setId(sosAlert.getId())
                .setPhoneNumber(sosAlert.getPhoneNumber())
                .setLatitude(sosAlert.getLatitude())
                .setLongitude(sosAlert.getLongitude())
                .setCreateDate(sosAlert.getCreateDate())
                .setChangeDate(sosAlert.getChangeDate());
        return response;
    }
}
