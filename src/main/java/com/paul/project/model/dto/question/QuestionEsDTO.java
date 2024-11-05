package com.paul.project.model.dto.question;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.paul.project.model.entity.Question;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(indexName = "question")
@Data
public class QuestionEsDTO implements Serializable {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Id
    private Long id;

    private String title;

    private String content;

    private String answer;

    private List<String> tags;

    private Long userId;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, format = {}, pattern = DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date, format = {}, pattern = DATE_TIME_PATTERN)
    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    public static QuestionEsDTO objToDto(Question question) {
        if (question == null) return null;
        QuestionEsDTO questionEsDTO = new QuestionEsDTO();
        BeanUtils.copyProperties(question, questionEsDTO);
        String tagsStr = question.getTags();
        if (StrUtil.isNotBlank(tagsStr)) {
            questionEsDTO.setTags(JSONUtil.toList(JSONUtil.parseArray(tagsStr), String.class));
        }
        return questionEsDTO;
    }

    public static Question dtoToObj(QuestionEsDTO questionEsDTO) {
        if (questionEsDTO == null) return null;
        Question question = new Question();
        BeanUtils.copyProperties(questionEsDTO, question);
        String tagsStr = question.getTags();
        if (StrUtil.isNotBlank(tagsStr)) {
            question.setTags(JSONUtil.toJsonStr(tagsStr));
        }
        return question;
    }


}
