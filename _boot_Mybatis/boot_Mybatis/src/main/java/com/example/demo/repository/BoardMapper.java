package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

@Mapper
public interface BoardMapper {

	int insert(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pgvo);

	BoardVO selectDetail(long bno);

	int updateModify(BoardVO bvo);

	int delete(long bno);

	int getTotalCount(PagingVO pgvo);

	long getBno();



}
