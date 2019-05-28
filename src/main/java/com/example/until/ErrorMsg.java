package com.example.until;

/**
 * 
 *
 * @Description:错误提示实体类
 * @author:     lijianzhou
 * @date:       2016年10月12日
 * Copyright (c) 2016, Sutu. All rights reserved
 */
public class ErrorMsg {
	public ErrorMsg(int line,int column,String errorMsg){
		this.line = line;
		this.column = column;
		this.errorMsg = errorMsg;
	}
	
	public ErrorMsg (){
		
	}
	
	/*行*/
	private int line;
	
	/*列*/
	private int column;
	
	/*错误消息*/
	private String errorMsg;

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
