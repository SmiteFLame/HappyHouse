package com.ssafy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.house.dto.MemberDto;
import com.ssafy.house.dto.PageBean;
import com.ssafy.house.service.HouseService;
import com.ssafy.house.service.HouseServiceImpl;
import com.ssafy.house.service.LoginService;
import com.ssafy.house.service.LoginServiceImpl;


@WebServlet("/main.do")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginService loginService;
	HouseService houseService;

	public void init() throws ServletException {
		super.init();
		loginService = new LoginServiceImpl();
		houseService = new HouseServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String root = request.getContextPath();
		String action = request.getParameter("action");
		System.out.println("IP>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + request.getRemoteAddr());
		System.out.println("action>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + action);
		String url = "index.jsp";
		try {
			if (action != null) {
				if (action.equals("login")) {
					url = login(request, response);
				} else if (action.equals("join")) {
					url = join(request, response);
				} else if (action.equals("search")) {
					url = searchAll(response, request);
				} else if (action.equals("searchPost")) {
					url = searchPost(response, request);
				} else if (action.equals("logout")) {
					url = logout(request, response);
				} else if (action.equals("listmember")) {
					url = listMember(response, request);
				}else if (action.equals("deletemember")) {
					url = deleteMember(response, request);
				}else if (action.equals("updatemember")) {
					url = updateMember(response, request);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			url = "error/error.jsp";
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + url + "?????? ??????");
		if (url.startsWith("redirect:")) {// ????????? redirect: ??? ???????????? sendRedirect??? ??????.
			// redirect:url ????????? url?????? redirect:??? ????????? ??????
			response.sendRedirect(url.substring(url.indexOf(":") + 1));
		} else {// ????????? redirect: ??? ???????????? ????????? ??????????????? forward??? ??????.
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	private String updateMember(HttpServletResponse response, HttpServletRequest request) {
		String userid = request.getParameter("puID");
		String userpwd = request.getParameter("puPassword");
		String userpwdck = request.getParameter("puPasswordCheck");
		String useremail = request.getParameter("puEmailCheck");
		String username = request.getParameter("puName");
		String url = "index.jsp";
		

		
		if (userpwd.equals(userpwdck)) {
			try {
				loginService.update(userid, userpwd, username, useremail);
			} catch (Exception e) {
				request.setAttribute("updatemsg", e.getMessage());
			}
		} else {
			request.setAttribute("updatemsg", "??????????????? ???????????? ????????????.");
		}
		return url;
		
	}

	private String deleteMember(HttpServletResponse response, HttpServletRequest request) {
		String userid = request.getParameter("pdID");
		String userpwd = request.getParameter("pdPassword");
		String userpwdck = request.getParameter("pdPasswordCheck");
		
		System.out.println(userpwd);
		String url = "index.jsp";
		if (userpwd.equals(userpwdck)) {
			System.out.println("delete");
			try {
				loginService.delete(userid);
			} catch (Exception e) {
				request.setAttribute("deletemsg", e.getMessage());
			}
		} else {
			request.setAttribute("deletemsg", "??????????????? ???????????? ????????????.");
		}
		return url;
	}

	private String searchPost(HttpServletResponse response, HttpServletRequest request) {
		String path = "index.jsp";
		request.setAttribute("postlist", houseService.searchPost());
		return path;
	}

	private String searchAll(HttpServletResponse response, HttpServletRequest request) {
		String path = "index.jsp";
		String key = request.getParameter("key");
		String word = request.getParameter("value");
		int pageNo = 1;
		PageBean bean = new PageBean(key, word, pageNo);
		request.setAttribute("houselist", houseService.searchAll(bean));
		return path;
	}

	private String listMember(HttpServletResponse response, HttpServletRequest request) {
		String path = "index.jsp";
		try {
			List<MemberDto> lst = loginService.listMember();
			if (lst != null) {
				request.setAttribute("members", lst);
				request.setAttribute("move", "member");
			} else {
				request.setAttribute("listmsg", "????????? ?????? ????????? ????????????.");
			}
		} catch (Exception e) {
			request.setAttribute("listmsg", e.getMessage());
		}
		return path;
	}



	private String join(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("usrID");
		String userpwd = request.getParameter("usrPassword");
		String userpwdck = request.getParameter("usrPasswordCheck");
		String useremail = request.getParameter("usrEmailCheck");
		String username = request.getParameter("usrName");
		String url = "index.jsp";

		if (userpwd.equals(userpwdck)) {
			try {
				loginService.join(userid, userpwd, username, useremail);
			} catch (Exception e) {
				request.setAttribute("joinmsg", e.getMessage());
			}
		} else {
			request.setAttribute("joinmsg", "??????????????? ???????????? ????????????.");
		}
		return url;
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index.jsp";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("loginID");
		String userpwd = request.getParameter("loginPassword");

		String url = "index.jsp";
		try {
			MemberDto userInfo = loginService.login(userid, userpwd);
			if (userInfo.getUserid().equals(userid)) {
				if (userInfo.getUserpwd().equals(userpwd)) {
					HttpSession session = request.getSession();
					session.setAttribute("userinfo", userInfo);
					System.out.println("????????? ??????");
				} else {
					throw new Exception("?????? ?????????????????????.");
				}
			} else {
				throw new Exception("???????????? ?????? ??????????????????.");
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
		}
		return url;
	}

}
