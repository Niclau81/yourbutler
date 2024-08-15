<%--
  Created by IntelliJ IDEA.
  User: NicholasLau
  Date: 12/8/2024
  Time: 10:54 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Login</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      background-color: #f0f0f0;
    }
    .container {
      background-color: white;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h2 {
      text-align: center;
    }
    form {
      display: flex;
      flex-direction: column;
    }
    input {
      margin: 10px 0;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
    }
    input[type="submit"] {
      background-color: #4CAF50;
      color: white;
      cursor: pointer;
    }
    input[type="submit"]:hover {
      background-color: #45a049;
    }
    .error {
      color: red;
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Login</h2>
  <c:if test="${not empty error}">
    <p class="error">${error}</p>
  </c:if>
  <form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" id="username" name="username" placeholder="Username" required>
    <input type="password" id="password" name="password" placeholder="Password" required>
    <input type="submit" value="Login">
  </form>
  <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
</div>
</body>
</html>
