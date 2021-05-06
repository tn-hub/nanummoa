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
import com.nanum.dto.VolApplyListDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.dto.VolDetailDto;
import com.nanum.dto.VolInfoDto;
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
		case "volInfo" :
			volInfo(request, response);
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
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		process(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
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
	
	/**
	 * 봉사신청하기 화면요청 : 회원정보(세션), 봉사정보(날짜별)
	 */
	protected void enrollVolForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("dto") == null ||
				session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");	
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
	private void enrollVol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("dto") == null ||
				session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");	
			return;
		}
		GeneralMemberDto dto = (GeneralMemberDto) session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		String[] volDetailNos = request.getParameterValues("volDetailNo");
		GeneralBiz biz = new GeneralBiz();
		try{
			for (String volDetailNo : volDetailNos) {
				System.out.println("[con] 봉사신청  volDetailNo : " +volDetailNo);
				biz.enrollVol(generalId, volDetailNo);
			}	
			response.sendRedirect(CONTEXT_PATH+"/general/generalController?action=volApplyList");
			
		} catch(CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 봉사신청 취소
	 */
	private void cancelVol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("dto") == null ||
				session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");	
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
	private void volApplyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("dto") == null ||
				session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");	
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
				System.out.println("총"+ totalCnt+"건");
				for (HashMap<String, Object> hashMap : list) {
					System.out.println("===");
					for(String key : hashMap.keySet()){
						System.out.println(key+" : "+hashMap.get(key));
					}
				}
				request.setAttribute("totalCnt", totalCnt);
				request.setAttribute("list", list);
			}
			request.getRequestDispatcher("/general/applylist.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 봉사상세정보 조회(통합)
	 */
	private void volInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String volInfoNo = request.getParameter("volInfoNo");
		GeneralBiz biz = new GeneralBiz();
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			biz.getVolInfo(volInfoNo, resultMap);
			
			for(String key : resultMap.keySet()){
				System.out.println(key+" : "+resultMap.get(key));
			}
			
			request.getRequestDispatcher("/vol_list.jsp").forward(request, response);
			
		} catch(CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 봉사정보 조회(날짜별)-신청시
	 */
	private void volInfoByDate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("dto") == null ||
				session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");	
			return;
		}
		String volInfoNo = request.getParameter("volInfoNo");
		GeneralBiz biz = new GeneralBiz();
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		GeneralMemberDto dto = (GeneralMemberDto) session.getAttribute("dto");
		String generalId = dto.getGeneralId();
		try{
			biz.volInfoByDate(generalId, volInfoNo, list);
			for (HashMap<String, Object> hashMap : list) {
				for(String key : hashMap.keySet()){
					System.out.println(key+" : "+hashMap.get(key));
				}
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/vol_list.jsp").forward(request, response);
			
		} catch(CommonException e) {
			e.printStackTrace();
		}
	}
}
