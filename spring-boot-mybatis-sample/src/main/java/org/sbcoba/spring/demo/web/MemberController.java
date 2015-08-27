package org.sbcoba.spring.demo.web;

import java.util.List;

import org.sbcoba.spring.demo.service.Member;
import org.sbcoba.spring.demo.service.MemberAnnotationMapper;
import org.sbcoba.spring.demo.service.MemberXmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	
	@Autowired
	MemberAnnotationMapper memberAnnotationMapper;
	
	@Autowired
	MemberXmlMapper memberXmlMapper;
	
	@RequestMapping(value="members")
	public List<Member> members() {
		return memberAnnotationMapper.findAll();
	}
	
	@RequestMapping(value="members", params="mapper=anno")
	public List<Member> annoMembers() {
		return memberAnnotationMapper.findAll();
	}
	
	@RequestMapping(value="members", params="mapper=xml")
	public List<Member> xmlMembers() {
		return memberXmlMapper.findAll();
	}
}
