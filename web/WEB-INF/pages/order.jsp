<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bf" uri="http://www.lsf.com/functions" %>

<c:if test="${empty carts}">
    <c:redirect url="books"/>
</c:if>
<html>
<head>
    <title>结算</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/pages/menu.jsp" %>
<%@include file="/WEB-INF/pages/modal.jsp" %>
<c:if test="${not empty msg}">
    <script>
        $(".modal").modal("show");
    </script>
</c:if>
<div class="container">
    <div class="row">
        <table align="center" class="table col-xs-12 table-striped">
            <thead>
            <tr>
                <th>图书名称</th>
                <th>购买数量</th>
                <th>单价</th>
                <th>小计</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="sum" value="0"/>
            <c:forEach var="en" items="${carts}">
                <c:set var="book" value="${bf:getBook(en.value.id)}"/>
                <c:set var="sum" value="${en.value.count*book.price+sum}"/>
                <tr>
                    <td>${book.name}</td>
                    <td>${en.value.count}</td>
                    <td><fmt:formatNumber value="${book.price}" type="currency"
                                          currencySymbol="￥"/></td>
                    <td><fmt:formatNumber value="${en.value.count*book.price}" type="currency"
                                          currencySymbol="￥"/></td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">合计：</td>
                <td colspan="2"><fmt:formatNumber value="${sum}" type="currency"
                                                  currencySymbol="￥"/></td>
            </tr>
            </tfoot>
        </table>
    </div>
    <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
            <form action="order.do">
                <input type="hidden" name="op" value="confirm">
                <label>地址:</label>
                <input type="text" name="address" required>
                <button>确认购买</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
