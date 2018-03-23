<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>
<%@ taglib uri="/WEB-INF/fnc.tld" prefix="fnc" %>
<%@ taglib uri="/WEB-INF/fns.tld" prefix="fns" %>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!-- <link rel="shortcut icon" href="${ctx}/images/tmps.png" type="image/x-png" /> -->
<style type="text/css">
    * {
        font-family: "微软雅黑";
        font-size: 10pt;
        margin: 0;
        padding: 0;
    }

    .span_list {
        width: 230px;
        display:inline-block;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .textarea_box {
        word-wrap: break-word;
        word-break: keep-all;
        line-height: 20px;
        padding: 2px;
        resize: none;
        border: 1px solid #999;
        border-color: #999 #d8d8d8 #d8d8d8 #999;
    }
</style>
<link href="${ctx}/css/page.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.0.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>--%>
<!-- js文件同步加载 -->
<script type="text/javascript" src="${ctx}/js/synchronousJS.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
<!--公用的下拉选择JS包括级连框架 -->
<script type="text/javascript" src="${ctx}/js/system/comm_selete.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/jsmap.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/json2.js"></script>

<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/main.css">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.idTabs.js"></script>
<script type="text/javascript" src="${ctx}/js/pubCommon.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.placeholder.min.js"></script>

<!--js 检验框架-->
<link type="text/css" rel="stylesheet" href="${ctx}/js/validate/css/validate.css"/>
<script src="${ctx}/js/validate/talent-validate-all-init.js" language="javascript"></script>
<!-- layer弹出层 -->
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>

<script type="text/javascript" src="${ctx}/js/jquery.SuperSlide.js"></script>
<script type="text/javascript">
    var basepath = "${ctx}";

</script>
