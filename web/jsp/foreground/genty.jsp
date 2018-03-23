<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
   <script type="text/javascript">
	   $(document).ready(function(){
		   var height = $(document.body).height();
		   window.parent.changeWid(height);
	   });

   </script>
	<script  type="text/javascript" src="${ctx}/js/fore/forepage.js"></script>
</head>
 <body style="overflow: hidden;height: 100%">

 <div class="akj">
	 <div class="akj2">
		 <div class="akj_left">
			 <div class="kj_bt">产品与服务</div>
			 <div class="kj_h two"><a href="javascript:void(0)" class="two" onclick="getCpContext('供应产品','/jsp/foreground/qycp.jsp')">供应产品</a></div>
			 <div class="kj_h two"><a href="javascript:void(0)" class="tow" onclick="getCpContext('公司设备','/jsp/foreground/qysb.jsp')">公司设备</a></div>
			 <div class="kj_l four"><a href="javascript:void(0)" class="four">协作服务</a></div>
		 </div>
		 <div class="akj_right">
			 <div class="kj_dh">当前位置：首页 > 产品与服务 > 协作服务</div>
			 <div class="akj_wz" style="padding-top: 30px;">
				 <table width="95%" border="0" cellspacing="0" cellpadding="0" >
					 <c:if test="${not empty childModelDeal}">
					 ${childModelDeal.ms}
					 </c:if>
				 </table>

			 </div>
		 </div>


	 </div>

 </div>

 </div>
 </div>
</body>
</html>