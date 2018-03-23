<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/fnc.tld" prefix="fnc"%>
<%@ taglib uri="/WEB-INF/fns.tld" prefix="fns"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
 <script type="text/javascript" src="${ctx}/js/jquery-1.9.1.js"></script> 
<!-- <script language="javascript" src="${ctx}/js/gridTree/jquery.min.js"></script> -->
 
<script language="javascript" src="${ctx}/js/gridTree/hashMap.js"></script>
 <script language="javascript" src="${ctx}/js/gridTree/GridTree.All.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/js/gridTree/GridTree2.css">
 
</head>

<body>
	<script type="text/javascript">
	 var basepath="${ctx}";
	 var myTree= new GridTree();
		$(function() {
		  var GridColumnType = [
                                {
					header : '名称',
					headerIndex : 'menuname',
					columntype : {
						inputtype : 'html',
						htmlStr : '$',
						 nameId : 'textbox'
					}
				} , {
					header : '地址连接',
					headerIndex : 'url',
					columntype : {
						inputtype : 'html',
					     htmlStr :  '$',
						 nameId : 'textbox'
					}
				}, {
					header : '操作',
					headerIndex : 'id',
					columntype : {
					 inputtype : 'html',
					 htmlStr : '<button onclick="alert(\'$\');">查看</button>',
					 nameId : 'textbox',
					}
				}];
		  $.post( basepath+"/menu/menutreeList.html",{},function(data){
					 var content = {columnModel:GridColumnType,
	                        data:eval(data.menuList),
	                        idColumn:'id',//id所在的列,一般是主键(不一定要显示出来)
	                        parentColumn:'parentId', //父亲列id
	                        width:'800px',
	                        height:'1px',                                
	                        rowCount:true,//是否自动计算行数                       
	                        checkOption:2,//1:出现单选按钮,2:出现多选按钮,其他:不出现选择按钮
	                        allCheck:true,//如果是多选,可以选择是否出现全部选择的按钮
	                        //debug:true,
	                        //pageBar:true,    
	                        styleOption:1,
	                        //pageSize:3,
	                        //disabeld:true,//为true就表示表格中的文本域,多选框等为不可编辑状态
	                        disableOptionColumn:'rddisbled',//用来标识指定的选择框是否禁用的属性,默认没有
	                        chooesdOptionColumn:'rddisbled',//用来标识默认的就选择多选框的属性,在有多选的选按钮的情况时候有用.
	                        multiChooseMode:3,
	                        //expandAll:true,//展开全部
	                      	tableId:'testTable',//表格树的id
	                        el:'newtableTree'//要进行渲染的div id
	        		   };
					myTree.loadData(content);
				    myTree.makeTable();
				}); 
		});
		
	//页面一打开就执行，放入ready是为了layer所需配件（css、扩展模块）加载完毕
	 function guanyu(){ 
	    //官网欢迎页
	    layer.open({
	        type: 2,
	        skin: 'layui-layer-lan',
	        title: 'layer弹层组件',
	        fix: true,
	        offset: ['100px', ''],
	        shadeClose: true,
	        maxmin: true,
	        area: ['1000px', '500px'],
	        content: 'http://www.baidu.com',
	        end: function(){
	           // layer.tips('试试相册模块？', '#photosDemo', {tips: 1})
	        }
	    });
	 }
	 function sel_gd(val){
	   alert(val);
	 }
	 
	</script>
	<div class="i_del" id="list">
		<div>
			<input type="text" /><a href="javascript:void(0);" class="add">添加</a>
		</div>
	</div>
   <div>
   <a href="javascript:void();" id="about" onclick="guanyu();">关于</a>
      <td height="40" align="left"> <input type="radio" id="sfgdfq_" name="sfgdfq_" onclick="sel_gd(this.value)" value="1"/>是<input type="radio" id="sfgdfq_" name="sfgdfq_" onclick="sel_gd(this.value)"  value="0"/>否</td>
  
   </div>
    <form method="post" action="update.do?method=uploader" enctype="multipart/form-data">  
        <input type="file" id="houseMaps1" name="houseMaps1"/> 
        <input type="file" id="houseMaps2" name="houseMaps2"/>  
        <input type="button" value="提交" onclick="ajaxFileUpload()"/>
    </form>
     	<div id='newtableTree'
			style='width: 100%; height: 100%; border: 1px solid #000099; overflow-x: visible; overflow-y: scroll;'>
			正在装载，请等待。。。
		</div>  
</body>
</html>
