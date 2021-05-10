package com.nanum.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nanum.dto.LocalDto;
import com.nanum.dto.VolBlockDto;
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
		System.out.println("[debug] 나눔모아 메인");
		HttpSession session = request.getSession(true);
		
		HashMap<String, LocalDto> localMap = new HashMap<>();
		HashMap<String, VolCategoryDto> volCategoryMap = new HashMap<>();
		ArrayList<VolBlockDto> volList =  new ArrayList<VolBlockDto>();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> localStatistics = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> categoryStatistics = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Integer> memberStatistics = new HashMap<String, Integer>();
		
		CommonBiz biz = new CommonBiz();
		try {
			biz.searchLocal(localMap);
			if(localMap != null) {
				session.setAttribute("localMap", localMap);	
			}
			
			biz.searchVolCategory(volCategoryMap);
			if(volCategoryMap != null) {
				session.setAttribute("volCategoryMap", volCategoryMap);	
			}
			
			biz.searchVol(volList);
			if(volList != null) {
				for (VolBlockDto dto : volList) {
					System.out.println("[con] vol : " + dto);
				}
				request.setAttribute("volList", volList);
			}
			
			biz.searchVolMapList(list);
			for (HashMap<String, Object> hashMap : list) {
				System.out.println(">>");
				for(String key : hashMap.keySet()){
					System.out.println(key+" : "+hashMap.get(key));
				}
			}
			
			biz.getLocalStatistics(localStatistics);
			request.setAttribute("localStatistics", localStatistics);
			
			biz.getCategoryStatistics(categoryStatistics);
			request.setAttribute("categoryStatistics", categoryStatistics);
			
			biz.getMemberStatistics(memberStatistics);
			request.setAttribute("memberStatistics", memberStatistics);
		} catch (CommonException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
	}

}
