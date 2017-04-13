<%@ taglib prefix = "tags" uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="formTag" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- TODO i18n -->
    <formTag:form method="post" action="/race" modelAttribute="raceInputForm">
        <table>
            <tr>
                <td>Amount Laps: <formTag:input path="amountLaps"/></td>
            </tr>
            <tr>
                <td>Amount Karts: <formTag:input path="amountKarts"/></td>
            </tr>
            <tr>
                <td><button type="submit" value="Start Race" name="submit">Start Race</button> </td>
            </tr>
        </table>
    </formTag:form>
</body>
</html>
