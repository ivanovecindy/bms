/**
 * Created by lenovo on 2016/10/25.
 */
/**
 * 网站导航
 */
function initQtDh(){
    $.post(basepath + "/web/getQtDh.action", { }, function(data) {
        if(data.mode_list!=""){
            var str='';
            $.each(data.mode_list, function(key, value) {
                if(value.weburl!=''&&value.weburl!=null&&value.weburl!='null'){
                    str+='<li><a  href="javascript:void(0)" onclick="setWebContext(\''+value.weburl+'\',\''+value.id+'\');"><i class="menu-icon '+value.icon+'"></i>'+value.modName+'</a>';
                }else{
                    str+='<li><a href="javascript:void(0)"><i class="menu-icon '+value.icon+'"></i>'+value.modName+'</a>';
                }
                   if(value.list_model!=''){
                      str+='<ul>';
                      $.each(value.list_model, function(k, v) {
                          if(v.weburl!=''&&v.weburl!=null&&v.weburl!='null'){
                              str+='<li><a  href="javascript:void(0)" onclick="setWebContext(\''+v.weburl+'\',\''+v.id+'\');">'+ v.modName+'</a></li>';
                          }else{
                              str+='<li><a href="javascript:void(0)">'+ v.modName+'</a></li>';
                          }

                      });
                      str+='</ul>';
                  }
                str+='</li>';

            });
            $("#id_ul").html(str);
        }

    });
}
/**
 * 得到前台设备图片
 */
function getSbtp(){

    $.post(basepath + "/web/getSbtp.action", { }, function(data) {
        if(data.mode_list!=""){
            var str="";
            $.each(data.modefj_list, function(key, value) {
                str+="<a href='"+value.url+"' target='_blank'><img  src='"+value.fjdz+value.fjmc+"' title='"+value.fjmc+"' alt='"+value.fjmc+"'/></a>";

            });
            $("#banner_list").html(str);
        }

    });
}

function setWebContext(url,id){
    if(url=='/jsp/foreground/index.jsp'){
        window.location.reload();
    }else{
        if(url.lastIndexOf("?")!=-1){
            $("#id_context").attr("src",basepath+url+"&id="+id);
        }else{
            $("#id_context").attr("src",basepath+url+"?id="+id);
        }

    }

}

function getContext(obj){

    if(obj=='jj'){//企业简介
     window.location.href=basepath+"/jsp/foreground/qyjj.jsp";
    }else{//企业资质
        window.location.href=basepath+"/jsp/foreground/qyzz.jsp";
    }
}
//留言提交
function lySubmit(){
    if (tt.validate()) {
        $.post(basepath + "/message/saveMessage.action", $("#lyform")
            .serializeArray(), function(data) {
            if(data.mesg){
                alert("提交成功");
            }

        });
    }else{
        $("#"+tt.vf.invalidEs[0].id).focus();
    }
}

function getCpContext(name,url){
    $.post(basepath + "/model/updateOrModel.action", {modName:name,weburl:url }, function(data) {
        if(data.model.id!=""){
            window.parent.forwordDeals(url+"?id="+data.model.id);
        }
    });
}