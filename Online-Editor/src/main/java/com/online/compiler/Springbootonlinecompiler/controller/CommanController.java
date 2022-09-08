package com.online.compiler.Springbootonlinecompiler.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommanController {

	@GetMapping("/home")
	public String homePage() {
		return "home-page";
	}
	
	
	@PostMapping(value="/compiler")
	public String getCompiler(@RequestParam Map<String,Object> data) {
		System.out.println("data:- "+data);
		String language = (String) data.get("language");
		
		System.out.println("language:- "+language);
		System.out.println("code:- \n"+data.get("code"));
		
		String seperator = File.separator;
		System.out.println("seperator:- "+seperator);
		
		UUID uid = UUID.randomUUID();
		
		File fl = new File(uid+"."+language);
		System.out.println("File:- "+fl);
	
		try {
	Runtime.getRuntime().exec("C:\\Program Files\\Java\\jdk1.8.0_171\\bin\\javac.exe HelloWorld.java",null,fl);
	System.out.println("Compilation done");
    Process pro = Runtime.getRuntime().exec("C:\\Program Files\\Java\\jdk1.8.0_171\\bin\\java.exe HelloWorld",null,fl);
    
    BufferedReader in = new BufferedReader(
            new InputStreamReader(pro.getInputStream()));
    System.out.println("Execution Done : "+ in.readLine());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
		try {
			PrintWriter pw = new PrintWriter(fl);
			pw.write((String) data.get("code"));
			System.out.println("printwriter:- "+pw);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "home-page";
	}
}
