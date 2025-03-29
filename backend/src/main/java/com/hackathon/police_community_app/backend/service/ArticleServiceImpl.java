package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.ArticleRequest;
import com.hackathon.police_community_app.backend.dto.response.ArticleResponse;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.entity.Article;
import com.hackathon.police_community_app.backend.entity.SosAlert;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.Status;
import com.hackathon.police_community_app.backend.mapper.ArticleMapper;
import com.hackathon.police_community_app.backend.repository.ArticleRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
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
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper mapper;

    private final ArticleRepository repository;
    private final UserRepository userRepository;

    @Override
    public PagedResponse<ArticleResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> articlePage = repository.findAll(pageable);
        return getPagedResponse(articlePage);
    }

    @Override
    public PagedResponse<ArticleResponse> getByAuthorId(Long authorId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> sosAlertPage = repository.findByAuthor(authorId, pageable);

        return getPagedResponse(sosAlertPage);
    }

    @Override
    public ArticleResponse getById(Long id) {
        Article sosAlert = repository.findByIdRequired(id);

        return mapper.toResponse(sosAlert);
    }

    @Override
    public ArticleResponse create(ArticleRequest request, Long authorId) {
        User author = userRepository.findByIdRequired(authorId);

        Article article = new Article();
        article.setTitle(request.getTitle())
                .setContent(request.getContent())
                .setEditable(request.getEditable())
                .setAuthor(author);

        article = repository.save(article);

        return mapper.toResponse(article);

    }

    @Override
    public ArticleResponse update(Long id, ArticleRequest request) {
        Article article = repository.findByIdRequired(id);

        article.setTitle(request.getTitle())
                .setContent(request.getContent())
                .setEditable(request.getEditable());

        return mapper.toResponse(article);

    }

    @Override
    public void delete(Long id) {
        Article article = repository.findByIdRequired(id);

        article.setIsDeleted(true);
    }

    private PagedResponse<ArticleResponse> getPagedResponse(Page<Article> sosAlertPage) {
        List<ArticleResponse> content = sosAlertPage.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        PagedResponse<ArticleResponse> response = new PagedResponse<>();
        response.setContent(content);
        response.setPage(sosAlertPage.getNumber());
        response.setSize(sosAlertPage.getSize());
        response.setTotalElements(sosAlertPage.getTotalElements());
        response.setTotalPages(sosAlertPage.getTotalPages());
        response.setLast(sosAlertPage.isLast());

        return response;
    }
}
