<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<%@ include file="../layout/header.jsp"%>

<div class="container">
        <div>
            <button class="btn btn-outline-info" onclick="history.back()"> back </button>
            <br/><br/>
            <div>
                🔢 : <span id="id"><i> ${board.id} </i></span>
                🧸 : <span><i> ${board.user.username} </i></span>
            </div>
            <br />
        <div>
              <h3> ${board.title} </h3>
        </div>
            <hr />
        <div>
              <div> ${board.content} </div>
        </div>
            <hr />

            <c:if test="${board.user.id == principal.user.id}">
            <a href="/board/${board.id}/updateForm" class="btn btn-info"> edit </a>
            <button id="btn-delete" class="btn btn-danger"> delete </button>
            </c:if>

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

