package jp.co.axa.apidemo;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.controllers.EmployeeRestController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;


//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmployeeRestControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	EmployeeService employeeService;	
	
	@Autowired
	EmployeeRestController target;
	
	@Before(value = "")
	public void setUp() throws Exception {
	mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@Test
	public void getEmployees() throws Exception {
		// Mock Setting
		when(this.employeeService.retrieveEmployees())
		.thenReturn(new ArrayList<Employee>());
		
		mockMvc.perform(get("/api/v1/employees")
				.contentType("application/json;charset=UTF-8"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status",is("")));
		
	}
	
	public List<Employee> getEmployeeList() {
		List<Employee> l = new ArrayList<Employee>();
		Employee e = new Employee(2L,"emp1", 2000, "D1");
		l.add(e);
		 return l;
	}

}
