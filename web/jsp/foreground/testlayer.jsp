<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/common/foremeta.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
   <script type="text/javascript">
	   $(document).ready(function(){
		   var height = $(document.body).height();
	   });
       function addTxlGrCode(id) {
           layer.open({
               type: 2,
               title : '菜单目录信息',
               fix : true,
               offset : [ '15px', '30px' ],
               area : [ '80%', '70%' ],
               fix : false, // 不固定
               maxmin : true,
               scrolling : 'auto',
               content: basepath +'/jsp/foreground/test_add.jsp?id=' + id ,
               btn: ['确认', '取消'],
               yes: function (index, layero) { //或者使用btn1 yes
                   // var body = layer.getChildFrame('#menuForm',index);//建立父子联系
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                   iframeWin.menuSubmit();
               }, cancel: function (index) {
                   layer.close(index);
               }
           });
       }
   </script>
</head>
 <body style="overflow: hidden;height: 100%">
 <input type="button" value="test" onclick="addTxlGrCode('')">

</body>
</html>