function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

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
                $("#comment_id").hide();
            } else {
                alert(response.tip);
            }
        }
    });
}