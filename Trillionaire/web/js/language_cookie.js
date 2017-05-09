var name = "somoveLanguage";

function chgLang() {
    var value = $("#ddlSomoveLanguage").children('option:selected').val();
    SetCookie(name, value);
    location.reload();
}
function SetCookie(name, value) {
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + encodeURI(value) + ";expires=" + exp.toUTCString();
}

function getCookie(name){    //取cookies函数
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr !== null) return decodeURI(arr[2]);
    return null
}

$(function () {

    var brlanguage = (navigator.language || navigator.userLanguage).toLowerCase();

    if (brlanguage.indexOf("en") > -1) {

        $("[data-localize]").localize("text", {pathPrefix: "json", language: "en"});
    }
    else if (brlanguage.indexOf("ja") > -1) {

        $("[data-localize]").localize("text", {pathPrefix: "json",  language: "ja"});

    } else {

        $("[data-localize]").localize("text", {pathPrefix: "json", language: "en"});
    }
    if (getCookie(name) !== "") {
        if (getCookie(name) === "ja") {
            $("[data-localize]").localize("text", {pathPrefix: "json",  language: "ja"});
        }
        if (getCookie(name) === "en") {
            $("[data-localize]").localize("text", {pathPrefix: "js", language: "en"});

        }

    }
    //根据cookie选择语言

});
