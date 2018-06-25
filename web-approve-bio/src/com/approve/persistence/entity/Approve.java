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
 * 管理员自建审批流程
 * @author Max
 *
 */

@Entity
@Table(name = "TBL_APPROVE")
public class Approve implements Serializable{
	private String id;
	private String name;
	private Long createTime;
	private String remark;
	private String approveType;
	
	private SysUser approvePeople;
	//private SysUser layerPeople;  改为自动获取
	private SysUser archivePeople;
	private String controlPeople;
	
	private SysUser manager;
	private SysUser chairman;
	
	private String status;
	
	private String isLayer;
	
	private SysUser layera;
	private SysUser layerb;
	
	private SysUser office;
	
	private ApproveType atype;
	
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
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}
	
	@Column(name = "APPROVE_TYPE")
	public String getApproveType() {
		return approveType;
	}
	
	@OneToOne
	@JoinColumn(name="APPROVE_PEOPLE", nullable = true)
	public SysUser getApprovePeople() {
		return approvePeople;
	}
	public void setApprovePeople(SysUser approvePeople) {
		this.approvePeople = approvePeople;
	}
	
	/*
	@OneToOne
	@JoinColumn(name="LAYER_PEOPLE", nullable = true)
	public SysUser getLayerPeople() {
		return layerPeople;
	}
	public void setLayerPeople(SysUser layerPeople) {
		this.layerPeople = layerPeople;
	}*/
	
	@OneToOne
	@JoinColumn(name="ARCHIVE_PEOPLE", nullable = true)
	public SysUser getArchivePeople() {
		return archivePeople;
	}
	public void setArchivePeople(SysUser archivePeople) {
		this.archivePeople = archivePeople;
	}
	
	@Column(name="CONTROL_PEOPLE")
	public String getControlPeople() {
		return controlPeople;
	}
	public void setControlPeople(String controlPeople) {
		this.controlPeople = controlPeople;
	}
	
	@OneToOne
	@JoinColumn(name="MANAGER", nullable = true)
	public SysUser getManager() {
		return manager;
	}
	public void setManager(SysUser manager) {
		this.manager = manager;
	}
	
	@OneToOne
	@JoinColumn(name="CHAIRMAN", nullable = true)
	public SysUser getChairman() {
		return chairman;
	}
	public void setChairman(SysUser chairman) {
		this.chairman = chairman;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	
	public void setIsLayer(String isLayer) {
		this.isLayer = isLayer;
	}
	
	@Column(name="IS_LAYER")
	public String getIsLayer() {
		return isLayer;
	}
	
	@OneToOne
	@JoinColumn(name="LAYERA_ID", nullable = true)
	public SysUser getLayera() {
		return layera;
	}
	public void setLayera(SysUser layera) {
		this.layera = layera;
	}
	
	@OneToOne
	@JoinColumn(name="LAYERB_ID", nullable = true)
	public SysUser getLayerb() {
		return layerb;
	}
	
	public void setLayerb(SysUser layerb) {
		this.layerb = layerb;
	}
	
	public void setOffice(SysUser office) {
		this.office = office;
	}
	
	@OneToOne
	@JoinColumn(name="OFFICE_ID", nullable = true)
	public SysUser getOffice() {
		return office;
	}
	
	@OneToOne
	@JoinColumn(name="APPROVE_TYPE_ID", nullable = true)
	public ApproveType getAtype() {
		return atype;
	}
	public void setAtype(ApproveType atype) {
		this.atype = atype;
	}
	
	

	
}
