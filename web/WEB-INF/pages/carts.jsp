<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bf" uri="http://www.lsf.com/functions" %>
<html>
<head>
    <title>购物车</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script>
        function deleteSelected() {
            let bids = document.getElementsByName("bid");
            let ids = [];
            for (let i = 0; i < bids.length; i++) {
                if (bids[i].checked) {
                    ids.push(bids[i].value);
                }
            }
            if (ids.length == 0) {
                alert('请先选中要批量删除的图书');
                return;
            }
            let url = "cart.do?op=dels";
            for (let i = 0; i < ids.length; i++) {
                url += "&bid=" + ids[i];
            }
            location = url;
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/pages/menu.jsp" %>
<h3 class="text-danger">${msg}</h3>
<div class="container">
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1">
            <div class="col-xs-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th>
                            <button class="bt btn-xs btn-primary" onclick="deleteSelected()">批量删除
                            </button>
                        </th>
                        <th>图书名称</th>
                        <th>购买数量</th>
                        <th>单价</th>
                        <th>小计</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty carts}" var="rt">
                        <tr>
                            <td colspan="5">暂无选购图书</td>
                        </tr>
                    </c:if>
                    <c:if test="${not rt}">
                        <c:set var="sum" value="0"/>
                        <c:forEach var="en" items="${carts}">
                            <c:set var="book" value="${bf:getBook(en.value.id)}"/>
                            <c:set var="sum" value="${en.value.count*book.price+sum}"/>
                            <tr>
                                <td><input type="checkbox" name="bid" value="${en.key}"></td>
                                <td>${book.name}</td>
                                <td>
                                    <button class="btn btn-xs btn-primary glyphicon glyphicon-minus"
                                            onclick="location='cart.do?op=sub&bid=${en.key}'"></button>
                                        ${en.value.count}
                                    <button class="btn btn-xs btn-primary glyphicon glyphicon-plus"
                                            onclick="location='cart.do?op=add&bid=${en.key}'"></button>
                                    <button class="btn btn-xs btn-danger glyphicon glyphicon-remove"
                                            onclick="location='cart.do?op=del&bid=${en.key}'"></button>
                                </td>
                                <td><fmt:formatNumber value="${book.price}" type="currency"
                                                      currencySymbol="￥"/></td>
                                <td><fmt:formatNumber value="${en.value.count*book.price}" type="currency"
                                                      currencySymbol="￥"/></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="3">合计：</td>
                        <td colspan="2"><fmt:formatNumber value="${sum}" type="currency"
                                                          currencySymbol="￥"/></td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
    <h3><a href="order.do?op=show" class="btn btn-lg btn-info">结算</a>
        <a href="cart.do?op=clear" class="btn btn-lg btn-success">清空购物车</a></h3>
</div>
</body>
</html>

