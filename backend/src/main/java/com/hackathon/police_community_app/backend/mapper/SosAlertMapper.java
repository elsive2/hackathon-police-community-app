package com.hackathon.police_community_app.backend.mapper;

import com.hackathon.police_community_app.backend.dto.response.RightsResponse;
import com.hackathon.police_community_app.backend.dto.response.SosAlertResponse;
import com.hackathon.police_community_app.backend.entity.SosAlert;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SosAlertMapper {
    public SosAlertResponse toResponse(SosAlert sosAlert) {
        SosAlertResponse response = new SosAlertResponse();
        response.setId(sosAlert.getId())
                .setPhoneNumber(sosAlert.getPhoneNumber())
                .setLatitude(sosAlert.getLatitude())
                .setLongitude(sosAlert.getLongitude())
                .setStatus(sosAlert.getStatus())
                .setComment(sosAlert.getComment())
                .setCreateDate(sosAlert.getCreateDate())
                .setChangeDate(sosAlert.getChangeDate())
                .setRightsResponse(
                        (new RightsResponse())
                                .setOccupied(Objects.nonNull(sosAlert.getResponsible()))
                );
        return response;
    }
}
