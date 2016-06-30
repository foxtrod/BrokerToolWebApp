<%@ page import="trader.Customer" %>
<%@ page import="java.security.cert.TrustAnchor" %>
<%--
  Created by IntelliJ IDEA.
  User: horbachevsky
  Date: 29.06.2016
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllCustomers</title>
</head>
<body>

<table border="1">
    <tr>
        <td>
            <a href='CustomerDetails'>Customer Details</a>
        </td>
        <td>
            <a href='AllCustomers'>All Customers</a>
        </td>
        <td>
            <a href="Stocks.xhtml">Stocks</a>
        </td>
    </tr>
</table>

<br>

<table border="1">
    <thead>
        <tr>
            <th>Customer Id</th>
            <th>Name</th>
            <th>Address</th>
            <th>Portfolio</th>
        </tr>
    </thead>
    <%
        Customer[] customers = (Customer[]) request.getAttribute("customers");

        for (Customer customer : customers) {
    %>
        <tr>
            <td><a href="CustomerController?customerIdentity=<%= customer.getId()%>&submit=Get Customer"><%= customer.getId() %></a></td>
            <td><%= customer.getName() %></td>
            <td><%= customer.getAddr() %></td>
            <td><a href="PortfolioController?customerIdentity=<%= customer.getId() %>">View</a></td>
        </tr>
    <%
        }
    %>

</table>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println(message);
        }
    %>


</body>
</html>
