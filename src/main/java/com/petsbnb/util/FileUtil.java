package com.petsbnb.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static String fileSave(MultipartFile file, String savePath) throws IOException, Exception{
		//저장할 디렉토리 생성 (만약에 없다면)
		File dir = new File(savePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//다른이름으로 저장할 변수
		String reFileName = "";
		//파일의 원래 이름을 저장 할 변수
		String orgFileName = "";
		//파일의 확장자를 저장할 변수
		String extension = "";
		//현재시간을 저장할 변수
		String now = "";
		//다른이름으로 바꾸어 저장시킬 파일 변수
		File savedFile;
		
		//파일의 원본 이름 추출
		orgFileName = CmmUtil.nvl(file.getOriginalFilename());
		//확장자 추출
		extension = orgFileName.substring(orgFileName.indexOf("."), orgFileName.length());
		//현재시간 추출
		now = new SimpleDateFormat("yyyyMMddhmsS").format(new Date());
		//현재시간으로 파일이름 재설정
		reFileName = now + extension;
		//저장할 파일 객체 생성
		savedFile = new File(savePath + reFileName);
		//저장
		file.transferTo(savedFile);
		
		//저장된 파일 이름 반환
		return reFileName;
	}
	
	public static void deleteFile(String savePath, String fileName) {
		File file = new File(savePath + fileName);
		if(file.exists()) file.delete();
	}
	
	public static void deleteFile(String fullPath) {
		File file = new File(fullPath);
		if(file.exists()) file.delete();
	}
}
