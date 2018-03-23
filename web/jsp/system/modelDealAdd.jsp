<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%
        String id = request.getParameter("id");
        String flag = request.getParameter("flag");
        String mcode = request.getParameter("mcode");
    %>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript"charset="utf-8" src="${ctx}/js/kindeditor-4.1.5/kindeditor.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/kindeditor-4.1.5/lang/zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/system/modelDeal.js"></script>

    <script type="text/javascript">
        var editor;
        $(function () {
            //加载页面要校验的字段
            tt.vf.req.add("title", "ms");
            //初始化数据
            if ($("#id").val() != '') {
                modelDealUp($("#id").val());
            }
            KindEditor.ready(function(K) {
                editor = K.create('textarea[name="ms"]', {
                    resizeType : 1,
                    allowPreviewEmoticons : false,
                    allowImageUpload : false,
                    width:'730px',
                    height:'150px',
                    items : [
                        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                        'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                        'insertunorderedlist', '|', 'emoticons', 'link', '|', 'table', 'code'],
                    afterBlur: function(){this.sync();
                    }
                });
            });
        });


    </script>
</head>
<body   style="overflow:auto;">
<form action="" id="menuForm" name="menuForm" method="post" enctype="multipart/form-data">
    <input id="id" name="id" value="<%=id %>" type="hidden"/>
    <input id="flag" name="flag" value="<%=flag %>" type="hidden"/>
    <input id="modeId" name="modeId" value="<%=mcode %>" type="hidden"/>
    <table class="list_table" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td align="right" class="td_right">标题:<b style="color:#F00;">*</b></td>

            <td height="40" align="left">
                <input id="title" name="title" class="input-text lh30" size="50"/></td>

        </tr>
        <tr>
            <td align="right" class="td_right">资源地址: </td>

            <td height="40" align="left">
                <input id="url" name="url" class="input-text lh30" size="150"/></td>
        </tr>
        <tr>
            <td align="right" class="td_right">网站页面地址: </td>

            <td height="40" align="left">
                <input id="weburl" name="weburl" class="input-text lh30" size="150"/></td>
        </tr>
        <tr>
            <td align="right" class="td_right">描述:<b style="color:#F00;">*</b></td>

            <td height="40" align="left">
                <textarea name="ms" id="ms" cols="100" rows="20" ></textarea></td>

        </tr>

        <tr>
            <td align="right">排序号:</td>
            <td><input type="text" class="input-text lh25" size="30" id="xh" name="xh" value="0"/></td>
        </tr>

        <tr>
        <tr>
            <td colspan="2">
                <table class="list_table" width="90%" border="0" align="left" id="tab_zdj" cellpadding="0"
                       cellspacing="0">
                    <tr>
                        <td align="right">附件:</td>
                        <td><input type="file" name="fj" id="fj" class="input-text lh30"   onchange ="getFile(this.id);"  size="10"/>(请上传jpg格式文件)
                        </td>
                    </tr>
                    <tr></tr>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input name="queding" id="queding" type="button"
                                                  class="ext_btn ext_btn_submit" value="确定" onclick="modelDealSubmit()"/>&nbsp;
                <input type="button" class="ext_btn" value="取消" onclick="quexiao()"/></td>
        </tr>

    </table>
</form>
</body>
</html>
