//菜单目录信息
//新增菜单目录信息页面
function addmenu(id, flag) {
	window.open(basepath + '/jsp/system/childModelDealAdd.jsp?id=' + id+ "&flag="
		+ flag+"&mcode="+$("#mcode").val(),"newChildModel");

}

function modelDealView(id){
	openDiv( '信息',basepath + '/childModelDeal/childModelDealView.action?id=' + id );
}


var extArrays =  new Array( "jpg","JPG","PDF","pdf","doc","docx");
function getFile(id_){
	var f = document.getElementById(id_).files;
	if(document.getElementById(id_).value!=''){
		var fileName = f[0].name;
		var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
		if(!extArrays.Contains(fileType)){
			alert("请上传jpg/JPG/PDF/pdf/doc/docx文件格式的文件");
			document.getElementById(id_).value='';
			return;
		}
		if (f[0].size>0) {
			var fileSize = 0;
			if (f[0].size > 1024 * 1024*3){
				fileSize = (Math.round(f[0].size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
				alert("你传的文件大小为"+fileSize+"已超出3M的最大限制!");
				return;
			}else{
				fileSize = (Math.round(f[0].size * 100 / 1024) / 100).toString() + 'KB';
			}
		}
	}
}
// 菜单目录信息提交保存
function menuSubmit() {
	$("#isView").val($('input:radio[name="isView_"]:checked').val());
	$("#ms").val($('#mstemp').val());
	if (tt.validate()) {
		layer.load(1, {shade: [0.8, '#393D49']});
		var url=basepath+'/childModelDeal/saveModeDeal.action';
		$('#menuForm').attr('action',url);
		var saveForm=$('#menuForm');
		saveForm.ajaxSubmit({
			dataType : 'json',
			success : function(data){
				if (data.mesg) {
					alert("保存成功");
					layer.closeAll('loading');
					window.opener.location.reload()
					window.close();
				}
			}
		});
	}else{
		 $("#"+tt.vf.invalidEs[0].id).focus();
	}
}

// 初始化数据
var data_ = null;
function menuView(id) {
	var path = basepath + "/childModelDeal/updateOrChildModelDeal.action?id=" + id;
	$.ajax({
		type : "post",
		url : path,
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
			  data_=data;
			   setTimeout("initFormData_()",500);//100毫秒后回填信息主要是下拉框数据选中
			   clearTimeout("initFormData_()");
			}
		}
	});
}

function initFormData_(){
	 if(data_!=null){
		   var st = [  "title",
					"xh" ,"url","weburl","id","modeDealId"];
			initFormData(data_, st, "childModelDeal");
			 if(data_.childModelDeal.ms!=''&&data_.childModelDeal.ms!=null&&data_.childModelDeal.ms!='null'){
				  editor.html(data_.childModelDeal.ms);
				 $("#mstemp").val(data_.childModelDeal.ms);
			 }
		  $("input:radio[name=isView_][value="+data_.childModelDeal.isView+"]").attr("checked",true);
		 if(data_.list_fj!=null){
			 $("#tab_zdj").empty();
			 var str='';
			 var cou=1;
			 $.each(data_.list_fj,function(key,valu) {
				 str+='<tr><td  align="right">附件'+cou+'名称:</td><td>';
				 str+='<input type ="hidden" name="fj_id_'+cou+'" id="fj_id_'+cou+'" value="'+valu.id+'" /><input type ="hidden" name="fj_h_'+cou+'" id="fj_h_'+cou+'" value="'+valu.id+'"/><input type="file"     class="input-text lh30"   onchange ="getFile(this.id);"  size="10"  name="fj_'+cou+'" id="fj_'+cou+'"/>(请上传jpg格式文件)' ;
				 str+='  &nbsp;&nbsp;页面文件是否下载:<input name="isfjView_'+cou+'"  type="hidden" id="isfjView_'+cou+'" value="'+valu.isView+'" />';
                 if(valu.isView=='1'){
					str+='<input name="isfjViewtemp_'+cou+'" id="isfjViewtemp_'+cou+'" type="radio" value="1" onclick="setView(this.id,this.value)" checked="checked" />是<input name="isfjViewtemp_'+cou+'" id="isfjViewtemp_'+cou+'" type="radio" value="0" onclick="setView(this.id,this.value)"  />否'
				 }else{
					 str+='<input name="isfjViewtemp_'+cou+'" id="isfjViewtemp_'+cou+'" type="radio" value="1" onclick="setView(this.id,this.value)" />是<input name="isfjViewtemp_'+cou+'" id="isfjViewtemp_'+cou+'" type="radio" value="0" onclick="setView(this.id,this.value)"  checked="checked"/>否'
             	 }
				 str+='  &nbsp;&nbsp;图片显示位置:<input name="xswz_'+cou+'"  type="hidden" id="xswz_'+cou+'" value="'+valu.xswz+'" />';
				 if(valu.xswz=='top'){
					 str+='<input name="xswztemp_'+cou+'" id="xswztemp_'+cou+'" type="radio" value="top" onclick="setView(this.id,this.value)" checked="checked" />头部<input name="xswztemp_'+cou+'" id="xswztemp_'+cou+'" type="radio" value="bottom" onclick="setView(this.id,this.value)"  />底部'
				 }else{
					 str+='<input name="xswztemp_'+cou+'" id="xswztemp_'+cou+'" type="radio" value="top" onclick="setView(this.id,this.value)" />头部<input name="xswztemp_'+cou+'" id="xswztemp_'+cou+'" type="radio" value="bottom" onclick="setView(this.id,this.value)"  checked="checked"/>底部'
				 }
				 if(valu.fjmc.lastIndexOf("jpg")!=-1){
					 str+='<input   class="ext_btn ext_btn_submit"	value="查看" 	onclick="getView(\''+valu.id+'\');"  type="button"  />&nbsp;&nbsp;<input   class="ext_btn ext_btn_submit"	value="下载" 	onclick="down(\''+valu.id+'\');"  type="button"  />&nbsp;&nbsp;<input id="srf_del_"  class="ext_btn ext_btn_error" value="删除" type="button" 	onclick="delfj(\''+valu.id+'\');" /></td></tr>';
                    }else{
					 str+='<input   class="ext_btn ext_btn_submit"	value="下载" 	onclick="down(\''+valu.id+'\');"  type="button"  />&nbsp;&nbsp;<input id="srf_del_"  class="ext_btn ext_btn_error" value="删除" type="button" 	onclick="delfj(\''+valu.id+'\');" /></td></tr>';
				 }
				 cou= key+2;
			 });
			 $("#count").val(cou);
			 $("#tab_zdj").append(str);
		 }
	 }
 }
// 删除标的信息
function delMenu(id) {
	var path = basepath + "/childModelDeal/deleteChildModelDeal.action";
	$.ajax({
		type : "post",
		url : path,
		data : {
			id : id
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
function quexiao() {
	window.close();
}
function quitView() {
	 var index = parent.layer.getFrameIndex(window.name);
	 parent.layer.close(index);
}
//预览
function getView(id){
	var title="附件件查看";
	var url=basepath+'/childModelDeal/previewPic.action?id='+id;
	openDiv(title,url);
/*	layer.open({
		type: 1,
		title: false,
		closeBtn: 0,
		offset: ['1px', ''],
		area: ['98%', '90%'],
		skin: 'layui-layer-nobg', //没有背景色
		shadeClose: true,
		content:[url, 'yes']
	});*/

}
//下载
function down(id){
	var url = basepath+"/childModelDeal/download.action?id="+id;
	window.location.href=url;
}


/**
 * 产品导航
 */
function initCPDh(id,cid){
	$.post(basepath + "/web/getSbtp.action", {modeDealId:id}, function(data) {
		if(data.mode_list!=""){
			var str='<div class="kj_bt">产品与服务</div>';
			var c=0;
			var count = data.mode_list.length;
			$.each(data.mode_list, function(key, value) {
				if(value.title!=''&&value.title!=null&&value.title!='null'){
					if(cid==''){
						if(c==0){
							str+='<div class="kj_l four" id="div_'+value.id+'"><a  href="javascript:void(0)"  id="a_'+value.id+'" class="four" onclick="getContext(\''+value.id+'\');" >'+value.title+'</a></div>';
							getContext(value.id);
						}else{
							str+='<div class="kj_h two" id="div_'+value.id+'"><a  href="javascript:void(0)"  id="a_'+value.id+'" class="two" onclick="getContext(\''+value.id+'\');" >'+value.title+'</a></div>';
                         }
					}else{
						var c_temp = count-1;
						 if(c==c_temp){
								str+='<div class="kj_l four" id="div_'+value.id+'"><a  href="javascript:void(0)"  id="a_'+value.id+'" class="four" onclick="getContext(\''+value.id+'\');" >'+value.title+'</a></div>';
								getContext(cid);
						}else{
							str+='<div class="kj_h two" id="div_'+value.id+'"><a  href="javascript:void(0)"  id="a_'+value.id+'" class="two" onclick="getContext(\''+value.id+'\');" >'+value.title+'</a></div>';
						}
					}
				}
                c++;

			});
			$("#cp_name").append(str);
		}

	});
}

function getContext(id){
	$("#cp_context").empty();
	var url = basepath + "/childModelDeal/updateOrChildModelDeal.action"
	$.ajax({
		cache: true,
		type: "POST",
		url:url,
		async: false,
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {

			var str='<tr><td colspan="2" align="left" class="cpwz" style=" background-image:url('+basepath+'/images/bg.png); background-repeat:repeat-x; background-position:top;"><strong>'+data.childModelDeal.title+'</strong></td></tr>';
			str+='<tr>';
			var bjtp ="";
			if(data.list_fj!=""){

				$.each(data.list_fj, function(key, value) {
				     var key_temp =key +1;
					if(key_temp%2==1){
						str+='</tr><tr>';
					}
					if(value.fjmc!=''&&value.fjmc!=null&&value.fjmc!='null'){
						if(value.xswz=='top'){
							str+='<td  height="200" width="100" align="center" valign="middle" >'+value.cxtj+'</td>';
						}
						if(value.xswz=='bottom'){
							bjtp = value.fjdz;
						}
					}
				});
				str+='</tr>';
			}
			if(data.childModelDeal!=""){
             str+='<tr> <td colspan="2"  >'+data.childModelDeal.ms+'</td> </tr>';
			}
			if(bjtp!=""){
				str+='<tr> <td colspan="2"   >'+bjtp+'</td> </tr>';
			}
			$("#dqwz").empty();
			$("#cp_context").empty();
			$("#dqwz").append('>当前位置：首页 > 产品与服务 > 供应产品 >'+data.childModelDeal.title);
		 	$("#cp_context").html(str);
		}

	});
	setDivCss(id);
}

function setDivCss(id){
	  $.each($("div[id^='div_']") , function(key, value) {
		 $(this).removeClass("kj_h two");
		 $(this).removeClass("kj_l four");
		 if($(this).attr("id").indexOf(id)==-1){
			 $(this).addClass("kj_h two");
		 }else{
			 $(this).addClass("kj_l four");
		 }
	});
	$.each($("a[id^='a_']") , function(key, value) {
		$(this).removeClass("two");
		$(this).removeClass("four");
		if($(this).attr("id").indexOf(id)==-1) {
            $(this).addClass("two");
		}else{
			$(this).addClass("four");
		}
	});

}




/**
 * 页面二级导航
 */
function getEjdh(id){
	$.post(basepath + "/web/getEjdh.action", {modeDealId:id}, function(data) {
		if(data.mode_list!=""){
			var str='<div class="kj_bt">产品与服务</div>';
			var c=0;
			$.each(data.mode_list, function(key, value) {
					if(c==0){
						str+='<div class="kj_l four" id="div_'+value.id+'"><a  href="javascript:void(0)"  id="a_'+value.id+'" class="four" onclick="getJsContext(\''+value.id+'\');" >'+value.modName+'</a></div>';
						 getJsContext(value.id);
					}else{
						str+='<div class="kj_h two" id="div_'+value.id+'"><a  href="javascript:void(0)"  id="a_'+value.id+'" class="two" onclick="getJsContext(\''+value.id+'\');" >'+value.modName+'</a></div>';

					}
				c++;
			});
			$("#cp_name").append(str);
		}

	});
}
//isEr
/**
 * 页面二级导航
 */
function getErdh(id){

	$.post(basepath + "/model/updateOrModel.action", {id:id}, function(data) {
		if(data.model!=""){
			$.post(basepath + "/web/getEjdh.action", {modeDealId:data.model.pId}, function(data) {
				if(data.mode_list!=""){
					var str='<div class="kj_bt">产品与服务</div>';
					$.each(data.mode_list, function(key, value) {
						if(id==value.id){
							str+='<div class="kj_l four" id="div_'+value.id+'"><a  href="javascript:void(0)"  id="a_'+value.id+'" class="four" onclick="getJsContext(\''+value.id+'\');" >'+value.modName+'</a></div>';
						}else{
							str+='<div class="kj_h two" id="div_'+value.id+'"><a  href="javascript:void(0)"  id="a_'+value.id+'" class="two" onclick="getJsContext(\''+value.id+'\');" >'+value.modName+'</a></div>';
                     	}
					 });
					getJsContext(id);
					$("#cp_name").append(str);
				}

			});
		}

	});
}
function getJsContext(id){
	 //页面显示位置
	$("#cp_context").empty();
	$.post(basepath + "/model/updateOrModel.action", {id:id}, function(data) {
		if(data.model!=""){
			$("#dqwz").empty();
			var str='>当前位置：首页 > 技术支持与下载 >'+data.model.modName;
			$("#dqwz").append(str);
			//资料下载
			if(data.model.modName.indexOf("资料下载")!=-1){
				var url = basepath + "/childModelDeal/getChildModelDealFj.action"
				$.ajax({
					cache: true,
					type: "POST",
					url:url,
					async: false,
					data : {
						modeDealId : id
					},
					dataType : "json",
					success : function(data) {
						var str='';
						if(data.list_fj!=""){

							$.each(data.list_fj, function(key, value) {
								str+=' <tr>';
								if(value.fjmc!=''&&value.fjmc!=null&&value.fjmc!='null'){
									str+='<td >'+value.title+'&nbsp;&nbsp;<input   class="ext_btn ext_btn_submit"	value="下载"  onclick="down(\''+value.id+'\');"  type="button"  /></td>';
								}
								str+='</tr>';
							});

						}

						$("#cp_context").append(str);
					}

				});
				setDivCss(id);
			}else{
				//技术支持
				var url = basepath + "/childModelDeal/getChildModelDeal.action"
				$.ajax({
					cache: true,
					type: "POST",
					url:url,
					async: false,
					data : {
						modeDealId : id
					},
					dataType : "json",
					success : function(data) {
						var str='<tr><td align="letf" >名称</td><td align="letf">生成时间</td></tr>';
						if(data.list_childModelDeal!=""){

							$.each(data.list_childModelDeal, function(key, value) {
								str+=' <tr>';
								if(value.title!=''&&value.title!=null&&value.title!='null'){
									str+='<td width="100%" height="100%" align="letf" nowrap="nowrap"><a href="javascript:void(0)" onclick="viewDeal(\''+value.id+'\')">'+value.title+'</a></td>';
									str+='<td width="100%" height="100%" align="letf" nowrap="nowrap">'+jsonDateFormat(value.createDate)+'</td>';
								}
								str+='</tr>';
							});

						}
						$("#cp_context").append(str);
					}

				});
				setDivCss(id);
			}
		}

	});



}

function setWZ(id){
	$.post(basepath + "/model/updateOrModel.action", {id:id}, function(data) {
		if(data.model!=""){
			$("#dqwz").empty();
			var str='>当前位置：首页 > 技术支持与下载 >'+data.model.modName;
			$("#dqwz").append(str);
		}

	});
}
function delfj(id){
	$.post(basepath + "/childModelDeal/delChildModelDealFj.action", {id:id}, function(data) {

	});
}

///无时分秒
function jsonDateFormat(jsonDate) {//json日期格式转换为正常格式
	try {
		var date = new Date(parseInt(jsonDate.toString().replace("/Date(", "").replace(")/", ""), 10));
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
		var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		return date.getFullYear() + "年" + month + "月" + day+"日";
	} catch (ex) {
		return "";
	}
}
//查看详情ss
function viewDeal(id){
	var title="技术支付详情查看";
	var url=basepath+'/childModelDeal/childModelDealView.action?For_view=1&id='+id;
 	 openDiv(title,url);
	//window.open(url,"newChildModel");
}