<%@ page import="java.net.http.HttpHeaders" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%
    String i18nValue = (String) request.getSession().getAttribute("i18n");
%>

<fmt:setBundle basename="<%=i18nValue%>"/>

<html>
<body>
<head>
    <title>PhraseApp - i18n</title>
</head>

<form action="us" method="post">
    <button type="submit">USA</button>
</form>
<form action="fr" method="post">
    <button type="submit">FR</button>
</form>
<form action="de" method="post">
    <button type="submit">DE</button>
</form>

<h2>i18nVlulue : <%=i18nValue%></h2>
<h2>
    <fmt:message key="farewell" />
</h2>
<h2>
    <fmt:message key="greetings" />
</h2>
<h2>
    <fmt:message key="inquiry" />
</h2>




</body>

</html>
