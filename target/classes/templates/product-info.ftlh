<!DOCTYPE html>
<html>
<head>
    <title>PHARMASY</title>
</head>
<body >
<h1>PHARMASY</h1><hr>
<h4>Подробная информация о товаре</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Название товара: </b>${product.name}<br>
<b>Описание товара: </b>${product.description}<br>
<b>Цена товара: </b>${product.price}<br>
<b>Количество товара: </b>${product.quantity}<br>
<form action="/product/sell/${product.id}" method="post">
    <b>Количество: </b><input type="number" name="quantity"><br><br>
    <input type="submit" value="Купить">
</form>
<hr>
<form action="/product/update/${product.id}" method="post" enctype="multipart/form-data">
    <label for="name">Название товара:</label>
    <input type="text" id="name" name="name" value="${product.name}" required><br><br>

    <label for="description">Описание товара:</label>
    <input type="text" id="description" name="description" value="${product.description}" required><br><br>

    <label for="price">Цена товара:</label>
    <input type="number" id="price" name="price" value="${product.price}" required><br><br>

    <label for="quantity">Количество товара:</label>
    <input type="number" id="quantity" name="quantity" value="${product.quantity}" required><br><br>

    <label for="file1">Фотография 1:</label>
    <input type="file" id="file1" name="file1"><br><br>

    <label for="file2">Фотография 2:</label>
    <input type="file" id="file2" name="file2"><br><br>

    <label for="file3">Фотография 3:</label>
    <input type="file" id="file3" name = "file3"><br><br>
    <input type="submit" value="Изменить товар">
</form>
<form action = " /product/delete/${product.id}" method="post">
    <input type="submit" value ="Удалить товар"/>
</form>
</body>
</html>