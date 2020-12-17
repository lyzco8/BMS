<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script>
        $(function () {
            $('#imgCode').click(function () {
                this.src = "code.jpg?t=" + new Date().getTime();
            });
        });
    </script>
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
            <form action="user">
                <input type="hidden" name="op" value="login">
                <p class="form-group">
                    <label for="n1">姓名</label>
                    <input class="form-control" type="text" name="name" id="n1">
                </p>
                <p class="form-group">
                    <label>密码</label>
                    <input class="form-control" type="password" name="pass">
                </p>
                <p>
                    <label>验证码</label><img src="code.jpg" id="imgCode" title="看不清楚，点击换一张">
                    <input type="text" class="form-control" name="code">
                </p>
                <p class="form-group">
                    <label>保存时间</label>
                    <select class="form-control" name="savetime">
                        <option value="0">不保存</option>
                        <option value="1">保存1天</option>
                        <option value="7">保存1周</option>
                        <option value="31">保存1月</option>
                        <option value="-1">永久保存</option>
                    </select>
                </p>
                <p class="form-group text-center">
                    <button class="btn btn-sm btn-primary">登录</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-sm btn-danger" type="reset">清空</button>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>

