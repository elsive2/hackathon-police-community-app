package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.dto.response.KeyValueResponse;
import com.hackathon.police_community_app.backend.dto.response.context.ContextKeyValueResponse;
import com.hackathon.police_community_app.backend.dto.response.context.ContextQueryCategoryResponse;
import com.hackathon.police_community_app.backend.dto.response.context.ContextResponse;
import com.hackathon.police_community_app.backend.enums.Priority;
import com.hackathon.police_community_app.backend.enums.QueryCategory;
import com.hackathon.police_community_app.backend.enums.Role;
import com.hackathon.police_community_app.backend.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Context", description = "Содержит информацию по всем контекстам (Enums)")
@RequestMapping("/api/context")
@RestController
public class ContextController {

    @Operation(summary = "Получение всех статусов")
    @GetMapping("/status")
    public ContextResponse getStatusContext() {
        ContextResponse contextResponse = new ContextResponse();
        List<String> values = Arrays.stream(Status.values()).map(Status::name).toList();

        contextResponse.setName(Status.class.getSimpleName())
                .setValues(values);

        return contextResponse;
    }

    @Operation(summary = "Получение всех приоритетов")
    @GetMapping("/priority")
    public ContextKeyValueResponse getPriorityContext() {
        ContextKeyValueResponse contextResponse = new ContextKeyValueResponse();
        List<KeyValueResponse> values = Arrays.stream(Priority.values()).map(priority ->
                new KeyValueResponse(priority.name(), String.valueOf(priority.getValue()))
        ).toList();

        contextResponse.setName(Priority.class.getSimpleName())
                .setValues(values);

        return contextResponse;
    }

    @Operation(summary = "Получение всех категорий заявок")
    @GetMapping("/query-category")
    public ContextQueryCategoryResponse getQueryCategoryContext() {
        ContextQueryCategoryResponse contextResponse = new ContextQueryCategoryResponse();

        List<ContextQueryCategoryResponse.QueryCategoryResponse> values = Arrays.stream(QueryCategory.values()).map(queryCategory -> new ContextQueryCategoryResponse.QueryCategoryResponse(
                queryCategory.name(),
                queryCategory.getDisplayName(),
                queryCategory.getPriority().getValue()
        )).toList();

        contextResponse.setName(QueryCategory.class.getSimpleName())
                .setValues(values);

        return contextResponse;
    }

    @Operation(summary = "Получение всех ролей")
    @GetMapping("/role")
    public ContextResponse getRoleContext() {
        ContextResponse contextResponse = new ContextResponse();
        List<String> values = Arrays.stream(Role.values()).map(Role::name).toList();

        contextResponse.setName(Role.class.getSimpleName())
                .setValues(values);

        return contextResponse;
    }
}
