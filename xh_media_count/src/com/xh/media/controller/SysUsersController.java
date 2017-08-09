package com.xh.media.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.gson.Gson;
import com.xh.media.bean.AdminBean;
import com.xh.media.bean.DeptBean;
import com.xh.media.model.SysGroups;
import com.xh.media.model.SysUserGroup;
import com.xh.media.model.SysUsers;
import com.xh.media.service.SysGroupsService;
import com.xh.media.service.SysUserGroupService;
import com.xh.media.service.SysUsersService;

import wap.util.Util;

@Controller
public class SysUsersController extends AbstractController {
	private SysUsersService sysUsersService;
	private SysUserGroupService sysUserGroupService;
	private SysGroupsService sysGroupsService;
	private ApplicationContext context;
	
	public SysUsersController(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		sysUsersService = (SysUsersService)context.getBean("sysUsersService");
		sysGroupsService = (SysGroupsService)context.getBean("sysGroupsService");
		sysUserGroupService = (SysUserGroupService)context.getBean("sysUserGroupService");
	}

	@RequestMapping("/sysusers")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response){
		int actId = Integer.parseInt(request.getParameter("actId").toString());
		System.out.println("asdasdasdasdasd===sysusers");
		try{
			switch(actId){
				case 1://修改密码
					SysUsers user = (SysUsers)request.getSession().getAttribute("userInfo");
					String oldPass = request.getParameter("oldpass");
					String newPass = request.getParameter("newpass");
					System.out.println("oldpass=="+oldPass+"&newpass=="+newPass);
					System.out.println("userinfo==="+user.getUserAccount());
					if(user.getPassword().equals(Util.Md5(oldPass))){
						SysUsers nuser = new SysUsers();
						System.out.println(nuser.getIsadmin());
						nuser.setId(user.getId());
						nuser.setPassword(Util.Md5(newPass));
						nuser.setTruePassword(newPass);
						nuser.setIsadmin(user.getIsadmin());
						sysUsersService.updateByPrimaryKeySelective(nuser);
						response.getWriter().write("true");
						response.getWriter().flush();
						response.getWriter().close();
					}
					break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("sysuserlist")
	protected ModelAndView SysuserList(HttpServletRequest request,
			HttpServletResponse response){
		String resultStr= "";
		Gson gson = new Gson();
		try{
			SysUsers su = new SysUsers();
			su = (SysUsers)request.getSession().getAttribute("userInfo");
			List<String> deptIdList = new ArrayList<String>();
			List<DeptBean> deptList = new ArrayList<DeptBean>();
			List<AdminBean> adminList = new ArrayList<AdminBean>();
			List<SysUsers> userList = new ArrayList<SysUsers>();
			String sort = "";
			String order = "";
			if(request.getParameter("sort")!=null){
				sort = request.getParameter("sort");
			}else{
				sort = "id";
			}
			if(request.getParameter("order")!=null){
				order = request.getParameter("order");
			}else{
				order = "desc";
			}
			String[] soStr = sort.split(",");
			String[] orStr = order.split(",");
			List orderList=new ArrayList();
			for(int i=0;i<soStr.length;i++){
				if("userAccount".equals(soStr[i])){
					orderList.add("user_account "+orStr[i]);
				}else if("userName".equals(soStr[i])){
					orderList.add("CONVERT(user_name USING gbk) COLLATE gbk_chinese_ci "+orStr[i]);
				}else if ("userType".equals(soStr[i])) {
					orderList.add("dept "+orStr[i]);
				}else if ("admin".equals(soStr[i])) {
					orderList.add("isadmin "+orStr[i]);
				}else if ("createTime".equals(soStr[i])) {
					orderList.add("create_time "+orStr[i]);
				}else{
					orderList.add(soStr[i]+" "+orStr[i]);
				}
			}
			SysUsers ou = new SysUsers();
			ou.setOrderList(orderList);
			if("-1".equals(su.getDept())){
				ou.setDept(su.getDept());
				userList = sysUsersService.getAllSysUsersList(ou);
//				AdminBean sa1 = new AdminBean();
//				sa1.setAdminId(-1);
//				sa1.setAdminName("超级管理员");
//				adminList.add(sa1);
//				AdminBean sa2 = new AdminBean();
//				sa2.setAdminId(0);
//				sa2.setAdminName("系统管理员");
//				adminList.add(sa2);
//				AdminBean sa3 = new AdminBean();
//				sa3.setAdminId(1);
//				sa3.setAdminName("站点管理员");
//				adminList.add(sa3);
			}else if("0".equals(su.getDept())){
				ou.setDept(su.getDept());
				userList = sysUsersService.getAllSysUsersList(ou);
			}else{
				ou.setDept(su.getDept());
				userList = sysUsersService.getSysUsersList(ou);
			}
			deptIdList = sysUsersService.getDeptList(Integer.parseInt(su.getDept()));
			for(String deptId:deptIdList){
				DeptBean db = new DeptBean();
				db.setDeptId(deptId);
				db.setDeptName(deptId);
				deptList.add(db);
			}
			for(SysUsers user:userList){
				if(user.getIsadmin()==1)
					user.setAdmin("是");
				else
					user.setAdmin("否");
			}
			for(SysUsers user:userList){
				if(user.getIsadmin()==1){
					if("-1".equals(user.getDept()))
						user.setUserType("超级管理员");
					else if("0".equals(user.getDept()))
						user.setUserType("系统管理员");
					else
						user.setUserType("站点管理员");
				}else{
					if("-1".equals(user.getDept()))
						user.setUserType("超级用户");
					else if("0".equals(user.getDept()))
						user.setUserType("系统用户");
					else
						user.setUserType("站点用户");
				}
			}
			
			System.out.println("userList.size()===="+userList.size());
			
			Map map = new HashMap();
			map.put("total", userList.size());
			map.put("rows", userList);
			resultStr = gson.toJson(map);
			request.getSession().setAttribute("deptList", deptList);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("getdeptlist")
	protected ModelAndView getSysUserDept(HttpServletRequest request,
			HttpServletResponse response){
		String resultStr= "";
		Gson gson = new Gson();
		try{
			SysUsers su = new SysUsers();
			su = (SysUsers)request.getSession().getAttribute("userInfo");
			List<SysGroups> deptList = new ArrayList<SysGroups>();
			if("-1".equals(su.getDept()) || "0".equals(su.getDept())){
				deptList = sysGroupsService.findGroupsByDept(Integer.parseInt(su.getDept()));
			}else{
				deptList.add(sysGroupsService.selectByPrimaryKey(Integer.parseInt(su.getDept())));
			}
			String resultStr1 = gson.toJson(deptList);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(resultStr1);
			response.getWriter().flush();
			response.getWriter().close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("sysuserinsert")
	protected ModelAndView SysuserInert(HttpServletRequest request,
			HttpServletResponse response){
		String resultStr= "";
		Gson gson = new Gson();
		try{
			String userAccount = "";
			String userName = "";
			String password = "";
			String desc = "";
			String isAdmin = "";
			String dept = "";
			if(request.getParameter("userAccount") != null)
				userAccount = request.getParameter("userAccount");
			if(request.getParameter("userName") != null)
				userName = request.getParameter("userName");
			if(request.getParameter("password") != null)
				password = request.getParameter("password");
			if(request.getParameter("desc") != null)
				desc = request.getParameter("desc");
			if(request.getParameter("isAdmin") != null)
				isAdmin = request.getParameter("isAdmin");
			if(request.getParameter("dept") != null)
				dept = request.getParameter("dept");
			int count = sysUsersService.countSysUserByAccountDeptAndStatus(userAccount, dept);
			if(count == 0){
				SysUsers su = new SysUsers();
				su = (SysUsers)request.getSession().getAttribute("userInfo");
				SysUsers newSu = new SysUsers();
				newSu.setUserAccount(userAccount);
				newSu.setUserName(userName);
				newSu.setPassword(Util.Md5(password));
				newSu.setTruePassword(password);
				newSu.setDesc(desc);
				newSu.setStatus((short)0);
				newSu.setDept(dept);
				newSu.setDuty(1+"");
				if((Integer.parseInt(dept))>0)
					newSu.setSubSystem("站点用户");
				else
					newSu.setSubSystem(sysGroupsService.selectByPrimaryKey(Integer.parseInt(dept)).getName());
				System.out.println("isAdmin="+isAdmin+"==Boolean.parseBoolean(isAdmin)=="+Boolean.parseBoolean(isAdmin));
				newSu.setIsadmin((short)Integer.parseInt(isAdmin));
				int result = sysUsersService.insertSelective(newSu);
				if(Integer.parseInt(newSu.getDept())>0 && result == 1){
					SysUserGroup sug = new SysUserGroup();
					int userId = sysUsersService.getUserIdByAccountDeptAndStatus(newSu.getUserAccount(), newSu.getDept());
					sug.setUserId(userId);
					sug.setGroupId(Integer.parseInt(newSu.getDept()));
					sysUserGroupService.insertSelective(sug);
				}
				response.getWriter().write("true");
			}else{
				response.getWriter().write("repeat");
			}
			response.getWriter().flush();
			response.getWriter().close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("sysuserupdate")
	protected ModelAndView SysuserUpdate(HttpServletRequest request,
			HttpServletResponse response){
		String resultStr= "";
		Gson gson = new Gson();
		try{
			String userId = "";
			String userAccount = "";
			String userName = "";
			String password = "";
			String desc = "";
			String isAdmin = "";
			String dept = "";
			if(request.getParameter("userId-modify") != null)
				userId = request.getParameter("userId-modify");
			if(request.getParameter("userAccount-modify") != null)
				userAccount = request.getParameter("userAccount-modify");
			if(request.getParameter("userName-modify") != null)
				userName = request.getParameter("userName-modify");
			if(request.getParameter("password-modify") != null)
				password = request.getParameter("password-modify");
			if(request.getParameter("desc-modify") != null)
				desc = request.getParameter("desc-modify");
			if(request.getParameter("isAdmin-modify") != null)
				isAdmin = request.getParameter("isAdmin-modify");
			if(request.getParameter("dept-modify") != null)
				dept = request.getParameter("dept-modify");
			System.out.println("userId_modify="+userId);
			System.out.println("userAccount="+userAccount);
			System.out.println("userName="+userName);
			System.out.println("password="+password);
			System.out.println("desc="+desc);
			System.out.println("isAdmin="+isAdmin);
			System.out.println("dept="+dept);
			SysUsers user = new SysUsers();
			user = sysUsersService.selectByPrimaryKey(Integer.parseInt(userId));//取得修改用户修改前的数据
			int count = sysUsersService.countSysUserByAccountDeptAndStatus(userAccount, dept);
			int sysUserId = 0;
			if(count>0)
				sysUserId = sysUsersService.getUserIdByAccountDeptAndStatus(userAccount, dept);
			if(count>0){//代表修改的名字在同一站点下不重复
				if(sysUserId == Integer.parseInt(userId)){//修改了除了名字和站点外的其他内容
					SysUsers su = new SysUsers();
					su = (SysUsers)request.getSession().getAttribute("userInfo");
					SysUsers newSu = new SysUsers();
					newSu.setId(Integer.parseInt(userId));
					newSu.setUserAccount(userAccount);
					newSu.setUserName(userName);
					newSu.setPassword(Util.Md5(password));
					newSu.setTruePassword(password);
					newSu.setDesc(desc);
					newSu.setDept(dept);
					if((Integer.parseInt(dept))>0)
						newSu.setSubSystem("站点用户");
					else
						newSu.setSubSystem(sysGroupsService.selectByPrimaryKey(Integer.parseInt(dept)).getName());
					System.out.println("isAdmin="+isAdmin+"==Boolean.parseBoolean(isAdmin)=="+Boolean.parseBoolean(isAdmin));
					newSu.setIsadmin((short)Integer.parseInt(isAdmin));
					int result = sysUsersService.updateByPrimaryKeySelective(newSu);
					if(Integer.parseInt(newSu.getDept())>0 && result == 1){
						SysUserGroup sug = new SysUserGroup();
						int id = sysUserGroupService.getIdByUserIdGroupId(Integer.parseInt(userId), Integer.parseInt(user.getDept()));
						sug.setId(id);
						sug.setUserId(Integer.parseInt(userId));
						sug.setGroupId(Integer.parseInt(newSu.getDept()));
						sysUserGroupService.updateByPrimaryKeySelective(sug);
					}
					response.getWriter().write("true");
				}else{
					response.getWriter().write("repeat");
				}
			}else{
				SysUsers su = new SysUsers();
				su = (SysUsers)request.getSession().getAttribute("userInfo");
				SysUsers newSu = new SysUsers();
				newSu.setId(Integer.parseInt(userId));
				newSu.setUserAccount(userAccount);
				newSu.setUserName(userName);
				newSu.setPassword(Util.Md5(password));
				newSu.setTruePassword(password);
				newSu.setDesc(desc);
				newSu.setDept(dept);
				if((Integer.parseInt(dept))>0)
					newSu.setSubSystem("站点用户");
				else
					newSu.setSubSystem(sysGroupsService.selectByPrimaryKey(Integer.parseInt(dept)).getName());
				System.out.println("isAdmin="+isAdmin+"==Boolean.parseBoolean(isAdmin)=="+Boolean.parseBoolean(isAdmin));
				newSu.setIsadmin((short)Integer.parseInt(isAdmin));
				int result = sysUsersService.updateByPrimaryKeySelective(newSu);
				if(Integer.parseInt(newSu.getDept())>0 && result == 1){
					SysUserGroup sug = new SysUserGroup();
					int id = sysUserGroupService.getIdByUserIdGroupId(Integer.parseInt(userId), Integer.parseInt(user.getDept()));
					sug.setId(id);
					sug.setUserId(Integer.parseInt(userId));
					sug.setGroupId(Integer.parseInt(newSu.getDept()));
					sysUserGroupService.updateByPrimaryKeySelective(sug);
				}
				response.getWriter().write("true");
			}
			response.getWriter().flush();
			response.getWriter().close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("sysuserdelete")
	protected ModelAndView SysuserDelete(HttpServletRequest request,
			HttpServletResponse response){
		String resultStr= "";
		Gson gson = new Gson();
		try{
			String ids = "";
			if(request.getParameter("ids") != null)
				ids = request.getParameter("ids");
			String[] id = ids.split(",");
			for(int i=0;i<id.length;i++){
				if(!"".equals(id[i].trim())){
					SysUsers su = new SysUsers();
					su.setId(Integer.parseInt(id[i]));
					su.setStatus((short)1);
					sysUsersService.updateByPrimaryKeySelective(su);
				}
			}
			response.getWriter().write("true");
			response.getWriter().flush();
			response.getWriter().close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	

}
