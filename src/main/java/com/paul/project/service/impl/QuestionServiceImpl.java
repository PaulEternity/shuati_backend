package com.paul.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paul.project.model.entity.Question;
import com.paul.project.service.QuestionService;
import com.paul.project.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 30420
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-10-28 17:23:50
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




