package com.nanum.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import com.nanum.dto.QnADto;
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
			case "qnaInput":
				qnaInput(request, response);
				break;
			case "qnaInputForm":
				qnaInputForm(request, response);
				break;
			case "qnaList":
				qnaList(request, response);
				break;	
			case "qnaDtl":
				qnaDtl(request, response);
				break;	
			case "qnaUpt":
				qnaUpt(request, response);
				break;		
			case "qnaDel":
				qnaDel(request, response);
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
					response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
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
					response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
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
					response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
					return;
				}
			} catch (CommonException e) {
				e.printStackTrace();
				response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=loginForm");
			}
		}
	}
	
	
	/**
	 * 전체 문의글에서 글쓰기 연동
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void qnaInputForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/qna/qnaInput.jsp").forward(request, response);
	}

	
	/**
	 * QNA 등록
	 */
	private void qnaInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String grade = (String) session.getAttribute("grade");
		String qnaTitle = request.getParameter("qnaTitle");
		String qnaContents = request.getParameter("qnaContents");
		
		QnADto dto = new QnADto();
		dto.setQnaTitle(qnaTitle);
		dto.setQnaContents(qnaContents);
		
		CommonBiz biz = new CommonBiz();
		
		// 작성자는 로그인에서
		if (grade.equals("G")) { 
			//GeneralMemberDto gdto = (GeneralMemberDto) session.getAttribute("dto");
			//System.out.println("gdto.getGeneralId()" + gdto.getGeneralId());
			//dto.setGeneralId(gdto.getGeneralId());
			dto.setGeneralId("user02");

			System.out.println("dto.getGeneralId() = "+dto.getGeneralId());
			try {
				biz.addQna_gen(dto);
				request.getRequestDispatcher("/qna/qnaList.jsp").forward(request, response);
			} catch (CommonException e) {
				e.printStackTrace();
			}
			
			
		}else if  (grade.equals("C")) {
			//CenterMemberDto cdto = (CenterMemberDto) session.getAttribute("dto");
			//dto.setCenterId(cdto.getCenterId());
			dto.setCenterId("user02");

			try {
				biz.addQna_cen(dto);
				request.getRequestDispatcher("/qna/qnaList.jsp").forward(request, response);
			} catch (CommonException e) {
				e.printStackTrace();
			}
		}else if  (grade.equals("A")) {// 어드민
			//AdminMemberDto adto = (AdminMemberDto) session.getAttribute("dto");
		}
	}
	
	/**
	 * QNA 목록조회 
	 */
	private void qnaList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String searchOpt = request.getParameter("search_opt");  // 검색 조건
		String searchText = request.getParameter("search_text"); // 검색문구 
		
		CommonBiz biz = new CommonBiz();
		ArrayList<QnADto> qnaList = new ArrayList<QnADto>(); // 담을 곳 선언
		
		try {
			biz.qnaList(qnaList, searchOpt, searchText);
			request.setAttribute("qnaList", qnaList);
			request.getRequestDispatcher("/qna/qnaList.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
			
	}
	
	
	/**
	 * QNA 상세조회 
	 */
	private void qnaDtl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String qnaNo = request.getParameter("qnaNo");
		System.out.println("qnaNo : " + qnaNo);
		
		CommonBiz biz = new CommonBiz();
		QnADto dto = new QnADto();	// 담을 곳 선언
		
		try {
			biz.qnaDetail(dto, qnaNo); 	// dao단에서 (담을곳, 조건,,,,)
			request.setAttribute("sdto", dto);	// 화면단에 던지기
			request.getRequestDispatcher("/qna/qnaDetail.jsp").forward(request, response);
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * QNA 수정
	 */
	private void qnaUpt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("수정 ======");
		
		String qnaTitle = request.getParameter("qnaTitle");
		String qnaContents = request.getParameter("qnaContents");
		String qnaNo = request.getParameter("qnaNo");
		
		QnADto dto = new QnADto(); //담을곳 선언
		dto.setQnaNo(Integer.parseInt(qnaNo));
		dto.setQnaContents(qnaContents);
		dto.setQnaTitle(qnaTitle);
		
		try {
			CommonBiz biz = new CommonBiz();
			biz.qnaUpdate(dto);
			request.getRequestDispatcher("/qna/qnaList.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * QNA 삭제
	 */
	private void qnaDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("삭제 ======");
		String qnaNo = request.getParameter("qnaNo");
		
		try {
			CommonBiz biz = new CommonBiz();
			biz.qnaDelete(qnaNo);
			request.getRequestDispatcher("/qna/qnaList.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
