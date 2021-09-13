package com.mycompany.webapp.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Member;

@Repository
public class Ch14MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberDao.class);
	
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public void insert(Ch14Member member) {
		sqlSessionTemplate.insert("member.insert", member);
	}
	
	public Ch14Member selectByMid(String mid) {
		//select를 해올때 member의 객체 하나 즉, 한 행에대한 정보만 가져올 것이다. 
		return sqlSessionTemplate.selectOne("member.selectByMid", mid);
	}
}
