package org.tarena.note.web.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.form")
	@ResponseBody //将方法返回值转成json字符串输出
	public NoteResult execute(
			@RequestHeader("Authorization") String msg){
		System.out.println("进入LoginController");
		NoteResult result = 
			userService.checkLogin(msg);
		return result;
	}
	
}
