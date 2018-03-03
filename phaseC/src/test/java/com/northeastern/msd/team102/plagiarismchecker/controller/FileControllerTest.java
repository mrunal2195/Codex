package com.northeastern.msd.team102.plagiarismchecker.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = FileController.class, secure = false)
public class FileControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private FileController fileController;
	
	@Test
    public void parse() throws Exception {
    	String mockParsedFile = "file_input funcdef  parameters  suite   simple_stmt    atom_expr     atom     trailer\"\r\n" + 
    			"               + \"      atom if_stmt  comparison   atom   comp_op   atom  atom_expr   atom   trailer";
    	
    	Mockito.when(fileController.parsePythonFile()).thenReturn(mockParsedFile);
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/file/parse").accept(MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(mockParsedFile, result.getResponse().getContentAsString());
    }
	
}