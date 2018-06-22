$(document).ready(function () {
    $(".photo").click(function () {
        var img = $(this);
        var src = img.attr('src');
        var id = img.attr('value');
        var currUser = document.getElementById("currentUser").value;
        var usersPhoto = document.getElementById("userId").value;
        $("body").append(
            "<div class='image-info-bg' id='image-info-bg'>" +
            "<div class='image-info'>" +
            "<img src='" + src + "' class='img'/>" +
            "</div>" +
            "<div class='add-comment-form'>" +
            "<form method='post' action='/add-comment/" + id + "'>" +
            "<textarea class='add-comment-text' name='text'/><br/>" +
            "<input type='hidden' name='userId' value='" + currUser + "'/>" +
            "<button>Отправить</button>" +
            "</form>" +
            "</div>" +
            "</div>" +
            "<div class='comments' id='comments'>" +
            "</div>"
        );
        if (currUser === usersPhoto) {
            $("#image-info-bg").append(
                "<div class='photo-settings'>" +
                "<form method='post' action='/set-main-photo/" + id + "'>" +
                "<button>Сделать основной</button>" +
                "<br/>" +
                "</form>" +
                "<form method='post' action='/remove-photo/" + id + "'>" +
                "<button>Удалить фото</button>" +
                "</form>" +
                "</div>");
        }
        $.get("/comments/" + id, function (data) {
            var commentsList = JSON.parse(data);
            for (var comment in commentsList) {
                $(".comments").append(
                    "<div class='comment'>" +
                    "<form method='get' action='/user_" + commentsList[comment].userId + "'>" +
                    "<input type='image' class='user-photo'" +
                    " src='/photo/" + commentsList[comment].photo + "' class='img'/>" +
                    "</form>" +
                    "<div class='user-name'>" + commentsList[comment].name + "</div>" +
                    "<div class='comment-date'>" + commentsList[comment].date + "</div>" +
                    "<div class='comment-text'>" + commentsList[comment].text + " </div>" +
                    "</div>");
            }
        });
        $(".image-info-bg").fadeIn(800);
        $(".img").click(function () {
            $(".image-info-bg,.comments").fadeOut(800);
            setTimeout(function () {
                $(".image-info-bg,.comments").remove();
            }, 800);

        });
    });
});
