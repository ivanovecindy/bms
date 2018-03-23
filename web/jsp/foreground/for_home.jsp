<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
   <script type="text/javascript">
	   var t = n =0, count;
	   $(document).ready(function(){
		   count=$("#banner_list a").length;
		   $("#banner_list a:not(:first-child)").hide();
		   $("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt'));
		   $("#banner_info").click(function(){window.open($("#banner_list a:first-child").attr('href'), "_blank")});
		   $("#banner li").click(function() {
			   var i = $(this).text() -1;//获取Li元素内的值，即1，2，3，4
			   n = i;
			   if (i >= count) return;
			   $("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt'));
			   $("#banner_info").unbind().click(function(){window.open($("#banner_list a").eq(i).attr('href'), "_blank")})
			   $("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
			   document.getElementById("banner").style.background="";
			   $(this).toggleClass("on");
			   $(this).siblings().removeAttr("class");
		   });
		   t = setInterval("showAuto()", 4000);
		   $("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 4000);});
	   })

	   function showAuto()
	   {
		   n = n >=(count -1) ?0 : ++n;
		   $("#banner li").eq(n).trigger('click');
	   }

	   $(function() {
		   $li1 = $(".apply_nav .apply_array");
		   $window1 = $(".apply .apply_w");
		   $left1 = $(".apply .img_l");
		   $right1 = $(".apply .img_r");

		   $window1.css("width", $li1.length*166);

		   var lc1 = 0;
		   var rc1 = $li1.length-5;
		   $left1.click(function() {
			   if (lc1 < 1) {
				   alert("已经是第一张图片");
				   return;
			   }
			   lc1--;
			   rc1++;
			   $window1.animate({left:'+=166px'}, 1000);
		   });

		   $right1.click(function() {
			   if (rc1 < 1) {
				   alert("已经是最后一张图片");
				   return;
			   }
			   lc1++;
			   rc1--;
			   $window1.animate({left:'-=166px'}, 1000);
		   });
		   //加载页面要校验的字段
		   tt.vf.req.add("name","context");
		  // initQycp();
	   });
	   function forwordDeal(url){
		   if(url!=''){
			   if(url=='qycp'){
				   $.post(basepath + "/model/updateOrModel.action", {modName:'供应产品'}, function(data) {
					   if(data.model!=""){
						   window.parent.forwordDeals('/jsp/foreground/qycp.jsp?id='+data.model.id);
					   }
				   });
			   }else{
				   window.parent.forwordDeals(url);
			   }
		   }

	   }
	   function initQycp(){
		   $.post(basepath + "/model/updateOrModel.action", {modName:'供应产品'}, function(datas) {
			   if(datas.model!=""){
				   $.post(basepath + "/web/initQycp.action", {modeDealId:datas.model.id}, function(data) {
					   if(data.list_fj!=""){
						   $("#div_cp").empty();
						   var str='<ul>'
						   $.each(data.list_fj, function(key, value) {
							   if(value.cxtj!=''&&value.cxtj!=null&&value.cxtj!='null'){
								   str+=' <li> <a  href="javascript:void(0)"   title="'+value.title+'" onclick="forwordDeal(\'/jsp/foreground/qycp.jsp?id='+datas.model.id+'&cid='+value.modeDealId+'\');"><img src="'+value.cxtj+'" alt="'+value.title+'"/></a></li>';
                                }
						   });
						   str+=' </ul>';
						   $("#div_cp").append(str)
					   }
				   });
			   }
		   });
	   }
   </script>
	<script type="text/javascript" src="${ctx}/js/fore/forepage.js"></script>


</head>
 <body style="overflow: hidden;height: 100%">
 <div class="js">
	 <div class="gssb">
		 <div class="gssb_top">公司设备</div>
		 <div class="gssb_down">
			 <div id="banner">
				 <div id="banner_bg"></div>
				 <!--标题背景-->
				 <div id="banner_info"></div>
				 <!--标题-->
				 <ul>
					 <li class="on">1</li>
					 <li>2</li>
					 <li>3</li>
					 <li>4</li>
					 <li>5</li>
				 </ul>
				 <div id="banner_list">
					 <a  href="javascript:void(0)"  onclick="forwordDeal('/jsp/foreground/qysb.jsp')" ><img  src="${ctx}/images/sb1.jpg" title="可逆轧机" alt="可逆轧机"/></a>
					 <a  href="javascript:void(0)"  onclick="forwordDeal('/jsp/foreground/qysb.jsp')" ><img src="${ctx}/images/sb2.jpg" title="四辊轧机" alt="四辊轧机"/></a>
					 <a  href="javascript:void(0)"  onclick="forwordDeal('/jsp/foreground/qysb.jsp')" ><img src="${ctx}/images/sb3.jpg" title="中频真空冶炼炉" alt="中频真空冶炼炉"/></a>
					 <a  href="javascript:void(0)"  onclick="forwordDeal('/jsp/foreground/qysb.jsp')" ><img src="${ctx}/images/sb4.jpg" title="热轧机" alt="热轧机"/></a>
					 <a  href="javascript:void(0)"  onclick="forwordDeal('/jsp/foreground/qysb.jsp')" ><img src="${ctx}/images/sb5.jpg" title="精密磨床" alt="精密磨床"/></a>
				 </div>
			 </div>
		 </div>
	 </div>
	 <!--公司设备结束-->
	 <!--公司介绍开始-->
	 <div class="gsjs">
		 <div class="gsjs_top">企业简介</div>
		 <div class="gsjs_down">
			 <p>陕西航空精密合金有限公司是一家专业从事软磁合金、永磁合金、弹性合金、膨胀合金、高温合金等的带材、棒材、板材、丝材以及软磁合金粉末材料的生产厂家，长期为国内多家军工单位及国有大型企业提供原材料。
			 </p>
			 <p>公司始创于1992年，现位于陕西省西安市阎良区国家航空高技术产业基地，占地45亩，建筑面积5000平方米；现有员工40余人，其中研发人员9名，博士研究生1名，硕士研究生3名，本科生10名；拥有500kg非真空感应冶炼炉、200kg真空感应炉、热轧机、四辊冷轧机、可逆轧机、热处理炉、检测仪器等各类生产设备50余台套。设有产品研发部、技术质量部、炼钢车间、热轧车间、冷轧成型车间、热处理车间、粉末车间、化学分析实验室和物理性能检测室等部门。连续数年被陕西省科学技术厅认定为高新技术企业，多项课题获得陕西省科技厅创业基金资助。 </p>
			 <p>公司自成立以来，与相关高校和科研院所建立起了良好的合作关系，不断投入研发经费，攻克多种精密......<a  href="javascript:void(0)"  onclick="forwordDeal('/jsp/foreground/qyjj.jsp')" class="one">[查看详情]</a></p>
		 </div>
	 </div>
 </div>
 <!-- 企业简介结束-->
 <div class="three">
	 <div class="cp_top">产品中心</div>
	 <div class="three_left">
		 <div class="top_slide_wrap">
			 <ul class="slide_box bxslider">
				 <li>
					 <a href="#" target="_blank">
						 <img src="${ctx}/static/worldcup/home/1.jpg" width="593" height=""/>
					 </a>
					 <div class="slide_info">
						 <div class="slide_info_card">
							 <a href="javascript:void(0)"  onclick="forwordDeal('qycp')">
								 <div class="slide_info_card_text">
									 <h2>
										 铁镍软磁合金
									 </h2>
									 <p>
										 用途：广泛应用于卫星通讯设备、精密测控设备、工业整流设备...             </p>
									 <p>
										 <strong>
											 点击进入&gt;
										 </strong>
									 </p>
								 </div>
							 </a>
							 <div class="slide_info_card_bg">
							 </div>
						 </div>
					 </div>
				 </li>
				 <li>
					 <a href="#" onclick="forwordDeal('qycp')" target="_blank">
						 <img src="${ctx}/static/worldcup/home/2.jpg" alt="" title="">
					 </a>
					 <div class="slide_info">
						 <div class="slide_info_card">
							 <a href="javascript:void(0)"  onclick="forwordDeal('qycp')">
								 <div class="slide_info_card_text">
									 <h2>
										 铁镍钴玻封合金
									 </h2>
									 <p>
										 用途：广泛用于军品的电连接器、抗电磁干扰的滤波连接器...
									 </p>
									 <p>
										 <strong>
											 点击进入&gt;
										 </strong>
									 </p>
								 </div>
							 </a>
							 <div class="slide_info_card_bg">
							 </div>
						 </div>
					 </div>
				 </li>

				 <li>
					 <a href="#" onclick="forwordDeal('qycp')" target="_blank">
						 <img src="${ctx}/static/worldcup/home/3.jpg" alt="">
					 </a>
					 <div class="slide_info">
						 <div class="slide_info_card">
							 <a href="javascript:void(0)"  onclick="forwordDeal('qycp')">
								 <div class="slide_info_card_text">
									 <h2>
										 磁温度补偿合金
									 </h2>
									 <p>
										 用途：适用于电磁回路和永磁回路中的磁分路补偿元件、度表，风速表、性温度开关...
									 </p>
									 <p>
										 <strong>
											 点击进入&gt;
										 </strong>
									 </p>
								 </div>
							 </a>
							 <div class="slide_info_card_bg">
							 </div>
						 </div>
					 </div>
				 </li>
				 <li>
					 <a href="#" onclick="forwordDeal('qycp')" target="_blank">
						 <img src="${ctx}/static/worldcup/home/4.jpg" alt="">
					 </a>
					 <div class="slide_info">
						 <div class="slide_info_card">
							 <a href="javascript:void(0)"  onclick="forwordDeal('qycp')">
								 <div class="slide_info_card_text">
									 <h2>
										 高饱和磁感应强度合金
									 </h2>
									 <p>
										 用途：广泛应用于航空发电机、精密直流电机，电磁铁极头、磁控管中的端焊管...
									 </p>
									 <p>
										 <strong>
											 点击进入&gt;
										 </strong>
									 </p>
								 </div>
							 </a>
							 <div class="slide_info_card_bg">
							 </div>
						 </div>
					 </div>
				 </li>
				 <li>
					 <a href="#" onclick="forwordDeal('qycp')" target="_blank">
						 <img src="${ctx}/static/worldcup/home/5.jpg" alt="">
					 </a>
					 <div class="slide_info">
						 <div class="slide_info_card">
							 <a href="javascript:void(0)"  onclick="forwordDeal('qycp')">
								 <div class="slide_info_card_text">
									 <h2>
										 高温合金
									 </h2>
									 <p>
										 用途：适合制造在650℃以下长期工作的航空发动机高温承力部件及航天、航空、燃气轮机及其他工业用的一般承力部件...
									 </p>
									 <p>
										 <strong>
											 点击进入&gt;
										 </strong>
									 </p>
								 </div>
							 </a>
							 <div class="slide_info_card_bg">
							 </div>
						 </div>
					 </div>
				 </li>
				 <li>
					 <a href="#" onclick="forwordDeal('qycp')" target="_blank">
						 <img src="${ctx}/static/worldcup/home/6.jpg" alt="">
					 </a>
					 <div class="slide_info">
						 <div class="slide_info_card">
							 <a href="javascript:void(0)"  onclick="forwordDeal('qycp')">
								 <div class="slide_info_card_text">
									 <h2>
										 弹性元件用合金
									 </h2>
									 <p>
										 用途：用于制作精密仪器仪表中弹性敏感元件、储能元件和频率元件...
									 </p>
									 <p>
										 <strong>
											 点击进入&gt;
										 </strong>
									 </p>
								 </div>
							 </a>
							 <div class="slide_info_card_bg">
							 </div>
						 </div>
					 </div>
				 </li>
			 </ul>

		 </div>

		 <script src="${ctx}/static/worldcup/home/jquery-1.11.1.min_044d0927.js">
		 </script>
		 <script src="${ctx}/static/worldcup/home/jquery.bxslider_e88acd1b.js">
		 </script>
		 <script src="${ctx}/static/worldcup/home/index_5d791568.js">
		 </script>
	 </div>
	 <div class="three_right">
		 <div id="bannera">
			 <div class="pro-switch">
				 <div class="slider">
					 <div class="flexslider">
						 <ul class="slides">
							 <li>
								 <div class="img"><a href="javascript:void(0)" onclick="forwordDeal('qycp')"><img src="${ctx}/images/cp2_1.jpg" height="350" width="580" alt="" /></a></div>
							 </li>
							 <li>
								 <div class="img"><a href="javascript:void(0)"  onclick="forwordDeal('qycp')"><img src="${ctx}/images/cp2_2.jpg" height="350" width=580" alt="" /></a></div>
							 </li>
							 <li>
								 <div class="img"><a href="javascript:void(0)"  onclick="forwordDeal('qycp')"><img src="${ctx}/images/cp2_3.jpg" height="350" width="580" alt="" /></a></div>
							 </li>
						 </ul>
					 </div>
				 </div>
			 </div>
		 </div>
		 <script defer src="${ctx}/js/16css.js"></script>
		 <script type="text/javascript">
			 $(function(){
				 $('.flexslider').flexslider({
					 animation: "slide",
					 start: function(slider){
						 $('div').removeClass('bannera');
					 }
				 });
			 });
		 </script>
	 </div>

 </div>



 </body>
</html>