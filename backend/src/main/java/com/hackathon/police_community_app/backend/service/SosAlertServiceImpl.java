package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.SosAlertRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.SosAlertResponse;
import com.hackathon.police_community_app.backend.entity.SosAlert;
import com.hackathon.police_community_app.backend.mapper.SosAlertMapper;
import com.hackathon.police_community_app.backend.repository.SosAlertRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class SosAlertServiceImpl implements SosAlertService {
    private final SosAlertMapper mapper;

    private final SosAlertRepository repository;

    public PagedResponse<SosAlertResponse> getAllSosAlerts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<SosAlert> sosAlertPage = repository.findAll(pageable);

        return getPagedResponse(sosAlertPage);
    }

    @Override
    public PagedResponse<SosAlertResponse> getAllByPhoneNumber(String phoneNumber, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SosAlert> sosAlertPage = repository.findByPhone(phoneNumber, pageable);

        return getPagedResponse(sosAlertPage);
    }

    @Override
    public SosAlertResponse getById(Long id) {
        SosAlert sosAlert = repository.findByIdRequired(id);

        return mapper.toResponse(sosAlert);
    }

    @Override
    public SosAlertResponse create(SosAlertRequest request) {
        SosAlert sosAlert = new SosAlert();
        sosAlert.setPhoneNumber(request.getPhoneNumber())
                .setLatitude(request.getLatitude())
                .setLongitude(request.getLongitude());

        sosAlert = repository.save(sosAlert);

        return mapper.toResponse(sosAlert);
    }

    @Override
    public SosAlertResponse update(Long id, SosAlertRequest request) {
        SosAlert sosAlert = repository.findByIdRequired(id);

        sosAlert.setLatitude(request.getLatitude())
                .setLongitude(request.getLongitude())
                .setPhoneNumber(request.getPhoneNumber());

        return mapper.toResponse(sosAlert);
    }

    @Override
    public void delete(Long id) {
        SosAlert sosAlert = repository.findByIdRequired(id);

        sosAlert.setIsDeleted(true);
    }

    private PagedResponse<SosAlertResponse> getPagedResponse(Page<SosAlert> sosAlertPage) {
        List<SosAlertResponse> content = sosAlertPage.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        PagedResponse<SosAlertResponse> response = new PagedResponse<>();
        response.setContent(content);
        response.setPage(sosAlertPage.getNumber());
        response.setSize(sosAlertPage.getSize());
        response.setTotalElements(sosAlertPage.getTotalElements());
        response.setTotalPages(sosAlertPage.getTotalPages());
        response.setLast(sosAlertPage.isLast());

        return response;
    }
}
