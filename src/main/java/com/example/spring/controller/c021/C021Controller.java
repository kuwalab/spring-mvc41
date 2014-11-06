package com.example.spring.controller.c021;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class C021Controller {
	@RequestMapping("/c021/branchForm")
	public String branchForm() {
		return "c021/branchForm";
	}

	@RequestMapping(value = "/c021/branchRecv", params = "branch1", method = RequestMethod.POST)
	public String branch1() {
		return "c021/branch1";
	}

	@RequestMapping(value = "/c021/branchRecv", params = "branch2", method = RequestMethod.POST)
	public String branch2() {
		return "c021/branch2";
	}

	@RequestMapping(value = "/c021/branchRecv", params = "branch3", method = RequestMethod.POST)
	public String branch3() {
		return "c021/branch3";
	}

	@RequestMapping(value = "/c021/branchRecv", params = "branch4", method = RequestMethod.POST)
	public String branch4() {
		return "c021/branch4";
	}
}
