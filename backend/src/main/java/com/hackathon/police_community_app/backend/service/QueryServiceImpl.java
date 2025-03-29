package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.QueryRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.QueryResponse;
import com.hackathon.police_community_app.backend.entity.Query;
import com.hackathon.police_community_app.backend.entity.QueryPhoto;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.QueryCategory;
import com.hackathon.police_community_app.backend.enums.Status;
import com.hackathon.police_community_app.backend.exception.RecordIsOccupiedException;
import com.hackathon.police_community_app.backend.exception.StatusWrongException;
import com.hackathon.police_community_app.backend.mapper.QueryMapper;
import com.hackathon.police_community_app.backend.repository.QueryPhotoRepository;
import com.hackathon.police_community_app.backend.repository.QueryRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
@AllArgsConstructor
@Service
public class QueryServiceImpl implements QueryService {
    private final QueryRepository queryRepository;

    private final UserRepository userRepository;

    private final QueryMapper mapper;
    private final QueryPhotoRepository queryPhotoRepository;

    @Override
    public PagedResponse<QueryResponse> getAllByUser(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Query> sosAlertPage = queryRepository.findByUser(userId, pageable);

        return getPagedResponse(sosAlertPage);
    }

    @Override
    public PagedResponse<QueryResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Query> articlePage = queryRepository.findAll(pageable);
        return getPagedResponse(articlePage);
    }

    @Override
    @Transactional
    public QueryResponse create(QueryRequest request, Long userId) {
        User user = userRepository.findByIdRequired(userId);

        Query query = new Query()
                .setUser(user)
                .setContent(request.getContent())
                .setCategory(QueryCategory.valueOf(request.getCategory()))
                .setLatitude(request.getLatitude())
                .setLongitude(request.getLongitude())
                .setStatus(Status.NEW);

        // Обрабатываем фотографии (если они есть)
        if (request.getPhotos() != null && !request.getPhotos().isEmpty()) {
            List<QueryPhoto> photos = new ArrayList<>(); // Создаем изменяемый список

            for (String photoUrl : request.getPhotos()) {
                QueryPhoto photo = new QueryPhoto()
                        .setPhotoUrl(photoUrl)
                        .setQuery(query);

                photos.add(photo);
            }

            query.setPhotos(photos);
        }

        Query savedQuery = queryRepository.save(query);

        return mapper.toResponse(savedQuery);
    }

    @Override
    @Transactional
    public QueryResponse update(Long id, QueryRequest request) {
        Query query = queryRepository.findByIdRequired(id);

        query.setContent(request.getContent())
                .setCategory(QueryCategory.valueOf(request.getCategory()))
                .setLatitude(request.getLatitude())
                .setLongitude(request.getLongitude());

        if (request.getPhotos() != null && !request.getPhotos().isEmpty()) {
            query.getPhotos().clear();

            request.getPhotos().forEach(photoDto -> {
                QueryPhoto photo = new QueryPhoto();
                photo.setPhotoUrl(photoDto)
                        .setQuery(query);
                query.getPhotos().add(photo);
            });
        }

        return mapper.toResponse(queryRepository.save(query));
    }

    @Override
    public void delete(Long id) {
        Query query = queryRepository.findByIdRequired(id);

        query.setIsDeleted(true);
    }

    @Override
    public QueryResponse getById(Long id) {
        Query query = queryRepository.findByIdRequired(id);

        return mapper.toResponse(query);
    }

    @Override
    public void finish(Long id) {
        Query query = queryRepository.findByIdRequired(id);

        // Можем переводить статус только из статуса IN_PROGRESS
        if (!query.getStatus().equals(Status.IN_PROGRESS)) {
            throw new StatusWrongException();
        }

        query.setStatus(Status.COMPLETED);
    }

    @Override
    public void reject(Long id) {
        Query query = queryRepository.findByIdRequired(id);

        // Можем переводить статус только из статуса IN_PROGRESS
        if (!query.getStatus().equals(Status.IN_PROGRESS)) {
            throw new StatusWrongException();
        }

        query.setStatus(Status.REJECTED);

    }

    @Override
    public void assignResponsible(Long id, Long userId) {
        Query query = queryRepository.findByIdRequired(id);
        User user = userRepository.findByIdRequired(userId);

        // Можем назначать ответственного только в статусе NEW
        if (!query.getStatus().equals(Status.NEW)) {
            throw new StatusWrongException();
        }

        if (Objects.nonNull(query.getResponsible())) {
            throw new RecordIsOccupiedException();
        }

        query.setResponsible(user)
                .setStatus(Status.IN_PROGRESS);

    }

    private PagedResponse<QueryResponse> getPagedResponse(Page<Query> page) {
        List<QueryResponse> content = page.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        PagedResponse<QueryResponse> response = new PagedResponse<>();
        response.setContent(content);
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLast(page.isLast());

        return response;
    }
}
