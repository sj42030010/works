package com.xh.media.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import wap.util.Util;

import com.google.gson.Gson;
import com.xh.media.bean.MenuBean;
import com.xh.media.model.SysGroupOrigin;
import com.xh.media.model.SysGroups;
import com.xh.media.model.SysMenuList;
import com.xh.media.model.SysUsers;
import com.xh.media.service.SysGroupOriginService;
import com.xh.media.service.SysGroupsService;
import com.xh.media.service.SysMenuListService;
import com.xh.media.service.SysUsersService;
@Controller
public class LoginController extends AbstractController {
	private SysUsersService sysUsersService;
	private SysMenuListService sysMenuListService;
	private SysGroupsService sysGroupsService;
	private SysGroupOriginService sysGroupOriginService;
	private ApplicationContext context;
	private Gson gson;
	
	public LoginController(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		sysUsersService = (SysUsersService)context.getBean("sysUsersService");
		sysMenuListService = (SysMenuListService)context.getBean("sysMenuListService");
		sysGroupsService = (SysGroupsService)context.getBean("sysGroupsService");
		sysGroupOriginService = (SysGroupOriginService)context.getBean("sysGroupOriginService");
		gson = new Gson();
	}
	
	@RequestMapping("/login")
	public ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp){  
//	     //1、收集参数、验证参数  
//	     //2、绑定参数到命令对象  
//	     //3、将命令对象传入业务对象进行业务处理  
//	     //4、选择下一个页面 
		 SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	     ModelAndView mv = new ModelAndView();
	     String username = req.getParameter("username");
	     String password = req.getParameter("password");
	     System.out.println("username===="+username+";;;;password===="+password);
	     SysUsers user = sysUsersService.getUserByUseraccount(username); 
	     if(user == null || !user.getPassword().equals(Util.Md5(password))){
	    	 System.out.println("用户名或密码错误");
	    	 mv.setViewName("login");  
		     return mv;
	     }else{
	    	 //验证登录
	    	 System.out.println("登录成功");
//	    	 List<String> query = sysUsersService.getUserCanVisitUrl(user.getUserAccount());
	    	 req.getSession().setAttribute("userInfo", user);
	 		 req.getSession().setAttribute("username", user.getUserName());
	 		 List<SysGroups> groupList = new ArrayList<SysGroups>();
	 		 List<SysGroupOrigin> originList = new ArrayList<SysGroupOrigin>();
	 		if(Integer.parseInt(user.getDept())>0)
	 			 groupList = sysGroupsService.findGroupsByUserId(Integer.parseInt(user.getDept()));
	 		 else
	 			groupList = sysGroupsService.findAllGroups();
	 		if(groupList.size()>0)
	 		{
	 			for(SysGroups sg:groupList)
	 			{
	 				List<SysGroupOrigin> oriList = new ArrayList<SysGroupOrigin>();
	 				oriList = sysGroupOriginService.findOriginListBySiteCode(String.valueOf(sg.getId()));
	 				if(oriList.size()>0)
	 				{
	 					for(SysGroupOrigin sgo:oriList)
	 					{
	 						originList.add(sgo);
	 					}
	 				}
	 			}
	 		}
	 		 System.out.println("groupList size===="+groupList.size());
	 		 req.getSession().setAttribute("groups", groupList);
	 		 req.getSession().setAttribute("origins", originList);
//	 		 req.getSession().setAttribute("userAuthority", query);
	 		 
	 		 //读取左侧菜单信息
	 		List<SysMenuList> menuList = new ArrayList<SysMenuList>();
	 		 if(user.getIsadmin()==1)
	 			 if("-1".equals(user.getDept()))
	 				 menuList = sysMenuListService.getAllMenuList("-1");
	 			 else
	 				menuList = sysMenuListService.getAllMenuList("0");
	 		 else
	 			menuList = sysMenuListService.getMenuList();
	 		 System.out.println("menuList==========="+menuList.size());
	 		 //System.out.println("database menulist data size is::::::::::"+menuList.size());
	 		 List<MenuBean> mbl = new ArrayList<MenuBean>();
	 		 mbl = getMenuInfo(0,menuList);
	 		 req.getSession().setAttribute("menu", mbl);
	 		 mv.setViewName("index");
		     return mv;
	     }
	}
	
	//递归生成菜单对象
	private List<MenuBean> getMenuInfo(int cid, List<SysMenuList> mll){
		List<MenuBean> mbl = new ArrayList<MenuBean>(); 
		for(SysMenuList ml:mll){
			MenuBean mb = new MenuBean();
			int parentId = ml.getParentId();
			if(cid == parentId){
				if(cid==0){
					mb.setUrl("javascript:void(0);");
				}else{
					mb.setUrl(ml.getUrl());
				}
				mb.setIcon(ml.getIcon());
				mb.setMenuid(ml.getMenuId()+"");
				mb.setMenuname(ml.getMenuName());
				mb.setModelId(ml.getModelId());
				mbl.add(mb);
			}
		}
		for(MenuBean mb:mbl){
			List<MenuBean> mbl1 = getMenuInfo(Integer.parseInt(mb.getMenuid()), mll);
			if(mbl1.size() == 0){
				if(cid == 0){
					mb.setMenus(mbl1);
				}
			}else{
				mb.setMenus(mbl1);
			}
		}
		return mbl;
	}
}
