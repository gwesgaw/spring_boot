package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.repository.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;

	@Override
	public void register(BoardVO bvo) {
		// TODO Auto-generated method stub
		mapper.insert(bvo);
		
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mapper.selectList(pgvo);
	}

	@Override
	public BoardVO getDetail(long bno) {
		// TODO Auto-generated method stub
		return mapper.selectDetail(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		mapper.updateModify(bvo);
	}

	@Override
	public int delete(long bno) {
		// TODO Auto-generated method stub
		return mapper.delete(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mapper.getTotalCount(pgvo);
	}


	
	

	

	
}
