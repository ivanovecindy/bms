package com.entity;

public class Page {
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private String pageStrTemp; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private int showCount = 10; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private String JSMethod;
    private String currentPage_temp;
	public String getJSMethod() {
		return JSMethod;
	}

	public void setJSMethod(String JSMethod) {
		this.JSMethod = JSMethod;
	}

	private int goPage;

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			sb.append("	<div class=\"message\">共<i class=\"blue\">"+totalResult+"</i>条记录，当前显示第&nbsp;<i class=\"blue\">"+currentPage+"&nbsp;</i>页</div>");
			sb.append("	<ul  class=\"paginList\">\n");
			sb.append("	<li class=\"paginItem\"><a href=\"javascript:void(0)\" onclick=\"nextPage(1)\"><span class=\"pagepres\"></span></a></li>\n");
            if(currentPage!=1){
              int per_page = currentPage-1;
				sb.append("	<li class=\"paginItem\"><a href=\"javascript:void(0)\" onclick=\"nextPage("+per_page+")\"><span class=\"pagepre\"></span></a></li>\n");
			}
			int showTag =10; // 分页标签显示数量
			int startTag = 1;
			if (currentPage > showTag) {
				startTag = currentPage - 1;
			}
			int mm=5;
			int startPage=1;
			int res = currentPage-mm;
			if(res>0){
				startPage=res;
			}else {
				startPage=1;
			}
			//int endTag = startTag + showTag - 1;
			int endTag = startPage + showTag - 1;
			for (int i = startPage; i <= totalPage && i <= endTag; i++) {
				if (currentPage == i)
					sb.append("<li class=\"paginItem current\">" + i + "</li>\n");
				else
					sb.append("	<li class=\"paginItem\"><a href=\"javascript:void(0)\" onclick=\"nextPage(" + i
							+ ")\">" + i + "</a></li>\n");
			}
			sb.append(" <li class=\"paginItem\">跳转到第<input id=\"pages_\" name=\"pages_\"  style=\"width: 30px;\"/>页 <input type=\"button\" onclick=\"goToPage();\" value=\"GO\" /></li>\n");
			
			sb.append("	<li class=\"paginItem\"><a href=\"javascript:void(0);\" onclick=\"nextPage(" + totalPage
						+ ")\"><span class=\"pagenxt\"></span></a></li> \n");
			sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("	 	document.forms[0].action = url+page;\n");
			sb.append("	   document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append(" document.location = url + page;\n");
			sb.append("	}\n");
			sb.append("}\n");		
			sb.append("function goToPage(){\n var pages = document.getElementById(\"pages_\").value; \n");
			sb.append("if(pages!=''){\n nextPage(pages);\n	}\n}");
			sb.append("</script>\n");
			//System.out.println(sb.toString());
		}
		pageStr = sb.toString();
		//System.out.println(pageStr);
		return pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getGoPage() {
		return goPage;
	}

	public void setGoPage(int goPage) {
		this.goPage = goPage;
		this.currentPage = goPage;
	}
	public static void main(String[] args){
		
	}

	public String getPageStrTemp() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			sb.append("	<div class=\"message\">共<i class=\"blue\">"+totalResult+"</i>条记录，当前显示第&nbsp;<i class=\"blue\">"+currentPage+"&nbsp;</i>页</div>");
			sb.append("	<ul  class=\"paginList\">\n");
			sb.append("	<li class=\"paginItem\"><a href=\"javascript:void(0)\" onclick=\"nextPage(1)\"><span class=\"pagepres\"></span></a></li>\n");
			if(currentPage!=1){
				int per_page = currentPage-1;
				sb.append("	<li class=\"paginItem\"><a href=\"javascript:void(0)\" onclick=\"nextPage("+per_page+")\"><span class=\"pagepre\"></span></a></li>\n");
			}
			int showTag =10; // 分页标签显示数量
			int startTag = 1;
			if (currentPage > showTag) {
				startTag = currentPage - 1;
			}
			int mm=5;
			int startPage=1;
			int res = currentPage-mm;
			if(res>0){
				startPage=res;
			}else {
				startPage=1;
			}
			//int endTag = startTag + showTag - 1;
			int endTag = startPage + showTag - 1;
			for (int i = startPage; i <= totalPage && i <= endTag; i++) {
				if (currentPage == i)
					sb.append("<li class=\"paginItem current\">" + i + "</li>\n");
				else
					sb.append("	<li class=\"paginItem\"><a href=\"javascript:void(0)\" onclick=\"nextPage(" + i
							+ ")\">" + i + "</a></li>\n");
			}
			sb.append(" <li class=\"paginItem\">跳转到第<input id=\"pages_\" name=\"pages_\"  style=\"width: 30px;\"/>页 <input type=\"button\" onclick=\"goToPage();\" value=\"GO\" /></li>\n");

			sb.append("	<li class=\"paginItem\"><a href=\"javascript:void(0);\" onclick=\"nextPage(" + totalPage
					+ ")\"><span class=\"pagenxt\"></span></a></li> \n");
			sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
		/*	if (entityOrField){
				sb.append("$(\"#"+currentPage_temp+"\").val(page);");
			}else{
				sb.append("$(\"#"+currentPage_temp+"\").val(page);");
			}*/
			sb.append(" "+getJSMethod()+"(page);}\n");
			sb.append("function goToPage(){\n var pages = document.getElementById(\"pages_\").value; \n");
			sb.append("if(pages!=''){\n nextPage(pages);\n	}\n}");
			sb.append("</script>\n");
			//System.out.println(sb.toString());
		}
		pageStrTemp = sb.toString();
		//System.out.println(pageStr);
		return pageStrTemp;
	}

	public String getCurrentPage_temp() {
		return currentPage_temp;
	}

	public void setCurrentPage_temp(String currentPage_temp) {
		this.currentPage_temp = currentPage_temp;
	}

	public void setPageStrTemp(String pageStrTemp) {
		this.pageStrTemp = pageStrTemp;
	}
}
