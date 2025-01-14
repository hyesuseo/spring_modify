package com.care.root.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.FileDTO;
import com.care.root.service.FileService;

@Controller
public class FileController {
	@Autowired FileService fs;
	
	
	@GetMapping("form")
	public String uploadForm() {
		
		return "uploadForm";
	}
	
	@PostMapping("upload")
	public String upload(MultipartHttpServletRequest mul, String id, String name, MultipartFile file) { 
		fs.fileProcess(file, id, name); //서비스로 연결
		//멀티파트 서블렛리퀘스트라는거 써줘야함
		System.out.println("mul.id:"+ mul.getParameter("id"));
		System.out.println("mul.name:"+mul.getParameter("name"));
		MultipartFile mf = mul.getFile("file");
		System.out.println("----------------------");
		System.out.println("id:"+ id);
		System.out.println("file"+file);
		
		System.out.println("mul.file:"+ mf);
		return "redirect:form";
	}
	
	@GetMapping("views")
	public String views(Model model) {
		model.addAttribute("list", fs.getList());
		
		return "result";
	}
	
	@GetMapping("download")
	public void download(String file, HttpServletResponse res) throws Exception { //물음표 다음에 file이라는 이름으로 전송되므로 file로 해야하고
		//다른 이름으로 쓸 경우에는 @RequestParam("file") String aaa 이렇게 써도 됨
		res.addHeader("Content-disposition", "attachment; fileName="+URLEncoder.encode(file, StandardCharsets.UTF_8));//전송하는 방식
		//contents-disposion은 다운로드 방식이라는 얘기, 파일이름을 붙여서 다운로드 하겠다는 것
		File file2 = new File(FileService.IMG_REPO+"/"+file);
		FileInputStream in = new FileInputStream(file2);
		FileCopyUtils.copy(in, res.getOutputStream());
		in.close();
	}
	
	@GetMapping("delete")
	public String delete(String file, String id) {
		fs.delete(file, id);
		return "redirect:form";
	}
	
	@GetMapping("modifyForm")
	public String modifyForm(String id, Model model) {
		//id만 받아오고 나머지는 다시 DB에 접근해서 정보를 가져옴
		//id에 해당하는 정보를 가져와서 list 로 형식으로 보내준다.
		FileDTO dto = fs.getMdata(id);
//		System.out.println(dto.getId());
//		System.out.println(dto.getImgFileName());
		model.addAttribute("info", fs.getMdata(id));
		
		
		return "modifyForm";
	}
	
	@PostMapping("modify")
	public String modify(MultipartFile file, String origin, FileDTO dto) { //dto로 받아보자
		//id, name, imgFileName이 있는데, 두 값만 들어오는 경우에는 id, name이 변화한 것만 dto로 저장된다.
		
		fs.modify(file, origin, dto);
		
	return "redirect:views";
	}
	
		
}
