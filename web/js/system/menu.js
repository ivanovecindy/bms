//菜单目录信息
//新增菜单目录信息页面
function addmenu(id, flag) {
	layer.open({
		type : 2,
		title : '菜单目录信息',
		fix : true,
		offset : [ '15px', '30px' ],
		area : [ '80%', '70%' ],
		fix : false, // 不固定
		maxmin : true,
		scrolling : 'auto',
		content : [
			basepath + '/jsp/system/menu_add.jsp?id=' + id + "&flag="
			+ flag, 'yes' ]
	});
}

// 菜单目录信息提交保存
function menuSubmit() {
	$("#modeType").val($('input:radio[name="modeType_"]:checked').val());
	$("#isView").val($('input:radio[name="isView_"]:checked').val());
	if (tt.validate()) {
		$.post(basepath + "/model/saveMenu.action", $("#menuForm")
				.serializeArray(), function(data) {
			parent.flushList(data.mesg);
		});
	}else{
		 $("#"+tt.vf.invalidEs[0].id).focus();
	}
}

// 初始化数据
var data_ = null;
function menuView(id) {
	var path = basepath + "/model/updateOrModel.action";
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
			var st = [ "id", "pId", "modName",   "url","weburl","icon",
					"xh" ];
			initFormData(data_, st, "model");
			var viewFlag = $("#flag").val();
			if (viewFlag == 'view') {
				$.each(st, function(k, v) {
					$("input[name='" + v + "']").attr("disabled", true);
					$("select[name='" + v + "']").attr("disabled", true);
					$("textarea[name='" + v + "']").attr("disabled", true);
				});
				$("#queding").css("display", "none");
			}
		 $("input:radio[name=modeType_][value="+data_.model.modeType+"]").attr("checked",true);
		 $("input:radio[name=isView_][value="+data_.model.isView+"]").attr("checked",true);
        if(data_.model.pId=='-1'){
     	   $("#sjcd").hide();
        }else{
     	   $("#t_tp").hide();
        }
	 }
 }
// 删除标的信息
function delMenu(id) {
	var path = basepath + "/model/deleteModel.action";
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