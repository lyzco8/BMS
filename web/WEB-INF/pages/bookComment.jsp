<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书详情</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script src="ckeditor/ckeditor.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/pages/menu.jsp" %>
<%@include file="/WEB-INF/pages/modal.jsp" %>
<c:if test="${not empty msg}">
    <script>
        $(".modal").modal("show");
    </script>
</c:if>
<c:if test="${not empty book}">
    <div class="container">
        <div class="row">
            <div class="col-xs-6 col-sm-6 col-xs-offset-3 col-sm-offset-0">
                <img src="imgs/book/${book.id}.jpg" width="100%">
            </div>
            <div class="col-xs-8 col-sm-6 col-xs-offset-2 col-sm-offset-0">
                <h3>${book.name}</h3>
                <h4>${book.author}</h4>
                <h4>${book.pubComp}</h4>
                <h4>${book.pubDate}</h4>
                <h4>${book.price}</h4>
                <h4>
                    <button class="btn btn-lg btn-primary glyphicon glyphicon-book" type="button"
                        ${book.count gt 0?"":"disabled"}
                            onclick="location='borrow.do?op=borrow&bid=${book.id}'">借阅
                    </button>
                    <button class="btn btn-lg btn-danger glyphicon glyphicon-shopping-cart" type="button"
                        ${book.count gt 0?"":"disabled"}
                            onclick="location='cart.do?op=add&bid=${book.id}'">购买
                    </button>
                </h4>
            </div>
        </div>
    </div>

    <hr>
    <c:if test="${empty comments}" var="rt">
        <h2>暂无评论</h2>
    </c:if>
    <c:if test="${not rt}">
        <c:forEach var="com" items="${comments}">
            <div>
                <h3>${com.title}</h3>
                <div>${com.content}</div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty user}">
        <form action="comment.do" method="post">
            <input type="hidden" name="bid" value="${book.id}">
            <div class="container">
                <p class="form-group">
                    <label for="t1">标题</label>
                    <input class="form-control" type="text" name="title" id="t1">
                </p>
                <textarea name="content" class="ckeditor"></textarea>
                <br>
                <button>提交</button>
            </div>
        </form>
    </c:if>
</c:if>
</body>
</html>