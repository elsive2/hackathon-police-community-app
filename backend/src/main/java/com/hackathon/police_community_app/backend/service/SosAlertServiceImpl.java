package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.SosAlertRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.SosAlertResponse;
import com.hackathon.police_community_app.backend.entity.SosAlert;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.Status;
import com.hackathon.police_community_app.backend.exception.SosAlertIsOccupiedException;
import com.hackathon.police_community_app.backend.exception.StatusWrongException;
import com.hackathon.police_community_app.backend.mapper.SosAlertMapper;
import com.hackathon.police_community_app.backend.repository.SosAlertRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@AllArgsConstructor
@Service
public class SosAlertServiceImpl implements SosAlertService {
    private final SosAlertMapper mapper;

    private final SosAlertRepository sosAlertRepository;

    private final UserRepository userRepository;

    public PagedResponse<SosAlertResponse> getAllSosAlerts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<SosAlert> sosAlertPage = sosAlertRepository.findAll(pageable);

        return getPagedResponse(sosAlertPage);
    }

    @Override
    public PagedResponse<SosAlertResponse> getAllByPhoneNumber(String phoneNumber, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SosAlert> sosAlertPage = sosAlertRepository.findByPhone(phoneNumber, pageable);

        return getPagedResponse(sosAlertPage);
    }

    @Override
    public SosAlertResponse getById(Long id) {
        SosAlert sosAlert = sosAlertRepository.findByIdRequired(id);

        return mapper.toResponse(sosAlert);
    }

    @Override
    public SosAlertResponse create(SosAlertRequest request, String phone) {
        // @TODO: валидация телефона
        SosAlert sosAlert = new SosAlert();
        sosAlert.setLatitude(request.getLatitude())
                .setLongitude(request.getLongitude())
                .setPhoneNumber(phone)
                .setStatus(Status.NEW);

        sosAlert = sosAlertRepository.save(sosAlert);

        return mapper.toResponse(sosAlert);
    }

    @Override
    public SosAlertResponse update(Long id, SosAlertRequest request) {
        SosAlert sosAlert = sosAlertRepository.findByIdRequired(id);

        sosAlert.setLatitude(request.getLatitude())
                .setLongitude(request.getLongitude());

        return mapper.toResponse(sosAlert);
    }

    @Override
    public void finish(Long id) {
        SosAlert sosAlert = sosAlertRepository.findByIdRequired(id);

        // Можем переводить статус только из статуса IN_PROGRESS
        if (!sosAlert.getStatus().equals(Status.IN_PROGRESS)) {
            throw new StatusWrongException();
        }

        sosAlert.setStatus(Status.COMPLETED);
    }

    @Override
    public void reject(Long id) {
        SosAlert sosAlert = sosAlertRepository.findByIdRequired(id);

        // @TODO: Чтобы менял статус только отвественный

        // Можем переводить статус только из статуса IN_PROGRESS
        if (!sosAlert.getStatus().equals(Status.IN_PROGRESS)) {
            throw new StatusWrongException();
        }

        sosAlert.setStatus(Status.REJECTED);
    }

    @Override
    public void assignResponsible(Long id, Long userId) {
        SosAlert sosAlert = sosAlertRepository.findByIdRequired(id);
        User user = userRepository.findByIdRequired(userId);

        // Можем назначать ответственного только в статусе NEW
        if (!sosAlert.getStatus().equals(Status.NEW)) {
            throw new StatusWrongException();
        }

        if (Objects.nonNull(sosAlert.getResponsible())) {
            throw new SosAlertIsOccupiedException();
        }

        sosAlert.setResponsible(user)
                .setStatus(Status.IN_PROGRESS);
    }

    @Override
    public void delete(Long id) {
        SosAlert sosAlert = sosAlertRepository.findByIdRequired(id);

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
