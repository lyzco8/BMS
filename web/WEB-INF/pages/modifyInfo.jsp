<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>修改信息</title>
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
                <input type="hidden" name="op" value="info">
                <p class="form-group">
                    <label for="i1">ID</label>
                    <input class="form-control" type="number" name="id" value="${user.id}" id="i1" disabled>
                </p>
                <p class="form-group">
                    <label for="n1">姓名:</label>
                    <input class="form-control" type="text" name="name" value="${user.name}" id="n1">
                </p>
                <p class="form-group">
                    <label for="e1">邮箱:</label>
                    <input class="form-control" type="email" name="email" value="${user.email}" id="e1">
                </p>
                <p class="form-group">
                    <label>性别:</label>
                    <input type="radio" name="gender" id="m1" value="M" ${user.gender eq 'M'?"checked":""} >
                    <label for="m1">男</label>
                    <input type="radio" name="gender" id="f1" value="F" ${user.gender eq 'F'?"checked":""} >
                    <label for="f1">女</label>
                </p>
                <p class="form-group">
                    <label for="a1">年龄:</label>
                    <input class="form-control" type="number" name="age" value="${user.age}" id="a1">
                </p>
                <p class="form-group">
                    <label for="p2">电话:</label>
                    <input class="form-control" type="tel" name="phone" value="${user.phone}" id="p2">
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
