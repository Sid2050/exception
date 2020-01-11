<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
    private String str = "PageTitle: ";
    public String createTitle(String arg) {
        return str + arg;
    }
%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="resources/css/TableStyle.css" />
        <title><%= createTitle((String) request.getAttribute("PageTitle"))%></title>
    </head>
    <body>
        <div>
            <c:import url="${PageBody}"/>
        </div>
    </body>
</html>