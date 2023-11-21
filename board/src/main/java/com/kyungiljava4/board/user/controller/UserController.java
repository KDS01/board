package com.kyungiljava4.board.user.controller;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyungiljava4.board.user.domain.User;
import com.kyungiljava4.board.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("regist")
	public String regist(Model model) {
		model.addAttribute("title", "회원가입");
		model.addAttribute("path", "/user/regist");
		model.addAttribute("content", "registFragment");
		model.addAttribute("contentHead", "registFragmentHead");

		return "basic/layout";
	}

	@PostMapping("regist")
	public String registPost(@RequestParam Map<String, String> map, Model model) {
		try {
			User tempUser = new User(map.get("userId"), map.get("password"), map.get("name"), map.get("phone"),
					map.get("email"));
			if (map.get("address") != "") {
				tempUser.setAddress(map.get("address"));
			}
			if (map.get("gitAddress") != "") {
				tempUser.setGitAddress(map.get("gitAddress"));
			}
			if (map.get("gender") != null) {
				tempUser.setGender(Integer.parseInt(map.get("gender")));
			}
			if (map.get("birth") != "") {
				tempUser.setBirth(Date.valueOf(map.get("birth")));
			}
			userService.add(tempUser);

			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("requestError", "회원가입 실패");
			model.addAttribute("title", "회원가입");
			model.addAttribute("path", "/user/regist");
			model.addAttribute("content", "registFragment");
			model.addAttribute("contentHead", "registFragmentHead");

			return "basic/layout";
		}
	}

	@PostMapping("login")
	public String loginPost(@RequestParam Map<String, String> map, HttpSession session) {
		User tempUser = new User();
		tempUser.setUserId(map.get("userId"));
		tempUser.setPassword(map.get("password"));
		tempUser = userService.login(tempUser);

		if (tempUser != null) {
			session.setAttribute("userName", tempUser.getName());
		}
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userName");
		return "redirect:/";
	}
}
