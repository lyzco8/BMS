$(function(){
    function fillData(pno,psize) {
        $.getJSON("userajax", {pno:pno,psize:psize}, function (ret) {
            if (ret.errorCode) {
                alert(ret.message);
                return;
            }
            let us=ret.users;
            for (let i = 0; i < us.length; i++) {
                addUser2Table(us[i]);
            }
            modifyNavi(ret.data.page);
        });
    }
    function addUser2Table(user) {
        let tr=$("<tr>");
        $("<td>").text(user.id).appendTo(tr);
        $("<td>").text(user.name).appendTo(tr);
        $("<td>").text(user.age).appendTo(tr);
        $("<td>").text(user.email).appendTo(tr);
        $("<td>").text(user.gender).appendTo(tr);
        $("<td>").text(user.state).appendTo(tr);
        $(".tb").append(tr);
    }
    function modifyNavi(page) {
        $(".pagination").empty();
        let li=$("<li>");
        if(page.start==1){

        }
    }
});