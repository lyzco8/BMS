<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
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
            <form action="user" method="post">
                <input type="hidden" name="op" value="reg">

                <p class="form-group">
                    <label for="n1">姓名</label>
                    <input class="form-control" type="text" name="name" id="n1">
                </p>
                <p class="form-group">
                    <label>密码</label>
                    <input class="form-control" type="password" name="pass">
                </p>
                <p class="form-group">
                    <label>再次输入密码</label>
                    <input class="form-control" type="password" name="repass">
                </p>
                <p class="form-group">
                    <label for="e1">邮箱</label>
                    <input class="form-control" type="email" name="email" id="e1">
                </p>
                <p class="form-group">
                    <label>性别</label>
                    <input type="radio" name="gender" id="m1" value="M" checked>
                    <label for="m1">男</label>
                    <input type="radio" name="gender" id="f1" value="F">
                    <label for="f1">女</label>
                </p>
                <p class="form-group">
                    <label for="a1">年龄</label>
                    <input class="form-control" type="number" name="age" id="a1">
                </p>
                <p class="form-group">
                    <label for="p2">电话</label>
                    <input class="form-control" type="tel" name="phone" id="p2">
                </p>
                <p class="form-group text-center">
                    <button class="btn btn-sm btn-primary">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-sm btn-danger" type="reset">清空</button>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>

