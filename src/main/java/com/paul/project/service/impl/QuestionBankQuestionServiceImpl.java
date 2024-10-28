package com.paul.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paul.project.model.entity.QuestionBankQuestion;
import com.paul.project.service.QuestionBankQuestionService;
import com.paul.project.mapper.QuestionBankQuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 30420
* @description 针对表【question_bank_question(题库题目)】的数据库操作Service实现
* @createDate 2024-10-28 17:23:50
*/
@Service
public class QuestionBankQuestionServiceImpl extends ServiceImpl<QuestionBankQuestionMapper, QuestionBankQuestion>
    implements QuestionBankQuestionService{

}




