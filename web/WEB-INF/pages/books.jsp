<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>图书列表</title>
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
                <th>作者</th>
                <th>出版社</th>
                <th>出版日期</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td><a href="books?op=one&bid=${book.id}">${book.name}</a></td>
                    <td>${book.author}</td>
                    <td>${book.pubComp}</td>
                    <td>${book.pubDate}</td>
                    <td>
                        <button class="btn btn-xs btn-primary glyphicon glyphicon-book" type="button"
                            ${book.count gt 0?"":"disabled"}
                                onclick="location='borrow.do?op=borrow&bid=${book.id}'">借阅
                        </button>
                        <button class="btn btn-xs btn-danger glyphicon glyphicon-shopping-cart"
                                type="button" ${book.count gt 0?"":"disabled"}
                                onclick="location='cart.do?op=add&bid=${book.id}'">购买
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

