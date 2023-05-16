package com.example.notesservice.functional;

import static com.example.utils.TestUtils.asJsonString;
import static com.example.utils.TestUtils.businessTestFile;
import static com.example.utils.TestUtils.currentTest;
import static com.example.utils.TestUtils.testReport;
import static com.example.utils.TestUtils.yakshaAssert;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.notesservice.controller.NoteController;
import com.example.notesservice.dto.NotesDto;
import com.example.notesservice.service.NoteService;
import com.example.utils.MasterData;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc
public class NoteControllerSecurityTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private NoteService noteService;
	
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	void testRestEndpointForFindAllNotesIsExposedAndWorking() throws Exception {
		List<NotesDto> list = new ArrayList<NotesDto>();
		list.add(MasterData.getNotesDto());
		Mockito.when(noteService.findAll()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/noteservice/public/all")
				.content(MasterData.asJsonString(MasterData.getNotesDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	//@WithMockUser(username = "spring", password = "secret", roles = "USER")
	@Test
	void testRestEndpointForDeletingNoteIsExposedAndWorking() throws Exception{
		NotesDto notesdto = com.example.utils.MasterData.getNotesDto();
		Integer id = notesdto.getId();
		Mockito.when(noteService.deleteNote(id))
		.thenReturn(notesdto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/noteservice/admin/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("Response : " + result.getResponse().getContentAsString());
		System.out.println("Response Code : " + result.getResponse().getStatus());
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(notesdto))? true : false,businessTestFile);
		
	}
}
