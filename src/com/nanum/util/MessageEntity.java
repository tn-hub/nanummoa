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
		error.add("[아이디 등록 오류]");			// 0
		error.add("[비밀번호 등록 오류]");		// 1
		// 11
		
		ArrayList<String> validation = new ArrayList<String>();
		validation.add("[아이디 정보 오류]");		// 0
		validation.add("[비밀번호 정보 오류]");		// 1
	// 3
		
		ArrayList<String> success = new ArrayList<String>();
		success.add("[아이디 찾기 완료]");	// 0
		success.add("[아이디 변경 완료]");		// 1
		
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
