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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterInfoDto;
import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.GeneralMemberDto;
import com.nanum.dto.QnAReplyDto;
import com.nanum.model.biz.AdminBiz;
import com.nanum.model.biz.CommonBiz;
import com.nanum.model.biz.GeneralBiz;
import com.nanum.util.CommonException;
import com.nanum.util.Paging;

/**
 * 관리자 컨트롤러
 */
@WebServlet("/admin/adminController")
public class AdminController extends HttpServlet {
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
		response.setContentType("text/html; charset=utf-8");
		System.out.println("action : " + action);
		switch (action) {
		case "centerAcceptList" :
			centerAcceptList(request, response);
			break;
		case "centerAccept" :
			centerAccept(request, response);
			break;
		case "centerRefuse" :
			centerRefuse(request, response);
			break;
		case "addReply" :
			addReply(request, response);
			break;
		case "getReply" :
			getReply(request, response);
			break;
		case "generalMinList" :
			generalMinList(request, response);
			break;
		case "centerMinList" :
			centerMinList(request, response);
			break;
		case "deleteReply" :
			deleteReply(request, response);
			break;
		case "updateReply" :
			updateReply(request, response);
			break;
		case "generalDetail" :
			generalDetail(request, response);
			break;
		case "centerDetail" :
			centerDetail(request, response);
			break;
		case "searchAllMemberForm" :
			searchAllMemberForm(request, response);
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

	
	/**센터회원가입 대기 회원*/
	private void centerAcceptList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}

		
		AdminBiz biz = new AdminBiz();
		CenterInfoDto cDto = new CenterInfoDto(); 
		ArrayList<CenterInfoDto> centerActList = new ArrayList<CenterInfoDto>();
		
		try {
			// 총건수 
			biz.getCenterAcceptListToCnt(cDto);
			request.setAttribute("cDto", cDto);
			
			
			biz.getCenterAcceptList(centerActList);
			request.setAttribute("centerActList", centerActList);
			
			
			request.getRequestDispatcher("/admin/centerInputAccept.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 센터회원가입 승인*/
	private void centerAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String centerId = request.getParameter("centerId");
		AdminBiz aBiz = new AdminBiz();
		
		try {
			aBiz.acceptCenter(centerId);
			response.sendRedirect(CONTEXT_PATH + "/admin/adminController?action=centerAcceptList");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**센터회원가입 거절*/
	private void centerRefuse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String centerId = request.getParameter("centerId");
		AdminBiz aBiz = new AdminBiz();

		try {
			aBiz.refuseCenter(centerId);
			response.sendRedirect(CONTEXT_PATH + "/admin/adminController?action=centerAcceptList");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 댓글 등록
	 */
	private void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String qno = request.getParameter("qno");
		String content = request.getParameter("content");
		
		System.out.println("컨트롤러 qno, content : " + qno + ", " + content);
		
		HttpSession session = request.getSession();
		AdminMemberDto dto = (AdminMemberDto)session.getAttribute("dto");
		String adminId = dto.getAdminId();
		QnAReplyDto replyDto = new QnAReplyDto();
		replyDto.setAdminId(adminId);
		replyDto.setQnaNo(Integer.parseInt(qno));
		replyDto.setReplyContents(content);
		
		AdminBiz aBiz = new AdminBiz();
		try {
			aBiz.addReply(replyDto);
			out.print("success");
		}catch (Exception e) {
			e.printStackTrace();
			out.print("fail");
		} finally {
			out.flush();
			out.close();
		}
		
	}
	
	/**
	 * 댓글 조회
	 */
	private void getReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String qno = request.getParameter("qno");
		
		CommonBiz cBiz = new CommonBiz();
		ArrayList<QnAReplyDto> list = new ArrayList<QnAReplyDto>();
		try {
			cBiz.getReply(qno, list);
			if (list.size() > 0) {
				JSONArray jsonArr = new JSONArray();
				for(QnAReplyDto qDto : list) {
					JSONObject obj = new JSONObject();
					obj.put("replyNo", qDto.getReplyNo());
					obj.put("adminId", qDto.getAdminId());
					obj.put("replyContents", qDto.getReplyContents());
					obj.put("replyWriteDate", qDto.getReplyWriteDate());
					
					jsonArr.add(obj);
				}
		        out.println(jsonArr);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		
	}

	/** 관리자 회원 일반회원 보기*/
	private void generalMinList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		
		AdminBiz biz = new AdminBiz();
		ArrayList<GeneralMemberDto> list = new ArrayList<GeneralMemberDto>();

		try {
			biz.getGenralMinList(list);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/admin/searchAllMember.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	/** 관리자 회원 센터회원 보기*/
	private void centerMinList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		AdminBiz biz = new AdminBiz();
		ArrayList<CenterMemberDto> list = new ArrayList<CenterMemberDto>();

		try {
			biz.getCenterMinList(list);
			System.out.println("getCenterMinList" + list.size());
			request.setAttribute("list", list);
			request.getRequestDispatcher("/admin/searchAllMember.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 댓글 삭제
	 */
	private void deleteReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int rNo = Integer.parseInt(request.getParameter("rNo"));
		String content = request.getParameter("content");
		
		
		AdminBiz aBiz = new AdminBiz();
		try {
			aBiz.deleteReply(rNo);
			out.print("success");
		}catch (Exception e) {
			e.printStackTrace();
			out.print("fail");
		} finally {
			out.flush();
			out.close();
		}
		
	}
	
	/**
	 * 댓글 수정
	 */
	private void updateReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int rNo = Integer.parseInt(request.getParameter("rNo"));
		String content = request.getParameter("content");
		
		AdminBiz aBiz = new AdminBiz();
		try {
			aBiz.updateReply(rNo, content);
			out.print("success");
		}catch (Exception e) {
			e.printStackTrace();
			out.print("fail");
		} finally {
			out.flush();
			out.close();
		}
		
	}
	
	
	/**관리자  일반회원 상세보기*/
	private void generalDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		
		String generalId = request.getParameter("generalId");
		
		AdminBiz biz = new AdminBiz();
		GeneralMemberDto dto = new GeneralMemberDto();

		try {
			biz.getGeneralDetail(dto, generalId);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("/admin/generalDetailnfo.jsp").forward(request, response);
		}catch (Exception e) {
		}
		}
	/**
	 * 봉사확인서 내역 조회 페이지
	 */
	private void confirmationListForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("dto") == null ||
				session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");	
			return;
		}
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		AdminBiz biz = new AdminBiz();
		try{
			biz.getConfirmationList(list);
			request.setAttribute("list", list);
			request.setAttribute("totalcount", list.size());
			request.getRequestDispatcher("/admin/confirmationList.jsp").forward(request, response);
			
		} catch(CommonException e) {
			e.printStackTrace();
		}
	}
	
	
	/**관리자 센터회원 상세보기*/
	private void centerDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("dto") == null || session.getAttribute("grade") == null ) {
			String url = CONTEXT_PATH + "/common/commonController?action=loginForm";
			out.println("<script>alert('로그인 후 이용해 주시기 바랍니다');location.href='" + url + "'; </script>");
			out.flush();
			out.close();
			return;
		}
		
		String centerId = request.getParameter("centerId");
		
		AdminBiz biz = new AdminBiz();
		CenterInfoDto dto = new CenterInfoDto();

		try {
			biz.getCenterDetail(dto, centerId);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("/admin/centerDetailnfo.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchAllMemberForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/admin/searchAllMember.jsp").forward(request, response);
	}
	/**
	 * 봉사확인서 다운
	 */
	private void confirmationForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("dto") == null ||
				session.getAttribute("grade") == null) {
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");	
			return;
		}
		
		String volConNo = request.getParameter("volConNo");
		String volInfoNo = request.getParameter("volInfoNo");
		String generalId = request.getParameter("gId");
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
