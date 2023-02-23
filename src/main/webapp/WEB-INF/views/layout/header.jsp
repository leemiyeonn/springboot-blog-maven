<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
  <title> blog </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>

<body>

  <nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/"> home </a>
  <button class="navbar-toggler" title="navbar" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
  <span class="navbar-toggler-icon" title="navbar-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">

  <c:choose>
  <c:when test="${empty principal}">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/auth/loginForm"> log in </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/auth/joinForm"> sign up </a>
      </li>
    </ul>
  </c:when>


  <c:otherwise>
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/board/saveForm"> write </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/user/updateForm"> info </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/logout"> logout </a>
      </li>
    </ul>
  </c:otherwise>
  </c:choose>




  </div>
</nav>
<br />