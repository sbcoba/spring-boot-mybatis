package sample.springboot.mybatis.xmlconfig.service;

import java.util.List;

import org.springframework.boot.mybatis.autoconfigure.Mapper;

@Mapper
public interface MemberMapper {
	List<Member> findAll();
	Member findById(Long id);
}
