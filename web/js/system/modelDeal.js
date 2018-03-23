//菜单目录信息
//新增菜单目录信息页面
function addModelDeal(id, flag) {
	$.post(basepath + "/model/updateOrModel.action", {id : $("#mcode").val()}, function(data) {

		 if(data!=null){
			var titel = data.model.modName;
			 openDiv(titel+'信息',basepath + '/jsp/system/modelDealAdd.jsp?id=' + id + "&flag="
				 + flag+"&mcode="+$("#mcode").val());
		 }
	});


}

function modelDealView(id){
	$.post(basepath + "/model/updateOrModel.action", {id : $("#mcode").val()}, function(data) {
		if(data!=null){
			var titel = data.model.modName;
			openDiv( titel+'信息',basepath + '/modelDeal/modelDealView.action?id=' + id );
		}
	});

}


var extArrays =  new Array( "jpg");
function getFile(id_){
	var f = document.getElementById(id_).files;
	if(document.getElementById(id_).value!=''){
		var fileName = f[0].name;
		var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
		if(!extArrays.Contains(fileType)){
			alert("请上传jpg文件格式的文件");
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
function modelDealSubmit() {

	if (tt.validate()) {
		layer.load(1, {shade: [0.8, '#393D49']});
		var url=basepath+'/modelDeal/saveModeDeal.action';
	   $('#menuForm').attr('action',url);
		var saveForm=$('#menuForm');
		saveForm.ajaxSubmit({
			dataType : 'json',
			success : function(data){
				if (data.mesg) {
					alert("保存成功");
					layer.closeAll('loading');
					parent.location.reload(true);
					quexiao();
				}
			}
		});
	}else{
		 $("#"+tt.vf.invalidEs[0].id).focus();
	}
}

// 初始化数据
var data_ = null;
function modelDealUp(id) {
	var path = basepath + "/modelDeal/updateOrModelDeal.action?id=" + id;
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
					"xh","url","weburl" ];
			initFormData(data_, st, "modelDeal");
		    editor.html(data_.modelDeal.ms);
		/* if(data_.list_fj!=null){
			 $("#tab_zdj").empty();
			 var str='';
			 var cou=1;
			 $.each(data_.list_fj,function(key,valu) {
				 str+='<tr><td  align="right">附件'+cou+'名称:</td><td>';
				 str+='<input type="file"  style="width:65px;" multiple="multiple"  onchange ="getFile(this.id);"  class="input-text lh30"  name="fj'+cou+'" id="fj'+cou+'"/>(请上传jpg格式文件)' ;
				 str+='<input   class="ext_btn ext_btn_submit"	value="查看" 	onclick="getView(\''+valu.id+'\');"  type="button"  />&nbsp;&nbsp;<input   class="ext_btn ext_btn_submit"	value="下载" 	onclick="down(\''+valu.id+'\');"  type="button"  />&nbsp;&nbsp;<input id="srf_del_"  class="ext_btn ext_btn_error" value="删除" type="button"  /></td></tr>';
				 cou= key+2;
			 });
			 $("#tab_zdj").append(str);
		 }*/

	 }
 }
// 删除标的信息
function delModelDeal(id) {
	var path = basepath + "/modelDeal/deleteModelDeal.action";
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
function quexiao() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

//预览
function getView(id){
	var title="附件件查看";
	var url=basepath+'/modelDeal/previewPic.action?id='+id;
	openDiv(title,url);
}
//下载
function down(id){
	var url = basepath+"/modelDeal/download.action?id="+id;
	window.location.href=url;
}

