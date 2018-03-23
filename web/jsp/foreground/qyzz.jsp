<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
   <script type="text/javascript">
	   $(document).ready(function(){
		   $(document).ready(function(){
			   window.parent.changeWid(700);
		   });
	   });
   </script>

	<script  type="text/javascript" src="${ctx}/js/fore/forepage.js"></script>
</head>
 <body style="overflow: hidden;height: 100%">

 <div class="kj">
	 <div class="kj2">
		 <div class="kj_left">
			 <div class="kj_bt">企业简介</div>
			 <div class="kj_h two"  onclick="getContext('jj')"><a href="#" class="two">企业简介</a></div>
			 <div class="kj_l four"  onclick="getContext('zz')"><a href="#" class="four">企业资质</a></div>
		 </div>
		 <div class="kj_right">
			 <div class="kj_dh">当前位置：首页 > 企业资质</div>
			 <div class="kj_wz">
				 <table width="95%" border="0" cellspacing="0" cellpadding="0">
					 <tr>
						 <td width="50%"><img src="${ctx}/images/zz1.jpg" width="378" height="247" /></td>
						 <td width="50%"><img src="${ctx}/images/zz2.jpg" width="378" height="247" /></td>
					 </tr>
					 <tr>
						 <td width="50%">&nbsp;</td>
						 <td width="50%">&nbsp;</td>
					 </tr>
					 <tr>
						 <td width="50%"><img src="${ctx}/images/zz3.jpg" width="378" height="247" /></td>
						 <td width="50%"><img src="${ctx}/images/zz4.jpg" width="378" height="247" /></td>
					 </tr>
					 <tr>
						 <td width="50%">&nbsp;</td>
						 <td width="50%">&nbsp;</td>
					 </tr>
				 </table>

			 </div>
		 </div>

	 </div>

 </div>
</body>
</html>