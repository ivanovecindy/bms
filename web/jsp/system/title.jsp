<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <c:choose>
  <c:when test="${not empty modeList}">
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <c:forEach items="${modeList}" var="m" varStatus="status">
		  <li>
		   <c:if test="${!status.last}">
		   <a href="javascript:void(0)"> ${m.modName}</a>->
		   </c:if>
		  <c:if test="${status.last}">
		     ${m.modName}
		     <input id="mcode" name="mcode" value="${m.id}" type="hidden"/>
		   </c:if> 
			</li>
		</c:forEach>
    </ul>
    </div></c:when></c:choose>