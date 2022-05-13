package com.spring.shop.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FIleService {
	
	public List<FileDTO> uploadFile(MultipartHttpServletRequest mr) throws FileNotFoundException, IOException {
		List<FileDTO> list = new ArrayList<FileDTO>();
		String path = mr.getSession().getServletContext().getRealPath("resources/file");
		
		File saveDir = new File(path);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		List<MultipartFile> fList = mr.getFiles("file");
			for (MultipartFile f : fList) {
				if (f.getSize()>0) {
				String name = f.getOriginalFilename();
				long size = f.getSize();
				File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);
				String fileSavedName = destination.getName();
				// 메모리에 올라와있는 파일을 실제 디스크에 저장한다.
				FileCopyUtils.copy(f.getInputStream(), new FileOutputStream(destination));
				
				FileDTO file = new FileDTO(name, fileSavedName, size);
				list.add(file);
			}
		}
		return list;
	}
}