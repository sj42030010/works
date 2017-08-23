<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page import="com.xh.media.bean.MenuBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
<title>融合媒体统计平台</title>
<link rel="stylesheet" type="text/css" href="easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="css/wu.css" />
<link rel="stylesheet" type="text/css" href="css/icon.css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/DateFormat.js"></script>
<meta charset="utf-8"/>
<!-- 引入 ECharts 文件 -->
<script type="text/javascript" src="js/dist/echarts.js"></script>
<script type="text/javascript" src="js/dist/echarts-all.js"></script>
</head>
<body class="easyui-layout">
	<!-- begin of header -->
	<div class="wu-header" data-options="region:'north',border:false,split:true">
    	<div class="wu-header-left">
        	<h1>数据统计平台</h1>
        </div>
        <div class="wu-header-right">
        	<p><strong class="easyui-tooltip">${sessionScope.username}</strong>，欢迎您！</p>
            <p><a href="#" onclick="openChangePass()">修改密码</a>|<a href="#" onclick="logout()">安全退出</a> </p>
        </div>
    </div>
    <!-- end of header -->
    <!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'"> 
    	<div class="easyui-accordion" data-options="border:false,fit:true">
    	<c:forEach items="${menu}" var="me">
    		<div title="${me.menuname}" data-options="iconCls:'${me.icon}'" style="padding:5px;">
    			<c:if test="${fn:length(me.menus) > 0}">
    				<ul class="easyui-tree wu-side-tree">
    				<c:forEach items="${me.menus}" var="subme">
    					<li iconCls="${subme.icon}" ><a href="javascript:void(0)" data-icon="${subme.icon}" data-link="temp/layout-${subme.modelId}.html" iframe="0">${subme.menuname}</a></li>
    				</c:forEach>
    				</ul>
    			</c:if>
    		</div>
		</c:forEach> 
        </div>
    </div>	
    <!-- end of sidebar --> 
    <!-- Begin of easyui-dialog -->
<div id="wu-dialog-changepass" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
	<form id="wu-form-changepass" method="post">
        <table>
            <tr>
                <td width="60" align="right">原密码:</td>
                <td><input type="password" name="oldpass" id="oldpass" class="wu-password" /></td>
            </tr>
            <tr>
                <td align="right">新密码:</td>
                <td><input type="password" name="newpass" id="newpass" class="wu-password" /></td>
            </tr>
            <tr>
                <td align="right">确认密码:</td>
                <td><input type="password" name="repass" id="repass" class="wu-password" /></td>
            </tr>
        </table>
    </form>
</div>
<!-- End of easyui-dialog -->   
    <!-- begin of main -->
    <div class="wu-main" data-options="region:'center'">
        <div id="wu-tabs" class="easyui-tabs" data-options="border:false,fit:true">  
           <!--  <div title="首页" data-options="href:'temp/layout-1.html',closable:true,iconCls:'icon-tip',cls:'pd3'"></div> -->
        </div>
    </div>
    <!-- end of main --> 
    <!-- begin of footer -->
	<div class="wu-footer" data-options="region:'south',border:true,split:true">
    	
    </div>
    <!-- end of footer -->  
    <script type="text/javascript">
		/* $(function(){
			$('.wu-side-tree a').bind("click",function(){
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
		}) */
		$(function(){
			$('.wu-side-tree li').bind("click",function(){
				var title = $(this).find("a").text();
				var url = $(this).find("a").attr('data-link');
				var iconCls = $(this).find("a").attr('data-icon');
				var iframe = $(this).find("a").attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
		})
		
		/**
		* Name 载入树形菜单 
		*/
		$('#wu-side-tree').tree({
			url:'temp/menu.txt',
			cache:false,
			onClick:function(node){
				var url = node.attributes['url'];
				if(url==null || url == ""){
					return false;
				}
				else{
					addTab(node.text, url, '', node.attributes['iframe']);
				}
			}
		});
		
		/**
		* Name 选项卡初始化
		*/
		$('#wu-tabs').tabs({
			tools:[{
				iconCls:'icon-reload',
				border:false,
				handler:function(){
					$('#wu-datagrid-1').datagrid('reload');
					$('#wu-datagrid-2').datagrid('reload');
					$('#wu-datagrid-3').datagrid('reload');
					$('#wu-datagrid-4').datagrid('reload');
					$('#wu-datagrid-5').datagrid('reload');
					$('#wu-datagrid-6').datagrid('reload');
				}
			}]
		});
			
		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
	       //alert(title+"::"+href+":::"+iconCls+":::"+iframe);
			var tabPanel = $('#wu-tabs');
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
				
				$(".tabs li").each(function (index, obj) {
			      //获取所有可关闭的选项卡
			        var tab = $(".tabs-closable", this).text();
			        $(".easyui-tabs").tabs('close', tab);
		           });
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
					 tabPanel.tabs('getSelected').css('width', 'auto'); 
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
					//tabPanel.tabs('select', subtitle);
				}
			}
			else
			{
				tabPanel.tabs('select',title);
			}
		}
		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#wu-tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
		
	/**
	* Name 修改密码
	*/
	function changePass(){
		var $oldpass = $('#oldpass');
        var $newpass = $('#newpass');
        var $rePass = $('#repass');
            //alert('111111='+$oldpass.val()+'=111111');
            //alert('222222='+$newpass.val()+'=222222');
            //alert('333333='+$rePass.val()+'=333333');
            if ($oldpass.val() == '') {
                $.messager.alert('系统提示', '请输入原密码！', 'warning');
                return false;
            }
            if ($newpass.val() == '') {
                $.messager.alert('系统提示', '请输入新密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                $.messager.alert('系统提示', '请再一次输入密码！', 'warning');
                return false;
            }
            if ($newpass.val() != $rePass.val()) {
                $.messager.alert('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }
		$('#wu-form-changepass').form('submit', {
			url:'sysusers?actId=1&oldpass='+ $oldpass.val() +'&newpass=' + $newpass.val(),
			success:function(data){
				//alert('asdasd=='+data+'==aasdasd');
				if(data){
					$.messager.confirm('信息提示','修改密码成功！请重新登录',function(r){
						if(r)
							logout();
					});
					$('#wu-dialog-changepass').dialog('close');
					//logout();
				}
				else
				{
					$.messager.alert('信息提示','修改密码失败！','info');
				}
			}
		});
	}	
	
	/**
	* Name 打开修改密码窗口
	*/
	function openChangePass(){
		//$('#wu-form-changepass').show();
		$('#wu-form-changepass').form('clear');
		$('#wu-dialog-changepass').dialog({
			closed: false,
			modal:true,
            title: "修改密码",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: changePass
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#wu-dialog-changepass').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改密码窗口
	*/
	function logout(){
		location.href='logout';
	}
	</script>
</body>
</html>