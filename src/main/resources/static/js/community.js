/**
 * 发布评论
 */
function postComment(e, type) {
    var parentId = null;
    var content = null;
    if (type == 1) {
        //    QUESTION：回复问题的评论
        parentId = $("#question_id").val();
        content = $("#comment_content").val();
    } else if (type == 2) {
        //    COMMENT:回复评论的评论
        var inputBox = e.previousElementSibling;
        parentId = inputBox.getAttribute("id").substring(11);
        content = inputBox.value;
    }
    // console.log("id和内容： " + parentId + "+++" + content);

    if (!content) {
        alert("回复内容不能为空！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": parentId,
            "content": content,
            "type": type
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

/**
 * 展开/折叠二级评论时，同时改变背景颜色
 * @param e:当前控件
 */
function show_subComments(e) {
    var subCommentId = e.getAttribute("data-target").substring(1);
    if ($("#" + subCommentId).hasClass("in")) {
        e.style.backgroundColor = "#efefef"
        // 之前是展开的，点击后将折叠
    } else {
        // 之前是折叠的，点击后将展开
        e.style.backgroundColor = "#499ef3";
        var subCommentContainer = $('#subComment-' + subCommentId).parent().parent();
        // debugger;
        if (subCommentContainer.children().length == 1) {
            // 已经加载过了
            $.getJSON("/comment/" + subCommentId, function (data) {
                console.log("开始填充二级评论内容");
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading sub-h5",
                        "html": comment.user.name
                    })).append($("<span/>", {
                        "html": "  •  "
                    })).append($("<span/>", {
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    })).append($("<div/>", {
                        "class": "sub-comment-content",
                        "html": comment.content
                    })).append($("<hr/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12"
                    }));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });

                console.log("填充二级评论内容结束")
            });
        }
    }

}
