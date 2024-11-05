package com.paul.project.esdao;

import com.paul.project.model.dto.question.QuestionEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface QuestionEsDao extends ElasticsearchRepository<QuestionEsDTO,Long> {
    List<QuestionEsDTO> findByUserId(Long userId);
}
