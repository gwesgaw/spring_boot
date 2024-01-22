package com.example.thymeleaf.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.thymeleaf.domain.SampleDTO;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;


@Log4j2
@Controller
public class SampleController {

	@GetMapping("/hello")
	public void hello(Model m) {
		log.info("hello........");
		m.addAttribute("msg", "Hello World~!!");
	}
	
	@GetMapping("/ex/ex1")
	public void ex1(Model m) {
		List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
		m.addAttribute("list", list);
	}
	
	@GetMapping("/ex/ex2")
	public void ex2(Model m) {
		
		List<String> strList = IntStream.range(1, 10)
				.mapToObj(i-> "data "+i)
				.collect(Collectors.toList());
		m.addAttribute("strList", strList);
		
		Map<String, String> map=new HashMap<>();
		map.put("a", "aaaaa");
		map.put("b", "bbbbb");
		map.put("c", "ccccc");
		map.put("d", "ddddd");
		map.put("e", "eeeee");
		m.addAttribute("map", map);
		
		SampleDTO sampleDto = new SampleDTO();
		sampleDto.setP1("value --- p1");
		sampleDto.setP2("value --- p2");
		sampleDto.setP3("value --- p3");
		m.addAttribute("dto", sampleDto);
	}
	
	@GetMapping("/ex/ex3")
	public void ex3(Model m) {
		m.addAttribute("arr", new String[] {"aaa","bbb","ccc"});
	}
	
	
}
