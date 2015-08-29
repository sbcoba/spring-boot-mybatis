package sample.springboot.mybatis.defaultconfig;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mybatis.autoconfigure.Mapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SampleApplication {
	
    public static void main(String[] args) {
    	SpringApplication.run(SampleApplication.class, args);
    }
    
    @RestController
    public static class MemberController {
    	
    	@Autowired
    	MemberMapper memberMapper;
    	
    	@RequestMapping("members")
    	public List<Map<String, Object>> members() {
    		return memberMapper.findAll();
    	}
    	
    	@RequestMapping("member/{id}")
    	public Map<String, Object> member(@PathVariable("id") Long id) {
    		return memberMapper.findById(id);
    	}
    }    
    
    @Mapper
    interface MemberMapper {
    	@Select("SELECT ID, NAME, PHONE FROM MEMBER")
    	List<Map<String, Object>> findAll();
    	
    	@Select("SELECT ID, NAME, PHONE FROM MEMBER WHERE ID = #{id}")
    	Map<String, Object> findById(Long id);
    }
}
