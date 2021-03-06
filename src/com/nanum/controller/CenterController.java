package com.nanum.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.nanum.dto.CenterInfoDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.LocalDto;
import com.nanum.dto.ServiceCategoryDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.dto.CenterVolDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.model.biz.CenterBiz;
import com.nanum.model.biz.CommonBiz;
import com.nanum.model.biz.GeneralBiz;
import com.nanum.util.CommonException;
import com.nanum.util.Utility;

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
		CONTEXT_PATH = (String) application.getAttribute("CONTEXT_PATH");
	}
  
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String action = request.getParameter("action");
		switch (action) {
		case "centerInputForm":
			centerInputForm(request, response);
			break;
		case "centerInput":
			centerInput(request, response);
			break;
		case "idCheck":
			idCheck(request, response);
			break;
		case "centerVolListForm":
			centerVolListForm(request, response);
			break;
		case "volInputForm":
			volInputForm(request, response);
			break;
		case "volInput":
			volInput(request, response);
			break;
		case "updateVolForm":
			updateVolForm(request, response);
			break;
		case "updateVol":
			updateVol(request, response);
			break;
		case "deleteVol":
			deleteVol(request, response);
			break;
		case "centerMyInfoForm":
			centerMyInfoForm(request, response);
			break;
		case "centerUpdate":
			centerUpdate(request, response);
			break;
		case "centerDelete":
			centerDelete(request, response);
			break;
		case "deadlineList":
			deadlineList(request, response);
			break;
		case "applyList":
			applyList(request, response);
			break;
		case "applicantInfoForm":
			applicantInfoForm(request, response);
			break;
		case "applyGeneral":
			applyGeneral(request, response);
			break;
		case "closeApply":
			closeApply(request, response);
			break;
		case "issueListForm":
			issueListForm(request, response);
			break;
		case "issueDetailListForm":
			issueDetailListForm(request, response);
			break;
		case "issueInfoForm":
			issueInfoForm(request, response);
			break;
		case "volIssue":
			volIssue(request, response);
			break;
		case "checkVolStatus":
			checkVolStatus(request, response);
			break;
		case "volIssueForm":
			volIssueForm(request, response);
			break;
		case "endVol":
			endVol(request, response);
			break;
		case "centerNameCheck":
			centerNameCheck(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * 센터회원 회원가입 폼 요청
	 */

	protected void centerInputForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(CONTEXT_PATH + "/center/centerInput.jsp");
	}

	/**
	 * 센터회원 아이디 중복 확인
	 */
	protected void idCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
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
			if (result) {
				out.print("not-usable");
			} else {
				out.print("usable");
			}
		} catch (CommonException e) {
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * 센터회원 회원가입 서비스
	 */
	protected void centerInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String appStatus = request.getParameter("appStatus");

		if (centerMemberName == null || centerMemberName.trim().length() == 0) {
			out.println("<script>alert('이름을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (centerMemberId == null || centerMemberId.trim().length() == 0) {
			out.println("<script>alert('아이디를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (centerMemberPw == null || centerMemberPw.trim().length() == 0) {
			out.println("<script>alert('비밀번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (centerMemberPw2 == null || centerMemberPw2.trim().length() == 0) {
			out.println("<script>alert('비밀번호를 입력해 주세요');history.go(-1); </script>");
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
			out.print("휴대폰 번호를 입력해 주세요");
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
		if (registerCode == null || registerCode.trim().length() == 0) {
			out.println("<script>alert('등록번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (centerName == null || centerName.trim().length() == 0) {
			out.println("<script>alert('기관이름을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (centerEntryDate == null || centerEntryDate.trim().length() == 0) {
			out.println("<script>alert('기관 등록일을 입력해 주세요');history.go(-1); </script>");
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
		if (ceoName == null || ceoName.trim().length() == 0) {
			out.println("<script>alert('대표 이름을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (ceoMobile2 == null || ceoMobile2.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (ceoMobile3 == null || ceoMobile3.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호 입력해 주세요');history.go(-1); </script>");
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
			biz.addCenterMember(cMemberDto, centerDto);
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			if (appStatus.equals("1")) {
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
	 * 센터회원 봉사 목록(모집중)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void centerVolListForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}

		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();

		CenterBiz biz = new CenterBiz();
		ArrayList<CenterVolDto> list = new ArrayList<CenterVolDto>();
		try {
			biz.centerVolList(centerId, list);
			request.setAttribute("list", list);
			request.setAttribute("tap", 1);
			request.getRequestDispatcher("/center/centerInfo.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 센터회원 봉사 목록(마감)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deadlineList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}

		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();

		CenterBiz biz = new CenterBiz();
		ArrayList<CenterVolDto> list = new ArrayList<CenterVolDto>();

		try {
			biz.deadlineList(centerId, list);
			request.setAttribute("list", list);
			request.setAttribute("tap", 2);
			
			request.getRequestDispatcher("/center/centerInfo.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 센터회원 내 정보 조회 페이지 요청 서비스
	 */
	protected void centerMyInfoForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}

		CenterMemberDto centerMemberDto = (CenterMemberDto) session.getAttribute("dto");
		CenterInfoDto centerDto = new CenterInfoDto();
		centerDto.setCenterId(centerMemberDto.getCenterId());
		CenterBiz biz = new CenterBiz();

		try {
			biz.getCenterMemberInfo(centerMemberDto, centerDto);
			request.setAttribute("centerMemberDto", centerMemberDto);
			request.setAttribute("centerDto", centerDto);
			request.getRequestDispatcher("/center/centerMyInfo.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 봉사 신청자 목록 조회
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void applyList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		CenterBiz biz = new CenterBiz();
		try {
			biz.applyList(centerId, volInfoNo, list);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/center/applyList.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 센터회원 내 정보 수정 서비스
	 */
	protected void centerUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}

		String centerMemberName = request.getParameter("name");
		String centerMemberPw = request.getParameter("pw");
		String centerMemberPw2 = request.getParameter("pw2");
		String mobile1 = request.getParameter("mobile1");
		String mobile2 = request.getParameter("mobile2");
		String mobile3 = request.getParameter("mobile3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String registerCode = request.getParameter("registerCode");
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
			out.println("<script>alert('이름을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (centerMemberPw != "") {
			if (centerMemberPw == null || centerMemberPw.trim().length() == 0) {
				out.println("<script>alert('비밀번호를 입력해 주세요');history.go(-1); </script>");
				out.flush();
				out.close();
				return;
			}
			if (centerMemberPw2 == null || centerMemberPw2.trim().length() == 0) {
				out.println("<script>alert('비밀번호를 입력해 주세요');history.go(-1); </script>");
				out.flush();
				out.close();
				return;
			}
		}
		if (mobile2 == null || mobile2.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (mobile3 == null || mobile3.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호를 입력해 주세요');history.go(-1); </script>");
			out.print("휴대폰 번호를 입력해 주세요");
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
		if (registerCode == null || registerCode.trim().length() == 0) {
			out.println("<script>alert('등록번호를 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (centerEntryDate == null || centerEntryDate.trim().length() == 0) {
			out.println("<script>alert('기관 등록일을 입력해 주세요');history.go(-1); </script>");
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
		if (ceoName == null || ceoName.trim().length() == 0) {
			out.println("<script>alert('대표 이름을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (ceoMobile2 == null || ceoMobile2.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		if (ceoMobile3 == null || ceoMobile3.trim().length() == 0) {
			out.println("<script>alert('휴대폰 번호 입력해 주세요');history.go(-1); </script>");
			out.print("휴대폰 번호 입력해 주세요");
			out.flush();
			out.close();
			return;
		}

		CenterMemberDto centerMemberDto = (CenterMemberDto) session.getAttribute("dto");
		CenterInfoDto centerDto = new CenterInfoDto();

		centerMemberName = centerMemberName.trim();
		centerMemberPw = centerMemberPw.trim();
		String mobile = mobile1 + "-" + mobile2.trim() + "-" + mobile3.trim();
		String email = email1.trim() + "@" + email2.trim();
		registerCode = registerCode.trim();
		centerEntryDate = centerEntryDate.trim();
		if (detailAddress != null && detailAddress.trim().length() != 0) {
			address = address + " " + detailAddress;
		}
		String ceoMobile = ceoMobile1 + "-" + ceoMobile2.trim() + "-" + ceoMobile3.trim();

		centerMemberDto.setCenterName(centerMemberName);
		if (centerMemberPw != "") {
			centerMemberDto.setCenterPass(centerMemberPw);
		}
		centerMemberDto.setCenterMobile(mobile);
		centerMemberDto.setCenterEmail(email);

		centerDto.setCenterId(centerMemberDto.getCenterId());
		centerDto.setRegisterCode(registerCode);
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
			biz.updateCenterMember(centerMemberDto, centerDto);
			String url = CONTEXT_PATH + "/center/centerController?action=centerMyInfoForm";
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
	 * 센터회원 회원탈퇴 서비스
	 */
	protected void centerDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		CenterBiz biz = new CenterBiz();

		try {
			biz.deleteCenterMember(centerId);

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
	 * 봉사활동 신청자 승인
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void applicantInfoForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		String generalId = request.getParameter("generalId");
		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));

		GeneralMemberDto general = new GeneralMemberDto();
		general.setGeneralId(generalId);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		CenterBiz biz = new CenterBiz();
		try {
			biz.applicantInfo(centerId, volInfoNo, general, list);

			request.setAttribute("general", general);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/center/applicantInfoForm.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 봉사 게시글 등록 화면 요청
	 */
	protected void volInputForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}

		ArrayList<VolCategoryDto> categoryList = new ArrayList<VolCategoryDto>();
		ArrayList<ServiceCategoryDto> serviceCategoryList = new ArrayList<ServiceCategoryDto>();

		GeneralBiz biz = new GeneralBiz();
		try {

			biz.getVolCategoryList(categoryList);
			biz.getServiceCategoryList(serviceCategoryList);

			request.setAttribute("volCategory", categoryList);
			request.setAttribute("volSubject", serviceCategoryList);
			request.getRequestDispatcher("/center/volInput.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 봉사 게시글 등록
	 */
	protected void volInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		String volTitle = request.getParameter("volTitle");
		String volContents = request.getParameter("volContents");

		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		String startVolDateStr = request.getParameter("startVolDate");
		String endVolDateStr = request.getParameter("endVolDate");

		String categoryNo = request.getParameter("categoryNo");
		String volSubject = request.getParameter("volSubject");
		String volType = request.getParameter("volType");

		String local = request.getParameter("local");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String volPlace = address + " " + detailAddress;

		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		String totalCountStr = request.getParameter("totalCount");
		int totalCount = Integer.parseInt(totalCountStr);

		if (volTitle == null || volTitle.trim().length() == 0) {
			out.println("<script>alert('제목을 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (volContents == null || volContents.trim().length() == 0) {
			out.println("<script>alert('내용을 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (startDateStr == null || startDateStr.trim().length() == 0 || endDateStr == null
				|| endDateStr.trim().length() == 0 || startTimeStr == null || startTimeStr.trim().length() == 0
				|| endTimeStr == null || endTimeStr.trim().length() == 0 || startVolDateStr == null
				|| startVolDateStr.trim().length() == 0 || endVolDateStr == null
				|| endVolDateStr.trim().length() == 0) {
			out.println("<script>alert('날짜를 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (startTimeStr == null || startTimeStr.trim().length() == 0 || endTimeStr == null
				|| endTimeStr.trim().length() == 0) {
			out.println("<script>alert('시간을 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (totalCountStr == null || totalCountStr.trim().length() == 0) {
			out.println("<script>alert('모집인원을 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (categoryNo == null || categoryNo.equals("none") || volSubject == null || volSubject.equals("none")
				|| volTitle == null || volTitle.equals("none")) {
			out.println("<script>alert('값을 선택해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (address == null || address.trim().length() == 0) {
			out.println("<script>alert('주소를 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		ArrayList<LocalDto> localList = new ArrayList<LocalDto>();
		GeneralBiz biz = new GeneralBiz();
		CenterBiz cBiz = new CenterBiz();
		String localNo = "1"; // 서울이외 지역 기본 1
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("centerId", centerId);
		map.put("volTitle", volTitle);
		map.put("volContents", volContents);
		map.put("startDate", startDateStr);
		map.put("endDate", endDateStr);
		map.put("startTime", startTimeStr);
		map.put("endTime", endTimeStr);
		map.put("categoryNo", categoryNo);
		map.put("volType", volType);
		map.put("volPlace", volPlace);
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("volSubject", volSubject);
		map.put("totalCount", totalCount);

		try {
			biz.getLocalList(localList);
			for (LocalDto localDto : localList) {
				if (localDto.getLocalName().equals(local)) {
					localNo = localDto.getLocalNo();
				}
			}
			map.put("localNo", localNo);
			ArrayList<String> dateList = Utility.getDateList(startVolDateStr, endVolDateStr);
			cBiz.addVol(map, dateList);

			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=volListForm");
		} catch (CommonException | ParseException e) {
			out.println("<script>alert('글 등록에 실패했습니다.');history.back();</script>");
			out.flush();
			out.close();
			e.printStackTrace();
		}
	}

	/**
	 * 봉사게시글 수정 화면요청
	 */
	protected void updateVolForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		ArrayList<VolCategoryDto> categoryList = new ArrayList<VolCategoryDto>();
		ArrayList<ServiceCategoryDto> serviceCategoryList = new ArrayList<ServiceCategoryDto>();
		ArrayList<LocalDto> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		String volInfoNoStr = request.getParameter("volInfoNo");
		int volInfoNo = Integer.parseInt(volInfoNoStr);

		GeneralBiz gBiz = new GeneralBiz();
		CommonBiz biz = new CommonBiz();
		try {
			gBiz.getVolCategoryList(categoryList);
			gBiz.getServiceCategoryList(serviceCategoryList);
			biz.volDetailInfo(map, volInfoNo);

			gBiz.getLocalList(list);
			for (LocalDto dto : list) {
				if (dto.getLocalNo() == map.get("localNo")) {
					map.put("local", dto.getLocalName());
				}
			}

			request.setAttribute("map", map);
			request.setAttribute("volCategory", categoryList);
			request.setAttribute("volSubject", serviceCategoryList);
			request.getRequestDispatcher("/center/updatevol.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 봉사게시글 수정
	 */
	protected void updateVol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();

		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}

		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		String volTitle = request.getParameter("volTitle");
		String volContents = request.getParameter("volContents");

		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		String startVolDateStr = request.getParameter("startVolDate");
		String endVolDateStr = request.getParameter("endVolDate");

		String categoryNo = request.getParameter("categoryNo");
		String volSubject = request.getParameter("volSubject");
		String volType = request.getParameter("volType");

		String local = request.getParameter("local");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String volPlace = address + " " + detailAddress;

		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		String totalCountStr = request.getParameter("totalCount");
		int totalCount = Integer.parseInt(totalCountStr);

		if (volTitle == null || volTitle.trim().length() == 0) {
			out.println("<script>alert('제목을 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (volContents == null || volContents.trim().length() == 0) {
			out.println("<script>alert('내용을 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (startDateStr == null || startDateStr.trim().length() == 0 || endDateStr == null
				|| endDateStr.trim().length() == 0 || startTimeStr == null || startTimeStr.trim().length() == 0
				|| endTimeStr == null || endTimeStr.trim().length() == 0 || startVolDateStr == null
				|| startVolDateStr.trim().length() == 0 || endVolDateStr == null
				|| endVolDateStr.trim().length() == 0) {
			out.println("<script>alert('날짜를 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (startTimeStr == null || startTimeStr.trim().length() == 0 || endTimeStr == null
				|| endTimeStr.trim().length() == 0) {
			out.println("<script>alert('시간을 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (totalCountStr == null || totalCountStr.trim().length() == 0) {
			out.println("<script>alert('모집인원을 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (categoryNo == null || categoryNo.equals("none") || volSubject == null || volSubject.equals("none")
				|| volTitle == null || volTitle.equals("none")) {
			out.println("<script>alert('값을 선택해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		if (address == null || address.trim().length() == 0) {
			out.println("<script>alert('주소를 입력해 주세요');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

		ArrayList<LocalDto> localList = new ArrayList<LocalDto>();
		GeneralBiz biz = new GeneralBiz();
		CenterBiz cBiz = new CenterBiz();
		String localNo = "1"; // 서울이외 지역 기본 1
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("volInfoNo", volInfoNo);
		map.put("centerId", centerId);
		map.put("volTitle", volTitle);
		map.put("volContents", volContents);
		map.put("startDate", startDateStr);
		map.put("endDate", endDateStr);
		map.put("startTime", startTimeStr);
		map.put("endTime", endTimeStr);
		map.put("categoryNo", categoryNo);
		map.put("volType", volType);
		map.put("volPlace", volPlace);
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("volSubject", volSubject);

		try {
			biz.getLocalList(localList);
			for (LocalDto localDto : localList) {
				if (localDto.getLocalName().equals(local)) {
					localNo = localDto.getLocalNo();
				}
			}
			map.put("localNo", localNo);

			ArrayList<String> dateList = Utility.getDateList(startVolDateStr, endVolDateStr);

			cBiz.updateVol(volInfoNo, startVolDateStr, endVolDateStr, dateList, totalCount, map);

			response.sendRedirect(
					CONTEXT_PATH + "/common/commonController?action=volDetatilForm&volInfoNo=" + volInfoNo);
		} catch (CommonException | ParseException e) {
			e.printStackTrace();
			out.println("<script>alert('글수정에 실패했습니다.');history.back();</script>");
			out.flush();
			out.close();
			return;
		}

	}

	/**
	 * 봉사게시글 삭제
	 */
	protected void deleteVol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		PrintWriter out = response.getWriter();
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();

		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));
		CenterBiz biz = new CenterBiz();
		try {
			biz.deleteVol(volInfoNo, centerId);
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=volListForm");
		} catch (CommonException e) {
			e.printStackTrace();
			out.println("<script>alert('글삭제에 실패했습니다.');history.back();</script>");
			out.flush();
			out.close();
			return;
		}
	}

	/**
	 * 봉사활동 신청승인
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void applyGeneral(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		String generalId = request.getParameter("generalId");
		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));

		String[] checkDates = request.getParameterValues("checkDate");
		CenterBiz biz = new CenterBiz();
		for (int i = 0; i < checkDates.length; i++) {
			try {
				biz.applyGeneral(checkDates[i]);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("generalId", generalId);
		request.setAttribute("volInfoNo", volInfoNo);
		request.getRequestDispatcher("/center/centerController?action=applicantInfoForm").forward(request, response);
	}

	/**
	 * 봉사활동 승인취소
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void closeApply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		String generalId = request.getParameter("generalId");
		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));

		int volApplyNo = Integer.parseInt(request.getParameter("volApplyNo"));

		CenterBiz biz = new CenterBiz();

		try {
			biz.closeApply(volApplyNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("generalId", generalId);
		request.setAttribute("volInfoNo", volInfoNo);
		request.getRequestDispatcher("/center/centerController?action=applicantInfoForm").forward(request, response);
	}

	/**
	 * 인증서발급 목록페이지
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void issueListForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();

		CenterBiz biz = new CenterBiz();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			biz.issueList(centerId, list);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/center/issue/issueList.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 인증서발급 상세페이지
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void issueDetailListForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		CenterBiz biz = new CenterBiz();
		try {
			biz.applyList(centerId, volInfoNo, list);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/center/issue/issueDetailList.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 인증서 발급,활동여부 페이지
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void issueInfoForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		String generalId = request.getParameter("generalId");
		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));

		GeneralMemberDto general = new GeneralMemberDto();
		general.setGeneralId(generalId);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		CenterBiz biz = new CenterBiz();
		try {
			biz.applicantInfo(centerId, volInfoNo, general, list);

			request.setAttribute("general", general);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/center/issue/issueInfo.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 인증서 폼
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void volIssueForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		String volInfoNo = request.getParameter("volInfoNo");
		String generalId = request.getParameter("generalId");
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("centerId", centerId);
		map.put("volInfoNo", volInfoNo);
		map.put("generalId", generalId);
		
		CenterBiz biz = new CenterBiz();
		try {
			biz.volIssueForm(map);
			request.setAttribute("volInfoNo", volInfoNo);
			request.setAttribute("map", map);
			request.setAttribute("generalId", generalId);
			request.getRequestDispatcher("/center/issue/issueForm.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 인증서발급 요청
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void volIssue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");

		String generalId = request.getParameter("generalId");
		String centerId = dto.getCenterId();
		String contents = request.getParameter("contents");
		String volInfoNo = request.getParameter("volInfoNo");
		String issueDate = request.getParameter("issueDate");
		//int volApplyNo = Integer.parseInt(request.getParameter("volApplyNo"));

		if (contents == null || contents.trim().length() == 0 || contents == null) {
			out.println("<script>alert('내용을 입력해 주세요');history.go(-1); </script>");
			out.flush();
			out.close();
			return;
		}
		contents.trim();

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("generalId", generalId);
		map.put("centerId", centerId);
		map.put("contents", contents);
		map.put("volInfoNo", volInfoNo);
		map.put("issueDate", issueDate);
		//map.put("volApplyNo", volApplyNo);
		
		CenterBiz biz = new CenterBiz();
		try {
			biz.volIssue(map);
			request.setAttribute("volInfoNo", volInfoNo);
			request.setAttribute("generalId", generalId);
			request.getRequestDispatcher("/center/centerController?action=issueDetailListForm").forward(request,response);
		} catch (CommonException e) {
			request.getRequestDispatcher("/center/centerController?action=issueDetailListForm").forward(request,response);
		}
	}

	/**
	 * 활동상태 변경(활동종료)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkVolStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		String generalId = request.getParameter("generalId");
		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));
		String[] checkDates = request.getParameterValues("checkDate");

		CenterBiz biz = new CenterBiz();
		
		
		try {
			for (int i = 0; i < checkDates.length; i++) {
				biz.checkVolStatus(checkDates[i]);
			}
			request.setAttribute("generalId", generalId);
			request.setAttribute("volInfoNo", volInfoNo);
			request.getRequestDispatcher("/center/centerController?action=issueInfoForm").forward(request,response);
		} catch (CommonException e) {
			request.setAttribute("generalId", generalId);
			request.setAttribute("volInfoNo", volInfoNo);
			request.getRequestDispatcher("/center/centerController?action=issueInfoForm").forward(request, response);
		}
	}

	/**
	 * 봉사등록마감, 종료
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void endVol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			return;
		}
		CenterMemberDto dto = (CenterMemberDto) session.getAttribute("dto");
		String centerId = dto.getCenterId();
		int volInfoNo = Integer.parseInt(request.getParameter("volInfoNo"));
		String recStatus = request.getParameter("recStatus");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("centerId", centerId);
		map.put("volInfoNo", volInfoNo);
		map.put("recStatus", recStatus);

		CenterBiz biz = new CenterBiz();
		try {
			biz.endVol(map);
			request.getRequestDispatcher("/center/centerController?action=deadlineList").forward(request,
					response);
		} catch (CommonException e) {

		}

	}
	
	/**
	 * 센터이름 등록여부 확인
	 */
	protected void centerNameCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerName = request.getParameter("centerName");
		System.out.println("centerName : " + centerName);
		PrintWriter out = response.getWriter();

		if (centerName == null || centerName.trim().length() == 0) {
			out.print("none");
			out.flush();
			out.close();
			return;
		}
		
		String appStatus = "0";
		try {
			String urlStr1 = "http://openapi.seoul.go.kr:8088/4f5874664c7268783837774a656e55/json/VOpenGroup/1/1000/";
			String urlStr2 = "http://openapi.seoul.go.kr:8088/4f5874664c7268783837774a656e55/json/VOpenGroup/1001/2000/";
			String urlStr3 = "http://openapi.seoul.go.kr:8088/4f5874664c7268783837774a656e55/json/VOpenGroup/2001/2477/";
			String[] urlStrArr = { urlStr1, urlStr2, urlStr3 };

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
		
		
			if (appStatus.equals("0")) {
				out.print("not-usable");
				out.flush();
				out.close();
			} else {
				out.print("usable");
				out.flush();
				out.close();
			}
		}
	
}
