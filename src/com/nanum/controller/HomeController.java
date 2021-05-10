package com.nanum.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nanum.dto.LocalDto;
import com.nanum.dto.VolCategoryDto;
import com.nanum.model.biz.CommonBiz;
import com.nanum.util.CommonException;

/**
 * 홈 컨트롤러
 */
@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletContext application;
	public String CONTEXT_PATH;

	public void init() throws ServletException {
		application = getServletContext();
		CONTEXT_PATH = application.getContextPath();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		HashMap<String, LocalDto> localMap = new HashMap<>();
		HashMap<String, VolCategoryDto> volCategoryMap = new HashMap<>();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> localStatistics = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> categoryStatistics = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Integer> memberStatistics = new HashMap<String, Integer>();
		
		CommonBiz biz = new CommonBiz();
		try {
			biz.searchLocal(localMap);
			biz.searchVolCategory(volCategoryMap);
			biz.searchVolMapList(list);
			biz.getLocalStatistics(localStatistics);
			biz.getCategoryStatistics(categoryStatistics);
			biz.getMemberStatistics(memberStatistics);
			
			session.setAttribute("localMap", localMap);	
			session.setAttribute("volCategoryMap", volCategoryMap);	
			request.setAttribute("list", list);
			request.setAttribute("localStatistics", localStatistics);
			request.setAttribute("categoryStatistics", categoryStatistics);
			request.setAttribute("memberStatistics", memberStatistics);
		} catch (CommonException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
	}

}
