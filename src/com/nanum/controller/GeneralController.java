package com.nanum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		response.setContentType("text/html; charset=utf-8");
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
		case "enrollVolForm" :
			enrollVolForm(request, response);
			break;	
		case "enrollVol" :
			enrollVol(request, response);
			break;	
		case "volApplyList" :
			volApplyList(request, response);
			break;
		case "cancelVol" :
			cancelVol(request, response);
			break;
		case "generalMyInfoForm" :
			generalMyInfoForm(request, response);
			break;
		case "generalUpdate" :
			generalUpdate(request, response);
			break;
		case "generalDelete" :
			generalDelete(request, response);
			break;
		case "confirmationListForm" :
			confirmationListForm(request, response);
			break;
		case "confirmationForm" :
			confirmationForm(request, response);
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
			
			request.setAttribute("local", localList);
			request.setAttribute("volCategory", categoryList);
			request.getRequestDispatcher("/general/generalInput.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 일반회원 아이디 중복 확인
	 */
	protected void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
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
			if (result) {
				out.println("<script>alert('not-usable');history.go(-1); </script>");
			} else {
				out.println("<script>alert('usable');history.go(-1); </script>");
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
		PrintWriter out = response.getWriter();
		
		String generalName = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday"); 
		String generalId = request.getParameter("id");
		String generalPw = request.getParameter("pw");
		String generalPw2 = request.getParameter("pw2");
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
			out.println("<script>alert('이름을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (generalId == null || generalId.trim().length() == 0) {
			out.println("<script>alert('아이디를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (generalPw == null || generalPw.trim().length() == 0) {
			out.println("<script>alert('비밀번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (generalPw2 == null || generalPw2.trim().length() == 0) {
			out.println("<script>alert('비밀번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (zipCode == null) {
			out.println("<script>alert('주소를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (mobile2 == null || mobile2.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (mobile3 == null || mobile3.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (email1 == null || email1.trim().length() == 0) {
			out.println("<script>alert('이메일을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (email2 == null || email2.trim().length() == 0) {
			out.println("<script>alert('이메일을 입력해 주세요');history.go(-1); </script>");
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
	
	/**
	 * 일반회원 내 정보 조회 페이지 요청 서비스
	 */
	protected void generalMyInfoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		
		GeneralMemberDto dto = (GeneralMemberDto)session.getAttribute("dto");
		ArrayList<LocalDto> localList = new ArrayList<LocalDto>();
		ArrayList<VolCategoryDto> categoryList = new ArrayList<VolCategoryDto>();
		GeneralBiz biz = new GeneralBiz();
		
		try {
			biz.getLocalList(localList);
			biz.getVolCategoryList(categoryList);
			biz.getGeneralInfo(dto);
			request.setAttribute("local", localList);
			request.setAttribute("volCategory", categoryList);
			request.setAttribute("GeneralDto", dto);
			request.getRequestDispatcher("/general/generalMyInfo.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 일반회원 내 정보 수정 요청 서비스
	 */
	protected void generalUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String generalName = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday"); 
		String generalPw = request.getParameter("pw");
		String generalPw2 = request.getParameter("pw2");
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
		
		if (generalName == null || generalName.trim().length() == 0) {
			out.println("<script>alert('이름을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		
		if (generalPw != "") {
			if (generalPw == null || generalPw.trim().length() == 0) {
				out.println("<script>alert('비밀번호를 입력해 주세요');history.go(-1); </script>");
				out.flush();
				out.close();
				return;
			}
			if (generalPw2 == null || generalPw2.trim().length() == 0) {
				out.println("<script>alert('비밀번호를 입력해 주세요');history.go(-1); </script>");
				out.flush();
				out.close();
				return;
			}
		}
		
		
		if (zipCode == null) {
			out.println("<script>alert('주소를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (mobile2 == null || mobile2.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (mobile3 == null || mobile3.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (email1 == null || email1.trim().length() == 0) {
			out.println("<script>alert('이메일을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (email2 == null || email2.trim().length() == 0) {
			out.println("<script>alert('이메일을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		
		HttpSession session = request.getSession();
		GeneralMemberDto dto = (GeneralMemberDto)session.getAttribute("dto");
		
		String generalId = dto.getGeneralId();
		generalName = generalName.trim();
		generalPw = generalPw.trim();
		if (detailAddress != null && detailAddress.trim().length() != 0) {
			address = address + " " + detailAddress;
		}
		String mobile = mobile1 + "-" + mobile2.trim() + "-" + mobile3.trim();
		String email = email1.trim() + "@" + email2.trim();
		
		dto.setGeneralName(generalName);
		dto.setGender(gender);
		dto.setBirthday(birthday);
		dto.setGeneralId(generalId);
		if (generalPw != "") {
			dto.setGeneralPass(generalPw);
		}
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
			biz.updateGeneralMember(dto);
			String url = CONTEXT_PATH + "/general/generalController?action=generalMyInfoForm";
			out.println("<script>alert('내 정보 수정 완료');location.href='" + url + "'; </script>");
		} catch (CommonException e) {
			e.printStackTrace();
			out.println("<script>alert('입력 정보가 올바르지 않습니다');history.go(-1); </script>");
		} finally {
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 일반회원 회원탈퇴 서비스
	 */
	protected void generalDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		GeneralMemberDto dto = (GeneralMemberDto)session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		GeneralBiz biz = new GeneralBiz();
		
		try {
			biz.deleteGeneralMember(generalId);
			
			if (session != null) {
				if (session.getAttribute("dto") != null) {
					session.removeAttribute("dto");
				}
				if (session.getAttribute("grade") != null) {
					session.removeAttribute("grade");
				}
				session.invalidate();
			}
			
			String url = CONTEXT_PATH + "/home";
			out.println("<script>alert('회원탈퇴가 완료되었습니다');location.href='" + url + "'; </script>");
		} catch (CommonException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 봉사신청하기 화면요청 : 회원정보(세션), 봉사정보(날짜별)
	 */
	protected void enrollVolForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		GeneralMemberDto dto = (GeneralMemberDto) session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		
		String volInfoNo = request.getParameter("volInfoNo");
		GeneralBiz biz = new GeneralBiz();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try {
			biz.volInfoByDate(generalId, volInfoNo, list);
			for (HashMap<String, Object> hashMap : list) {
				System.out.println("===");
				for(String key : hashMap.keySet()){
					System.out.println(key+" : "+hashMap.get(key));
				}
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/general/enrollVolForm.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 봉사신청
	 */
	protected void enrollVol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
				if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
					String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
					out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
					out.flush();
					out.close();
					return;
				}
		GeneralMemberDto dto = (GeneralMemberDto) session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		String[] volDetailNos = request.getParameterValues("volDetailNo");
		GeneralBiz biz = new GeneralBiz();
		
		try{
			biz.enrollVol(generalId, volDetailNos);
			response.sendRedirect(CONTEXT_PATH+"/general/generalController?action=volApplyList");
			
		} catch(CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 봉사신청 취소
	 */
	protected void cancelVol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		GeneralMemberDto dto = (GeneralMemberDto) session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		String volApplyNo = request.getParameter("volApplyNo");
		String volDetailNo = request.getParameter("volDetailNo");
		GeneralBiz biz = new GeneralBiz();
		System.out.println("g_id : " + generalId);
		System.out.println("volApplyNo : " + volApplyNo);
		System.out.println("volDetailNo : " + volDetailNo);
		try{
			biz.cancelVol(generalId, volApplyNo, volDetailNo);
			response.sendRedirect(CONTEXT_PATH+"/general/generalController?action=volApplyList");
		} catch(CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 봉사신청목록 조회
	 */
	protected void volApplyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		GeneralBiz biz = new GeneralBiz();
		GeneralMemberDto dto = (GeneralMemberDto) session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		
		try {
			biz.searchVolApplyList(generalId, list);
			
			if(list != null) {
				int totalCnt = list.size();
				
				request.setAttribute("totalCnt", totalCnt);
				request.setAttribute("list", list);
			}
			request.getRequestDispatcher("/general/applylist.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 봉사확인서 내역 조회 페이지
	 */
	protected void confirmationListForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		
		GeneralMemberDto dto = (GeneralMemberDto)session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		GeneralBiz biz = new GeneralBiz();
		try{
			biz.getConfirmationList(generalId, list);
			
			request.setAttribute("list", list);
			request.setAttribute("totalcount", list.size());
			request.getRequestDispatcher("/general/confirmationList.jsp").forward(request, response);
			
		} catch(CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 봉사확인서 다운
	 */
	protected void confirmationForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		
		String volConNo = request.getParameter("volConNo");
		String volInfoNo = request.getParameter("volInfoNo");
		GeneralMemberDto dto = (GeneralMemberDto)session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		HashMap<String, String> selectInfo = new HashMap<String, String>();
		selectInfo.put("volConNo", volConNo);
		selectInfo.put("volInfoNo", volInfoNo);
		selectInfo.put("generalId", generalId);
		HashMap<String, String> map = new HashMap<String, String>();
		GeneralBiz biz = new GeneralBiz();
		try{
			biz.getConfirmation(selectInfo, map);
			
			request.setAttribute("map", map);
			request.getRequestDispatcher("/general/confirmation.jsp").forward(request, response);
			
		} catch(CommonException e) {
			e.printStackTrace();
		}
	}
}
