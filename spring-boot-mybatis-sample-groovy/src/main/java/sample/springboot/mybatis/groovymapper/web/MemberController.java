package sample.springboot.mybatis.groovymapper.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.springboot.mybatis.groovymapper.service.Member;
import sample.springboot.mybatis.groovymapper.service.MemberMapper;

@RestController
public class MemberController {
	
	@Autowired
	MemberMapper memberMapper;
	
	@RequestMapping("members")
	public List<Member> members() {
		return memberMapper.findAll();
	}
	
	@RequestMapping("member/{id}")
	public Member member(@PathVariable("id") Long id) {
		return memberMapper.findById(id);
	}
}
