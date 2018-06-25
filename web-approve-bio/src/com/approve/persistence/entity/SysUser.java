package com.approve.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 系统登录用户
 * @author Max
 *
 */

@Entity
@Table(name = "SYS_USER")
public class SysUser implements Serializable{
	private String id;
	private String userName;
	private String loginName;
	private String password;
	private String remark;
	private String isAdmin;
	private Long createTime;
	private Department dept;
	private String post;
	private String status;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name = "ID", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Column(name = "IS_ADMIN")
	public String getIsAdmin() {
		return isAdmin;
	}
	
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	@OneToOne
	@JoinColumn(name="DEPT_ID")
	public Department getDept() {
		return dept;
	}
	
	public void setPost(String post) {
		this.post = post;
	}
	
	@Column(name = "POSITION")
	public String getPost() {
		return post;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	
	
	
}
