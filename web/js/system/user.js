//用户信息
//新增用户信息页面
function adduser(id, flag) {
	layer.open({
		type : 2,
		title : '用户信息',
		fix : true,
		offset : [ '1px', '' ],
		area : [ '98%', '90%' ],
		fix : false, // 不固定
		maxmin : true,
		scrolling : 'auto',
		content : [
				basepath + '/jsp/system/user_add.jsp', 'yes' ]
	});
}
function updateuser(id, flag) {
	layer.open({
		type : 2,
		title : '角色信息',
		fix : true,
		offset : [ '1px', '' ],
		area : [ '98%', '90%' ],
		fix : false, // 不固定
		maxmin : true,
		scrolling : 'auto',
		content : [
				basepath + '/jsp/system/user_update.jsp?id=' + id + "&flag="
						+ flag, 'yes' ]
	});
}
//角色代码校验
function checkUser(){
	var bol=false; 
	$.ajax({ 
        type: "post", 
         url: basepath + "/user/checkUser.action", 
         async:false, 
         data:$("#userForm").serializeArray(),
         dataType: "json", 
         success: function (data) { 
        	 if(data.mesg){
        		 alert("用户已存在!");
    			 bol=true; 
    		 }else{
    			 bol=false; 
    		 }
           } 
        });
	return bol;
}
var InterValObj; //timer变量，控制时间
var count = 40; //间隔函数，1秒执行
var curCount=0;//当前剩余秒数
// 用户注册验证
function userSendValidate() {
	 //加载页面要校验的字段
    //tt.vf.req.add("loginName","yzm");
	tt.vf.req.add("loginName");
    tt.vf.email.add("loginName");

	if (tt.validate()) {
		$.post(basepath + "/user/checkUser.action", $("#userForm")
				.serializeArray(), function(data) {
			 if(data.mesg){
				 alert("用户已存在!");
				 return ;
			 }else{
				 //发送邮箱验证
				 //设置button效果，开始计时
				 curCount = count;
				 $("#btnSendCode").attr("disabled", "true");
				 $("#btnSendCode").val("请在" + curCount + "秒后再次发送验证码");
				 InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
				 	$.post(basepath + "/user/sendEmial.action", $("#userForm")
							.serializeArray(), function(data) {
						    $("#yzm_").val(data.mesg);
					});
			 } 
		});
	}else{
		 $("#"+tt.vf.invalidEs[0].id).focus();
	}
}
//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {
		window.clearInterval(InterValObj);//停止计时器
		$("#btnSendCode").removeAttr("disabled");//启用按钮
		$("#btnSendCode").val("重新发送验证码");
		code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
	}
	else {
		curCount--;
		$("#btnSendCode").val("请在" + curCount + "秒后再次发送验证码");
	}
}
//用户信息提交保存
function userSubmit() {
	 //加载页面要校验的字段
     tt.vf.req.add("loginName","yzm","password","password_");
	//tt.vf.req.add("loginName","password","password_");
    tt.vf.email.add("loginName");
    if($("#yzm").val()!=$("#yzm_").val()){
    	alert("验证码不一致");
    	return;
    }
    if($("#password").val()!=$("#password_").val()){
    	alert("两次密码输入不一致");
    	return;
    }
    $("#userType").val($('input:radio[name="userType_"]:checked').val());
	if (tt.validate()) {
	   if(!checkUser()){
		   layer.load(1, {shade: [0.8, '#393D49']});
		   $.post(basepath + "/user/saveUser.action", $("#userForm")
					.serializeArray(), function(data) {
				$("#userId").val(data.userId);
				alert("您已注册成功，请您登录后进行实名认证！");
				layer.closeAll('loading');
				window.location.href=basepath + "/jsp/login.jsp";
			});
	   }
	}else{
		 $("#"+tt.vf.invalidEs[0].id).focus();
	}
}
/**
 * 后台用户注册提交
 */
function backUserSubmit(){
	 //加载页面要校验的字段
	var ttReqV = tt.vf.req;
		 tt.vf.req.add("loginName","userName","tel");
	     tt.vf.email.add("loginName");
	new tt.RV().set(new RegExp("(^[0][1-9]{2,3}-[0-9]{5,8}$)|(^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$)"),
		"电话号码格式不对，正确格式如下：\n座机号码：021-4213550(或)\n手机号码：13635456878").add("tel","qygh");
		if (tt.validate()) {
		   if(!checkUser()){ 
			   layer.load(1, {shade: [0.8, '#393D49']});
			   $.post(basepath + "/user/saveBackUser.action", $("#userForm")
						.serializeArray(), function(data) {
					//$("#userId").val(data.userId);
				   layer.closeAll('loading');
				   window.parent.flushList(data.mesg);
				});
		   }
		}else{
			 $("#"+tt.vf.invalidEs[0].id).focus();
		}
}

//用户信息提交保存
function upPwd() {
	 tt.vf.req.add("pwd","pwd_");
  if($("#pwd").val()!=$("#pwd_").val()){
  	alert("两次密码输入不一致");
  	return;
  } 
	if (tt.validate()) {
		$.post(basepath + "/user/updateUserPwd.action", $("#userForm")
				.serializeArray(), function(data) {
			if(data.mesg){
				alert("修改成功!");
				top.location.href=basepath+"/login/logout.action?upPwd=upPwd";
			}
		});
	}else{
		 $("#"+tt.vf.invalidEs[0].id).focus();
	}
}
//用户信息提交保存
function updateLoginName() {
	 tt.vf.req.add("loginName");
	if (tt.validate()) {
		$.post(basepath + "/user/updateLoginName.action", $("#userForm")
				.serializeArray(), function(data) {
			if(data.mesg){
				alert("修改成功!");
			}
		});
	}else{
		 $("#"+tt.vf.invalidEs[0].id).focus();
	}
}
// 初始化数据
var data_ = null; 
//下载
function down(code){
	 var id = $("#id").val();
	 var url = basepath+"/user/download.action?id="+id+'&code='+code;
	 window.location.href=url;
}
// 删除标的信息
function deluser(id) {
	if(confirm("确定要删除数据吗？")){
	var path = basepath + "/user/deleteUser.action";
	$.ajax({
		type : "post",
		url : path,
		data : {
			ids : id
		},
		dataType : "json",
		success : function(data) {
			if (data.mesg) {
				alert("删除成功");
				window.location.reload(true);
			}
		}
	});
  }
}
//资料保存
function userIdentSave(){
	$("#tjzt").val("0");//数据提交状态 0为没有提交 1已提交
	if(tt.validate()){
		$.post(basepath + "/user/checkUser.action", $("#userForm")
			.serializeArray(), function(data) {
			if(data.mesg){
				alert("用户已存在!");
				$("#loginName").focus();
				return ;
			}else{
		layer.load(1, {shade: [0.8, '#393D49']});
		var url=basepath+'/user/filesUpload.action';
		$('#userForm').attr('action',url);
			var saveForm=$('#userForm');
			saveForm.ajaxSubmit({
				dataType : 'json',
				success : function(data){
					if(data.mesg){
						alert("保存成功!");
						layer.closeAll('loading');
						window.location.reload(true);
					}
				}
			});
			}
		});
	}else{
		$("#"+tt.vf.invalidEs[0].id).focus();
	}
}
//资料提交
function userIdentSubmit(){
	$("#tjzt").val("1");//数据提交状态 0为没有提交 1已提交
	  if(tt.validate()){
		  $.post(basepath + "/user/checkUser.action", $("#userForm")
			  .serializeArray(), function(data) {
			  if(data.mesg){
				  alert("用户已存在!");
				  $("#loginName").focus();
				  return ;
			  }else{
		     layer.load(1, {shade: [0.8, '#393D49']});
			var url=basepath+'/user/filesUpload.action';
			$('#userForm').attr('action',url);
			var saveForm=$('#userForm');
			saveForm.ajaxSubmit({
				dataType : 'json',
				success : function(data){
					if(data.mesg){
						alert("您提交的申请将于1个工作日内完成审核，如需加急办理，请致电：029-68296597!");
						layer.closeAll('loading');
						window.location.reload(true);
					}
				}
			});
		  }
		  });
	    }else{
			 $("#"+tt.vf.invalidEs[0].id).focus();
		}
}
var extArray = new Array("jpg");
function getFile(id_){
	 var f = document.getElementById(id_).files;
	 if(document.getElementById(id_).value!=''){
		 var fileName = f[0].name;
		 var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
		 if(!extArray.Contains(fileType)){
			 alert("请上传图片文件格式的文件");
			 document.getElementById(id_).value='';
			 return;
		 }
         if (f[0].size>0) {
             var fileSize = 0;
             if (f[0].size > 1024 * 1024*5){
            	 fileSize = (Math.round(f[0].size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            	 alert("你传的文件大小为"+fileSize+"已超出5M的最大限制!");
            	 return;
             }else{
            	 fileSize = (Math.round(f[0].size * 100 / 1024) / 100).toString() + 'KB';
             }
         }
	 }
}
//用户查看
function viewUser(id){
	 var title="用户查看";
	 var url =basepath+"/user/viewUser.action?userId="+id;
	 openDiv(title,url);
}
//用户参与的项目
function yhcyjpxm(id){
	 var title="用户参与的竞拍项目";
	 var url =basepath+"/project/queryCYJPXM.action?userid="+id;
	 openDiv(title,url);
} 
//分配角色
function fpRole(){
	var userId = $("#userId").val();
	layer.open({
	    type: 2,
	    title:"角色",
	    fix: true,
		offset : [ '1px', '' ],
		area : [ '98%', '90%' ],
	    fix: false, //不固定
	    maxmin: true,
		scrolling: 'auto',
	    content:[url=basepath+'/role/roleFpList.action?userId='+userId, 'yes']
	});
}
//分配部门
function fpDep(){
	var userId = $("#userId").val();
	 layer.open({
		    type: 2,
		    title:"部门",
		    fix: true,
		    offset: ['1px', ''],
			area: ['50%', '90%'],
		    fix: false, //不固定
		    maxmin: true,
			scrolling: 'auto',
		    content:[url=basepath+'/jsp/system/dept_tree.jsp?userId='+userId, 'yes']
		});
}

//分配岗位
function fdJob(){
	var userId = $("#userId").val();
	 layer.open({
		    type: 2,
		    title:"岗位",
		    fix: true,
		    offset: ['1px', ''],
			area: ['50%', '90%'],
		    fix: false, //不固定
		    maxmin: true,
			scrolling: 'auto',
		    content:[url=basepath+'/jsp/system/job_tree.jsp?userId='+userId, 'yes']
		});
}
//取消
function quexiao() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
//交易所用户
function addBackUser(userId) {
	 var title="用户添加";
	 if(userId!=''){
		 title="用户修改";
	 }
	 var url =basepath+"/jsp/system/back_user_reg.jsp?userId="+userId;
	 openDiv(title,url);
}
//鉴定机构用户
function addJdjgUser(userId) {
	 var title="用户添加";
	 if(userId!=''){
		 title="用户修改";
	 }
	 var url =basepath+"/jsp/system/jdjg_user_reg.jsp?userId="+userId;
	 openDiv(title,url);
}
/**
 * 后台用户注册提交
 */
function jdjgUserSubmit(){
	 //加载页面要校验的字段
	 if (tt.validate()) {
		   if(!checkUser()){ 
			   layer.load(1, {shade: [0.8, '#393D49']});
			   $.post(basepath + "/user/saveJdjgUser.action", $("#userForm")
						.serializeArray(), function(data) {
				   layer.closeAll('loading');
				   window.parent.flushList(data.mesg);
				});
		   }
		}else{
			 $("#"+tt.vf.invalidEs[0].id).focus();
		}
}

//初始化用户密码
function initPwd(userId) {
	$.post(basepath + "/user/updateUserPwd.action", {userId:userId,password:'111111'}, function(data) {
		if(data.mesg){
			alert("修改成功!");
		}
	});

}
