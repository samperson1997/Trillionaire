<%--
  Created by IntelliJ IDEA.
  User: michaeltan
  Date: 2017/5/6
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Language</title>
    <script src="../js/jquery.localize.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/jquery.localize.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/language_cookie.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="top_lan">
    <select id="ddlSomoveLanguage" onchange="chgLang();">
        <option value="en">ENGLISH</option>
        <option value="ja">日本語</option>
    </select>
</div>
<div>
    <ul class="dropdown-menu">
        <li><a data-localize="hpt.management"></a></li>
        <li><a data-localize="hpt.support"></a></li>
        <li><a data-localize="hpt.tutorial"></a></li>
        <li><a data-localize="hpt.features"></a></li>
    </ul>
</div>
</body>
</html>
