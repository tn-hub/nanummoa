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
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.nanum.dto.AdminMemberDto;
import com.nanum.dto.CenterInfoDto;
import com.nanum.dto.QnAReplyDto;
import com.nanum.model.biz.AdminBiz;
import com.nanum.util.Paging;
import com.nanum.model.biz.CommonBiz;

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
		case "deleteReply" :
			deleteReply(request, response);
			break;
		case "updateReply" :
			updateReply(request, response);
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	
	private void centerAcceptList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AdminBiz biz = new AdminBiz();
		CenterInfoDto cDto = new CenterInfoDto(); 
		ArrayList<CenterInfoDto> centerActList = new ArrayList<CenterInfoDto>();
		String pageNum = request.getParameter("pageNum"); 

		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		
		try {
			// 총건수 
			biz.getCenterAcceptListToCnt(cDto);
			request.setAttribute("cDto", cDto);
			
			int pageCount = 2;  
			int curPage = Integer.parseInt(pageNum) * pageCount; 
			
			// 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
			Paging.makeBlock(curPage, pageCount);   // 현재페이지 번호, 원하는row 건수 
			
			// 하단 페이징 번호 max 조회 
			Paging.makeLastPageNum(cDto.getTotAcceptCnt(), pageCount) ; // 총건수, 원하는row 건수
			
			// 값가져오기 
			Integer sartNum = Paging.getBlockStartNum();
			Integer lastNum = Paging.getBlockLastNum();
			Integer lastPageNum = Paging.getLastPageNum();
			
			
			biz.getCenterAcceptList(centerActList, sartNum, lastNum);
			request.setAttribute("centerActList", centerActList);
			
			// jsp 에 총건수 및 건수 보여주기 위해 셋팅 
			request.setAttribute("lastPageNum", lastPageNum);
			request.setAttribute("curPageNum", pageNum);
			
			request.getRequestDispatcher("/admin/centerInputAccept.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
}
