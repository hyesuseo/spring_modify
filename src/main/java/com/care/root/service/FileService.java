package com.care.root.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.care.root.dto.FileDTO;

public interface FileService {

	public static String IMG_REPO = "D://Hyesuseo//4. SPRING//imgrepo"; //이미지를 저장하기 위한 공간
	public void fileProcess(MultipartFile file, String id, String name);
	public List<FileDTO> getList();
	public void delete(String file, String id);
	public FileDTO getMdata(String id);
	public void modify(MultipartFile file, String origin, FileDTO dto);
}
