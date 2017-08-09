<%@ page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
  <title>CMS系统</title>
<script language="javascript">
  function f_submit(form){
	//alert(form.task.value+";;;;"+form.password.value);
    if (form.username.value==""){
      alert("对不起，请输入用户名！");
      form.username.focus();
      return false;
    }
    if (form.password.value==""){
      alert("对不起，请输入密码！");
      form.password.focus();
      return false;
    }
    /*
    if (form.j_captcha_response.value==""){
      alert("对不起，请输入验证码！");
      form.j_captcha_response.focus();
      return false;
    }
    */
    return true;
  }
  </script>
  <style>
    font{
    FONT-SIZE: 9pt;
    }
    td{
    FONT-SIZE: 9pt;
    }
    .btn
    {
    background-color:#8097B7;
    color:#ffffff;
    }
  </style>
  </head>
  <body onload="form1.username.focus();" leftmargin="0" topmargin="0">
  <form action="login" id="form1" method="post" onsubmit="return(f_submit(this))">
    <table width="100%" height="259" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="top">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="292" height="66">
                <!--<img src="images/index_r1_c1.gif" width="292" height="66">-->
              </td>
            </tr>
          </table>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="45%" valign="top" background="images/index_r2_c1.gif">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="0%" height="65">&nbsp;</td>
                    <td width="38%">&nbsp;</td>
                    <td width="62%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="85">&nbsp;</td>
                    <td valign="top" colspan="2">
                      <div align="center">
                        <!-- <img src="images/index_r3_c3.gif" width="270" height="75"> -->
                      </div>
                    </td>
                    
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td valign="top">
                      <div align="center">
                        <img src="images/index_r5_c4.gif" width="57" height="27">
                      </div>
                    </td>
                    <td>
                      <table width="100%" border="0" cellspacing="0" cellpadding="3">
                        <tr>
                        	<td align="right">
                            	<font color="#FFFFFF">用户名：</font>
                          	</td>
                          	<td>
                            	<input type="text" name="username" size="15"/>
                        	</td>
                        </tr>
                        <tr>
                        	<td align="right">
                            	<font color="#FFFFFF">密码：</font>
                          	</td>
                          	<td>
                          		<input type="password" name="password" size="15"/>
                        	</td>
                        </tr>
                        <!--
                        <tr>
                          <td align="right">
                            <font color="#FFFFFF">验证码：</font>
                          </td>
                          <td>
                            <input type='text' name='j_captcha_response' value='' size="4">
                            <img src="jcaptcha">
                          </td>
                        </tr>
                        -->
                        <tr>
                          <td colspan="2" height=10>                          </td>
                        </tr>
                        <tr>
                          <td align="center" colspan="2">
                            <input type="submit" value="登录" />
                            <input type="reset" value="重输" />
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
              <td width="10%" background="images/index_r2_c1.gif">&nbsp;</td>
              <td width="45%" background="images/index_r2_c1.gif">
                <!-- <img src="images/logo2.gif" height="100"> -->
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <input type="hidden" name="task" id="task" value="logon"/>
  </form>
  </body>
</html>

