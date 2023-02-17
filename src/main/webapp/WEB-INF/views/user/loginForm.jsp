<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
          <label for="username"> username : </label>
          <input type="text" name="username" class="form-control" id="username" placeholder="Enter username" name="username">
        </div>

        <div class="form-group">
          <label for="password"> password : </label>
          <input type="password" name="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>

        <button id="btn-login" class="btn btn-primary"> login </button>
      </form>
</div>
<%@ include file="../layout/footer.jsp"%>

