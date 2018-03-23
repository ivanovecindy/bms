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
      <script type="text/javascript"charset="utf-8" src="${ctx}/js/kindeditor-4.1.5/kindeditor.js"></script>
      <script type="text/javascript" charset="utf-8" src="${ctx}/js/kindeditor-4.1.5/lang/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/system/message.js"></script>
 

</head>
<body style="overflow:auto;">
<div style="margin-top: 3px;">	<jsp:include page="../../jsp/system/title.jsp"></jsp:include> </div><br>
            <form action="${ctx}/message.js/messagelList.action" method="post" name="roleForm" id="roleForm">
                <input id="mcode" name="mcode" value="${mcode}" type="hidden"/>

                <table class="list_table" >
                        <thead>
                        <tr>
                            <th nowrap="nowrap">序号</th>
                            <th nowrap="nowrap">留言姓名</th>
                            <th nowrap="nowrap">反馈内容</th>
                            <th nowrap="nowrap">联系方式</th>
                            <th nowrap="nowrap">留言时间</th>
                            <th nowrap="nowrap">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty messageList}">
                                <c:forEach items="${messageList}" var="mes" varStatus="vs">
                                    <tr >
                                        <td nowrap="nowrap">${vs.index+1}</td>
                                        <td nowrap="nowrap">${mes.name }</td>
                                        <td   >
                                            <span class="span_list" title="${mes.context }" >${mes.context}</span>
                                             </td>
                                        <td nowrap="nowrap">${mes.tel }</td>
                                        <td nowrap="nowrap"><fmt:formatDate value="${mes.createDate }" type="both"/></td>
                                        <td nowrap="nowrap"> <a href="javascript:void(0);" onclick="mesView('${mes.id}')" target="rightFrame" target="rightFrame"  class="tablelink">查看</a> <a href="javascript:void(0)" onclick="delMessage('${mes.id}');"  class="tablelink"> 删除</a>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr >
                                    <td colspan="10" align="center">没有相关数据</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>

                        </tbody>
                    </table>
                    <div class="pagin">
                        ${message.page.pageStr }
                    </div>
            </form>
     	
      
</body>

</html>
