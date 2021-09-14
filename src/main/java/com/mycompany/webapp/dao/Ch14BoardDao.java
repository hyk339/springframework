package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Ch14Board;


//@Mapper라는 것을 붙여서 인터페이스 구현객체가 알아서 만들어진다.
@Mapper
public interface Ch14BoardDao {
	public List<Ch14Board> selectByPage();
	public int count();
	public Ch14Board selectByBno(int bno);
	public int insert(Ch14Board board);
	public int deleteByBno(int bno);
	public int update(Ch14Board board);
}
