package sample.springboot.mybatis.hybrid;

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

import sample.springboot.mybatis.hybrid.service.Member;
import sample.springboot.mybatis.hybrid.service.MemberAnnotationMapper;
import sample.springboot.mybatis.hybrid.service.MemberXmlMapper;
import sample.springboot.mybatis.hybrid.web.MemberController;

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
    public void controllerMembersAnnoTest() throws Exception {
    	mockMvc.perform(
    		get("/members").param("mapper", "anno").accept(MediaType.APPLICATION_JSON))        
	    	.andExpect(handler().handlerType(MemberController.class))
	    	.andExpect(handler().methodName("annoMembers"))        
	    	.andExpect(status().isOk())
	    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
    	);
    }
    
    @Test
    public void controllerMemberXmlTest() throws Exception {
        mockMvc.perform(
			get("/members").param("mapper", "xml").accept(MediaType.APPLICATION_JSON))        
    		.andExpect(handler().handlerType(MemberController.class))
            .andExpect(handler().methodName("xmlMembers"))        
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
		);
    }

	@Autowired
	MemberXmlMapper memberXmlMapper;
	
	@Test
	public void xmlFindAllTest() {
		List<Member> members = memberXmlMapper.findAll();
		log.debug("Members by xml mapper : {}", members);
		assertThat(members.size(), is(1));
		assertThat(members.get(0).getId(), is(1L));
		assertThat(members.get(0).getName(), is("xml"));
		assertThat(members.get(0).getPhone(), is("010-9912-1234"));		
	}    
    
	@Autowired
	MemberAnnotationMapper memberAnnotationMapper;
	
	@Test
	public void annotationFindAllTest() {
		List<Member> members = memberAnnotationMapper.findAll();
		log.debug("members by annotation mapper : {}", members);
		assertThat(members.size(), is(1));
		assertThat(members.get(0).getId(), is(2L));
		assertThat(members.get(0).getName(), is("annotation"));
		assertThat(members.get(0).getPhone(), is("010-2333-4321"));
	}

}
