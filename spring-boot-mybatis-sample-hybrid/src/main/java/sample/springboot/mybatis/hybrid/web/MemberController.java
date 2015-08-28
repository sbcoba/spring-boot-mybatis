package sample.springboot.mybatis.hybrid.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.springboot.mybatis.hybrid.service.Member;
import sample.springboot.mybatis.hybrid.service.MemberAnnotationMapper;
import sample.springboot.mybatis.hybrid.service.MemberXmlMapper;

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
