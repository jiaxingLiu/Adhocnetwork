package com.zhichenhaixin.chentong.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装返回值的对象
 * @version V1.0
 * @author pwl
 * @date 2018年6月1日 下午3:02:26
 * @Description 
 */
@Getter
@Setter
public class Result implements Serializable{
	
	
	
	private static final long serialVersionUID = 623305533229406128L;

	/** 返回码 */
	private int code;
	
	/** 说明  */
	private String msg;
	
	/** 返回的数据  */
	private Object data;

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
