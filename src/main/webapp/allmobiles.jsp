<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<table>
   <thead>
      <tr>
         <th scope="col">ID</th>
         <th scope="col">model</th>
         <th scope="col">price</th>
         <th scope="col">manufacturer</th>
         <th scope="col">link mobile</th>
      </tr>
   </thead>
   <tbody>
   <c:forEach var="mobile" items="${mobiles}">
      <tr>
         <td>${mobile.id}</td>
         <td>${mobile.model}</td>
         <td>${mobile.price}</td>
         <td>${mobile.manufacturer}</td>
         <td><a href="${pageContext.request.contextPath}/showmobile?id=${mobile.id}">Link</a></td>
      </tr>
   </c:forEach>
   </tbody>
</table>

<br>
<a href="/mobiles/mainpage.jsp">Main page</a>