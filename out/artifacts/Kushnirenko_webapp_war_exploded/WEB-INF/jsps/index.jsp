<%--
  Created by IntelliJ IDEA.
  User: ivan
  Date: 08.10.15
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome_page</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery-1.11.1.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/ajax.js"></script>
</head>
<body>
<div class="main" align="center">
    <h2 align="middle" style="font-weight:bold"> Добро пожаловать! </h2>
    Поздравляем, вы <b>${visitor_count}</b> посетитель и ничего не выиграете потому, что мы ничего не разыгрываем.<br/>

    <div id="result" class="StringShow" align="left">
        <ul id="sortable" class="sort">

        </ul>
    </div>
    <p align="center"><textarea id="ta" class="textarea" cols="75" rows="4">Type your text here...</textarea></p>
    <button id="1">Добавить</button>
    <button id="2">Сохранить в файл</button>
</div>
</body>
</html>
