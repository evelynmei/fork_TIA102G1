//DOMContentLoaded
$(function () {

    //Side bar有子層的項目, 才有切換底色效果
    $(document).on("click", "nav.mt-2", function (e) {
        var target = e.target;
        if ($(target).closest("li").find("ul").hasClass("nav-treeview")) {
            $(target).closest("li>a").toggleClass("active");
        }
    });

    //子項目被點擊的切換底色效果
    $(document).on("focus", "ul[class='nav nav-treeview']", function (e) {
        var target = e.target;
        $(target).closest("li>a").addClass("active");

        //離開焦點
        $(target).on("blur", function () {
            $(target).closest("li>a").removeClass("active");
        });
    });


});