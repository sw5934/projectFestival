package com.spring.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class DownloadFileUtils {
	
	public static ResponseEntity<byte[]> download(String filePath) throws Exception{
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String formatName = filePath.substring(filePath.lastIndexOf(".")+1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		HttpHeaders headers = new HttpHeaders();
		
		try {
			in = new FileInputStream(filePath);
			//이미지도 다운로드 받고 싶으면 mType은 필요x
			if(mType !=null) {
				headers.setContentType(mType);
			}else {
				String fileName = filePath.substring(filePath.indexOf("$$")+2);

				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment;filename=\""
							+new String(fileName.getBytes("utf-8"),"ISO-8859-1")+"\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		
		}catch(IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			in.close();
		}
		return entity;
	}
	
	

}
