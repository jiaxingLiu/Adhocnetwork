package com.zhichenhaixin.chentong.model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * @version V1.0
 * @author pwl
 * @date 2018年6月12日 上午10:45:13
 * @Description 
 */
@Entity
@Component
public class User {
	
	private int uId; // 用户id号
	private String uName; // 用户名
	private String uAccount; // 账号
	private String uPwd; // 密码
	private int uSex; // 性别 0:男 1：女
	private String uEmail; // 邮箱
	private String uPhone; // 联系方式
	private String uBirthday; // 出生日期
	private String Department; // 部门名称
	private String uDuty; // 职务名称
	private String uDutydesc; // 职务描述
	private int role_id; // 角色id号
	private int uStatus; // 用户状态
	// 新增两个字段 便于密码修改
	private String uNewpwd; // 新密码
	private String uConfirmpwd; // 确认密码
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuAccount() {
		return uAccount;
	}
	public void setuAccount(String uAccount) {
		this.uAccount = uAccount;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	public int getuSex() {
		return uSex;
	}
	public void setuSex(int uSex) {
		this.uSex = uSex;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuBirthday() {
		return uBirthday;
	}
	public void setuBirthday(String uBirthday) {
		this.uBirthday = uBirthday;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getuDuty() {
		return uDuty;
	}
	public void setuDuty(String uDuty) {
		this.uDuty = uDuty;
	}
	public String getuDutydesc() {
		return uDutydesc;
	}
	public void setuDutydesc(String uDutydesc) {
		this.uDutydesc = uDutydesc;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getuStatus() {
		return uStatus;
	}
	public void setuStatus(int uStatus) {
		this.uStatus = uStatus;
	}
	public String getuNewpwd() {
		return uNewpwd;
	}
	public void setuNewpwd(String uNewpwd) {
		this.uNewpwd = uNewpwd;
	}
	public String getuConfirmpwd() {
		return uConfirmpwd;
	}
	public void setuConfirmpwd(String uConfirmpwd) {
		this.uConfirmpwd = uConfirmpwd;
	}
	
	
}

