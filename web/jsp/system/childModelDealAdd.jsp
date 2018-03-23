<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%
        String id = request.getParameter("id");
        String flag = request.getParameter("flag");
        String mcode = request.getParameter("mcode");
    %>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript"charset="utf-8" src="${ctx}/js/kindeditor-4.1.5/kindeditor.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/kindeditor-4.1.5/lang/zh_CN.js"></script>
    <script type="text/javascript">
        var editor;
        $(function () {
            //加载页面要校验的字段
            tt.vf.req.add("title");
            //初始化数据
            if ($("#id").val() != '') {
                menuView($("#id").val());
            }
            KindEditor.ready(function(K) {
                editor = K.create('textarea[name="mstemp"]', {
                    resizeType : 1,
                    allowPreviewEmoticons : false,
                    allowImageUpload : false,
                    width:'730px',
                    height:'150px',
                    items : [
                        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                        'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                        'insertunorderedlist', '|', 'emoticons', 'link'],
                    afterBlur: function(){this.sync();

                    }
                });
            });
            $("#srf_del_").live("click", function () {
                $(this).parent("td").parent("tr").remove();
            });
        });

      function addfj(){
            var cou=$("#count").val();
            if(cou=='1'){
                cou++;
            }
            var str='';
            str+='<tr><td  align="right">附件'+cou+'名称:</td><td>';
            str+='<input type ="hidden" name="fj_id_'+cou+'" id="fj_id_'+cou+'"  /><input type ="hidden" name="fj_h_'+cou+'" id="fj_h_'+cou+'"  /><input type="file"     onchange ="getFile(this.id);"  class="input-text lh30"  size="10" name="fj_'+cou+'" id="fj_'+cou+'"/>(请上传jpg/JPG/PDF/pdf/doc/docx格式文件)' ;
            str+='  &nbsp;&nbsp;页面文件是否下载:<input name="isfjView_'+cou+'"  type="hidden" id="isfjView_'+cou+'" value="0" /><input name="isfjViewtemp_'+cou+'" id="isfjViewtemp_'+cou+'" type="radio" value="1" onclick="setView(this.id,this.value)" />是<input name="isfjViewtemp_'+cou+'" id="isfjViewtemp_'+cou+'" type="radio" value="0" onclick="setView(this.id,this.value)"  checked="checked"/>否';
            str+='  &nbsp;&nbsp;图片显示位置:<input name="xswz_'+cou+'"  type="hidden" id="xswz_'+cou+'" value="0" /><input name="xswztemp_'+cou+'" id="xswztemp_'+cou+'" type="radio" value="top" onclick="setBgView(this.id,this.value)"   checked="checked"/>头部<input name="xswztemp_'+cou+'" id="xswztemp_'+cou+'" type="radio" value="bottom" onclick="setBgView(this.id,this.value)" />底部';
            str+='<input id="srf_del_"  class="ext_btn ext_btn_error" value="删除" type="button"  /></td></tr>';
            $("#tab_zdj").append(str);
            cou++;
            $("#count").val(cou);
        }
        function setView(id,value){
            if(id.indexOf("_")!=-1){
                $("#isfjView_"+id.split("_")[1]).val(value);
            }
        }
        function setBgView(id,value){
            if(id.indexOf("_")!=-1){
                $("#xswz_"+id.split("_")[1]).val(value);
            }
        }
    </script>
    <script type="text/javascript" src="${ctx}/js/system/childModelDeal.js"></script>
</head>
<body   style="overflow:auto;">
<form action="" id="menuForm" name="menuForm" method="post" enctype="multipart/form-data">
    <input id="id" name="id" value="<%=id %>" type="hidden"/>
    <input id="flag" name="flag" value="<%=flag %>" type="hidden"/>
    <input id="modeId" name="modeId" value="<%=mcode %>" type="hidden"/>
    <input id="modeDealId" name="modeDealId" value="<%=mcode %>" type="hidden"/>
    <input id="count" name="count"   type="hidden" value="1"/>
    <table class="list_table" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td align="right" class="td_right">标题:<b style="color:#F00;">*</b></td>

            <td height="40" align="left">
                <input id="title" name="title" class="input-text lh30" size="50"/></td>

        </tr>
        <tr>
            <td align="right" class="td_right">资源地址:<b style="color:#F00;">*</b></td>

            <td height="40" align="left">
                <input id="url" name="url" class="input-text lh30" size="150"/></td>
        </tr>
        <tr>
            <td align="right" class="td_right">网站页面地址:<b style="color:#F00;">*</b></td>

            <td height="40" align="left">
                <input id="weburl" name="weburl" class="input-text lh30" size="150"/></td>
        </tr>
        <tr ><td align="right" class="td_right">是否网站显示:</td>
            <td >
                <input name="isView"  type="hidden" id="isView"   />
                <input name="isView_" id="isView_" type="radio" value="1" checked="checked" />是<input name="isView_" id="isView_" type="radio" value="0" />否</td>
            </td>
        </tr>
        <tr>
            <td align="right" class="td_right">描述:<b style="color:#F00;">*</b></td>

            <td height="40" align="left">
                <input type="hidden" id="ms" name="ms">
                <textarea name="mstemp" id="mstemp" cols="100" rows="20" ></textarea></td>

        </tr>

        <tr>
            <td align="right">排序号:</td>
            <td><input type="text" class="input-text lh25" size="20" id="xh" name="xh" value="0"/><input type="button"
                                                                                                         id="tj_but"
                                                                                                         class="ext_btn ext_btn_submit"
                                                                                                         value="添加"
                                                                                                         onclick="addfj();"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <table class="list_table" width="90%" border="0" align="left" id="tab_zdj" cellpadding="0"
                       cellspacing="0">
                    <tr>
                        <td align="right">附件1名称:</td>
                        <td><input type ="hidden" name="fj_id_1" id="fj_id_1"  /><input type ="hidden" name="fj_h_1" id="fj_h_1"  /><input type="file" name="fj_1" id="fj_1" class="input-text lh30"   onchange ="getFile(this.id);"  size="10"/>(请上传jpg/JPG/PDF/pdf/doc/docx格式文件)
                          &nbsp;&nbsp;页面文件是否下载:<input name="isfjView_1"  type="hidden" id="isfjView_1"  value="0"  />
                            <input name="isfjViewtemp_1" id="isfjViewtemp_1" type="radio" value="1" onclick="setView(this.id,this.value)" />是<input name="isfjViewtemp_1" id="isfjViewtemp_1" type="radio" value="0" onclick="setView(this.id,this.value)" checked="checked"/>否
                            &nbsp;&nbsp;图片显示位置:<input name="xswz_1"  type="hidden" id="xswz_1"  value="top"  />
                            <input name="xswztemp_1" id="xswztemp_1" type="radio" value="top" onclick="setBgView(this.id,this.value)" checked="checked" />头部<input name="xswztemp_1" id="xswztemp_1" type="radio" value="bottom" onclick="setBgView(this.id,this.value)" checked="checked"/>底部
                        </td>

                    </tr>

                </table></td></tr>
        <tr>
            <td colspan="2" align="center"><input name="queding" id="queding" type="button"
                                                  class="ext_btn ext_btn_submit" value="确定" onclick="menuSubmit()"/>&nbsp;
                <input type="button" class="ext_btn" value="取消" onclick="quexiao()"/></td>
        </tr>
    </table>
</form>
 </body>
</html>
