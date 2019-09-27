package com.spring.dto;

import java.util.Date;

public class AttachVO {
	private int ano;
	private String uploadPath;
	private String filename;
	private String fileType;
	private Date regDate;
	private String uuid;
	private int unqId;
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getUnqId() {
		return unqId;
	}
	public void setUnqId(int unqId) {
		this.unqId = unqId;
	}
	
	@Override
	public String toString() {
		return "AttachVO [ano=" + ano + ", uploadPath=" + uploadPath + ", filename=" + filename + ", fileType="
				+ fileType + ", regDate=" + regDate + ", uuid=" + uuid + ", unqId=" + unqId + "]";
	}

}
