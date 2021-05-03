package com.nanum.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 공통 컨트롤러
 */
@WebServlet(urlPatterns={"/common/commonController"}, loadOnStartup = 1)
public class CommonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletContext application;
	public String CONTEXT_PATH;
    
	
	public void init() {
		application = getServletContext();
		CONTEXT_PATH = application.getContextPath();
		application.setAttribute("CONTEXT_PATH", CONTEXT_PATH);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		switch(action) {
//		case "":
//			(request, response);
//			break;
//		case "":
//			(request, response);
//			break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
