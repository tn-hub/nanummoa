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

import com.nanum.dto.CenterMemberDto;
import com.nanum.dto.VolInfoDto;
import com.nanum.model.biz.CenterBiz;
import com.nanum.util.CommonException;

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
	 * 센터회원 봉사 목록
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void centerVolListForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		CenterMemberDto dto = new CenterMemberDto();
		
		//String centerId = dto.getCenterId();
		String centerId = "center01";
		
		CenterBiz biz = new CenterBiz();
		ArrayList<VolInfoDto> list = new ArrayList<VolInfoDto>();
		
		for(VolInfoDto dd : list) {
			System.out.println(dd.getVolTitle());
		}
		
		try {
			biz.centerVolList(centerId,list);
			request.setAttribute("list",list);
			request.getRequestDispatcher("/center/centerInfo.jsp").forward(request, response);	
		} catch (CommonException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
