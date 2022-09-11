<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ateliê da Maria</title>
</head>
<body>
<h1><%= "Ateliê da Maria" %>
</h1>
<h2><%= "Este sistema lista, adiciona, exclui e atualiza um conserto." %>
</h2>
<h3><%= "Escolha uma das opções abaixo para começar: " %>
</h3>
<input type="button" onclick="location.href='listar-conserto.xhtml';" value="Mostrar consertos" />
<br/>
<input type="button" onclick="location.href='add';" value="Adicionar consertos" />
<br/>
<input type="button" onclick="location.href='edit';" value="Atualizar consertos" />
<br/>
<br/>

</body>
</html>
