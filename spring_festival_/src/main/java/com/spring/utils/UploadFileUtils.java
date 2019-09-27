package com.spring.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.spring.dto.AttachVO;

public class UploadFileUtils {
	private static final Logger logger=	LoggerFactory.getLogger(UploadFileUtils.class);
	
	//uploadFile 저장
	public static AttachVO uploadFile(String uploadPath, String originalName, int unqId, byte[] filedata) throws Exception{
		
		AttachVO attach = new AttachVO();
		
		attach.setUnqId(unqId);
		
		//중복파일명 해결
		UUID uid = UUID.randomUUID();
		
		attach.setUuid(uid.toString().replace("-", ""));
		
		String saveName = uid.toString().replace("-", "") + "$$" + originalName;
		
		//저장경로
		String savePath = calcPath(uploadPath, unqId);
		
		attach.setUploadPath(savePath);
		attach.setFileType(originalName.substring(originalName.lastIndexOf(".")+1));
		attach.setFilename(originalName);
		
		//해당경로에 해당파일명으로 저장
		File target = new File(uploadPath + savePath, saveName);
		FileCopyUtils.copy(filedata, target);
		logger.info(target.getAbsolutePath());
		
		//썸네일 만드는 작업
		String formatName = originalName.substring(originalName.lastIndexOf(".") +1);
		
		if(MediaUtils.getMediaType(formatName) !=null) {
			//이미지 썸네일 생성.
			//uploadFileName = 썸네일 이미지 파일명(경로포함)
			makeThumbnail(uploadPath, savePath, saveName);			
		}else {
			//=저장된 원본 파일명(경로포함)
			makeIcon(uploadPath, savePath, saveName);
		}
		return attach;		
	}
	
	//날짜를 계산하고 폴더를 만듦
	private static String calcPath(String uploadPath, int unqId) throws Exception{
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		String savePath = File.separator + unqId + datePath;
		File path = new File(uploadPath + savePath);
		
		if(!path.exists()) {
			path.mkdirs();
		}
		
		logger.info(savePath);
		
		return savePath;
	}
	//재미있는 썸네일이미지 만들기^^(원본 이미지, 썸네일이미지 두개로 저장)
	private static void makeThumbnail(String uploadPath, String path, String fileName) throws Exception{
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,70);
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
	}
	
	//원본파일형태로 저장(attach에 실어놔서 큰 의미 X)
	public static void makeIcon(String uploadPath, String path, String fileName) throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
	}
}
