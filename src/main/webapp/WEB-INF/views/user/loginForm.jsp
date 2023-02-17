<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <div class="form-group">
          <label for="username"> username : </label>
          <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
        </div>

        <div class="form-group">
          <label for="password"> password : </label>
          <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>
        <div class="form-group form-check">
          <label class="form-check-label">
            <input class="form-check-input" type="checkbox" name="remember"> Remember me
          </label>
        </div>
      </form>
        <button id="btn-login" class="btn btn-primary"> login </button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
