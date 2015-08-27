package org.sbcoba.spring.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.boot.mybatis.autoconfigure.Mapper;

@Mapper
public interface MemberAnnotationMapper {
	@Select("SELECT id, name, phone FROM MEMBER where name = 'annotation'")
	List<Member> findAll();
}
