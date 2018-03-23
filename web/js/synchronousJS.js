//几个js文件同步加载	
//.在页面中调用这个first.js文件，并在页面底部调用这个方法，需要添加几个js就调用几次。
/**
 * 方法有两个参数一个是url，另一个是返回函数
 */
function loadScript(url, callback){  
 	    var script = document.createElement("script")  
 	    script.type = "text/javascript";  
 	    if (script.readyState){  //IE  
 	        script.onreadystatechange = function(){  
 	            if (script.readyState == "loaded" || script.readyState == "complete"){  
 	                script.onreadystatechange = null;  
	                callback();  
 	            }  
 	        };  
 	    } else {  //Others  
        script.onload = function(){  
 	            callback();  
 	        };  
     }  
 	    script.src = url;  
     document.getElementsByTagName("head")[0].appendChild(script);  
 	} 