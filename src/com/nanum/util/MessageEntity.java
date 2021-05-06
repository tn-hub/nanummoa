package com.nanum.util;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageEntity {
	private HashMap<String, ArrayList<String>> messageList = new HashMap<String, ArrayList<String>>();
	private String url;
	private String linkTitle;
	private String type;
	private int index;
	private String message;
	
	public MessageEntity() {

		ArrayList<String> error = new ArrayList<String>();
		error.add("[학과검색 오류]");			// 0
		error.add("[교수정보 등록 오류]");		// 1
		error.add("[로그인 오류]");			// 2
		error.add("[교수정보 수정 오류]");		// 3
		error.add("[내 강의과목 검색 오류]");	// 4
		error.add("[개설가능과목 검색 오류]");	// 5
		error.add("[강의 하기 오류]");			// 6
		error.add("[강의 중복 등록 오류]");		// 7
		error.add("[폐강 적용 오류]");			// 8
		error.add("[설문 검색 오류]");			// 9
		error.add("[성적 조회 오류]");			// 10
		error.add("[성적 수정 오류]");			// 11
		
		ArrayList<String> validation = new ArrayList<String>();
		validation.add("[아이디 정보 오류]");		// 0
		validation.add("[비밀번호 정보 오류]");		// 1
		validation.add("[이름 정보 오류]");		// 2
		validation.add("[주소 정보 오류]");		// 3
		
		ArrayList<String> success = new ArrayList<String>();
		success.add("[교수정보 등록 성공]");	// 0
		success.add("[교수 로그인 성공]");		// 1
		success.add("[교수정보 수정 성공]");	// 2
		success.add("[성적 수정 성공]");		// 3
		success.add("[로그아웃 성공]");		// 4
		success.add("[강의 개설 성공]");		// 5
		success.add("[폐강 처리 성공]");		// 6
		
		ArrayList<String> message = new ArrayList<String>();
		message.add("[이 페이지는 로그인이 필요합니다.]");
		
		messageList.put("error", error);
		messageList.put("validation", validation);
		messageList.put("success", success);
		messageList.put("message", message);
	}
	
	public MessageEntity(String type, int index) {
		this();
		
		this.type = type;
		this.index = index;
	}

	public String getMessage() {
		return messageList.get(type).get(index);
	}

	public String getUrl() {
		return url;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public String getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}
	

	/**
	 * @return the messageList
	 */
	public HashMap<String, ArrayList<String>> getMessageList() {
		return messageList;
	}

	/**
	 * @param messageList the messageList to set
	 */
	public void setMessageList(HashMap<String, ArrayList<String>> messageList) {
		this.messageList = messageList;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

}
