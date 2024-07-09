package com.a.ezn.repo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.a.ezn.MemberVO;

@Repository
public class UserRepository {
	
	@Autowired
	SqlSessionTemplate template;
	
	public int join(MemberVO vo) {
		return template.insert("UserMapper.insert", vo);
	}
}