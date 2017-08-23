package com.xh.media.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.xh.media.model.SysGroups;
import com.xh.media.model.SysUserGroup;
import com.xh.media.model.SysUsers;
import com.xh.media.service.SysGroupsService;
import com.xh.media.service.SysUserGroupService;
import com.xh.media.service.SysUsersService;

@Controller
public class SysGroupsController {
	
	private ApplicationContext context;
	private Gson gson;
	private SysGroupsService sysGroupsService;
	private SysUserGroupService sysUserGroupService;
	private SysUsersService sysUsersService;
	public SysGroupsController() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		gson=new Gson();
		sysGroupsService=(SysGroupsService) context.getBean("sysGroupsService");
		sysUserGroupService = (SysUserGroupService)context.getBean("sysUserGroupService");
		sysUsersService=(SysUsersService) context.getBean("sysUsersService");
	}
	@RequestMapping("/sysGroupslist")
	protected ModelAndView showAllGroups(HttpServletRequest request,HttpServletResponse response){
		Map map=new HashMap();
		List<SysGroups> findAllGroups = sysGroupsService.findAllGroups();
		map.put("total", findAllGroups.size());
		map.put("rows", findAllGroups);
		String resultStr = gson.toJson(map);
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(resultStr);
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/sysGroupsinsert")
	protected ModelAndView insertGroups(HttpServletRequest request,HttpServletResponse response){
		   SysGroups sysGroups=new SysGroups();
		   int ID=-1;
		   if (request.getParameter("userAccount")!=null) {
		  	  ID=Integer.parseInt(request.getParameter("userAccount"));
		  	  sysGroups.setId(ID);
		    }
		   String Name="";
		   if (request.getParameter("userName")!=null) {
			   Name=request.getParameter("userName");
			   sysGroups.setName(Name);
		    }
		   List<SysGroups> findGroupsByUserId = sysGroupsService.findGroupsByUserId(ID);
		   List<SysGroups> findGroupsByName = sysGroupsService.findGroupsByName(sysGroups);
		 try {
		   if (findGroupsByUserId.size()!=0||findGroupsByName.size()!=0) {
				response.getWriter().write("repeat");
			   }else{
				response.getWriter().write("true");
				sysGroups.setCreateDate(new Date());
				sysGroups.setOrderNumber(0);
				sysGroups.setParent(1);
				sysGroups.setStatus((byte) 1);
				sysGroupsService.insert(sysGroups);
			   }
			    response.getWriter().flush();
				response.getWriter().close();
			 } catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}

	@RequestMapping("/sysGroupsupdate")
	protected ModelAndView updateGroups(HttpServletRequest request,HttpServletResponse response){
		SysGroups sysGroups=new SysGroups();
		   int ID=-1;
		   if (request.getParameter("ID-modify")!=null) {
		  	  ID=Integer.parseInt(request.getParameter("ID-modify"));
		  	  sysGroups.setId(ID);
		    }
		   int oldID=-2;
		   if (request.getParameter("oldID-modify")!=null) {
			   oldID=Integer.parseInt(request.getParameter("oldID-modify"));
			   sysGroups.setOldId(oldID);
			 }
		   String Name="";
		   if (request.getParameter("Name-modify")!=null) {
			   Name=request.getParameter("Name-modify");
			   sysGroups.setName(Name);
		    }
		   List<SysGroups> findGroupsByUserId = sysGroupsService.findGroupsByUserId(ID);
		   List<SysGroups> findGroupsByName = sysGroupsService.findGroupsByName(sysGroups);
		  try {
		   if (findGroupsByUserId.size()!=0&&findGroupsByName.size()!=0) {
					response.getWriter().write("repeat");
				 }else if(findGroupsByUserId.size()!=0&&findGroupsByName.size()==0){//id 相同名称不同
					 int lines = sysGroupsService.updateById(sysGroups);
					 if (lines>0) {
						 response.getWriter().write("true");
					 }
				 }else if(findGroupsByUserId.size()==0&&findGroupsByName.size()!=0) {//id 不同名称相同
						List<SysUserGroup> sysUserGroup = sysUserGroupService.getSysUserGroupByGroupId(oldID);
						for (SysUserGroup sysUserGroup2 : sysUserGroup) {
							sysUserGroup2.setGroupId(ID);
							sysUserGroupService.updateGroupIdById(sysUserGroup2);
						}
						List<Integer> idByDept = sysUsersService.getIdByDept(Integer.toString(oldID));
						
						for (int i = 0; i < idByDept.size(); i++) {
							SysUsers users=new SysUsers();
							users.setId(idByDept.get(i));
							users.setDept(Integer.toString(ID));
							int updateDeptById = sysUsersService.updateDeptById(users);
						}
					 int lines = sysGroupsService.updateByName(sysGroups);
					 if (lines>0) {
						 response.getWriter().write("true");
					 }
				 }else{
						List<SysUserGroup> sysUserGroup = sysUserGroupService.getSysUserGroupByGroupId(oldID);
						for (SysUserGroup sysUserGroup2 : sysUserGroup) {
							sysUserGroup2.setGroupId(ID);
							sysUserGroupService.updateGroupIdById(sysUserGroup2);
						}
						List<Integer> idByDept = sysUsersService.getIdByDept(Integer.toString(oldID));
						for (Integer integer : idByDept) {
							SysUsers users=new SysUsers();
							users.setId(integer);
							users.setDept(Integer.toString(ID));
							sysUsersService.updateDeptById(users);
						}
					 int lines = sysGroupsService.updateByOldId(sysGroups);
					 if (lines>0) {
						 response.getWriter().write("true");
					 }
				 }
		    response.getWriter().flush();
			response.getWriter().close();
			} catch (IOException e) {
					e.printStackTrace();
		}
			  
		return null;
	}
}
