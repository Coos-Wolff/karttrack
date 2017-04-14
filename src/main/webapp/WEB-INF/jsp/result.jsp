<%@ taglib prefix = "tags" uri = "http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><tags:message code="title.result"/></title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <td><h1><tags:message code="table.header.result"/></h1></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><tags:message code="label.winner"/></td>
            </tr>
            <tr>
                <td><tags:message code="label.kart.number"/></td>
            </tr>
            <tr>
                <td>${kart}</td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td><tags:message code="label.fastest.lap"/></td>
            </tr>
            <tr>
                <td>${time}</td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td><tags:message code="label.lap.number"/></td>
            </tr>
            <tr>
                <td>${lap}</td>
            </tr>
            <tr>
                <td><tags:message code="label.total.race.time"/></td>
            </tr>
            <tr>
                <td>${totalRaceTime}</td>
            </tr>
        </tbody>
    </table>
    <a href="<tags:url value="/"/>"><tags:message code="url.to.index"/></a>
</body>
</html>
