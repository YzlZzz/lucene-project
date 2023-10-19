<%--
  Created by IntelliJ IDEA.
  User: yzl
  Date: 2023-10-16
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>高级检索</title>
</head>
<body>
<form action="http://localhost:8080/search" method="post">
    <br>
    <div style="text-align: center" >
        <img src="img/search.svg" width="70px">
    </div>

    <br><br>
    <div class="search-container">
        <label for="andQuery">包含以下全部关键词（AND）：</label>
        <input type="text" name="and" id="andQuery">
    </div>
    <br>

    <div class="search-container">
        <label for="orQuery">包含以下任意关键词（OR）：</label>
        <input type="text" name="or" id="orQuery">
    </div>
    <br>

    <div class="search-container">
        <label for="notQuery">不包含以下关键词（NOT）：</label>
        <input type="text" name="not" id="notQuery">
    </div>
    <br>

    <div class="options">
        <label>搜索域：</label>

        <input type="radio" name="searchFields" id="fullText" value="0" checked>
        <label for="fullText">全文</label>
        <input type="radio" name="searchFields" id="title" value="1">
        <label for="title">标题</label>
        <input type="radio" name="searchFields" id="author" value="2">
        <label for="author">作者</label>
    </div>
    <br><br>

    <div style="text-align: center" >
        <button type="submit" name="searchButton" id="search-button">搜  索</button>
    </div>

</form>

</body>
<style>
    .search-container {
        text-align: center; /* 让容器内的内容居中对齐 */
        height: 30px;

    }

    .search-container input{
        border-radius: 5px;
        height: 25px;
    }

    .search-container label {
        display: inline-block;
        text-align: right; /* 标签文本靠右对齐 */
        width: 230px; /* 调整标签的宽度，使文本靠输入框右端对齐 */
    }

    .options{
        text-align: center;
    }

    .options label{
        margin-right: 10px;
    }

    #search-button {
        width: 80px;
        height: 35px;
        border-radius: 5px;
        border: 2px solid black;
        font-size: 18px;
        font-family: 黑体;
        font-weight: bold;
        background-color: white;
    }

    #search-button:hover {
        background-color: black;
        color: white;
    }
</style>
</html>
