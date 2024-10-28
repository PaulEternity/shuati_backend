package com.paul.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paul.project.model.entity.QuestionBank;
import com.paul.project.service.QuestionBankService;
import com.paul.project.mapper.QuestionBankMapper;
import org.springframework.stereotype.Service;

/**
* @author 30420
* @description 针对表【question_bank(题库)】的数据库操作Service实现
* @createDate 2024-10-28 17:23:50
*/
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank>
    implements QuestionBankService{

}




