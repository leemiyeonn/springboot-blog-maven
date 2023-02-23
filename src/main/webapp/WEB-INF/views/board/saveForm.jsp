<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<%@ include file="../layout/header.jsp"%>

<div class="container">

    <form>
        <div class="form-group">
          <label for="title"> title </label>
          <input type="text" class="form-control" placeholder="Enter title" id="title">
        </div>

        <div class="form-group">
          <label for="comment"> content </label>
          <textarea class="form-control" rows="5" id="content"></textarea>
        </div>

        <button id="btn-save" class="btn btn-primary"> save </button>
    </form>

</div>

<%@ include file="../layout/footer.jsp"%>

