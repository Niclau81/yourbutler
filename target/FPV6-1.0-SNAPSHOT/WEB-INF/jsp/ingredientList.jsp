<%--
  Created by IntelliJ IDEA.
  User: NicholasLau
  Date: 7/8/2024
  Time: 3:07 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Butler - Ingredient Selection</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&family=Satisfy&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #FFF5E6;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            max-width: 1500px;
            width: 90%;
            background-color: #FFFFFF;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        h1 {
            font-family: 'Satisfy', cursive;
            color: #FF6B6B;
            font-size: 2.5em;
            text-align: center;
            margin-top: 0;
            margin-bottom: 20px;
        }
        .ingredient-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
            gap: 5px;
            margin-bottom: 10px;
        }
        .ingredient-item {
            display: flex;
            align-items: center;
        }
        .ingredient-item label {
            display: flex;
            align-items: center;
            width: 100%;
            margin-top: 2px;
        }
       .ingredient-item input[type="checkbox"] {
            margin-right: 10px;
        }
        .cuisine-section {
            flex: 0 0 60%;
        }
        .form-footer {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
        }
        select {
            width: 20%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #FF6B6B;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #FF8E8E;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="user-section">
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
                    <input type="text" name="username" placeholder="Username" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <input type="submit" value="Login">
                </form>
            </c:when>
            <c:otherwise>
                <p>Welcome, ${sessionScope.user.username}!</p>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <input type="submit" value="Logout" class="logout-button">
                </form>
            </c:otherwise>
        </c:choose>
    </div>

    <h1>Food Waste Reduction Recipe Finder</h1>
    <form action="${pageContext.request.contextPath}/recipe" method="post">
        <h2>Cuisine:</h2>
        <div class="cuisine-section">
            <select name="cuisineId" id="cuisine">
                <option value="">Any Cuisine</option>
                <c:forEach var="cuisine" items="${cuisines}">
                    <option value="${cuisine.cuisineId}">${cuisine.cuisineName}</option>
                </c:forEach>
            </select>
        </div>
        <h2>Ingredients:</h2>
        <div class="ingredient-grid">
            <c:forEach var="ingredient" items="${ingredients}">
                <div class="ingredient-item">
                    <label>
                        <input type="checkbox" name="ingredientIds" value="${ingredient.ingredientId}">
                            ${ingredient.ingredientName}
                    </label>
                </div>
            </c:forEach>
        </div>
        <div class="form-footer">
            <input type="submit" value="Find Recipes">
        </div>
    </form>
    <div class="contribute-link">
        <a href="mailto:admin@yourbutler.com?subject=Recipe%20Contribution&body=I%20would%20like%20to%20contribute%20a%20recipe%3A%0A%0ARecipe%20Name%3A%20%0AIngredients%3A%20%0AInstructions%3A%20%0A%0AThank%20you%21">Contribute a Recipe</a>
    </div>
</div>
</body>
</html>