<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript" src="${ctx}/js/system/childModelDeal.js"></script>

    <script type="text/javascript">

        $(function () {

        })

    </script>
</head>
<body style="overflow:auto;">

<center>
    <form action="" id="menuForm" name="menuForm" method="post" enctype="multipart/form-data">
  
        <table class="list_table" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td align="right" class="td_right">标题:</td>

            <td height="40" align="left">
             ${childModelDeal.title}</td>

        </tr>
            <tr>
                <td align="right" class="td_right">资源地址:<b style="color:#F00;">*</b></td>

                <td height="40" align="left">
                    ${childModelDeal.url}</td>
            </tr>
        <tr>
            <td align="right" class="td_right">描述:</td>

            <td height="40" align="left"  >
                ${childModelDeal.ms}
                </td>

        </tr>

        <tr>
            <td align="right">排序号:</td>
            <td>${modelDeal.xh}</td>
        </tr>
       <c:if test="${not empty list_fj}">
             <c:forEach items="${list_fj}" var="fj" varStatus="vs">
                 <tr>
                     <td colspan="2">
                         附件${vs.index+1}:${fj.fjmc}&nbsp;&nbsp;<input type="button"
                                                                      id="tj_but"
                                                                      class="ext_btn ext_btn_submit"
                                                                      value="查看"
                                                                      onclick="getView('${fj.id}');"/>&nbsp;&nbsp;<input type="button"
                                                                                                 id="tj_but"
                                                                                                 class="ext_btn ext_btn_submit"
                                                                                                 value="下载"
                                                                                                 onclick="down('${fj.id}');"/>
                     </td>
                 </tr>
                </c:forEach>
                </c:if>
            <tr>
            <td colspan="2" align="center">
                <input type="button" class="ext_btn" value="取消" onclick="quitView()"/></td>
        </tr>
        </table>
    </form>
</center>


</body>

</html>
