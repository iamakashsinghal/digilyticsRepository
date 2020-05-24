package com.digilytics.digilyticsProject;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.digilytics.digilyticsProject.controller.UserController;
import com.digilytics.digilyticsProject.dto.Response;
import com.digilytics.digilyticsProject.service.UserService;

import junit.framework.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

/**
 * @author akash
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class DigilyticsProjectApplicationTests {

	private MockMvc mockMvc;
	
	@Mock
    private UserService userService;
	@Mock
    HttpServletResponse response;
    @Spy
    @InjectMocks
    private UserController controller = new UserController();
    @Autowired
    ResourceLoader resourceLoader;

	@BeforeEach
	 public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

	@SuppressWarnings("deprecation")
	@Test
    public void testUploadFile() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:testCsv.csv");
		InputStream input = resource.getInputStream();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", input);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/register").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals("testCsv.csv", result.getResponse().getContentAsString());
    }
	
	@Test
    public void testDownload() throws Exception {
        controller.downloadCSV(response);
    }
}
