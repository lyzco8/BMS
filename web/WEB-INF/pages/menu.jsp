<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="books?op=index">BMS</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="books?op=list">图书列表</a></li>
                <!--已登录-->
                <c:if test="${not empty user}" var="rt">
                    <li><a href="cart.do">购物车</a></li>
                    <c:if test="${user.state eq 1}">
                        <li><a href="borrow.do">图书归还</a></li>
                    </c:if>
                    <!--管理员用户-->
                    <c:if test="${user.state gt 1}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">管理员
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="borrow.do">图书归还</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="bookAdmin.do">图书管理</a></li>
                                <li><a href="userAdmin.do">用户管理</a></li>
                            </ul>
                        </li>
                    </c:if>
                    <!--管理员用户结束-->
                    <li><a href="modify.do?op=showpass">修改密码</a></li>
                    <li><a href="modify.do?op=showinfo">修改信息</a></li>
                    <li><a href="order.do?op=list">我的订单</a></li>
                    <li><a href="modify.do?op=logoff">安全退出</a></li>
                </c:if>
                <!--已登录结束-->
                <!--未登录-->
                <c:if test="${not rt}">
                    <li><a href="user">登陆</a></li>
                    <li><a href="user?op=showreg">注册</a></li>
                </c:if>
                <!--未登录结束-->
            </ul>
        </div>
    </div>
</nav>