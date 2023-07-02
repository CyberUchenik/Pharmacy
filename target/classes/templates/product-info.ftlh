<!DOCTYPE html>
<html>
<head>
    <title>PHARMASY</title>
</head>
<body >
<h1>PHARMASY</h1><hr>
<h4>Подробная информация о товаре</h4>
<b>Название товара: </b>${product.name}<br>
<b>Описание товара: </b>${product.description}<br>
<b>Цена товара: </b>${product.price}<br>
<b>Количество товара: </b>${product.quantity}<br>
<hr>
<h3>Создать новый товар</h3>
<form action ="/product/delete/${product.id}" method="post">
    <input type="submit" value ="Удалить товар"/>
</form>
</body>
</html>