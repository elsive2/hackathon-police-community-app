package com.hackathon.police_community_app.backend.mapper;

import com.hackathon.police_community_app.backend.dto.response.ArticleResponse;
import com.hackathon.police_community_app.backend.dto.response.UserResponse;
import com.hackathon.police_community_app.backend.entity.Article;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleMapper {
    private UserMapper userMapper;

    public ArticleResponse toResponse(Article article) {
        ArticleResponse response = new ArticleResponse();
        UserResponse user = userMapper.toResponse(article.getAuthor());

        response.setId(article.getId())
                .setTitle(article.getTitle())
                .setContent(article.getContent())
                .setEditable(article.getEditable())
                .setCreationDate(article.getCreateDate())
                .setChangeDate(article.getChangeDate())
                .setAuthor(user);

        return response;
    }
}
