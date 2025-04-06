package com.paul.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paul.project.common.BaseResponse;
import com.paul.project.common.DeleteRequest;
import com.paul.project.model.dto.questionBank.QuestionBankAddRequest;
import com.paul.project.model.dto.questionBank.QuestionBankQueryRequest;
import com.paul.project.model.dto.questionBank.QuestionBankUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionBankControllerService {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String adminToken;

    @BeforeEach
    void setUp() throws Exception {
        // 模拟管理员登录，获取 Token
        String loginPayload = "{\"username\":\"paul\",\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginPayload))
                .andExpect(status().isOk())
                .andReturn();

        BaseResponse<?> response = objectMapper.readValue(result.getResponse().getContentAsString(), BaseResponse.class);
        this.adminToken = "Bearer " + response.getData().toString();
    }

    @Test
    void testAddQuestionBank() throws Exception {
        QuestionBankAddRequest request = new QuestionBankAddRequest();
        request.setDescription("Test Description");

        mockMvc.perform(post("/questionBank/add")
                        .header("Authorization", adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNumber());
    }

    @Test
    void testDeleteQuestionBank() throws Exception {
        DeleteRequest request = new DeleteRequest();
        request.setId(1L);

        mockMvc.perform(post("/questionBank/delete")
                        .header("Authorization", adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    void testUpdateQuestionBank() throws Exception {
        QuestionBankUpdateRequest request = new QuestionBankUpdateRequest();
        request.setId(1L);
        mockMvc.perform(post("/questionBank/update")
                        .header("Authorization", adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    void testGetQuestionBankVOById() throws Exception {
        QuestionBankQueryRequest request = new QuestionBankQueryRequest();
        request.setId(1L);

        mockMvc.perform(get("/questionBank/get/vo")
                        .header("Authorization", adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists());
    }
}

