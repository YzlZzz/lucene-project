<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE HTML>

<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" crossorigin="anonymous">

    <title>File Search</title>
    <style>
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

        .search-container{
            text-align: center;
        }
        .search-container a{
            text-align: left;
            width: 50px;
        }


    </style>
</head>
<body>
<br><br><br><br><br><br><br><br>
<center>
    <img  src="img/search.svg" width="7%">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</center>
<br><br>

<div class="search-container">
    <form action="http://localhost:8080/search" method="get">
            <input type="text" name="searchInput" id="search-input" required>
            <button type="submit" name="searchButton" id="search-button" >搜 索</button>
            <a href="http://localhost:8080/advancedsearch" style="margin-left: 5px">高级检索</a>
    </form>
</div>
</body>

</html>