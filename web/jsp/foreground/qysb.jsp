<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
   <script type="text/javascript">
	   $(document).ready(function(){
		   var height = parseInt(document.body.scrollHeight);
		   window.parent.changeWid(height);
	   });
   </script>
	<script  type="text/javascript" src="${ctx}/js/fore/forepage.js"></script>
</head>
 <body style="overflow: auto;">

 <div class="akj">
	 <div class="akj2">
		 <div class="akj_left">
			 <div class="akj_left">
				 <div class="kj_bt">产品与服务</div>
				 <div class="kj_h two"><a href="javascript:void(0)" class="two" onclick="getCpContext('供应产品','/jsp/foreground/qycp.jsp')">供应产品</a></div>
				 <div class="kj_l four"><a href="javascript:void(0)" class="four" >公司设备</a></div>
				 <div class="kj_h two"><a href="javascript:void(0)" class="two" onclick="getCpContext('协作服务','/web/getGenty.action')">协作服务</a></div>
			 </div>
		 </div>
		 <div class="akj_right">
			 <div class="kj_dh">当前位置：首页 > 产品与服务 > 公司设备</div>
			 <div class="akj_wz">
				 <table width="95%" border="0" cellspacing="0" cellpadding="0">
					 <tr>
						 <td>&nbsp;</td>
					 </tr>
					 <tr>
						 <td align="center"><img src="${ctx}/images/big_sb1.jpg" width="585" height="380" /></td>
					 </tr>
					 <tr>
						 <td align="center"><p align="center">四辊轧机 </p></td>
					 </tr>
					 <tr>
						 <td align="center"><img src="${ctx}/images/big_sb2.jpg" width="585" height="380" /></td>
					 </tr>
					 <tr>
						 <td align="center">可逆轧机</td>
					 </tr>
					 <tr>
						 <td align="center"><img src="${ctx}/images/big_sb3.jpg" width="585" height="380" /></td>
					 </tr>
					 <tr>
						 <td align="center">热轧机</td>
					 </tr>
					 <tr>
						 <td align="center"><img src="${ctx}/images/big_sb4.jpg" width="585" height="380" /></td>
					 </tr>
					 <tr>
						 <td align="center">中频真空冶炼炉</td>
					 </tr>
					 <tr>
						 <td align="center"><img src="${ctx}/images/big_sb5.jpg" width="585" height="380" /></td>
					 </tr>
					 <tr>
						 <td align="center">精密磨床</td>
					 </tr>
				 </table>

			 </div>
		 </div>


	 </div>

 </div>

 </div>
</body>
</html>