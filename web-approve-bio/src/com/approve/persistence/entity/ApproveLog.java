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
 * 系统审批日志
 * @author Max
 *
 */

@Entity
@Table(name = "TBL_APPROVE_LOG")
public class ApproveLog implements Serializable{
	private String id;
	private Approve approve;
	private SysUser toUser;
	private SysUser fromUser;
	private String content;
	private Long createTime;
	private Integer stage;
	private String status;
	
	private UserApprove ua;
	
	
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
	@JoinColumn(name="TO_USER_ID")
	public SysUser getToUser() {
		return toUser;
	}
	public void setToUser(SysUser toUser) {
		this.toUser = toUser;
	}
	
	@OneToOne
	@JoinColumn(name="FROM_USER_ID")
	public SysUser getFromUser() {
		return fromUser;
	}
	public void setFromUser(SysUser fromUser) {
		this.fromUser = fromUser;
	}
	
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "STAGE")
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setApprove(Approve approve) {
		this.approve = approve;
	}
	
	@OneToOne
	@JoinColumn(name="APPROVE_ID")
	public Approve getApprove() {
		return approve;
	}
	
	
	public void setUa(UserApprove ua) {
		this.ua = ua;
	}
	
	@OneToOne
	@JoinColumn(name="UA_ID")
	public UserApprove getUa() {
		return ua;
	}
	
	
	
	
	
}
