package com.approve.struts.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.approve.persistence.entity.Department;
import com.approve.persistence.entity.Setting;
import com.approve.persistence.entity.StaticSetting;
import com.approve.persistence.entity.SysFreeApprove;
import com.approve.persistence.entity.SysUser;
import com.approve.struts.BaseAction;

public class SettingAction extends BaseAction{
	
	private String managerid;
	private String archiveid;
	
	public String getManagerid() {
		return managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public String getArchiveid() {
		return archiveid;
	}

	public void setArchiveid(String archiveid) {
		this.archiveid = archiveid;
	}

	public String backInit() throws Exception{
		return SUCCESS;
	}
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void upload() throws Exception{
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String path = ServletActionContext.getServletContext().getRealPath("/images");
		
		
		try {
			if(file != null){
				String fileEx = getFileFileName().substring(getFileFileName().lastIndexOf("."));
				if(fileEx.equals(".jpg")){
					FileInputStream inputStream = new FileInputStream(this.file);
					String filePath = path+"/1.jpg";
					FileOutputStream outputStream = new FileOutputStream(filePath);
					byte[] buff = new byte[1024];
					int length = 0;
					while((length = inputStream.read(buff))!= -1){
						outputStream.write(buff,0,length);
					}
					inputStream.close();
					outputStream.flush();
					outputStream.close();
					
					message="恭喜您，图片上传成功!";
				}
			}
		} catch (Exception e) {
			message = e.toString();
		}
		
		out.print(message);
		out.flush();
		out.close();
	}
	
	private Setting set;
	public void setSet(Setting set) {
		this.set = set;
	}

	public Setting getSet() {
		return set;
	}
	
	public String titleInit() throws Exception{
		
		set = settingServ.getSetting();
		
		return SUCCESS;
	}
	
	public String title() throws Exception{
		
		if(set.getTitle().equals("")){
			message = "× 清填写网站标题！";
		}else{
			int flag = settingServ.update(set);
			if(flag > 0){
				message = "√ 恭喜您，标题更新成功!";
				returnURL = "system/setting.htm";
			}else{
				message = "× 很抱歉，标题更新失败!";
			}
		}
		
		return SUCCESS;
	}
	
	public String freeApproveInit() throws Exception{
		List<SysFreeApprove> list = sysFreeApproveServ.getAllFreeApproveList();
		request.setAttribute("list", list);
		
		String ids = "";
		if(list!=null && list.size()>0){
			for(int i = 0;i<list.size();i++){
				SysUser user = list.get(i).getUser();
				ids += user.getId()+",";
			}
			ids = ids.substring(0,ids.length()-1);
		}
		
		request.setAttribute("ids", ids);
		
		managerid = staticSettingServ.getStaticSettingByColumnName("MANAGER_ID").getColumnValue();
		archiveid = staticSettingServ.getStaticSettingByColumnName("ARCHIVE_ID").getColumnValue();
		//TODO 获取高层列表
		List<SysUser> highusers = sysUserServ.getSysUserByPost("0");
		request.setAttribute("highusers", highusers);
		//TODO 获取所有人列表
		if(archiveid !=null && !archiveid.equals("")){
			String deptid = sysUserServ.getSysUserById(archiveid).getDept().getId();
			List<SysUser> archives = sysUserServ.getSysUserByDeptId(deptid);
			request.setAttribute("archives", archives);
			request.setAttribute("deptid", deptid);
		}
		List<Department> depts = departmentServ.getList();
		request.setAttribute("depts", depts);
		request.setAttribute("archiveid", archiveid);
		request.setAttribute("managerid", managerid);
		
		return SUCCESS;
	}
	
	public String freeApprove() throws Exception{
		
		if(managerid == null || managerid.equals("")){
			message= "x 请选择审批副总！";
			return SUCCESS;
		}
		if(archiveid == null || archiveid.equals("")){
			message = "x 请选择归档人！";
			return SUCCESS;
		}
		
		StaticSetting managerset = staticSettingServ.getStaticSettingByColumnName("MANAGER_ID");
		managerset.setColumnValue(managerid);
		StaticSetting archiveset = staticSettingServ.getStaticSettingByColumnName("ARCHIVE_ID");
		archiveset.setColumnValue(archiveid);
		
		staticSettingServ.update(managerset);
		staticSettingServ.update(archiveset);
		
		
		sysFreeApproveServ.deleteAll();
		if(ids!=null && !ids.equals("")){
			String[] idss = ids.split(",");
			for(int i = 0;i<idss.length;i++){
				SysUser u = sysUserServ.getSysUserById(idss[i]);
				SysFreeApprove fa = new SysFreeApprove();
				fa.setUser(u);
				sysFreeApproveServ.insert(fa);
			}
		}

		
		message = "√ 恭喜您，自由审批设置成功!";
		returnURL = "setting/freeApproveInit.htm";
		
		return SUCCESS;
	}

	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
}
