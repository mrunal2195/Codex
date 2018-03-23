package com.northeastern.msd.team102.plagiarismchecker.controller;

import com.northeastern.msd.team102.plagiarismchecker.entity.FileUpload;
import com.northeastern.msd.team102.plagiarismchecker.entity.Homework;
import com.northeastern.msd.team102.plagiarismchecker.entity.Report;
import com.northeastern.msd.team102.plagiarismchecker.entity.User;
import com.northeastern.msd.team102.plagiarismchecker.service.HomeworkService;
import com.northeastern.msd.team102.plagiarismchecker.service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;

/**
 * Test case suite to test HomeWork controller
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = HomeworkController.class, secure = false)
public class HomeworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeworkService homeworkService;


    @Test
    public void createHomework() throws Exception {
        Homework testHomeWork = new Homework();
        testHomeWork.setName("HomeWork1");
        testHomeWork.setDescription("Home Work1 description");
        String testJson="{\"id\":0,\"name\":\"HomeWork1\",\"description\":\"Home Work1 description\",\"user\":null}";
        String ExpectedOutput="";
        Mockito.when(homeworkService.createHomework(testHomeWork,3)).thenReturn(testHomeWork);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/rest/homework/create").accept(MediaType.APPLICATION_JSON).content(testJson)
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId","3");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(ExpectedOutput, result.getResponse()
                .getContentAsString());
    }

    @Test
    public void findAllHomeworkForUser() throws Exception {
        Homework testHomeWork = new Homework();
        testHomeWork.setName("HomeWork1");
        testHomeWork.setDescription("Home Work1 description");
        List<Homework> testHomeWorkList = new ArrayList<>();
        testHomeWorkList.add(testHomeWork);
        String ExpectedOutput="[{\"id\":0,\"name\":\"HomeWork1\",\"description\":\"Home Work1 description\",\"user\":null}]";
        Mockito.when(homeworkService.findAllByUserId(3)).thenReturn(testHomeWorkList);
        MvcResult result;
        result=mockMvc.perform(MockMvcRequestBuilders.get("/rest/homework/findAllHomeworkForUser").param("userId","3"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(ExpectedOutput, result.getResponse().getContentAsString());
    }

}