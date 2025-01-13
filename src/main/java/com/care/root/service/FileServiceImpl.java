package com.care.root.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.dto.FileDTO;
import com.care.root.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired FileMapper map;
	@Override
	public void fileProcess(MultipartFile file, String id, String name) {
			FileDTO dto = new FileDTO();
			dto.setId(id);
			dto.setName(name);
			
			if (!file.isEmpty()) { //해당파일이 비어있지 않다면
				SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-"); //괄호 안에는 지정할 형식을 적어줌
				String sysFileName = fo.format(new Date());
				sysFileName += file.getOriginalFilename();
				dto.setImgFileName(sysFileName);
				File f = new File(IMG_REPO+"/"+sysFileName); //경로를 파일형태로 바꿔준다.
				try {
					file.transferTo(f); //안에 들어가는 값이 file 형식이어야 해서 
				} catch (Exception e) {
					e.printStackTrace();
				} 
			
			}else {
				dto.setImgFileName("nan"); //파일이 존재하지 않는다면 이미지 파일의 이름은 nan으로 설정
			}
			map.save(dto);
			
		
	}
	@Override
	public List<FileDTO> getList() {
		
		return map.getList();
	}
	@Override
	public void delete(String file, String id) {
		int result = 0;
		try {
			result = map.delete(id);
			if(result ==1) {
				File d = new File(IMG_REPO+"/"+file);
				if(d.exists()) {
					d.delete();
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public int modify(String file, String id, String name) {
		int result = 0;
		
		result = map.modify(file, id, name);
		return 0;
	}
	

}
