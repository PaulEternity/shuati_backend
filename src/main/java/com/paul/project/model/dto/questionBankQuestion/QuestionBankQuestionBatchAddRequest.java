package com.paul.project.model.dto.questionBankQuestion;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionBankQuestionBatchAddRequest implements Serializable {


    /**
     * 题库ID
     */
    private Long questionBankId;

    /**
     * 题目列表
     */
    private List<Long> questionList;

    private static final long serialVersionUID = 1L;

}
