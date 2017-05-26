window.onload = init;

function init() {
    //初始化对象
    editor = ace.edit("code");

    //设置风格和语言
    theme = "xcode"
    language = "python"
    editor.setTheme("ace/theme/" + theme);
    editor.session.setMode("ace/mode/" + language);

    //字体大小
    editor.setFontSize("90%");

    //设置只读（true时只读，用于展示代码）
    editor.setReadOnly(false);

    //自动换行,设置为off关闭
    editor.setOption("wrap", "free")

    //启用提示菜单
    ace.require("ace/ext/language_tools");
    editor.setOptions({
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true

    });
}

function remove() {
    editor.setValue("");
}

function switchTheme() {
    if ($("#switch-theme-button").text().indexOf("护眼模式") >= 0) {
        $("#switch-theme-button").html("<i class=\"fa fa-eye\"></i> 浅色模式");
        editor.setTheme("ace/theme/tomorrow_night_blue");

    } else if ($("#switch-theme-button").text().indexOf("浅色模式") >= 0) {
        $("#switch-theme-button").html("<i class=\"fa fa-eye\"></i> 护眼模式");
        editor.setTheme("ace/theme/xcode");

    }
}
