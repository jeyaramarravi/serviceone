package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.controller.ApplicationController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = HotelbookingApplication.class)
public class ApplocationControllerTest {

	final String CREATE_ROOM = "/api/room";
	final String VACANT_ROOM = "/api/show_vacant_rooms";
	final String OCCUPIED_ROOM = "/api/show_occupied_rooms";
	final String ALL_ROOM = "/api/show_all_rooms";
	final String HOTEL_BOOKING = "/api/hotelbooking";
	final String CHECK_IN = "/api/check_in/1";
	final String CHECK_OUT = "/api/check_out/1";
	@Autowired
	ApplicationController controller;
	
	@Autowired
	WebApplicationContext context;
	
	MockMvc mvc;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}
	
	@Test
	public void testShowAllRoom() throws Exception {
		String uri = ALL_ROOM;
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri);
		request.header("Content-Type", MediaType.APPLICATION_JSON);
		request.header("Accept", MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		Assert.assertNotNull(result);
	}
	
}
