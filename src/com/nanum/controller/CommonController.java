package com.nanum.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.model.biz.CommonBiz;
import com.nanum.util.CommonException;

/**
 * 공통 컨트롤러
 */
@WebServlet(urlPatterns = { "/common/commonController" }, loadOnStartup = 1)
public class CommonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;

	public void init() {
		application = getServletContext();
		CONTEXT_PATH = application.getContextPath();
		application.setAttribute("CONTEXT_PATH", CONTEXT_PATH);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		switch (action) {
		case "loginForm":
			loginForm(request, response);
			break;
		case "login":
			login(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * 로그인 페이지 요청
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/common/login.jsp").forward(request, response);
	}

	/**
	 * 로그인 요청
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String grade = request.getParameter("grade");

		if (memberId == null || memberId.trim().length() == 0 || memberId == "") {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		if (memberPw == null || memberPw.trim().length() == 0 || memberPw == "") {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}

		memberId = memberId.trim();
		memberPw = memberPw.trim();

		HttpSession session = request.getSession();

		CommonBiz biz = new CommonBiz();
		
		if (grade.equals("G")) {
			GeneralMemberDto dto = new GeneralMemberDto();
			dto.setGeneralId(memberId);
			dto.setGeneralPass(memberPw);
			try {
				biz.login(dto);
				if (dto.getGeneralName() != null) {
					session.setAttribute("dto", dto);
					session.setAttribute("grade", grade);
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				} else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					//response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
					return;
				}
			} catch (CommonException e) {
				e.printStackTrace();
				request.getRequestDispatcher("/common/commonController?action=loginForm").forward(request, response);
				return;
			}
		} else if (grade.equals("C")) {
			CenterMemberDto dto = new CenterMemberDto();
			dto.setCenterId(memberId);
			dto.setCenterPass(memberPw);
			try {
				biz.login(dto);
				if (dto.getCenterName() != null) {
					session.setAttribute("dto", dto);
					session.setAttribute("grade", grade);
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
				e.printStackTrace();
				response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			}
		} else if (grade.equals("A")) {
			AdminMemberDto dto = new AdminMemberDto();
			dto.setAdminId(memberId);
			dto.setAdminPass(memberPw);
			try {
				biz.login(dto);
				if (dto.getAdminName() != null) {
					session.setAttribute("dto", dto);
					session.setAttribute("grade", grade);
					request.getRequestDispatcher("/index.jsp").forward(request, response);	
				}else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
				e.printStackTrace();
				response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			}
		}
		
		
	}

}
