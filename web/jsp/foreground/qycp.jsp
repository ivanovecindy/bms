<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<%
	 String id = request.getParameter("id");
	 String cid = request.getParameter("cid")!=null? request.getParameter("cid"):"";
	%>
	<meta http-equiv="pragma" content="no-cache">
	<script type="text/javascript" src="${ctx}/js/system/childModelDeal.js"></script>
   <script type="text/javascript">
	   $(document).ready(function(){
		   initCPDh('<%=id%>','<%=cid%>')
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
</head>
 <body style="overflow: auto;">

 <div class="akj">
	 <div class="akj2">
		 <div class="akj_left" id="cp_name">
		  </div>
		  <div class="akj_right">
			 <div class="kj_dh" id="dqwz">当前位置：首页 > 产品与服务 > 供应产品</div>
			 <div class="akj_wz">
		 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" id="cp_context"  >
          </table>
			 </div>
		 </div>


	 </div>

 </div>

</body>
</html>