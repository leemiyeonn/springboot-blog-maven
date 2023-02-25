<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}"/>
        <div class="form-group">
          <label for="username"> username : </label>
          <input type="text" value="${principal.user.username}" class="form-control" id="username" placeholder="Enter username" name="username" readonly>
        </div>
        <div class="form-group">
          <label for="password"> password : </label>
          <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" autocomplete="off">
        </div>
        <div class="form-group">
          <label for="email"> email : </label>
          <input type="email" value="${principal.user.email}" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>
      </form>
        <button id="btn-update" class="btn btn-primary" title="save"> submit </button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


