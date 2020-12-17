<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员还书</title>
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
        <table class="col-xs-12 table">
            <thead>
            <tr>
                <th>用户姓名</th>
                <th>图书名称</th>
                <th>借阅时间</th>
                <th>应归还时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bookvos}" var="bvo">
                <tr>
                    <td>${bvo.userName}</td>
                    <td>${bvo.bookName}</td>
                    <td>${bvo.lendDate}</td>
                    <td>${bvo.willDate}</td>
                    <td>
                        <button class="btn btn-xs btn-primary"
                                onclick="location='borrow.do?op=return&bid=${bvo.bid}&uid=${bvo.rid}'">归还
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