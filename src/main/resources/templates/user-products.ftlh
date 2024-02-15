<!DOCTYPE html>
<html>
<head>
    <title>PHARMASY</title>
</head>
<body >
<h1>PHARMASY</h1><hr>
<h4>Товары со всего Мира</h4>
<form action="/" method="get">                                                       //TODO надо action поставить везде
    Поиск по названию объявления: <input type="text" name ="name"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list products as product>
<div>
    <p><b>${product.name} </b> ${product.price} теңге | <a href="/user/product/${product.id}">Подробнее...</a></p>
</div>
<#else>
<h3>Товаров нет</h3>
</#list>
</list>
<hr>
</body>
</html>