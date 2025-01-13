package com.care.root.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public String modifyForm(@RequestParam String file, @RequestParam String id, @RequestParam String name, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("file", file);
		model.addAttribute("name", name);
		
		return "modifyForm";
	}
	
	@GetMapping("modify")
	public String modify(@RequestParam String file, @RequestParam String id, @RequestParam String name, Model model) {
		
		fs.modify(String file)
		model.addAttribute("list", list결과값);
	return "redirect:views";
	}
	
		
}
