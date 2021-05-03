package com.nanum.util;


public class CommonException extends Exception {
	private MessageEntity entity;
	
	public CommonException() {}
	
	public CommonException(MessageEntity entity) {
		this.entity = entity;
	}
	
	public MessageEntity getMessageEntity() {
		return entity;
	}
}
