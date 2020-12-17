<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>修改密码</title>
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
        <div class="col-xs-4 col-xs-offset-4">
            <form action="modify.do" method="post">
                <input type="hidden" name="op" value="pass">
                <p class="form-group">
                    <label for="p1">旧密码:</label>
                    <input class="form-control" type="password" name="oldpass" id="p1">
                </p>
                <p class="form-group">
                    <label for="p2">新密码:</label>
                    <input class="form-control" type="password" name="newpass1" id="p2">
                </p>
                <p class="form-group">
                    <label for="p3">新密码:</label>
                    <input class="form-control" type="password" name="newpass2" id="p3">
                </p>
                <p class="form-group text-center">
                    <button class="btn btn-sm btn-primary">修改</button>
                    <button class="btn btn-sm btn-danger" type="reset">清空</button>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>

