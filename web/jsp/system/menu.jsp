<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <%@ include file="/common/meta.jsp"%>
 <script type="text/javascript" src="${ctx}/js/system/menu.js"></script>
<script language="javascript" src="${ctx}/js/gridTree/hashMap.js"></script>
 <script language="javascript" src="${ctx}/js/gridTree/GridTree.All.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/js/gridTree/GridTree2.css">
<script type="text/javascript">
	  function flushList(obj){
	     if(obj){
	      alert("保存成功");
	      window.location.reload(true);
	     }
    }
    var basepath="${ctx}";
	 var myTree= new GridTree();
		$(function() {
		  var GridColumnType = [
                                {
					header : '名称',
					headerIndex : 'modName',
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
				  header : '网站页面地址',
				  headerIndex : 'weburl',
				  columntype : {
					  inputtype : 'html',
					  htmlStr :  '$',
					  nameId : 'textbox'
				  }
			  }, {
					header : '序号',
					headerIndex : 'xh',
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
					 htmlStr : '<a href="javascript:void(0);" onclick="addmenu(\'$\',\'add\')" target="rightFrame" target="rightFrame" class="tablelink">修改</a> <a  href="javascript:void(0);" onclick="addmenu(\'$\',\'view\')" target="rightFrame" target="rightFrame" class="tablelink">查看</a> <a href="javascript:void(0)" onclick="delMenu(\'$\');"  class="tablelink"> 删除</a>',
					 nameId : 'textbox',
					}
				}];
		       $.post( basepath+"/model/menutreeList.action",{},function(data){
					 var content = {columnModel:GridColumnType,
	                        data:eval(data.menuList),
	                        idColumn:'id',//id所在的列,一般是主键(不一定要显示出来)
	                        parentColumn:'pId', //父亲列id
	                        width:'800px',
	                        height:'1px',                                
	                        rowCount:true,//是否自动计算行数                       
	                        checkOption:2,//1:出现单选按钮,2:出现多选按钮,其他:不出现选择按钮
	                       // allCheck:true,//如果是多选,可以选择是否出现全部选择的按钮
	                        //debug:true,
	                        //pageBar:true,    
	                        styleOption:1,
	                        //pageSize:3,
	                        //disabeld:true,//为true就表示表格中的文本域,多选框等为不可编辑状态
	                        //disableOptionColumn:'rddisbled',//用来标识指定的选择框是否禁用的属性,默认没有
	                        //chooesdOptionColumn:'rddisbled',//用来标识默认的就选择多选框的属性,在有多选的选按钮的情况时候有用.
	                        multiChooseMode:3,
	                        //expandAll:true,//展开全部
	                      	tableId:'testTable',//表格树的id
	                        el:'newtableTree'//要进行渲染的div id
	        		   };
					myTree.loadData(content);
				    myTree.makeTable();
				}); 
		});
</script>
</head>
<body>
<div style="margin-top: 3px;padding-bottom: 2px;">	<jsp:include page="../../jsp/system/title.jsp"></jsp:include> </div>
     <form action="" method="post" name="menuForm" id="menuForm">
		 <div id="button"  style="text-align: left;margin-left: 5px;margin-bottom: 5px;">
			 <a href="javascript:void(0);" onclick="addmenu('')"  target="rightFrame"  class="ext_btn" style="margin-bottom: 5px;"><span class="add"></span>添加</a>
		     <div id='newtableTree'  class="form_table" border="0" cellpadding="0" cellspacing="0"
			style='width: 90%; height: 80%; border: 1px solid #000099; overflow-x: visible; overflow-y: scroll;'>
			正在装载，请等待。。。
		</div>  
     </form>
</body>

</html>
