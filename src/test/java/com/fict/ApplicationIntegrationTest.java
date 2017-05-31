package com.fict;

import com.fict.entities.Customer;
import com.fict.entities.Role;
import com.fict.repository.RoleRepository;
import com.fict.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by stevo on 5/31/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "flyway.enabled=false")
@AutoConfigureTestDatabase
public class ApplicationIntegrationTest {

	private MockMvc mockMvc;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private TestingAuthenticationToken testingAuthenticationToken;


	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		User user = new User("kondinskis@gmail.com", "", AuthorityUtils.createAuthorityList("NORMAL"));
		testingAuthenticationToken = new TestingAuthenticationToken(user, null);
		SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);

		Role role = new Role();
		role.setId(1L);
		role.setName("ADMIN");
		roleRepository.save(role);

		role.setId(2L);
		role.setName("NORMAL");
		roleRepository.save(role);

		Customer c = new Customer();
		c.setEmbg("1234123412341");
		c.setFirstName("stevo");
		c.setLastName("kondinski");
		c.setPassword("123");
		c.setEmail("kondinskis@gmail.com");
		customerService.registerCustomer(c);
	}

	@Test
	public void givenEmailGetCustomer() throws Exception {

		mockMvc.perform(get("/customer").principal(testingAuthenticationToken)
			.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

	}

}