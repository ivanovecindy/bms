<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%String id = request.getParameter("id"); 
   String flag = request.getParameter("flag");
   %>
 <%@ include file="/common/meta.jsp"%>
      <script type="text/javascript">
          var editor;

      </script>
<script type="text/javascript" src="${ctx}/js/system/message.js"></script>
</head>
<body style="overflow:auto;">
<form  id="lyform" name="lyform" method="post">
    <table class="list_table" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td align="right" nowrap="nowrap" width="14%">您的姓名：</td><td nowrap="nowrap">${message.name}
            </td>
        </tr>
        <tr>
            <td align="right" width="14%">联系方式：</td><td nowrap="nowrap">${message.tel}</td>
        </tr>
        <tr>
            <td align="right" width="14%">反馈内容：</td><td nowrap="nowrap">${message.context}</td>
        </tr>
        <tr>
            <td align="center" colspan="2"><a href="javascript:void(0)"  onclick="quexiao()" class="btn_all2 b_hong">关闭</a>
            </td>
        </tr>
    </table></form>
      
</body>

</html>
