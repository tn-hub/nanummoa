package com.nanum.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		case "centerAcceptListForm" :
			centerAcceptListForm(request, response);
			break;
		case "centerInputAcceptForm":
			centerInputAcceptForm(request, response);
			break;
		case "centerList":
			centerList(request, response);
			break;
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	
	private void centerAcceptListForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/admin/centerList.jsp").forward(request, response);
	}
	
	
	private void centerInputAcceptForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/admin/centerInputAccept.jsp").forward(request, response);
	}
	
	private void centerList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
}
