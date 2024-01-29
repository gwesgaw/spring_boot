package com.example.demo.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@
Component
public class FileHandler {
	
	private final String UP_DIR = "D:\\myProject\\_java\\_fileUpload\\";
	
	public List<FileVO> uploadFile(MultipartFile[] files){
		//오늘 날짜 경로 생성
		List<FileVO> flist = new ArrayList<>();
		LocalDate date = LocalDate.now();
		String today = date.toString();
		
		//오늘날짜(today)의 폴더 구성
		today = today.replace("-", File.separator);
		File folders =new File(UP_DIR, today);
		//D:\\myProject\\_java\\_fileUpload\\2024\\01\\29
		//실제 폴더를 생성하는 명령어 mkdir(한개폴더 생성) / mkdirs(여러 폴더 한번에 생성)
		if(!folders.exists()) {//exists있는지 체크
			folders.mkdirs(); //포더 생성 명령 mkdirs(폴더 1개 생성)
		}
		
		//--------------------------------- 폴더 생성 완료
		
		//fileVO 생성
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			//파일이름(OriginalName()) : 파일 경로를 포함하고 있을 수도 있음.
			String originalFilename = file.getOriginalFilename(); //이름
			String onlyFilename = originalFilename.substring(
					originalFilename.lastIndexOf(File.separator)+1); //실 파일명만 추출
			fvo.setFileName(onlyFilename); //파일 이름 추출
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			//-----------------------------fvo 설정 마무리
			
			//디스크에 저장할 파일 객체를 생성 -> 저장
			//uuid_fileName  uuid_th_fileName
			String fullFileName = uuid.toString()+"_"+onlyFilename;
			File storeFile = new File(folders, fullFileName);
			
			//저장
			try {
				//원본파일
				file.transferTo(storeFile); 
				if(isImageFile(storeFile)) {
					fvo.setFileType(1);
					//썸네일 생성
					File thumbNail = new File(folders, uuid.toString()+"_th_"+onlyFilename);
				//file-type 이미지 파일이면 1, 아니면 0
				Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>> file 저장 에러");
			}
			//for문 안
			flist.add(fvo);
		}
		
		return flist;
		}
	
	public boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image") ? true : false;
	}

}
