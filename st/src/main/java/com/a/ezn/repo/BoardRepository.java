package com.a.ezn.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.a.ezn.BoardVO;

@Repository
public class BoardRepository {
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAME_SPACE = "BoardMapper";
	
	public List<BoardVO> getAllData() {
		return sqlSessionTemplate.selectList(NAME_SPACE + ".getAll");
	}
	
	public BoardVO selectOne(int sno) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".findById", sno);
	}
	
	public int insertOne(BoardVO vo) {
		return sqlSessionTemplate.insert(NAME_SPACE + ".insert", vo);
	}
	
	public int update(BoardVO vo) {
		return sqlSessionTemplate.update(NAME_SPACE +".update", vo);
	}
	
	public int delete(int sno) {
		return sqlSessionTemplate.delete(NAME_SPACE +".delete", sno);
	}
}
