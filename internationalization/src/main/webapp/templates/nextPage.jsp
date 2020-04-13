<%@ page import="java.net.http.HttpHeaders" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%
    String i18nValue = "MessagesBundle_en_US";
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if ((cookie == null) || (cookie.getName() == null)) {
            continue;
        }
        if (cookie.getName().equals("i18n")) {
            i18nValue = cookie.getValue();
        }
    }
%>

<fmt:setBundle basename="<%=i18nValue%>"/>


<html>
<body>
<head>
    <title>PhraseApp - i18n</title>
</head>

<form action="internationalization/us" method="post">
    <button type="submit">USA</button>
</form>
<form action="internationalization/fr" method="post">
    <button type="submit">FR</button>
</form>
<form action="internationalization/de" method="post">
    <button type="submit">DE</button>
</form>

<h2><%=i18nValue%>
</h2>
index.jsp templates
<h2>
    <fmt:message key="farewell"/>
</h2>
</body>
<form action="previousPage.jsp">
    <button type="submit">Previous</button>
</form>

<form action="nextPage.jsp">
    <button type="submit">Next</button>
</form>
</html>
