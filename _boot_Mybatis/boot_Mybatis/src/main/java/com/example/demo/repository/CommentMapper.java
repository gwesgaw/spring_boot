package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;

@Mapper
public interface CommentMapper {

	int insert(CommentVO cvo);

	List<CommentVO> getList(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	int edit(CommentVO cvo);

	int bnoTotalCount(long bno);

	int delete(long cno);

}
