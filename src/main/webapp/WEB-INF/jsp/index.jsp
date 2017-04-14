<%@ taglib prefix = "tags" uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="formTag" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><tags:message code="title.index"/></title>
</head>
<body>
<div>
    <formTag:form method="post" action="/start" modelAttribute="raceInputForm">
        <table>
            <thead>
                <div>
                    <tr>
                        <td><h1><tags:message code="table.header.index"/></h1></td>
                    </tr>
                </div>
            </thead>
            <tbody>
                <tr>
                    <td><tags:message code="input.amount.laps"/> </td>
                </tr>
                <tr>
                    <td><formTag:input path="amountLaps"/></td>
                </tr>
                <tr>
                    <td><tags:message code="input.amount.karts"/> </td>
                </tr>
                <tr>
                    <td><formTag:input path="amountKarts"/></td>
                </tr>
                <tr>
                    <td><button type="submit" name="submit"><tags:message code="button.start.race"/></button></td>
                </tr>
            </tbody>
        </table>
    </formTag:form>
</div>
</body>
</html>
