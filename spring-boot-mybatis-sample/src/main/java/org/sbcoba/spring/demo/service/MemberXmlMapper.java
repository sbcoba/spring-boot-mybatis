package org.sbcoba.spring.demo.service;

import java.util.List;

import org.springframework.boot.mybatis.autoconfigure.Mapper;

/**
 * mapper.xml check
 * @author sbcoba
 *
 */
@Mapper
public interface MemberXmlMapper {
	List<Member> findAll();
}
