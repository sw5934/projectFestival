package com.spring.utils;

import java.io.File;

import org.springframework.http.MediaType;

import com.spring.dto.AttachVO;

public class DeleteFileUtils {
	
	public static void delete(String uploadPath, AttachVO attach) throws Exception{
		
		//fileType으로 이미지인지 확인
		MediaType mType = MediaUtils.getMediaType(attach.getFileType());
		
		if(mType != null) {			//이미지의 썸네일 삭제
			File thumbnail = new File(uploadPath + attach.getUploadPath() + File.separator + "s_" 
								+ attach.getUuid() + attach.getFilename());
			thumbnail.delete();
		}
		
		//원본파일 삭제
		File sourceFile = new File(uploadPath + attach.getUploadPath() + File.separator
									+ attach.getUnqId() + attach.getFilename());
		
		sourceFile.delete();
	}
}
