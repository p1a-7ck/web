<%@ page import="com.epam.java.rt.lab.action.ActionResult" %>
<% ActionResult result = (ActionResult) request.getAttribute("result"); %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <script src="static/js/bootstrap.min.js"></script>
    <title><%=result.getProperty("title")%></title>

</head>
<body>
    <div class="row">
        <div class="col-xs-offset-1 col-xs-10">
            <jsp:include page="_header.jsp"/>
            <% if (result.getProperty("navs") != null) { %>
                <jsp:include page="<%=(String)result.getProperty(\"navs\")%>"/>
            <% } %>
            <jsp:include page="<%=(String)result.getProperty(\"jsp\")%>"/>
            <jsp:include page="_footer.jsp"/>
        </div>
    </div>
</body>
</html>