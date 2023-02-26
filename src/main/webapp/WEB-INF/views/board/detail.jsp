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

        <br/>
        <br/>

        <div class="card">

            <form>
                <input type="hidden" id="userId" value="${principal.user.id}"/>
                <input type="hidden" id="boardId" value="${board.id}"/>
                <div>
                    <div class="card-body">
                    <textarea id="reply-content" class="form-control" rows="1"></textarea>
                    </div>

                    <div class="card-footer">
                    <button type="button" id="btn-reply-save" class="btn btn-link"> submit </button>
                    </div>
                </div>
            </form>
        </div>

        <br/>

        <div class="card">
                <div class="card-header"> list </div>
                <ul id="reply--box" class="list-group">
                    <c:forEach var="reply" items="${board.replies}">
                      <li id="reply--1" class="list-group-item d-flex justify-content-between">
                        <div> ${reply.content} </div>
                            <div class="d-flex">
                                <div class="font-italic"> user : ${reply.user.username} &nbsp;&nbsp; </div>
                                <button class="badge"> delete </button>
                            </div>
                      </li>
                    </c:forEach>
                </ul>
        </div>


</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

