<%@ taglib prefix = "tags" uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="formTag" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><tags:message code="title.race"/></title>
</head>
<body>
    <formTag:form method="post" action="/finish" modelAttribute="raceInputForm">
        <table>
            <thead>
                <tr>
                    <td><h1><tags:message code="table.header.race"/></h1></td>
                </tr>
            </thead>
            <tr>
                <td><h3><tags:message code="race.on.the.way"/></h3></td>
            </tr>
            <tr>
                <td><formTag:hidden path="amountLaps" value="${lap}"/></td>
            </tr>
            <tr>
                <td><formTag:hidden path="amountKarts" value="${kart}"/></td>
            </tr>
            <tr>
                <td><button type="submit" name="submit"><tags:message code="button.finish.race"/></button></td>
            </tr>
        </table>
    </formTag:form>
</body>
</html>
