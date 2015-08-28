package sample.springboot.mybatis.groovymapper.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.boot.mybatis.autoconfigure.Mapper;

@Mapper
public interface MemberMapper {
	
	@Select("""
        SELECT ID, 
		       NAME, 
		       PHONE 
		FROM   MEMBER
	""")
	List<Member> findAll();
	
	@Select("""
        SELECT ID, 
		       NAME, 
		       PHONE 
		FROM   MEMBER
		WHERE  ID = #{id}
	""")
	Member findById(Long id);
}
