<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
   <script type="text/javascript">
	   $(document).ready(function(){

	   });
   </script>
	<script type="text/javascript" src="${ctx}/js/fore/forepage.js"></script>
</head>
 <body style="overflow: hidden;height: 100%">

 <div class="kj">
	 <div class="kj2">
		 <div class="kj_left">
			 <div class="kj_bt">在线留言</div>
			 <div class="kj_l four"><a href="#" class="four">在线留言</a></div>
		 </div>
		 <div class="kj_right">
			 <div class="kj_dh">当前位置：首页 > 在线留言</div>
			 <div class="kj_wz">
				 <form  id="lyform" name="lyform" method="post">
				 <table width="95%" border="0" cellspacing="0" cellpadding="0">
					 <tr>
						 <td bgcolor="#E9E9E9">感谢您关注陕西航空精密合金有限公司，有问题请联系我们， <em>*</em>为必填项，填写完毕请提交留言，谢谢。</td>
					 </tr>
					 <tr>
						 <td align="left"><div class="big_lyxx"><div class="big_lyxx_wz"><em>*</em>您的姓名：</div><div class="big_lyxx_bd"><input type="text" id="name" name="name" class="big_xllb" /></div></div>
						 </td>
					 </tr>
					 <tr>
						 <td align="left"><div class="big_lyxx"><div class="big_lyxx_wz">联系方式：</div><div class="big_lyxx_bd"><input type="text" id="tel" name="tel" class="big_xllb" /></div></div></td>
					 </tr>
					 <tr>
						 <td align="left"><div class="big_lyxx2"><div class="big_lyxx_wz"><em>*</em>反馈内容：</div><div class="big_lyxx_bd2"><textarea id="context" name="context" class="xllb" style="height:70px; width:500px;"></textarea></div></div></td>
					 </tr>
					 <tr>
						 <td align="center"><div class="big_lyxx"><a href="javascript:void(0)"  onclick="lySubmit()" class="btn_all2 b_hong">提交留言</a>
						 </div>
						 </td>
					 </tr>
					 <tr>
						 <td align="left">&nbsp;</td>
					 </tr>
				 </table></form>

			 </div>
		 </div>

	 </div>

 </div>

</body>
</html>