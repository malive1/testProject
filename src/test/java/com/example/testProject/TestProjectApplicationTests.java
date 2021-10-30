package com.example.testProject;

import com.example.testProject.controller.MainController;
import com.example.testProject.entity.DtoListValidate;
import com.example.testProject.entity.DtoUser;
import com.example.testProject.entity.ResultsRequests;
import com.example.testProject.service.ServiceValidate;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.LinkedList;
import java.util.Set;
import javax.validation.*;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class TestProjectApplicationTests {

	@Autowired
	private ServiceValidate serviceValidate;
	private MockMvc mockMvc;
	@InjectMocks
	MainController mainController;

	/**
	 * Check validate null field.
	 */
	@Test
	@Order(1)
	void validateNullableCheckTest() {

		Throwable thrown = catchThrowable(() -> {
			DtoUser testDto = new DtoUser(null, "test",
					"test", "+79780000000","email@server.com","123456","123456");

		});
		assertThat(thrown).isInstanceOf(NullPointerException.class);
		assertThat(thrown.getMessage()).isNotBlank();
	}

	/**
	 * Check email,name,password validation.
	 */
	@Test
	@Order(2)
	void validateServiceTest() {
		DtoUser testDto = new DtoUser("test", "t123est",
				"test", "+79780000000","emailserver.com","123456y","123456");


		DtoListValidate infoValidate = new DtoListValidate();
		LinkedList<ResultsRequests> checkInfo = serviceValidate.validateDtoObject(testDto);
		int countCheckMesage=0;

		for (ResultsRequests searchVar : checkInfo) {
			if(searchVar.getValueKeyData().equals("PASSWORD")){countCheckMesage++;}
			if(searchVar.getValueKeyData().equals("NAME")){countCheckMesage++;}
			if(searchVar.getValueKeyData().equals("MAIL")){countCheckMesage++;}
		}


		assertEquals(countCheckMesage,3);
	}

	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(mainController)
				.build();
	}
	@Test
	@Order(3)
	void addTest() throws Exception {

		/*MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\n" +
						"  \"checkPassword\": \"123456\",\n" +
						"  \"email\": \"test@mail.com\",\n" +
						"  \"middleName\": \"test\",\n" +
						"  \"password\": \"123456\",\n" +
						"  \"phone\": \"+79780000000\",\n" +
						"  \"surname\": \"test\"\n" +
						"}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andReturn();
		String message = requireNonNull(response.getResolvedException(), "Не получено сообщение от контроллера").getMessage();
		assertTrue(message.contains("can't parse JSON.  Raw result:\n" +
				"\n" +
				"ERROR"));*/
	}

}
