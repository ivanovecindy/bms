<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <%@ include file="/common/meta.jsp"%>
  <script type="text/javascript" src='${ctx}/js/system/initmenu.js'>
  </script>
  <script type="text/javascript">
    $(function() {
      //导航切换
      $(".menuson li").click(function() {
        $(".menuson li.active").removeClass("active")
        $(this).addClass("active");
      });

      $('.title').click(function() {
        var $ul = $(this).next('ul');
        $('dd').find('ul').slideUp();
        if ($ul.is(':visible')) {
          $(this).next('ul').slideUp();
        } else {
          $(this).next('ul').slideDown();
        }
      });
    })
  </script>


</head>

<body style="background:#f0f9fd;">
<!-- <div class="lefttop"><span></span>项目综合管理</div> -->
<form action="" target="rightFrame" id="leftForm" name="leftForm">
  <input id="mcode" name="mcode" type="hidden"/>
  <input id="ywzt" name="ywzt" type="hidden"/>
  <input id="buyer_xm" name="buyer_xm" type="hidden"/>
  <input id="jsp_" name="jsp_" type="hidden"/>
  <div id="menus_div" class="leftmenu" >

  </div>
</form>
<%--  <dl class="leftmenu">
 <dd><div class="title">
     <span><img src="images/leftico05.png"  width="16" height="16"/></span>
     <a href="${ctx}/tcpconfiginfo/tcpconfiginfoList.html" target="rightFrame">竞价报告交易所用户</a></div>
<!-- 	    <ul class="menuson"> -->
         <li><cite></cite><a href="${ctx}/jsp/jj/jysyh_fxbg.jsp" target="rightFrame">竞价分析报告</a><i></i></li>
         <li><cite></cite><a href="${ctx}/jsp/jj/jysyh_jgqr.jsp" target="rightFrame" >竞价结果确认单</a><i></i></li>
         <li><cite></cite><a href="${ctx}/jsp/jj/jysyh_bjjl.jsp" target="rightFrame" >报价历史记录</a><i></i></li>
<!--         </ul> -->
 </dd>
 <dd>
   <div class="title"><span><img src="${ctx}/images/leftico05.png" width="16" height="16" /></span>
       <a href="${ctx}/tcpRegulatoryInfo/tcpRegulatoryInfoList.html" target="rightFrame">竞价报告监管机构</a></div>
<!--         <ul class="menuson"> -->
         <li><cite></cite><a href="${ctx}/jsp/jj/jysyh_jgbg.jsp" target="rightFrame">监管机构竞价结果报告</a><i></i></li>
<!--         </ul> -->
 </dd>

 <dl class="leftmenu">
  <dd>
 <div class="title">
 <span><img src="${ctx}/images/xm_pic.png" /></span>项目信息管理
 </div>
     <ul class="menuson">
     <li><cite></cite><a href="${ctx}/project/projectList.html" target="rightFrame">企业项目信息</a><i></i></li>
      <li><cite></cite><a href="changepassword.html" target="rightFrame">未提交项目</a><i></i></li>
     <li><cite></cite><a href="usemanager.html" target="rightFrame">个人项目信息</a><i></i></li>
      <li><cite></cite><a href="${ctx}/jsp/AuditList.jsp" target="rightFrame">项目审核</a><i></i></li>
      <li><cite></cite><a href="${ctx}/jsp/AuditConfig.jsp" target="rightFrame">审核配置</a><i></i></li>
      <li><cite></cite><a href="${ctx}/jsp/siteImgManager.jsp" target="rightFrame">站点图片管理</a><i></i></li>
      <li><cite></cite><a href="${ctx}/jsp/project/tab_yxsrfsqzrr.jsp" target="rightFrame">自然人意向受让方申请</a><i></i></li>
      <li><cite></cite><a href="${ctx}/jsp/project/tab_yxsrfsqfr.jsp" target="rightFrame">法人意向受让方申请</a><i></i></li>
      </ul>
 </dd>

  <dd>
 <div class="title">
 <span><img src="${ctx}/images/xm_pic.png" /></span>竞价管理
 </div>
     <ul class="menuson">
     <li class="active"><cite></cite><a href="${ctx}/tcpconfiginfo/BiddingMagList.html" target="rightFrame">竞价管理</a><i></i></li>
     <li><cite></cite><a href="${ctx}/dt/index.html" target="_blank">竞价大厅</a><i></i></li>
      </ul>
 </dd>

 <dd>
 <div class="title">
 <span><img src="${ctx}/images/leftico02.png" /></span>资源管理
 </div>
   <ul class="menuson">
     <li><cite></cite><a href="menu.html" target="rightFrame">创建资源</a><i></i></li>
     <li><cite></cite><a href="menu/menuList.html" target="rightFrame" >查看资源</a><i></i></li>
     </ul>
 </dd>
 <dd>
  <div class="title">
 <span><img src="${ctx}/images/leftico02.png" /></span>用户管理
 </div>
 <ul class="menuson">
     <li><cite></cite><a href="${ctx}/user/adduser.html" target="rightFrame">创建用户</a><i></i></li>
     <li><cite></cite><a href="${ctx}/user/userslist.html" target="rightFrame" >用户查询</a><i></i></li>
 </ul>
 </dd>
<dd>

 <div class="title">
 <span><img src="${ctx}/images/leftico02.png" /></span>角色管理
 </div>
 <ul class="menuson">
     <li><cite></cite><a href="${ctx}/jsp/role.jsp" target="rightFrame">创建角色</a><i></i></li>
     <li><cite></cite><a href="${ctx}/role/roleList.html" target="rightFrame" >角色查询</a><i></i></li>
 </ul>
 </dd>
 <dd><div class="title"><span><img src="${ctx}/images/leftico03.png" /></span>权限管理</div>
 <ul class="menuson">
<!--         <li><cite></cite><a href="#"  target="rightFrame">设置权限</a><i></i></li> -->
     <li><cite></cite><a href="${ctx}/authority/authorityList.html"  target="rightFrame">权限查询</a><i></i></li>
 </ul>
 </dd>


 <dd>
     <div class="title"><span><img src="${ctx}/images/leftico04.png" /></span>组织机构</div>
<!--     <ul class="menuson"> -->
<!--         <li><cite></cite><a href="#">添加组织</a><i></i></li> -->
<!--         <li><cite></cite><a href="#">组织管理</a><i></i></li> -->
<!--         <li><cite></cite><a href="#">添加岗位</a><i></i></li> -->
<!--         <li><cite></cite><a href="#">岗位管理</a><i></i></li> -->
<!--     </ul> -->
<!--     </dd>   -->
<!--     <dd><div class="title"><span><img src="images/leftico04.png" /></span>数据字典管理</div> -->
 <ul class="menuson">
<!--         <li><cite></cite><a href="org/addorg.html"  target="rightFrame">添加组织</a><i></i></li> -->
<!--         <li><cite></cite><a href="#"  target="rightFrame">组织管理</a><i></i></li> -->
<!--         <li><cite></cite><a href="job/addjob.html"  target="rightFrame">添加岗位</a><i></i></li> -->
<!--         <li><cite></cite><a href="#"  target="rightFrame">岗位管理</a><i></i></li> -->
     <li><cite></cite><a href="${ctx}/org/OrgAdd.html"  target="rightFrame">添加组织</a><i></i></li>
     <li><cite></cite><a href="${ctx}/org/OrgList.html"  target="rightFrame">组织管理</a><i></i></li>
     <li><cite></cite><a href="${ctx}/job/addjob.html"  target="rightFrame">添加岗位</a><i></i></li>
     <li><cite></cite><a href="${ctx}/job/jobList.html"  target="rightFrame">岗位管理</a><i></i></li>
 </ul>

 </dd>

<dd><div class="title"><span><img src="${ctx}/images/leftico04.png" /></span>数据字典管理</div>
 <ul class="menuson">
     <li><cite></cite><a href="${ctx}/jsp/project/base_data.jsp" target="rightFrame">基础数据字典</a><i></i></li>
     <li><cite></cite><a href="${ctx}/jsp/project/district.jsp" target="rightFrame">行政区数据字典</a><i></i></li>
     <li><cite></cite><a href="${ctx}/jsp/project/trans_type.jsp" target="rightFrame">资产分类数据字典</a><i></i></li>
 </ul>
 </dd>

 <dd><div class="title"><span><img src="${ctx}/images/leftico05.png" width="16" height="16" /></span>日志管理</div>
 <ul class="menuson">
     <li><cite></cite><a href="sysLog/syslogs.html" target="rightFrame">系统日志</a><i></i></li>
     <li><cite></cite><a href="sysLog/applogs.html" target="rightFrame" >业务日志</a><i></i></li>
 </ul>
 </dd>

 <dd><div class="title"><span><img src="${ctx}/images/leftico05.png" width="16" height="16" /></span>日志管理</div>
 <ul class="menuson">
     <li><cite></cite><a href="sysLog/syslogs.html" target="rightFrame">系统日志</a><i></i></li>
     <li><cite></cite><a href="sysLog/applogs.html" target="rightFrame" >业务日志</a><i></i></li>
 </ul>
 </dd>





 </dl> --%>

</body>
</html>
