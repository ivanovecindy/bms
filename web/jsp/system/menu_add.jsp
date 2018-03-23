<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%String id = request.getParameter("id"); 
   String flag = request.getParameter("flag");
   %>
 <%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="${ctx}/js/system/menu.js"></script>
 
<script type="text/javascript">
$(function(){	
	 //加载页面要校验的字段
    tt.vf.req.add("modName");
         //初始化数据
    if($("#id").val()!=''){
      menuView($("#id").val());
    }
    createMenuSel("pId",-1);

})	
</script>
</head>
<body  style="overflow:auto;">
	 
     	<center>
       <form action="" id="menuForm" name="menuForm">
       <input id="id" name="id" value="<%=id %>" type="hidden" />
       <input id="flag" name="flag" value="<%=flag %>" type="hidden" />
       <table  class="form_table" border="0" cellpadding="0" cellspacing="0" align="center">
       <tr id="sjcd"><td align="right">上级菜单:(不选择为顶级菜单)</td>
           <td>
               <select name="pId" id="pId" class="select" style="width: 300px;">
                   <option value="">请选择:</option>
               </select>
           </td>
       </tr>
        <tr ><td align="right">资源名称:<b style="color:#F00;">*</b></td>
        <td><input type="text"  class="input-text lh30" size="50" name="modName" id="modName"  /></td>
        </tr>
        <tr><td align="right">资源路径:</td>
         <td><input type="text" class="input-text lh30" size="50"  id="url" name="url"  /></td></tr>
           <tr><td align="right">网站页面路径:</td>
         <td><input type="text" class="input-text lh30" size="50"  id="weburl" name="weburl"  /></td></tr>
           <tr ><td align="right" class="td_right">是否前台模块:</td>
                 <td  >
                   <input name="modeType"  type="hidden" id="modeType"   />
                   <input name="modeType_" id="modeType_" type="radio" value="1" checked="checked" />是<input name="modeType_" id="modeType_" type="radio" value="0" />否</td>
              </td>
           </tr>
           <tr ><td align="right" class="td_right">是否网站显示:</td>
               <td >
                   <input name="isView"  type="hidden" id="isView"   />
                   <input name="isView_" id="isView_" type="radio" value="1" checked="checked" />是<input name="isView_" id="isView_" type="radio" value="0" />否</td>
               </td>
           </tr>
           <tr ><td align="right">图标名称:</td>
               <td><input type="text"  class="input-text lh30" size="50" name="icon" id="icon"  /></td>
           </tr>
        <tr ><td align="right">排序号:</td>
        <td><input type="text" class="input-text lh25" size="30"  id="xh" name="xh" value="0" /></td></tr>
       <tr>
       <td colspan="2" align="center"> <input name="queding" id="queding" type="button"   class="ext_btn ext_btn_submit" value="确定" onclick="menuSubmit()" />&nbsp;
        <input  type="button"   class="ext_btn" value="取消" onclick="quexiao()" /></td>
      </tr>
    </table> 
   </form></center>
     	
      
</body>

</html>
