package com.nanum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
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
import com.nanum.dto.LocalDto;
import com.nanum.dto.QnADto;
import com.nanum.dto.SearchAllDto;
import com.nanum.dto.ServiceCategoryDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.dto.VolInfoDto;
import com.nanum.model.biz.CommonBiz;
import com.nanum.model.biz.GeneralBiz;
import com.nanum.util.CommonException;
import com.nanum.util.Gmail;
import com.nanum.util.MessageEntity;
import com.nanum.util.Paging;
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
		response.setContentType("text/html; charset=utf-8");
		String action = request.getParameter("action");
		switch (action) {
		case "loginForm":
			loginForm(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
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
		case "volListForm":
			volListForm(request, response);
			break;
		case "findIdForm":
			findIdForm(request, response);
			break;
		case "inputForm":
			inputForm(request, response);
			break;
		case "findPwForm":
			findPwForm(request, response);
			break;
		case "findPw":
			findPw(request, response);
			break;
		case "checkEmailForm":
			checkEmailForm(request, response);
			break;
		case "checkEmail":
			checkEmail(request, response);
			break;
		case "newPwForm":
			newPwForm(request, response);
			break;
		case "newPw":
			newPw(request, response);
			break;
		case "volDetatilForm":
			volDetatilForm(request, response);
			break;		
		case "searchAllForm":
			searchAllForm(request, response);
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
		PrintWriter out = response.getWriter();

		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String grade = request.getParameter("grade");

		if (memberId == null || memberId.trim().length() == 0 || memberId == "") {
			out.println("<script>alert('[오류] 아이디를 입력하세요');history.go(-1); </script>");
			out.flush();
			return;
		}
		if (memberPw == null || memberPw.trim().length() == 0 || memberPw == "") {
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
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
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
				if (dto.getCenterName() != null && dto.getAppStatus().equals("1")) {
					session.setAttribute("dto", dto);
					session.setAttribute("grade", grade);
					response.sendRedirect(CONTEXT_PATH + "/home");
				} else if (dto.getCenterName() != null && dto.getAppStatus().equals("0")) {
					out.println("<script>alert('[오류] 미승인된 아이디 입니다 .');history.go(-1); </script>");
					out.flush();
					return;
				} else {
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
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
					response.sendRedirect(CONTEXT_PATH + "/home");
				} else {
					out.println("<script>alert('[오류] 로그인 정보가 맞지 않습니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
				out.println("<script>alert('[오류]');history.go(-1); </script>");
				out.flush();
			}
		}

	}

	/**
	 * 아이디찾기 페이지
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findIdForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/common/findInfo/findId.jsp").forward(request, response);
	}

	String secureCode = null;

	/**
	 * 이메일로 임시번호 발급
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addSecureCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String grade = request.getParameter("grade");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String host = "http://localhost:8090/nanummoa/";
		String from = "gusqls904@gmail.com";
		String code = SHA256.getEncrypt(email, "cos");

		System.out.println("이름 : " + name);
		System.out.println("등급 : " + grade);
		System.out.println("이메일 : " + email);

		if (email == null || email.trim().length() == 0 || email == "") {
			out.print("none_email");
			out.flush();
			out.close();
			return;
		}
		if (name == null || name.trim().length() == 0 || name == "") {
			out.print("none_name");
			out.flush();
			out.close();
			return;
		}
		email.trim();
		name.trim();

		String subject = "나눔모아 이메일 인증 메일입니다.";
		secureCode = Utility.getSecureString(8, true);

		CommonBiz biz = new CommonBiz();
		if (grade.equals("G")) {
			GeneralMemberDto dto = new GeneralMemberDto();
			dto.setGeneralName(name);
			dto.setGeneralEmail(email);
			try {
				biz.checkEmail(dto);
				if (dto.getGeneralId() != null) {
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

						out.print("send_email");
						out.flush();
					} catch (Exception e) {
						out.print("not_email");
						out.flush();
					}
				} else {
					out.print("not_info");
					out.flush();
				}
			} catch (CommonException e) {
				out.print("error");
				out.flush();
			} finally {
				out.close();
			}
		} else if (grade.equals("C")) {
			CenterMemberDto dto = new CenterMemberDto();
			dto.setCenterName(name);
			dto.setCenterEmail(email);
			try {
				biz.checkEmail(dto);
				if (dto.getCenterId() != null) {
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

						out.print("send_email");
						out.flush();
					} catch (Exception e) {
						out.print("not_email");
						out.flush();
					}
				} else {
					out.println("not_info");
					out.flush();
				}
			} catch (CommonException e) {
				out.println("error");
				out.flush();
			} finally {
				out.close();
			}
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
	protected void findId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");

		CommonBiz biz = new CommonBiz();

		if (code.equals(secureCode)) {
			if (grade.equals("G")) {
				GeneralMemberDto dto = new GeneralMemberDto();
				dto.setGeneralName(name);
				dto.setGeneralEmail(email);
				try {
					biz.findId(dto);
					request.setAttribute("dto", dto);
					MessageEntity message = new MessageEntity("success", 0);
					message.setLinkTitle("로그인");
					message.setUrl(CONTEXT_PATH + "/common/commonController?action=loginForm");
					request.setAttribute("message", message);
					request.setAttribute("id", dto.getGeneralId());
					request.getRequestDispatcher("/common/findInfo/idpwmessage.jsp").forward(request, response);
				} catch (CommonException e) {
					out.println("<script>alert('[오류]');history.go(-1); </script>");
					out.flush();
				}
			} else if (grade.equals("C")) {
				CenterMemberDto dto = new CenterMemberDto();
				dto.setCenterName(name);
				dto.setCenterEmail(email);
				try {
					biz.findId(dto);
					MessageEntity message = new MessageEntity("success", 0);
					message.setLinkTitle("로그인");
					message.setUrl(CONTEXT_PATH + "/common/commonController?action=loginForm");
					request.setAttribute("message", message);
					request.setAttribute("dto", dto);
					request.setAttribute("id", dto.getCenterId());
					request.getRequestDispatcher("/common/findInfo/idpwmessage.jsp").forward(request, response);
				} catch (CommonException e) {
					out.println("<script>alert('[오류]');history.go(-1); </script>");
					out.flush();
				}
			}
		} else {
			out.println("<script>alert('[오류] 인증번호가 맞지 않습니다.');history.go(-1); </script>");
			out.flush();
		}
	}

	/**
	 * 비밀번호찾기 페이지
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findPwForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/common/findInfo/findPw.jsp").forward(request, response);
	}

	/**
	 * 비밀번호찾기(이메일 검색)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findPw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String memberId = request.getParameter("memberId");
		String grade = request.getParameter("grade");

		if (memberId == null || memberId.trim().length() == 0 || memberId == "") {
			out.println("<script>alert('[오류] 아이디를 입력하세요');history.go(-1); </script>");
			out.flush();
			return;
		}
		memberId = memberId.trim();
		CommonBiz biz = new CommonBiz();

		if (grade.equals("G")) {
			GeneralMemberDto dto = new GeneralMemberDto();
			dto.setGeneralId(memberId);
			try {
				biz.findPw(dto);
				if (dto.getGeneralEmail() != null) {
					request.setAttribute("grade", grade);
					request.setAttribute("dto", dto);
					request.getRequestDispatcher("/common/commonController?action=checkEmailForm").forward(request,
							response);
				} else {
					out.println("<script>alert('[오류] 존재하지 않는 아이디 입니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
				out.println("<script>alert('[오류]');history.go(-1); </script>");
				out.flush();
				return;
			}
		} else if (grade.equals("C")) {
			CenterMemberDto dto = new CenterMemberDto();
			dto.setCenterId(memberId);
			try {
				biz.findPw(dto);
				if (dto.getCenterEmail() != null) {
					request.setAttribute("grade", grade);
					request.setAttribute("dto", dto);
					request.getRequestDispatcher("/common/findInfo/checkEmail.jsp").forward(request, response);
				} else {

					out.println("<script>alert('[오류] 존재하지 않는 아이디 입니다.');history.go(-1); </script>");
					out.flush();
					return;
				}
			} catch (CommonException e) {
				out.println("<script>alert('[오류]');history.go(-1); </script>");
				out.flush();
				return;
			}
		}
	}

	/**
	 * 비밀번호찾기(이메일 인증) 페이지
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkEmailForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/common/findInfo/checkEmail.jsp").forward(request, response);
	}

	/**
	 * 비밀번호찾기(이메일 인증)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");

		System.out.println("이름 : " + name);
		System.out.println("코드 : " + code);
		System.out.println("메일 : " + email);
		System.out.println("등급 : " + grade);

		if (name == null || name.trim().length() == 0 || name == "") {
			out.println("<script>alert('[오류] 이름을 입력하세요');history.go(-1); </script>");
			out.flush();
			return;
		}
		if (code == null || code.trim().length() == 0 || code == "") {
			out.println("<script>alert('[오류] 인증번호를 입력하세요');history.go(-1); </script>");
			out.flush();
			return;
		}

		name = name.trim();
		code = code.trim();

		CommonBiz biz = new CommonBiz();
		if (code.equals(secureCode)) {
			if (grade.equals("G")) {
				GeneralMemberDto dto = new GeneralMemberDto();
				dto.setGeneralName(name);
				dto.setGeneralEmail(email);
				try {
					biz.checkEmail(dto);
					System.out.println("비밀번호 : " + dto.getGeneralPass());
					if (dto.getGeneralPass() != null) {
						request.setAttribute("grade", grade);
						request.setAttribute("dto", dto);
						request.getRequestDispatcher("/common/commonController?action=newPwForm").forward(request,
								response);
					} else {
						out.println("<script>alert('[오류] 정보가 맞지 않습니다');history.go(-1); </script>");
						out.flush();
						return;
					}
				} catch (CommonException e) {
					out.println("<script>alert('[오류]');history.go(-1); </script>");
					out.flush();
					return;
				}
			} else if (grade.equals("C")) {
				CenterMemberDto dto = new CenterMemberDto();
				dto.setCenterName(name);
				dto.setCenterEmail(email);
				try {
					biz.checkEmail(dto);
					if (dto.getCenterPass() != null) {
						request.setAttribute("grade", grade);
						request.setAttribute("dto", dto);
						request.getRequestDispatcher("/common/commonController?action=newPwForm").forward(request,
								response);
					} else {
						out.println("<script>alert('[오류] 정보가 맞지 않습니다');history.go(-1); </script>");
						out.flush();
						return;
					}
				} catch (CommonException e) {
					out.println("<script>alert('[오류]');history.go(-1); </script>");
					out.flush();
					return;
				}
			}
		} else {
			out.println("<script>alert('[오류] 인증번호가 맞지 않습니다.');history.go(-1); </script>");
			out.flush();
			return;
		}
	}

	/**
	 * 새비밀번호 페이지
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void newPwForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/common/findInfo/newPw.jsp").forward(request, response);
	}

	/**
	 * 새비밀번호 설정
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void newPw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String memberPw = request.getParameter("memberPw");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");

		if (memberPw == null || memberPw.trim().length() == 0 || memberPw == "") {
			out.println("<script>alert('[오류] 이름을 입력하세요');history.go(-1); </script>");
			out.flush();
			return;
		}
		if (email == null || email.trim().length() == 0 || email == "") {
			out.println("<script>alert('[오류] 인증번호를 입력하세요');history.go(-1); </script>");
			out.flush();
			return;
		}

		memberPw = memberPw.trim();
		email = email.trim();
		CommonBiz biz = new CommonBiz();

		if (grade.equals("G")) {
			GeneralMemberDto dto = new GeneralMemberDto();
			dto.setGeneralPass(memberPw);
			dto.setGeneralEmail(email);
			try {
				biz.newPw(dto);
				MessageEntity message = new MessageEntity("success", 1);
				message.setLinkTitle("로그인");
				message.setUrl(CONTEXT_PATH + "/common/commonController?action=loginForm");
				request.setAttribute("message", message);
				request.getRequestDispatcher("/common/findInfo/idpwmessage.jsp").forward(request, response);
			} catch (Exception e) {
				out.println("<script>alert('[오류] 인증번호를 입력하세요');history.go(-1); </script>");
				out.flush();
			}
		} else if (grade.equals("C")) {
			CenterMemberDto dto = new CenterMemberDto();
			dto.setCenterPass(memberPw);
			dto.setCenterEmail(email);
			try {
				biz.newPw(dto);
				MessageEntity message = new MessageEntity("success", 1);
				message.setLinkTitle("로그인");
				message.setUrl(CONTEXT_PATH + "/common/commonController?action=loginForm");
				request.setAttribute("message", message);
				request.getRequestDispatcher("/common/findInfo/idpwmessage.jsp").forward(request, response);
			} catch (Exception e) {
				out.println("<script>alert('[오류] 인증번호를 입력하세요');history.go(-1); </script>");
				out.flush();
			}
		}
	}

	/**
	 * 전체 문의글에서 글쓰기 연동
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void qnaInputForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/qna/qnaInput.jsp").forward(request, response);
	}

	/**
	 * QNA 등록
	 */
	private void qnaInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			GeneralMemberDto gdto = (GeneralMemberDto) session.getAttribute("dto");
			dto.setGeneralId(gdto.getGeneralId());

			System.out.println("dto.getGeneralId() = " + dto.getGeneralId());
			try {
				biz.addQna_gen(dto);
				response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=qnaList");
			} catch (CommonException e) {
				e.printStackTrace();
			}

		} else if (grade.equals("C")) {
			CenterMemberDto cdto = (CenterMemberDto) session.getAttribute("dto");
			dto.setCenterId(cdto.getCenterId());

			try {
				biz.addQna_cen(dto);
				response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=qnaList");
			} catch (CommonException e) {
				e.printStackTrace();
			}
		} else if (grade.equals("A")) {// 어드민
			// AdminMemberDto adto = (AdminMemberDto) session.getAttribute("dto");
		}
	}

	/**
	 * QNA 목록조회
	 */
	private void qnaList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		int curPage = 6;
		String searchOpt = request.getParameter("search_opt");
		String searchText = request.getParameter("search_text");
		CommonBiz biz = new CommonBiz();
		ArrayList<QnADto> qnaList = new ArrayList<QnADto>();
		QnADto cdto = new QnADto();
		try {

			request.setAttribute("searchText", searchText);
			request.setAttribute("searchOpt", searchOpt);
			
			biz.qnaListTotCnt(cdto); // 총건수 조회 
			
			Paging.makeBlock(curPage);
			
			Integer blockStartNum = Paging.getBlockStartNum();
			Integer blockLastNum = Paging.getBlockLastNum();
			Integer lastPageNum = Paging.getLastPageNum();
			
			System.out.println("blockStartNum=  " + blockStartNum);
			System.out.println("blockLastNum=  " + blockLastNum);
			System.out.println("lastPageNum=  " + lastPageNum);
			

			biz.qnaListTotCnt(cdto);
			request.setAttribute("cdto", cdto);

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
	private void qnaDtl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
	private void qnaUpt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	private void qnaDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qnaNo = request.getParameter("qnaNo");

		try {
			CommonBiz biz = new CommonBiz();
			biz.qnaDelete(qnaNo);
			response.sendRedirect(CONTEXT_PATH + "/common/commonController?action=qnaList");
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 봉사 상세보기
	 * 
	 * @param request
	 * @param response
	 */
	private void volDetatilForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommonBiz biz = new CommonBiz();
		GeneralBiz gBiz = new GeneralBiz();
		String volInfoNo = request.getParameter("volInfoNo");

		// 카테고리 가져오기
		ArrayList<VolCategoryDto> categoryList = new ArrayList<VolCategoryDto>();

		try {
			gBiz.getVolCategoryList(categoryList);
			request.setAttribute("volCategory", categoryList);
			// 상세조회
			VolInfoDto dto = new VolInfoDto();
			biz.volDetailInfo(dto, Integer.parseInt(volInfoNo));
			request.setAttribute("vDto", dto);
			request.getRequestDispatcher("/volInfo.jsp").forward(request, response);

		} catch (CommonException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 봉사조회 페이지 요청 서비스
	 */
	private void volListForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommonBiz biz = new CommonBiz();
		GeneralBiz gBiz = new GeneralBiz();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ArrayList<LocalDto> localList = new ArrayList<LocalDto>();
		ArrayList<VolCategoryDto> volCategoryList = new ArrayList<VolCategoryDto>();
		ArrayList<ServiceCategoryDto> serviceList = new ArrayList<ServiceCategoryDto>();
		HashMap<String, String> searchMap = new HashMap<String, String>();

		String local = request.getParameter("local");
		String category = request.getParameter("category");
		String service = request.getParameter("service");
		String status = request.getParameter("status");
		String volStart = request.getParameter("volStart");
		String volEnd = request.getParameter("volEnd");
		String[] volType = request.getParameterValues("volType");
		String volTitle = request.getParameter("volTitle");
		String centerName = request.getParameter("centerName");

		if (local == null) {
			local = "0";
		}
		if (category == null) {
			category = "0";
		}
		if (service == null) {
			service = "0";
		}
		if (status == null) {
			status = "0";
		} else if (status.equals("2")) {
			status = null;
		}
		if (volStart == null) {
			volStart = Utility.getCurrentDate();
		}
		if (volEnd == null) {
			volEnd = Utility.getCurrentDate(3);
		}
		if (volTitle != null) {
			if (volTitle.trim().length() == 0) {
				volTitle = null;
			} else {
				volTitle = volTitle.trim();
			}
		}
		if (centerName != null) {
			if (centerName.trim().length() == 0) {
				centerName = null;
			} else {
				centerName = centerName.trim();
			}
		}

		searchMap.put("local", local);
		searchMap.put("category", category);
		searchMap.put("service", service);
		searchMap.put("status", status);
		searchMap.put("volStart", volStart);
		searchMap.put("volEnd", volEnd);
		if (volType == null || volType.length == 2) {
			searchMap.put("volType", null);
		} else {
			searchMap.put("volType", volType[0]);
		}
		searchMap.put("volTitle", volTitle);
		searchMap.put("centerName", centerName);

		try {
			if (!local.equals("0")) {
				HashMap<String, LocalDto> localMap = new HashMap<String, LocalDto>();
				biz.searchLocal(localMap);
				LocalDto dto = localMap.get(local);
				searchMap.put("local", dto.getLocalName());
			}

			gBiz.getLocalList(localList);
			gBiz.getVolCategoryList(volCategoryList);
			biz.searchServiceCategory(serviceList);
			String[] date = { Utility.getCurrentDate(), Utility.getCurrentDate(3) };
			String sql = biz.searchVolList(list, searchMap);
			int total = biz.volListTotalCount(searchMap, sql);
			System.out.println("total : " + total);

			request.setAttribute("date", date);
			request.setAttribute("localList", localList);
			request.setAttribute("volCategoryList", volCategoryList);
			request.setAttribute("serviceList", serviceList);
			request.setAttribute("volList", list);
			request.setAttribute("total", total);
			request.setAttribute("searchMap", searchMap);
			request.getRequestDispatcher("/common/vol_list.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 회원가입 페이지 요청
	 */
	protected void inputForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/common/inputForm.jsp").forward(request, response);
	}

	/**
	 * 로그아웃 서비스
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.out.println("[debug] 로그아웃 요청");
		if (session != null) {
			if (session.getAttribute("dto") != null) {
				session.removeAttribute("dto");
			}

			if (session.getAttribute("grade") != null) {
				session.removeAttribute("grade");
			}

			session.invalidate();
		}

		response.sendRedirect(CONTEXT_PATH + "/home");
	}
	
	
	/**
	 * 통합검색
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchAllForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String searchAllOpt = "";
		String searchAllText = "";
		
		if(request.getParameter("main_searchAll_text") != null || request.getParameter("main_searchAll_text") == "") {
			// 전체통합검색
			searchAllOpt = request.getParameter("main_searchAll_opt");
			searchAllText = request.getParameter("main_searchAll_text");
		}else {
			//통합검색
			searchAllOpt = request.getParameter("searchAll_opt");
			searchAllText = request.getParameter("searchAll_text");
		}
		
		System.out.println("searchAll_opt" + searchAllOpt);
		System.out.println("searchAllText" + searchAllText);
		
		CommonBiz biz = new CommonBiz();
		ArrayList<SearchAllDto> saList = new ArrayList<SearchAllDto>();
		
		try {
			request.setAttribute("searchAllOpt", searchAllOpt);
			request.setAttribute("searchAllText", searchAllText);
			
			biz.searchAllList(saList, searchAllOpt, searchAllText);
			request.setAttribute("saList", saList);
			
			request.getRequestDispatcher("/common/searchMain.jsp").forward(request, response);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
}