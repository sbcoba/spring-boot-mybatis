package sample.springboot.mybatis.xmlconfig;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

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

import sample.springboot.mybatis.xmlconfig.SampleApplication;
import sample.springboot.mybatis.xmlconfig.service.Member;
import sample.springboot.mybatis.xmlconfig.service.MemberMapper;
import sample.springboot.mybatis.xmlconfig.web.MemberController;

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
    
	@Autowired
	MemberMapper memberMapper;
	
	@Test
	public void findAllTest() {
		List<Member> members = memberMapper.findAll();
		log.debug("members : {}", members);
		assertThat(members.size(), is(2));
	}
	
	@Test
	public void findByIdTest() {
		Member member1 = memberMapper.findById(1L);
		log.debug("member1 : {}", member1);
		assertThat(member1.getId(), is(1L));
		assertThat(member1.getName(), is("xml"));
		assertThat(member1.getPhone(), is("010-9912-1234"));
		
		Member member2 = memberMapper.findById(2L);
		log.debug("member2 : {}", member2);
		assertThat(member2.getId(), is(2L));
		assertThat(member2.getName(), is("annotation"));
		assertThat(member2.getPhone(), is("010-2333-4321"));
		
		Member member3 = memberMapper.findById(3L);
		log.debug("member3 : {}", member3);
		assertThat(member3, is(nullValue()));
	}
}
