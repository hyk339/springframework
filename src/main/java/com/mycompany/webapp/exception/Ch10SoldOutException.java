package com.mycompany.webapp.exception;

public class Ch10SoldOutException extends RuntimeException {
	//spring에서 예외를 만들때는 runtimeException으로 만들어야한다.
	//일반 예외로 만들게 되면 try catch를 써야한다.
	//그래서 실행예외인 runtimeException을 써야한다.
	//Exception을 사용할수 있으나 번거롭다.
	//Exception을 만들때 고정적으로 넣는게 있다.
	
	public Ch10SoldOutException() {
		super("품절");
	}
	
	public Ch10SoldOutException(String message) {
		super(message);
	}
}
