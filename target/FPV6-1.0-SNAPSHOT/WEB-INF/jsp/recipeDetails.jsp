<%--
  Created by IntelliJ IDEA.
  User: NicholasLau
  Date: 7/8/2024
  Time: 3:09 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recipe Details</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #FFF5E6;
            margin: 0;
            padding: 20px;
        }
        .page-container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #FFFFFF;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .recipe-container {
            margin-bottom: 30px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        h1 {
            font-family: 'Satisfy', cursive;
            color: #FF6B6B;
            text-align: center;
            font-size: 2.5em;
            margin-top: 0;
        }
        .image-container {
            text-align: center;
            margin-bottom: 20px;
            height: 300px;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }
        .image-container img {
            max-width: 100%;
            max-height: 100%;
            object-fit: cover;
            border-radius: 5px;
        }
        .recipe-info {
            margin-bottom: 20px;
        }
        .recipe-info p {
            margin: 5px 0;
        }
        ol {
            padding-left: 20px;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="page-container">
    <c:choose>
        <c:when test="${not empty recipes}">
            <c:forEach var="recipe" items="${recipes}">
                <div class="recipe-container">
                    <h1>${recipe.recipeName}</h1>
                    <div class="image-container">
                        <img src="${pageContext.request.contextPath}${recipe.photo}" alt="${recipe.recipeName}">
                    </div>
                    <div class="recipe-info">
                        <p><strong>Cuisine:</strong> ${recipe.cuisineId}</p>
                        <p><strong>Cooking Time:</strong> ${recipe.cookingTime}</p>
                    </div>
                    <h2>How to Make It:</h2>
                    <ol>
                        <c:forEach var="step" items="${recipe.cookingSteps}">
                            <li>${step.stepDescription}</li>
                        </c:forEach>
                    </ol>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h1>No Recipes Found</h1>
            <p>${message}</p>
        </c:otherwise>
    </c:choose>
    <a href="${pageContext.request.contextPath}/ingredient" class="back-link">Back to Ingredient Selection</a>
</div>
</body>
</html>