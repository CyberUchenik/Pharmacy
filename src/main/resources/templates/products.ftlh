<!DOCTYPE html>
<html>
<head>
    <title>PHARMASY</title>
</head>
<body >
<h1>PHARMASY</h1><hr>
<h4>Товары со всего Мира</h4>
<form action="/" method="get">
      Поиск по названию объявления: <input type="text" name ="name"><br>
      <input type="submit" value="Поиск"/>
</form>
<#list products as product>
    <div>
        <p><b>${product.name} </b> ${product.price} теңге | <a href="/product/${product.id}">Подробнее...</a></p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>
<hr>
<h3>Создать новый товар</h3>
<form action ="/product/create" method="post" enctype="multipart/form-data">
    Название товара: <input type ="text", name ="name"/><br><br>
    Описание товара: <input type ="text", name ="description"/><br><br>
    Цена товара: <input type ="number", name ="price"/><br><br>
    Количество товара: <input type ="number", name ="quantity"/><br><br>
    Первая фотография: <input type ="file", name ="file1"/><br><br>
    Вторая фотография: <input type ="file", name ="file2"/><br><br>
    Третья фотография : <input type ="file", name ="file3"/><br><br>

    <input type = "submit" value = "Добавить товар"/>
</form>
</body>
</html>