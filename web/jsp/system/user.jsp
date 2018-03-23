<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <%@ include file="/common/meta.jsp"%>

<script type="text/javascript">

     $(document).ready(function(e) {

	});

</script>
</head>
<body>
<form action="${ctx}/user/userList.action" method="post" name="roleForm" id="roleForm">
    <div class="formbody">
      <table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="40">登录名称:&nbsp;&nbsp;<input name="LOGIN_NAME" id="LOGIN_NAME" type="text" value="${user.LOGIN_NAME}"   class="df_input" />
					&nbsp;&nbsp;用户名称:&nbsp;&nbsp;<input name="USER_NAME" id="USER_NAME" type="text" value="${USER_NAME}"   class="df_input" />

					&nbsp;&nbsp;<input type="submit"  value="查询" class="btn_all b_hong"  />
				</td>
			</tr>
		</table>
    <table class="tablelist" id="senfe" >
    	<thead>
    	<tr>
       	<th nowrap="nowrap">序号</th>
		 <th nowrap="nowrap">登录名称</th>
		 <th nowrap="nowrap">用户名称</th>
        </tr>
        </thead>
        <tbody>
         <c:choose>
				<c:when test="${not empty userList}">
					<c:forEach items="${userList}" var="users" varStatus="vs">
						<tr >
							<td nowrap="nowrap">${vs.index+1}</td>
							<td nowrap="nowrap">${users.LOGIN_NAME }</td>
							<td nowrap="nowrap">${users.USER_NAME}</td>

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
			${user.page.pageStr }
		</div>
    </div>
	 </form>
</body>

</html>
