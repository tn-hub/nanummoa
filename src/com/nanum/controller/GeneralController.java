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
 * 일반회원 컨트롤러
 */
@WebServlet("/general/generalController")
public class GeneralController extends HttpServlet {
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
		case "generalInputForm" :
			generalInputForm(request, response);
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
	 * 일반회원 회원가입 폼 요청
	 */
	protected void generalInputForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
