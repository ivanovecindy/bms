<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="keywords" content="精密合金，软磁合金(1J22、1J50、1J79、1J85)、硬磁合金(2J4、2J12)、弹性合金(3J1、3J21、3J53)、膨胀合金（4J29、4J36、4J42），高温合金、粉末合金（铁硅、铁硅铝、FeSi、FeSiAL）" />
	<script  type="text/javascript" src="${ctx}/js/fore/forepage.js"></script>
	<script type="text/javascript">

		$(document).ready(function(){

			//网站导航
			initQtDh();
		})
		 // 设置为主页
		//设为首页 < a onclick="SetHome(this,window.location)" > 设为首页 < /a>
		function SetHome(obj,vrl){
			try{
				obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
			}
			catch(e){
				if(window.netscape) {
					try {
						netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
					}
					catch (e) {
						alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
					}
					var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
					prefs.setCharPref('browser.startup.homepage',vrl);
				}
			}
		}
		// 加入收藏
		function shoucang()
		{
			var sURL = window.location;
			var sTitle = document.title;
			try
			{
				window.external.addFavorite(sURL, sTitle);
			}
			catch (e)
			{
				try
				{
					window.sidebar.addPanel(sTitle, sURL, "");
				}
				catch (e)
				{
					alert("加入收藏失败，请使用Ctrl+D进行添加");
				}
			}
		}

		function changeWid(hei){
			  document.getElementById("id_context").height = 1500;
		}
		function
		forwordDeals(url){
			$("#id_context").attr("src",basepath+url);
		}
		function search(){
			var searchNr = $("#searchNr").val();
			if(searchNr!=''){
				$.post(basepath + "/model/updateOrModel.action", {modName:'供应产品'}, function(data) {
					if(data.model!=""){
					    forwordDeals('/jsp/foreground/qycp.jsp?id='+data.model.id);
					}
				});
			}
		}
		function getBack(){
          var url = basepath+"/jsp/system/login.jsp";
			window.open(url);
		}
	</script>
</head>

<body>
<div id="leftsead">
	<ul>

		<li><a href="tencent://message/?uin=263821486&Site=test315.nesky.cn&Menu=yes"><img src="${ctx}/images/fo3_ll04.png" width="131" height="49" class="hides"/><img src="${ctx}/images/fo3_l04.png" width="47" height="49" class="shows" /></a></li>
		<li><a href="#"><img src="${ctx}/images/fo3_ll05.png" width="131" height="49" class="hides"/><img src="${ctx}/images/fo3_l05.png" width="47" height="49" class="shows" /></a></li>
		<li><a id="top_btn"><img src="${ctx}/images/fo3_ll06.png" width="131" height="49" class="hides"/><img src="${ctx}/images/fo3_l06.png" width="47" height="49" class="shows" /></a></li>
	</ul>
</div><!--leftsead end-->
<script type="text/javascript">
	$(document).ready(function(){
		$("#leftsead a").hover(function(){
			if($(this).prop("className")=="youhui"){
				$(this).children("img.hides").show();
			}else{
				$(this).children("img.hides").show();
				$(this).children("img.shows").hide();
				$(this).children("img.hides").animate({marginRight:'0px'},'slow');
			}
		},function(){
			if($(this).prop("className")=="youhui"){
				$(this).children("img.hides").hide('slow');
			}else{
				$(this).children("img.hides").animate({marginRight:'-143px'},'slow',function(){$(this).hide();$(this).next("img.shows").show();});
			}
		});
		$("#top_btn").click(function(){if(scroll=="off") return;$("html,body").animate({scrollTop: 0}, 600);});
	});
</script>
<div class="top">
	<div class="top_center">
		<div class="top_search">
			<input name="searchNr" type="text" id="searchNr" value="输入搜索内容">
			<a href="javascript:void(0)" onclick="search();" style="font-size:16px;"><i class="fa fa-search"></i></a>
		</div>
		<div class="top_menu">
			<a href="javacript:void(0)" onclick="shoucang();">加入收藏</a>
			│
			<a href="javacript:void(0)"  onclick="SetHome(this,window.location);" >设为首页</a>
			│
			<a href="javacript:void(0)"  onclick="getBack();" >网站后台</a>
		</div>
	</div>
</div>
<!-- Website Menu -->
<div class="menu2">
	<ul class="menu boxed clearfix" id="id_ul">
	</ul>
</div>
<!--/ Website Menu -->
<iframe frameborder="0" id="id_context" src="${ctx}/jsp/foreground/for_home.jsp"
		width="100%" height="725px;"	 style=" overflow: auto;"   ></iframe>

<div class="bottom">
	<p>地址：西安市阎良区国家航空高技术产业基地蓝天3路6号 &nbsp;&nbsp;&nbsp;&nbsp;邮编：710089 &nbsp;&nbsp;&nbsp;&nbsp; 电话：029-89082220&nbsp;&nbsp;&nbsp;&nbsp;029-89082219 &nbsp;&nbsp;&nbsp;&nbsp;传真：029-89082216  </p>
	<p>邮箱：hkjm88@126.com&nbsp;&nbsp;&nbsp;&nbsp;联系人：穆女士&nbsp;&nbsp;&nbsp;&nbsp; QQ：263821486 张女士&nbsp;&nbsp;&nbsp;&nbsp;  QQ：527985163 </p>
</div>
</body>
</html>