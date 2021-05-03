package com.nanum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.model.biz.GeneralBiz;
import com.nanum.util.CommonException;


/**
 * 일반회원 컨트롤러
 */
@WebServlet("/general/generalController")
public class GeneralController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;
	
	public void init() {
		application = getServletContext();
		CONTEXT_PATH = (String)application.getAttribute("CONTEXT_PATH");
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println("action : " + action);
		
		switch (action) {
		case "generalInputForm" :
			generalInputForm(request, response);
			break;
		case "idCheck" :
			idCheck(request, response);
			break;
		case "generalInput" :
			generalInput(request, response);
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * 일반회원 회원가입 폼 요청
	 */
	protected void generalInputForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GeneralBiz biz = new GeneralBiz();
		ArrayList<LocalDto> localList = new ArrayList<LocalDto>();
		ArrayList<VolCategoryDto> categoryList = new ArrayList<VolCategoryDto>();
		
		try {
			biz.getLocalList(localList);
			biz.getVolCategoryList(categoryList);
			System.out.println("localList size" + localList.size());
			System.out.println("categoryList size" + categoryList.size());
			request.setAttribute("local", localList);
			request.setAttribute("volCategory", categoryList);
			request.getRequestDispatcher("/signUp/userInput.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 일반회원 아이디 중복 확인
	 */
	protected void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		PrintWriter out = response.getWriter();
		
		if (id == null || id.trim().length() == 0) {
			out.print("none");
			out.flush();
			out.close();
			return;
		}
		
		id = id.trim();
		GeneralBiz biz = new GeneralBiz();
		try {
			boolean result = biz.isGeneralId(id);
			System.out.println("result : " + result);
			if (result) {
				out.print("not-usable");
			} else {
				out.print("usable");
			}
		} catch (CommonException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 일반회원 회원가입 요청 서비스
	 */
	protected void generalInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String generalName = request.getParameter("generalName");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday"); 
		String generalId = request.getParameter("generalId");
		String generalPw = request.getParameter("generalPw");
		String generalPw2 = request.getParameter("generalPw2");
		String zipCode = request.getParameter("zipCode");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String mobile1 = request.getParameter("mobile1");
		String mobile2 = request.getParameter("mobile2");
		String mobile3 = request.getParameter("mobile3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String localNo = request.getParameter("localNo");
		String categoryNo = request.getParameter("categoryNo");
		
		System.out.println(gender + localNo + categoryNo);
		if (generalName == null || generalName.trim().length() == 0) {
			out.print("이름을 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (generalId == null || generalId.trim().length() == 0) {
			out.print("아이디를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (generalPw == null || generalPw.trim().length() == 0) {
			out.print("비밀번호를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (generalPw2 == null || generalPw2.trim().length() == 0) {
			out.print("비밀번호를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (zipCode == null) {
			out.print("주소를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (mobile2 == null || mobile2.trim().length() == 0) {
			out.print("휴대폰 번호를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (mobile3 == null || mobile3.trim().length() == 0) {
			out.print("휴대폰 번호를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (email1 == null || email1.trim().length() == 0) {
			out.print("이메일을 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (email2 == null || email2.trim().length() == 0) {
			out.print("이메일을 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		
		generalName = generalName.trim();
		generalId = generalId.trim();
		generalPw = generalPw.trim();
		if (detailAddress != null && detailAddress.trim().length() != 0) {
			address = address + " " + detailAddress;
		}
		String mobile = mobile1 + "-" + mobile2.trim() + "-" + mobile3.trim();
		String email = email1.trim() + "@" + email2.trim();
		
		GeneralMemberDto dto = new GeneralMemberDto();
		dto.setGeneralName(generalName);
		dto.setGender(gender);
		dto.setBirthday(birthday);
		dto.setGeneralId(generalId);
		dto.setGeneralPass(generalPw);
		dto.setGeneralZipCode(zipCode);
		dto.setGeneralAddress(address);
		dto.setGeneralMobile(mobile);
		dto.setGeneralEmail(email);
		if (!localNo.equals("none")) {
			dto.setLocalNo(localNo);
		}
		if (!categoryNo.equals("none")) {
			dto.setCategoryNo(categoryNo);
		}
		
		GeneralBiz biz = new GeneralBiz();
		
		try {
			biz.addGeneralMember(dto);
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('회원가입 완료');location.href='" + url + "'; </script>");
		} catch (CommonException e) {
			e.printStackTrace();
			out.println("<script>alert('회원가입 정보가 올바르지 않습니다');history.go(-1); </script>");
		} finally {
			out.flush();
			out.close();
		}
		
		
	}

}
