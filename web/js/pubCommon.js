 //标的物查看页面连接地址
function formDataSubmit(obj,url,postFunction){
	  $.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:$('#'+obj).serialize(),// 你的formid
			async: false,
		    error: function(request) {
		    },
		    success: function(data) {
            	if(postFunction&&typeof postFunction == 'function'){
            		postFunction(data);
            		return false;
            	}else{
    		        if(data=='true'){
    		        	alert("操作成功！");
    		        }else if(data=='false'){
    			        alert("操作失败！");
    			    }else{
    				    alert(data);
    			    }
            	}

		    }

		});
}

String.prototype.trim= function()  {
	var ret = this.replace(/(\n[\s|\t]*\r*\n)/g, "");
	ret = ret.replace(/(^\s*)|(\s*$)/g,"");
	return ret;
}


 function Trim(str){
	 if(typeof(str)=='undefined'){
		 return ;
	 }else{
		  return str.replace(/(^\s*)|(\s*$)/g, "");  
	 }
     
}

function TrimAll(str,is_global){
	var result;
	result = str.replace(/(^\s+)|(\s+$)/g,"");
	if(is_global.toLowerCase()=="g")
	    result = result.replace(/\s/g,"");
	return result;
}  

if(!Array.indexOf){   
	  Array.prototype.indexOf = function(Object){   
	     for(var i = 0;i<this.length;i++){   
	        if(this[i] == Object){   
	           return i;   
	         }   
	     }   
	     return -1;   
	   } 
}

function removeArray(arrs,tag){
	if(arrs!=null&&arrs.length>0&&tag!=null&&tag!=''){
 		var p = -1;
 		for(var i in arrs){
	 		if(arrs[i]==tag){p = i; break;}
 		}
 		if (p >= 0) {arrs.splice(p,1);} 
	}
}

String.prototype.endWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substring(this.length - str.length) == str)
		return true;
	else
		return false;
	return true;
}

String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
}

function regNumberInput(obj,inputStr){
	  var reg;
	  var minV = obj.getAttribute('min');
	  var maxV = obj.getAttribute('max');
	  var digit = obj.getAttribute('digit');
	  var negative  = obj.getAttribute('negative');
	  digit = digit || "0";
	  digit = Number(digit);
	  if(negative == "true" && (!minV || Number(minV)<0)){
		  if(obj.value == '' && inputStr=='-') return true;
	  }
	  if(negative == "true" && (!minV || Number(minV)<0)){
		  if(digit == 0){
			  reg = /^[-]?\d+$/
		  } else if(digit == 1){
			  reg = /^[-]?\d*\.?\d{0,1}$/
		  } else if(digit == 2){
			  reg = /^[-]?\d*\.?\d{0,2}$/
		  } else if(digit == 3){
			  reg = /^[-]?\d*\.?\d{0,3}$/
		  } else if(digit == 4){
			  reg = /^[-]?\d*\.?\d{0,4}$/
		  } else {
			  reg = /^[-]?[-\+]?\d+(\.\d+)?$/
		  }

	  } else {
		  if(digit == 0){
			  reg = /^[0-9]*$/
		  } else if(digit == 1){
			  reg = /^\d*\.?\d{0,1}$/
		  } else if(digit == 2){
			  reg = /^\d*\.?\d{0,2}$/
		  } else if(digit == 3){
			  reg = /^\d*\.?\d{0,3}$/
		  } else if(digit == 4){
			  reg = /^\d*\.?\d{0,4}$/
		  } else {
			  reg = /^[-\+]?\d+(\.\d+)?$/
		  }
	  }
	  var ret = regInput(obj, reg, inputStr);
	  if(ret) {
			var tmp = Number(obj.value+inputStr);
			if(minV && tmp < minV) ret = false;
			if(maxV && tmp > maxV) ret = false;
	  }
	  return ret;
	}

function regInput(obj, reg, inputStr){
	  var docSel = document.selection.createRange();
	  if (docSel.parentElement().tagName != "INPUT")return false
	  var oSel = docSel.duplicate();
	  oSel.text = "";
	  var srcRange	= obj.createTextRange();
	  oSel.setEndPoint("StartToStart", srcRange);
	  var str = oSel.text + inputStr + srcRange.text.substr(oSel.text.length);
	  return reg.test(str);
}

function vailForm(obj){
	var formObj = null;
	if(obj instanceof jQuery){
		formObj = obj;
	}else if(typeof obj == "string"){
		formObj = $("#"+obj);
	}else{
		formObj = $(obj);
	}
	var flag = true;
	$(formObj).find("[mustentered='true']").each(function(indx){
		//if($(this).attr("id")!="txfJbxx_xfsyfymc"){
			if($.trim($(this).val())==''){
			    alert($(this).attr("key_content")+"不能为空！");
			    $(this).focus();
			    flag = false;
			    return false;
		     }
		//}
		
	});
	return flag;
}

/**
	检查是否为数字
**/
function checkNum(obj){
	var re = /^-?[1-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	if (!re.test(obj.value)){
		if(isNaN(obj.value)){
			top.$.messager.alert('系统提示',"只能输入数字",'error');
			//alert("只能输入数字");
			obj.value="";
			$(obj).focus();
			return false;
		}
	 }
}

/**
	检查是否为电话、手机
**/
function chkTel(obj, errMsg) {
	var reg = /^(0[\d]{2,3})?\d{6,8}(-\d{3,4})?$/;
	var reg1 = /^(0[\d]{2,3}-)?\d{6,8}(-\d{3,4})?$/;
	var reg2 = /^0?1((3[0-9]{1})|(5[0-9]{1})|(8[0-9]{1})){1}[0-9]{8}$/;
	var value = obj.value;
	if (value != "") {
		if (!reg.test(value) && !reg1.test(value) && !reg2.test(value)) {
			top.$.messager.alert('系统提示',errMsg,'error');
			//alert(errMsg);
			$(obj).focus();
			obj.value="";
			return false;
		}
	}
	return true;
}

/**
	检查是否为Email
**/
function chkEmail(objEmail, blankMsg, errMsg) {
	var isemail = (/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
	var value = objEmail.value;
	blankMsg = blankMsg || "";
	errMsg = errMsg || "";
	if (blankMsg != "" && value == "") {
		//alert(blankMsg);
		top.$.messager.alert('系统提示',blankMsg,'error');
		objEmail.value="";
		$(objEmail).focus();
		return false;
	}
	if (value != "" && !isemail.test(value)) {
		//alert(errMsg);
		top.$.messager.alert('系统提示',errMsg,'error');
		objEmail.value="";
		$(objEmail).focus();
		return false;
	}
	return true;
}

function checkAjzh(obj){
	var re = /^[1-9]*(\.\d*)?$|^[0-9]*(\.\d*)?$/;
	var val = obj.value.trim();
	if (!re.test(val)){
		alert("非法数字");
		obj.value="";
		$(obj).focus();
		return false;
	}else{
	}
}

//checkbox 设值隐藏域
function setCheckboxValue(obj,hiddenId){
	if(obj.checked==true){
		$("#"+hiddenId).val("1");
	}else{
		$("#"+hiddenId).val("0");
	}
}

function createTableTrHTML(obj,prex,tdOrTh,fields){
	 var html = "";
	 for(var i=0;i<fields.length;i++){
		 if(obj&&prex){
			html+="<"+tdOrTh+">"+obj[fields[i]]+"&nbsp;</"+tdOrTh+">";
		 }else{
			html+="<"+tdOrTh+">"+fields[i]+"</"+tdOrTh+">";
		 }
	 }
	 return html;
 }

//判断是否为空
function checkNulls(va){return va==null || va.trim()=='' ||  va== 'null' ||  va== 'NULL' || typeof(va) == 'undefined';}
//如果value不为空，则组装查询条件
function createQuery(before, value, end ){
	var ret = "";
	if(!checkNulls(value) ){
		ret =" "+ before + value + end +" ";
	}
	return ret;
}
function clearNoNum(obj)
{
	//先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g,"");
	//必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g,"");
	//保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g,".");
	//保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}
//JSON数据自动初始化页面数据
function initFormData(data,st,obj){
	   $.each(data,function(key,valu) {
		   if(key==obj){
			   $.each(valu,function(k,v) {
				   if(st.Contains(k)){
					   if(v!=null){
						   $("input[name='"+k+"']").val(v); 
						   $("select[name='"+k+"']").val(v); 
						   $("select[name='"+k+"']").find("option[value='"+v+"']").attr("selected",true);
						   $("textarea[name='"+k+"']").text(v);   
					   }
				   }
			   });
		   }else{
			   $("#"+key).val(valu); 
		   }
	   });
}
//span数据标签填充 
function initFormDataText(data,st,obj){
	   $.each(data,function(key,valu) {
		   if(key==obj){
			   $.each(valu,function(k,v) {
				   if(st.Contains(k)){
					   if(v!=null){
						   $("#"+k).text(v);
					   }
				   }
			   });
		   }else{
			   $("#"+key).val(valu); 
		   }
	   });
}

/**
 * 初始化列表中下拉框数据显示
 * @param arr 列表中的字段所在的数组
 * @param table 列表数据table
 */
function initListSelData(arr,table){
	if(table.rows.length>0){
  	   	var n=0;
  	   	  for(var i=1;i<table.rows.length;i++){
  	   	     $.each(arr,function(k,v) {
  	   	    	   try{
  	   	    		 var code = table.rows[i].cells[v].innerHTML;
  	   	             n=i;
  	   	             if(Trim(code)!=''){
  	   	        	var arr = code.split(','); 
  	   	       var result = "";
	   				for (var j=0;j<arr.length;j++)
	   				{
	   				  $.ajax({  
					    	url: basepath + "/baseData/getCachBaseData.html" , 
					         method:"POST",
					        async:false,
					        dataType: 'json',  
					        data: [{name:'code',value:arr[j]}], 
					         error: function(){  
					        	$.messager.alert('Error', '数据传输失败!', 'error');  
					         },  
					        success: function (data){ 
					        	if(j==0){
					        		result=  data.code_name;
					        	}else{
						        	result=result+","+ data.code_name;
					        	}
					         }  
					     }); 
	   				  
	   				}
	   			     table.rows[n].cells[v].innerHTML= result;
  	   	             }
  	   	    	   }catch(e){
  	   	    		   
  	   	    	   }
  	   	           
     		   });
  	   	  }
  	   	}  
}
function initListSelItemsRate(arr,table){
	if(table.rows.length>0){
  	   	var n=0;
  	   	  for(var i=1;i<table.rows.length;i++){
  	   	     $.each(arr,function(k,v) {
  	   	    	   try{
  	   	    		 var code = table.rows[i].cells[v].innerHTML;
  	   	             n=i;
  	   	             if(Trim(code)!=''){
  	   	            $.ajax({  
				    	url: basepath + "/itemsRateInfo/getCachItemsRateInfo.html" , 
				         method:"POST",
				        async:false,
				        dataType: 'json',  
				        data: [{name:'code',value:code}], 
				         error: function(){  
				        	$.messager.alert('Error', '数据传输失败!', 'error');  
				         },  
				        success: function (data){ 
				           table.rows[n].cells[v].innerHTML=data.code_name;
				         }  
				     });  
  	   	             }
  	   	    	   }catch(e){
  	   	    		   
  	   	    	   }
  	   	           
     		   });
  	   	  }
  	   	}  
}
function initListSelData_list(arr,table){
	if(table.rows.length>0){
  	   	var n=0;
  	   	  for(var i=1;i<table.rows.length;i++){
  	   	     $.each(arr.split(","),function(k,v) {
  	   	    	   try{
  	   	    		 var userid = table.rows[i].cells[v].innerHTML;
  	   	             n=i;
  	   	             if(Trim(userid)!=''){
	  	   	            $.ajax({  
		  	   	           url: basepath + "/baseData/getCachBaseData.html" , 
					         method:"POST",
					        async:false,
					        dataType: 'json',  
					        data: [{name:'code',value:code}], 
					         error: function(){  
					        	$.messager.alert('Error', '数据传输失败!', 'error');  
					         },  
					        success: function (data){ 
					        	alert(data.code_name);
					           //table.rows[n].cells[v].innerHTML=data.code_name;
					         }  
					     });
  	   	             }
  	   	    	   }catch(e){
  	   	    		   
  	   	    	   }
  	   	           
     		   });
  	   	  }
  	   	}  
}
/**
 * 初始化表单中角色数据
 * @param arr  表格中的数据项
 * @param table 表格
 */
function initListRoleData(arr,table){
	if(table.rows.length>0){
  	   	var n=0;
  	   	  for(var i=1;i<table.rows.length;i++){
  	   	     $.each(arr,function(k,v) {
  	   	    	   try{
  	   	    		 var userid = table.rows[i].cells[v].innerHTML;
  	   	             n=i;
  	   	             if(Trim(userid)!=''){
	  	   	            $.ajax({  
					    	url: basepath + "/user/getUserRoleName.html" , 
					        method:"POST",
					        async:false,
					        dataType: 'json',  
					        data: [{name:'userid',value:userid}], 
					         error: function(){  
					        	$.messager.alert('Error', '数据传输失败!', 'error');  
					         },  
					        success: function (data){ 
					           table.rows[n].cells[v].innerHTML='<span class="span_list" title="'+data.role_name+'">'+data.role_name+'</span>';
					         }  
					     });
  	   	             }
  	   	    	   }catch(e){
  	   	    		   
  	   	    	   }
  	   	           
     		   });
  	   	  }
  	   	}  
}

/**
 * 初始化表单中岗位数据
 * @param arr  表格中的数据项
 * @param table 表格
 */
function initListJobData(arr,table){
	if(table.rows.length>0){
  	   	var n=0;
  	   	  for(var i=1;i<table.rows.length;i++){
  	   	     $.each(arr,function(k,v) {
  	   	    	   try{
  	   	    		 var userid = table.rows[i].cells[v].innerHTML;
  	   	             n=i;
  	   	             if(Trim(userid)!=''){
	  	   	            $.ajax({  
					    	url: basepath + "/job/getUserJobsName.html" , 
					        method:"POST",
					        async:false,
					        dataType: 'json',  
					        data: [{name:'userid',value:userid}], 
					         error: function(){  
					        	$.messager.alert('Error', '数据传输失败!', 'error');  
					         },  
					        success: function (data){ 
					           table.rows[n].cells[v].innerHTML='<span class="span_list" title="'+data.job_name+'">'+data.job_name+'</span>';
					         }  
					     });
  	   	             }
  	   	    	   }catch(e){
  	   	    		   
  	   	    	   }
  	   	           
     		   });
  	   	  }
  	   	}  
}

/**
 * 初始化表单中部门数据
 * @param arr  表格中的数据项
 * @param table 表格
 */
function initListDeptData(arr,table){
	if(table.rows.length>0){
  	   	var n=0;
  	   	  for(var i=1;i<table.rows.length;i++){
  	   	     $.each(arr,function(k,v) {
  	   	    	   try{
  	   	    		 var depts = table.rows[i].cells[v].innerHTML;
  	   	             n=i;
  	   	             if(Trim(depts)!=''){
	  	   	            $.ajax({  
					    	url: basepath + "/department/getUserDeptName.html" , 
					        method:"POST",
					        async:false,
					        dataType: 'json',  
					        data: [{name:'depts',value:depts}], 
					         error: function(){  
					        	$.messager.alert('Error', '数据传输失败!', 'error');  
					         },  
					        success: function (data){ 
					           table.rows[n].cells[v].innerHTML=data.dept_name;
					         }  
					     });
  	   	             }
  	   	    	   }catch(e){
  	   	    		   
  	   	    	   }
  	   	           
     		   });
  	   	  }
  	   	}  
}
/**
 * 初始化表单中下拉框数据显示
 * @param id  表单中下拉框数据
 * @param sp_id 表单中下拉框数据非编码显示
 */
function initViewFromSel(id,sp_id){
	if(Trim($("#"+id).val())!=''){
	 $.ajax({  
	    	url: basepath + "/baseData/getCachBaseData.html" , 
	         method:"POST",
	        async:false,
	        dataType: 'json',  
	        data: [{name:'code',value:$("#"+id).val()}], 
	         error: function(){  
	        	$.messager.alert('Error', '数据传输失败!', 'error');  
	         },  
	        success: function (data){ 
	        	if(data.code_name!=null){
	        	$("#"+sp_id).text(data.code_name);
	        	}
	         }  
	     });
	}
}
function initViewFromInput(id,sp_id){
	if(Trim($("#"+id).val())!=''){
	 $.ajax({  
	    	url: basepath + "/baseData/getCachBaseData.html" , 
	         method:"POST",
	        async:false,
	        dataType: 'json',  
	        data: [{name:'code',value:$("#"+id).val()}], 
	         error: function(){  
	        	$.messager.alert('Error', '数据传输失败!', 'error');  
	         },  
	        success: function (data){ 
	        	if(data.code_name!=null){
	        	$("#"+sp_id).val(data.code_name);
	        	}
	         }  
	     });
	}
}
//下拉框行政区数据
function initViewSelOrg(id,sp_id){
	if(Trim($("#"+id).val())!=''){
		 $.ajax({  
		    	url: basepath + "/district/updateOrDistrict.html" , 
		         method:"POST",
		        async:false,
		        dataType: 'json',  
		        data: [{name:'bm',value:$("#"+id).val()}], 
		         error: function(){  
		        	$.messager.alert('Error', '数据传输失败!', 'error');  
		         },  
		        success: function (data){ 
		        	if(data.district.dis_name!=null){
		        		$("#"+sp_id).text(data.district.dis_name);
		        	}
		         }  
		     });
	}
}
Array.prototype.Contains = function(element) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == element) {
            return true;
        }
    }
    return false;
};
Array.prototype.distinct = function(){
    var self = this;
    var _a = this.concat().sort();
    _a.sort(function(a,b){
        if(a == b){
            var n = self.indexOf(a);
            self.splice(n,1);
        }
    });
    return self;
};
//打开层
function openDiv(title,url){
	 layer.open({
		    type: 2,
		    title:title,
		    fix: true,
		    offset: ['1px', ''],
			area: ['100%', '100%'],
		    fix: false, //不固定
		    maxmin: true,
			scrolling: 'auto',
		    content:[url, 'yes']
		});
}
function openDivs(title,url,width,height){
	 layer.open({
		    type: 2,
		    title:title,
		    fix: true,
		    offset: ['1px', ''],
			area: [width, height],
		    fix: false, //不固定
		    maxmin: true,
			scrolling: 'auto',
		    content:[url, 'yes']
		});
}
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}
/**
 * 两个时间进行比较
var current_time = "2007-3-2";
var stop_time = "2007-1-31";
alert(CompareDate(current_time,stop_time));
 */
function CompareDate(d1,d2)
{
  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
}
/**
 *  //四舍五入  
        alert("保留2位小数：" + toDecimal(3.14159267));  
        alert("强制保留2位小数：" + toDecimal2(3.14159267));  
        alert("保留2位小数：" + toDecimal(3.14559267));  
        alert("强制保留2位小数：" + toDecimal2(3.15159267));  
        alert("保留2位小数：" + fomatFloat(3.1, 2));  
        alert("保留1位小数：" + fomatFloat(3.15159267, 1)); 
 * @param x
 * @returns
 */
//保留两位小数   
//功能：将浮点数四舍五入，取小数点后2位  
function toDecimal(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return;  
    }  
    f = Math.round(x*100)/100;  
    return f;  
}  


//制保留2位小数，如：2，会在2后面补上00.即2.00  
function toDecimal2(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return false;  
    }  
    var f = Math.round(x*100)/100;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {  
        rs = s.length;  
        s += '.';  
    }  
    while (s.length <= rs + 2) {  
        s += '0';  
    }  
    return s;  
}  
  
function fomatFloat(src,pos){     
     return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);     
}


/**
 * 调用：fmoney(“12345.675910”, 3)，返回12,345.676
 * @param s
 * @param n
 * @returns {String}
 */
function fmoney(s, n)   
{   
   n=n>0&&n<=20?n:2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }  
   return t.split("").reverse().join("") + "." + r;   
}
/**
 * 还原金额,去除逗号：
 * 调用：rfmoney(“12,345.676”)，返回12345.675
 * @param s
 * @returns
 */
function rmoney(s)   
{   
   return parseFloat(s.replace(/[^\d\.-]/g, ""));   
}
//得到项目信息
function getXmxx(id){
	var path=basepath+"/project/getPorjectInfor.html";
	alert(path);
	$.ajax({ 
      type: "post", 
       url: path,
       cache:false, 
       async:false,
        data:{xmid:id},
       dataType: "json", 
       success: function (data) {
    	    if(data!=''){
    	    	return data;
    	    }else{
    	    	return '';
    	    }
         } 
      });
}
function isEmpty(v) {
    switch (typeof v) {
    case 'undefined':
        return true;
    case 'string':
        if (v.replace(/(^[ \t\n\r]*)|([ \t\n\r]*$)/g, '').length == 0) return true;
        break;
    case 'boolean':
        if (!v) return true;
        break;
    case 'number':
        if (0 === v || isNaN(v)) return true;
        break;
    case 'object':
        if (null === v || v.length === 0) return true;
        for (var i in v) {
            return false;
        }
        return true;
    }
    return false;
}

