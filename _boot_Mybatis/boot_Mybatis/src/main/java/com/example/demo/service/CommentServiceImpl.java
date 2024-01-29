package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.repository.BoardMapper;
import com.example.demo.repository.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentMapper mapper;

	@Override
	public int post(CommentVO cvo) {
		// TODO Auto-generated method stub
		return mapper.insert(cvo);
	}

	/*
	 * @Override public List<CommentVO> getList(long bno) { // TODO Auto-generated
	 * method stub return mapper.getList(bno); }
	 */

	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// 컨트롤러에서 처리해도 되지만, 처리 속도가 더 빨라짐
		//totalCount
		int totalCount = mapper.bnoTotalCount(bno);
		//List
		List<CommentVO> list = mapper.getList(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}
	
	@Override
	public int edit(CommentVO cvo) {
		// TODO Auto-generated method stub
		return mapper.edit(cvo);
	}

	@Override
	public int remove(long cno) {
		// TODO Auto-generated method stub
		return mapper.delete(cno);
	}

}
