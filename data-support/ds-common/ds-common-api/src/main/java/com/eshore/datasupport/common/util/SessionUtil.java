package com.eshore.datasupport.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eshore.datasupport.common.pojo.Yhb;

public class SessionUtil {
	
	  private SessionUtil() {
	  }

	/**
	 * 
	 * @param request
	 * @return 如果返回值为null ,则无登录用户
	 */
	public static Yhb getCurrentUserInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		Yhb yhb = (Yhb)session.getAttribute(Conts.SESSIONFLAG);
		if(yhb==null){
			throw new SessionTimeoutException("Session没有可用的登录用户信息!");
		}
		return yhb;
	}
}
