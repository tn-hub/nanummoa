package com.nanum.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nanum.dto.CenterInfo;
import com.nanum.dto.QnADto;
import com.nanum.model.biz.AdminBiz;

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
		CenterInfo cDto = new CenterInfo(); 
		ArrayList<CenterInfo> centerActList = new ArrayList<CenterInfo>();
		
		try {
			
			biz.getCenterAcceptListToCnt(cDto);
			request.setAttribute("cDto", cDto);
			
			biz.getCenterAcceptList(centerActList);
			request.setAttribute("centerActList", centerActList);
			
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
}
