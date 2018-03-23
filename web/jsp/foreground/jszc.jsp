<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<%
		String id = request.getParameter("id");
		String isEr = request.getParameter("isEr")!=null? request.getParameter("isEr"):"";//是否二级
	%>
	<meta http-equiv="pragma" content="no-cache">
   <script type="text/javascript">
	   $(document).ready(function(){
		   var isEr ='<%=isEr%>';
		   if(isEr!=''){
			   getErdh('<%=id%>');//从二级导航进入
		   }else{
			   getEjdh('<%=id%>');//得到页面的二级导行
		   }

		   var height = $(document.body).height()+50;
		   window.parent.changeWid(height);
		   $.ajaxSetup({
			   'complete': function () {
				   //修改iframe高度
				   var height = parseInt(document.body.scrollHeight);
				   window.parent.changeWid(height);
			   }
		   });
	   });
   </script>
	<script type="text/javascript" src="${ctx}/js/system/childModelDeal.js"></script>
</head>
<body style="overflow: auto;">

 <div class="kj">
	 <div class="kj2">
		 <div class="kj_left"  id="cp_name">

		 </div>
		 <div class="kj_right">
			 <div class="kj_dh" id="dqwz">当前位置：首页 > 技术支持与下载 > 技术支持</div>
			 <div class="kj_wz">
				 <table width="95%" border="0" cellspacing="0" cellpadding="0" id="cp_context"   >


				 </table>
			 </div>
		 </div>

	 </div>

 </div>
 </div>
</body>
</html>