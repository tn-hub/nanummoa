package com.nanum.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.CenterVolDto;
import com.nanum.dto.VolInfoDto;
import com.nanum.model.biz.CenterBiz;
import com.nanum.util.CommonException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.nanum.dto.CenterInfoDto;

/**
 * 센터회원 컨트롤러
 */
@WebServlet("/center/centerController")
public class CenterController extends HttpServlet {
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
		case "centerInputForm" :
			centerInputForm(request, response);
			break;
		case "centerInput":
			centerInput(request, response);
			break;
		case "idCheck":
			idCheck(request, response);
			break;
		case "centerVolListForm" :
			centerVolListForm(request, response);
			break;
//		case "":
//			(request, response);
//			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * 센터회원 회원가입 폼 요청
	 */
	protected void centerInputForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(CONTEXT_PATH + "/signUp/centerInput.jsp");
	}
	
	/**
	 * 센터회원 아이디 중복 확인
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
		CenterBiz biz = new CenterBiz();
		try {
			boolean result = biz.isCenterId(id);
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
	 * 센터회원 회원가입 서비스
	 */
	protected void centerInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String centerMemberName = request.getParameter("name");
		String centerMemberId = request.getParameter("id");
		String centerMemberPw = request.getParameter("pw");
		String centerMemberPw2 = request.getParameter("pw2");
		String mobile1 = request.getParameter("mobile1");
		String mobile2 = request.getParameter("mobile2");
		String mobile3 = request.getParameter("mobile3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String registerCode = request.getParameter("registerCode");
		String centerName = request.getParameter("centerName");
		String centerEntryDate = request.getParameter("centerEntryDate");
		String zipCode = request.getParameter("zipCode");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String ceoName = request.getParameter("ceoName");
		String ceoMobile1 = request.getParameter("ceoMobile1");
		String ceoMobile2 = request.getParameter("ceoMobile2");
		String ceoMobile3 = request.getParameter("ceoMobile3");
		String service = request.getParameter("service");
		
		if (centerMemberName == null || centerMemberName.trim().length() == 0) {
			out.print("이름을 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (centerMemberId == null || centerMemberId.trim().length() == 0) {
			out.print("아이디를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (centerMemberPw == null || centerMemberPw.trim().length() == 0) {
			out.print("비밀번호를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (centerMemberPw2 == null || centerMemberPw2.trim().length() == 0) {
			out.print("비밀번호를 입력해 주세요");
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
		if (registerCode == null || registerCode.trim().length() == 0) {
			out.print("등록번호를 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (centerName == null || centerName.trim().length() == 0) {
			out.print("기관이름을 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (centerName == null || centerName.trim().length() == 0) {
			out.print("기관이름을 입력해 주세요");
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
		if (ceoName == null || ceoName.trim().length() == 0) {
			out.print("대표 이름을 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (ceoMobile2 == null || ceoMobile2.trim().length() == 0) {
			out.print("휴대폰 번호 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		if (ceoMobile3 == null || ceoMobile3.trim().length() == 0) {
			out.print("휴대폰 번호 입력해 주세요");
			out.flush();
			out.close();
			return;
		}
		
		centerMemberName = centerMemberName.trim();
		centerMemberId = centerMemberId.trim();
		centerMemberPw = centerMemberPw.trim();
		String mobile = mobile1 + "-" + mobile2.trim() + "-" + mobile3.trim();
		String email = email1.trim() + "@" + email2.trim();
		registerCode = registerCode.trim();
		centerName = centerName.trim();
		centerEntryDate = centerEntryDate.trim();
		if (detailAddress != null && detailAddress.trim().length() != 0) {
			address = address + " " + detailAddress;
		}
		String ceoMobile = ceoMobile1 + "-" + ceoMobile2.trim() + "-" + ceoMobile3.trim();
		
		CenterMemberDto cMemberDto = new CenterMemberDto();
		CenterInfoDto centerDto = new CenterInfoDto();
		
		String appStatus = "0";
		 try {
			String urlStr1 = "http://openapi.seoul.go.kr:8088/4f5874664c7268783837774a656e55/json/VOpenGroup/1/1000/";
			String urlStr2 = "http://openapi.seoul.go.kr:8088/4f5874664c7268783837774a656e55/json/VOpenGroup/1001/2000/";
			String urlStr3 = "http://openapi.seoul.go.kr:8088/4f5874664c7268783837774a656e55/json/VOpenGroup/2001/2477/";
			String[] urlStrArr = {urlStr1, urlStr2, urlStr3};
			
			for (String urlStr : urlStrArr) {
				URL url = new URL(urlStr);

				String line = "";
				String result = "";

				BufferedReader br;
				br = new BufferedReader(new InputStreamReader(url.openStream()));
				while ((line = br.readLine()) != null) {
					result = result.concat(line);
				}
				// JSON parser 만들어 문자열 데이터를 객체화한다.
				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(result);
				JSONObject data = (JSONObject) obj.get("VOpenGroup");
				JSONArray data2 = (JSONArray) data.get("row");

				// 객체형태로
				for (int i = 0; i < data2.size(); i++) {
					JSONObject row = (JSONObject) data2.get(i);
					String centerNameData = (String) row.get("KORNAME");
					if (centerName.equals(centerNameData)) {
						System.out.println("centerName : " + centerNameData);
						appStatus = "1";
					}
				}
				br.close();
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		 
		 System.out.println("appStatus : " + appStatus);
		 cMemberDto.setCenterName(centerMemberName);
		 cMemberDto.setCenterId(centerMemberId);
		 cMemberDto.setCenterPass(centerMemberPw);
		 cMemberDto.setCenterMobile(mobile);
		 cMemberDto.setCenterEmail(email);
		 cMemberDto.setAppStatus(appStatus);
		 
		 centerDto.setCenterId(centerMemberId);
		 centerDto.setRegisterCode(registerCode);
		 centerDto.setCenterName(centerName);
		 centerDto.setCenterEntryDate(centerEntryDate);
		 centerDto.setCenterZipCode(zipCode);
		 centerDto.setCenterAddress(address);
		 centerDto.setCeoName(ceoName);
		 centerDto.setCeoMobile(ceoMobile);
		 if (service != null || service.trim().length() != 0) {
			 centerDto.setService(service);
		 }
		 
		 CenterBiz biz = new CenterBiz();
		 
		 try {
			biz.addGeneralMember(cMemberDto, centerDto);
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			if (appStatus.equals("Y")) {
				out.println("<script>alert('회원가입 완료');location.href='" + url + "'; </script>");
			} else {
				url = CONTEXT_PATH + "/home";
				out.println("<script>alert('관리자 승인 후 로그인 하실 수 있습니다');location.href='" + url + "'; </script>");
			}
		} catch (CommonException e) {
			e.printStackTrace();
			out.println("<script>alert('회원가입 정보가 올바르지 않습니다');history.go(-1); </script>");
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * 센터회원 봉사 목록
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void centerVolListForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CenterMemberDto dto = (CenterMemberDto)session.getAttribute("dto");
		String centerId = dto.getCenterId();
		
		CenterBiz biz = new CenterBiz();
		ArrayList<CenterVolDto> list = new ArrayList<CenterVolDto>();
		
		try {
			biz.centerVolList(centerId,list);
			request.setAttribute("list",list);
			request.getRequestDispatcher("/center/centerInfo.jsp").forward(request, response);	
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 센터회원 봉사 목록(모집중)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void recruitList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CenterMemberDto dto = (CenterMemberDto)session.getAttribute("dto");
		String centerId = dto.getCenterId();
		
		CenterBiz biz = new CenterBiz();
		ArrayList<CenterVolDto> list = new ArrayList<CenterVolDto>();
		
		try {
			biz.recruitList(centerId,list);
			request.setAttribute("list",list);
			request.getRequestDispatcher("/center/centerInfo.jsp").forward(request, response);	
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
}
