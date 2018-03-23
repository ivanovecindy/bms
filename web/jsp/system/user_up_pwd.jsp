<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <%@ include file="/common/meta.jsp" %>
    <script src="${ctx}/js/md5.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/js/system/user.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
    <div class="place"><span>用户修改密码</span></div>
    <center>
        <form action="" id="userForm" name="userForm">
            <table class="form_table" border="0" cellpadding="0" cellspacing="0" align="center">
                <input type="hidden" name="id" id="id" value="${sessionScope.sessionUser.id}"/>
                <tr ><td align="right">新密码:<b style="color:#F00;">*</b></td>
                    <td><input type="password"  class="input-text lh30" size="30" name="pwd" id="pwd"  /></td>
                </tr>
                <tr ><td align="right">再输一次新密码:<b style="color:#F00;">*</b></td>
                    <td><input type="password"  class="input-text lh30" size="30" name="pwd_" id="pwd_"  /></td>
                </tr>

                <tr>
                    <td colspan="2" align="center"><input name="queding" id="queding" type="button" class="ext_btn ext_btn_submit"
                                                          value="保存" onclick="upPwd()"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </center>


</body>

</html>
