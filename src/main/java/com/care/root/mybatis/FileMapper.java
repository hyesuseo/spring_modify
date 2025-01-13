package com.care.root.mybatis;

import java.util.List;

import com.care.root.dto.FileDTO;

public interface FileMapper {
	public void save(FileDTO dto);
	public List<FileDTO> getList();
	public int delete(String id);
}
