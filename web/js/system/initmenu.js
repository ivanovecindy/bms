/***********************************************************************
 JSON.stringify(obj)将JSON转为字符串。JSON.parse(string)将字符串转为JSON格式；

**********************************************************/
 var _menus;
$(function(){
	  $.ajax({ 
      type : "post", 
      url : basepath+"/login/leftMenu.action?parentId=-1",
      async : false, 
      success : function(data){ 
    	 _menus = JSON.parse(data.menu_);
    	 InitLeftMenu();
    	 
       } 
      }); 
})

//初始化左侧
function InitLeftMenu() {
	var menulist ='<dl >';
    $.each(_menus.menus, function(i, n) {
		menulist +='<dd>';
		if(n.url!=''){
			menulist += '<div  class="title" ><span><img   src="'+basepath+'/images/side_h3.gif"   width="16" height="16"/></span><a href="javascript:void(0)" id="'+n.menuid+'" onclick="getUrl(\''+n.menuid+'\',\''+n.url+'\')" target="rightFrame">' + n.menuname + '</a></div> ';
		}else{
			menulist += '<div  class="title" ><span><img   src="'+basepath+'/images/side_h3.gif"   width="16" height="16"/></span>' + n.menuname + '</div> ';
		}

		menulist += '<ul class="menuson" >';
        $.each(n.menus, function(j, o) {
			if(o.url!=''){
				menulist += '<li><cite></cite><a href="javascript:void(0)" id="'+o.menuid+'" onclick="getUrl(\''+o.menuid+'\',\''+o.url+'\')" target="rightFrame">' + o.menuname + '</a><i></i></li>'
			}else{
				menulist += '<li><cite></cite><a href="javascript:void(0)" id="'+o.menuid+'"  >' + o.menuname + '</a><i></i></li>'
			}

        })
        menulist += '</ul>';
		menulist += ' </dd>';
    });
    menulist +='</dl>';
    $("#menus_div").html(menulist);
}
 
function getUrl(id,url){
		if(url.indexOf("?")==-1){
			if(url.indexOf(".jsp")==-1){
				$("#leftForm").attr("action", basepath+url);
			}else{
				var urls=url.substring(5,url.length-4);
				$("#jsp_").val(urls);
				$("#leftForm").attr("action", basepath+"/base/gotoJsp.action");
			}
		}else{
			if(url.indexOf(".jsp")==-1){
				var request = GetRequest(url);
				$("#buyer_xm").val(request['buyer_xm']);
				$("#leftForm").attr("action", basepath+url);
			}else{
				var urls =  url.split("?")[0];

				$("#jsp_").val(urls.substring(5,urls.length-4));
				$("#leftForm").attr("action", basepath+"/base/gotoJsp.action");
			}
		}
		$("#mcode").val(id);
		$("#leftForm").submit();

}
//js获取url传递参数
function GetRequest(url) {
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) { 
	      var str = url.split("?")[1];
	      if(str.indexOf("&") != -1){
	    	  strs = str.split("&");
		      for(var i = 0; i < strs.length; i ++) {
		         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
		      }
	      }
	   }else{
	   }
	   return theRequest;
	}