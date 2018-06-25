package com.approve.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户使用审批流程
 * @author Max
 *
 */

@Entity
@Table(name = "TBL_USER_APPROVE")
public class UserApprove implements Serializable{
	private String id;
	
	private String title;
	private String content;
	
	private SysUser user;
	private Approve approve;
	private Long createTime;
	
	private int index;
	private String approveNum;
	
	private String remark;
	
	private String status;
	
	//TODO 是否删除
	private String dstatus;
	
	
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
	
	@OneToOne
	@JoinColumn(name="USER_ID")
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	
	@OneToOne
	@JoinColumn(name="APPROVE_ID")
	public Approve getApprove() {
		return approve;
	}
	public void setApprove(Approve approve) {
		this.approve = approve;
	}
	
	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "APPROVE_INDEX")
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	@Column(name = "APPROVE_NUM")
	public String getApproveNum() {
		return approveNum;
	}
	public void setApproveNum(String approveNum) {
		this.approveNum = approveNum;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Transient
	public String getStatus() {
		return status;
	}
	
	@Column(name = "STATUS")
	public String getDstatus() {
		return dstatus;
	}
	
	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
	}
	
	
}
