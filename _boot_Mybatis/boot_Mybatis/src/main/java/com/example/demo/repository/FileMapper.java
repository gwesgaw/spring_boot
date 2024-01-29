package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.FileVO;

@Mapper
public interface FileMapper {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

	

	

}
