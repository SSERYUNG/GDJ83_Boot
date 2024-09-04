package com.sse.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public String fileSave(String path,MultipartFile multipartFile) throws Exception {
//		어디에 저장할 것인가
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
//		저장할 파일명 생성
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
//		파일을 하드디스크에 저장
		file = new File(file,fileName);
		multipartFile.transferTo(file);

//		저장된 파일명을 리턴
		return fileName;
	}

}
