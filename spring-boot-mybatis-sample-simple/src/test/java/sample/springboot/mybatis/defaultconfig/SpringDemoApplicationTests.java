package sample.springboot.mybatis.defaultconfig;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sample.springboot.mybatis.defaultconfig.SampleApplication.MemberController;
import sample.springboot.mybatis.defaultconfig.SampleApplication.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleApplication.class)
@WebAppConfiguration
public class SpringDemoApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(SpringDemoApplicationTests.class);
	
    @Autowired 
    WebApplicationContext wac;
    
    MockMvc mockMvc;    
        
    @Before 
    public void init() {    
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)   
                	.alwaysDo(print())
                	.build();
    }   
	
    @Test
    public void controllerMemberTest() throws Exception {
        mockMvc.perform(
			get("/member/{id}", 1).accept(MediaType.APPLICATION_JSON))        
    		.andExpect(handler().handlerType(MemberController.class))
            .andExpect(handler().methodName("member"))        
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
		);
    }
    
    @Test
    public void controllerMembersTest() throws Exception {
    	mockMvc.perform(
    		get("/members").accept(MediaType.APPLICATION_JSON))        
	    	.andExpect(handler().handlerType(MemberController.class))
	    	.andExpect(handler().methodName("members"))        
	    	.andExpect(status().isOk())
	    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
    	);
    }
    
	@Autowired
	MemberMapper memberMapper;
	
	@Test
	public void findAllTest() {
		List<Map<String, Object>> members = memberMapper.findAll();
		log.debug("members : {}", members);
		assertThat(members.size(), is(2));
	}
	
	@Test
	public void findByIdTest() {
		Map<String, Object> member1 = memberMapper.findById(1L);
		log.debug("member1 : {}", member1);
		assertThat((Integer) member1.get("ID"), is(1));
		assertThat((String) member1.get("NAME"), is("xml"));
		assertThat((String) member1.get("PHONE"), is("010-9912-1234"));
		
		Map<String, Object> member2 = memberMapper.findById(2L);
		log.debug("member2 : {}", member2);
		assertThat((Integer) member2.get("ID"), is(2));
		assertThat((String) member2.get("NAME"), is("annotation"));
		assertThat((String) member2.get("PHONE"), is("010-2333-4321"));
		
		Map<String, Object> member3 = memberMapper.findById(3L);
		log.debug("member3 : {}", member3);
		assertThat(member3, is(nullValue()));
	}
}
