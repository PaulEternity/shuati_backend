package com.paul.project.job.cycle;


import cn.hutool.core.collection.CollUtil;
import com.paul.project.esdao.QuestionEsDao;
import com.paul.project.mapper.QuestionMapper;
import com.paul.project.model.dto.question.QuestionEsDTO;
import com.paul.project.model.entity.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class IncSyncPostToEs {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionEsDao questionEsDao;

    //每次查询进五分钟内的数据，每分钟一次
    @Scheduled(fixedRate = 60 * 1000)
    public void run() {
        long FIVE_MINUTES = 5 * 60 * 1000L;
        Date fiveMinutesAgoDate = new Date(new Date().getTime() - FIVE_MINUTES);
        List<Question> questionList = questionMapper.listQuestionWithDelete(fiveMinutesAgoDate);
        if (CollUtil.isEmpty(questionList)) {
            log.info("no inc question");
            return;
        }
        List<QuestionEsDTO> questionEsDTOS = questionList.stream()
                .map(QuestionEsDTO::objToDto)
                .collect(Collectors.toList());
        final int pageSize = 500;
        int total = questionEsDTOS.size();
        log.info("total " + total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            questionEsDao.saveAll(questionEsDTOS.subList(i, end));
        }
        log.info("IncSyncQuestionToEs end, total {}", total);
    }

}
