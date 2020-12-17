<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的订单</title>
    <%@include file="/WEB-INF/pages/inc.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/pages/menu.jsp"%>
<%@include file="/WEB-INF/pages/modal.jsp" %>
<c:if test="${not empty msg}">
    <script>
        $(".modal").modal("show");
    </script>
</c:if>
<div class="container">
    <div class="row">
        <table align="center" class="table col-xs-12 table-striped" >
            <thead>
            <tr>
                <th>订单编号</th>
                <th>地址</th>
                <th>总价</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty orders}" var="rt">
                <tr><td colspan="4">暂无订单</td></tr>
            </c:if>
            <c:if test="${not rt}">
                <c:forEach var="od" items="${orders}">
                    <tr>
                        <td>${od.oid}</td>
                        <td>${od.address}</td>
                        <td>${od.summary}</td>
                        <td>
                            <button class="btn btn-xs btn-danger"
                                    onclick="location='order.do?op=del&oid=${od.oid}'">退货
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

