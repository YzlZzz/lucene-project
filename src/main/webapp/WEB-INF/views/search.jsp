<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://jakarta.apache.org/taglibs/standard/permittedTaglibs" %>
<%--
  Created by IntelliJ IDEA.
  User: yzl
  Date: 2023-10-6
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${pageTitle}</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
</head>
<body>

<div class="search-container">
    <form action="http://localhost:8080/search">
        <img src="img/search.svg" width="30px">
        <input type="text" name="searchInput" id="search-input" required>
        <button type="submit" name="searchButton" value="0" id="search-button" onclick="">搜 索</button>
    </form>
</div>

<div class="output-container" >
    <table class="output-table">
        <tr class="result-line">
            <th class="title" style="background: #E2E2E2">题名</th>
            <th class="author" style="background: #E2E2E2">作者</th>
            <th class="email" style="background: #E2E2E2">邮箱</th>
        </tr>
        <c:forEach items="${docs}" var="doc">
            <tr class="result-line">
                <td><a href="${doc.get("fileName")}">${doc.get("title")}</a></td>
                <td>${doc.get("author")}</td>
                <td>${doc.get("contact")}</td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
<style>
    .result-line{
        height: 50px;
    }
    .title {
        width: 50%;
        text-align: left;
    }

    .author {
        width: 25%;
        text-align: left;
    }

    .email {
        width: 25%;
        text-align: left;
    }

    .output-table {
        width: 100%;
        border: 0;
    }

    .search-container {
        line-height: 60px;
    }

    .search-container img {
        vertical-align: middle;
        margin-right: 20px;
    }

    input {
        padding-left: 15px;
        width: 500px;
        height: 40px;
        border-radius: 20px;
        border: 3px solid black;
        font-size: 18px;
    }

    #search-button {
        width: 100px;
        height: 40px;
        border-radius: 10px;
        border: 3px solid black;
        font-size: 20px;
        font-weight: bold;
        background-color: white;
        margin-left: 15px;
    }

    #search-button:hover {
        background-color: black;
        color: white;
    }
</style>
</html>
