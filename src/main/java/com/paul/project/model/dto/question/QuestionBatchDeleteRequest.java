package com.paul.project.model.dto.question;

import com.paul.project.model.entity.Question;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionBatchDeleteRequest implements Serializable {
    private List<Long> questionIdList;

    private static final long serialVersionUID = 1L;
}
