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
          <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" autocomplete="off">
        </div>
        <div class="form-group">
          <label for="email"> email : </label>
          <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>
      </form>
        <button id="btn-save" class="btn btn-primary" title="save"> submit </button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


