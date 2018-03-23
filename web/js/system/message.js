//留言查看
function mesView(id){
	if(id!=''){
		openDiv("留言查看",basepath+"/message/viewMessage.action?id="+id);
	}
}
function quexiao() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function delMessage(id){
	if(id!=''){
		$.post(basepath + "/message/deleteMessage.action", {id:id}, function(data) {
			if (data.mesg) {
				alert("删除成功");
				window.location.reload(true);
			}
		});
	}
}