package com.paul.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private MockHttpSession session; // 用于存储 session

    @BeforeEach
    void setUp() throws Exception {
        session = new MockHttpSession(); // 初始化 session

        // 模拟用户登录
        MvcResult result = mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testUser\", \"password\":\"testPass\"}")
                        .session(session)) // 绑定 session
                .andExpect(status().isOk())
                .andReturn();

        // 确保登录成功，保存 session
        assertNotNull(result.getResponse().getCookie("JSESSIONID"));
    }

    @Test
    void testCreateQuestion() throws Exception {
        String questionJson = "{ \"title\": \"测试题目\", \"content\": \"这是一道测试题\" }";

        mockMvc.perform(post("/question/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionJson)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    void testGetQuestionList() throws Exception {
        mockMvc.perform(get("/question/list")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testDeleteQuestion() throws Exception {
        long questionId = 1;

        mockMvc.perform(delete("/question/delete/" + questionId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    //从ES中获取题目
    @Test
    void testSearchFromEs() throws Exception {
        mockMvc.perform(get("/question/search")
                        .param("searchText", "测试")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testAccessControl() throws Exception {
        // 模拟爬虫访问过多
        for (int i = 0; i < 25; i++) {
            mockMvc.perform(get("/question/list").session(session));
        }

        // 预期被封禁
        mockMvc.perform(get("/question/list").session(session))
                .andExpect(status().isForbidden());
    }
}
