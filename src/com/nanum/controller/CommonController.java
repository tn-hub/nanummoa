package com.nanum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.ArrayList;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import com.nanum.util.Gmail;
import com.nanum.util.SHA256;
import com.nanum.util.Utility;

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
		case "mail":
			mail(request, response);
			break;
		case "addSecureCode":
			addSecureCode(request, response);
			break;
		case "findId":
			findId(request, response);
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
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('[오류] 아이디를 입력하세요');history.go(-1); </script>");
			out.flush();
			return;
		}
		if (memberPw == null || memberPw.trim().length() == 0 || memberPw == "") {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('[오류] 비밀번호를 입력하세요');history.go(-1); </script>");
			out.flush();
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
					
					response.sendRedirect(CONTEXT_PATH + "/home");
				} else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('[오류]');history.go(-1); </script>");
				out.flush();
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
					response.sendRedirect("/home");
				} else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('[오류]');history.go(-1); </script>");
				out.flush();
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
					response.sendRedirect("/home");
				} else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('[오류]');history.go(-1); </script>");
				out.flush();
			}
		}

	}

	String secureCode = null;
	
	/**
	 * 이메일 임시번호 발급
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addSecureCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + "@" + email2;
		
		
		// DB 저장 했다고 가정 (DB에는 emailAuth 필드가 있어야 하고 최초에는 0이 저장되어 있음) 1 인증 0 미인증
		// DB에 저장했으니 google email 인증 페이지로 이동

		String host = "http://localhost:8090/nanummoa/";
		String from = "gusqls904@gmail.com";
		String to = request.getParameter("email");
		String code = SHA256.getEncrypt(email, "cos");

		// 사용자에게 보낼 메시지
		String subject = "나눔모아(아이디찾기) 이메일 인증 메일입니다.";

		// 임시번호 발급
		secureCode = Utility.getSecureString(10, true);

		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465"); // TLS 587, SSL 465
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.sockerFactory.fallback", "false");

		try {
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(email);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(secureCode, "text/html; charset=UTF8");
			Transport.send(msg);

			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("email", email);

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('메일확인해주세요');history.go(-1); </script>");
			out.flush();
			
		} catch (Exception e) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일 인증 오류')");
			script.println("history.back();");
			script.println("</script>");
		}
	}

	/**
	 * 이메일 임시번호 인증
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void mail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter script = response.getWriter();
		 if(code.equals(secureCode)){	
			script.println("<script>");
			script.println("alert('이메일 인증에 성공하였습니다.')");
			script.println("location.href='/nanummoa/common/commonController?action=findId'");
			script.println("</script>");
		} else{
			script.println("<script>");
			script.println("alert('이메일 인증을 실패하였습니다.')");
			script.println("location.href='/nanummoa/common/error.jsp'");
			script.println("</script>");
		}  
	}

	/**
	 * 아이디 찾기
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String name = (String)session.getAttribute("name");
		
		CommonBiz biz = new CommonBiz();
		
		GeneralMemberDto dto = new GeneralMemberDto();
		dto.setGeneralEmail(email);
		dto.setGeneralName(name);
		
		try {
			biz.findId(dto);
			if (dto.getGeneralId() != null) {
				System.out.println("일반회원 아이디 : " + dto.getGeneralId());
				
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/common/idpwmessage.jsp").forward(request, response);
			}else {
				CenterMemberDto center = new CenterMemberDto();
				center.setCenterEmail(email);
				center.setCenterName(name);
				try {
					biz.findId(center);	
					if (center.getCenterId() != null) {
						System.out.println("센터 아이디 : " + center.getCenterId());
						request.setAttribute("dto", center);
						request.getRequestDispatcher("/common/idpwmessage.jsp").forward(request, response);		
					}else {
						System.out.println(dto.getGeneralId());
						System.out.println(center.getCenterId());
						System.out.println("정보 틀림");
						return;
					}
				} catch (Exception e) {
					System.out.println("아이디 찾기 오류");
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("아이디 찾기 오류");
			return;
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
		String searchOpt = request.getParameter("search_opt");
		String searchText = request.getParameter("search_text");
		CommonBiz biz = new CommonBiz();
		ArrayList<QnADto> qnaList = new ArrayList<QnADto>();
		
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
		CommonBiz biz = new CommonBiz();
		QnADto dto = new QnADto();
		
		try {
			biz.qnaDetail(dto, qnaNo);
			request.setAttribute("sdto", dto);
			request.getRequestDispatcher("/qna/qnaDetail.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * QNA 수정
	 */
	private void qnaUpt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String qnaTitle = request.getParameter("qnaTitle");
		String qnaContents = request.getParameter("qnaContents");
		String qnaNo = request.getParameter("qnaNo");
		
		QnADto dto = new QnADto();
		dto.setQnaNo(Integer.parseInt(qnaNo));
		dto.setQnaContents(qnaContents);
		dto.setQnaTitle(qnaTitle);
		
		try {
			CommonBiz biz = new CommonBiz();
			biz.qnaUpdate(dto);
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=qnaList");
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * QNA 삭제
	 */
	private void qnaDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String qnaNo = request.getParameter("qnaNo");
		
		try {
			CommonBiz biz = new CommonBiz();
			biz.qnaDelete(qnaNo);
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=qnaList");
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
