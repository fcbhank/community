function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if (!content) {
        alert("回复内容不能为空！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        dataType: "json",
        success: function (response) {
            if (response.code == 200) {
                // $("#comment_id").hide();
                window.location.reload();
            } else if (response.code == 2003) {
                var isAccepted = confirm(response.tip);
                if (isAccepted == true) {
                    window.open("https://github.com/login/oauth/authorize?client_id=325b2ee6c4c797aa87c6&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                    window.localStorage.setItem("closeable", "true");
                }
            } else {
                alert(response.tip);
            }
        }
    });
}