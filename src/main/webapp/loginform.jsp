<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>Login</title>
    </head>

    <body>
        <h3>Login Page</h3>
        <form method="POST" action="${pageContext.request.contextPath}/login" autocomplete="off">
            <table border="0">
                <tr>
                    <td>User Name</td>
                    <td><input type="text" name="loginId" value= "" /> </td>
                </tr>
                <tr>
                    <td colspan ="2">
                        <input type="submit" value= "Submit" />
                        <a href="${pageContext.request.contextPath}/">Cancel</a>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>