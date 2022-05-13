<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<form role="form" method="post" action="/boardEdit">
		<div class="form-group row">
			<input class="form-control form-control-user" type="text" name="bi_title" id="bi_title" value="${board.bi_title}">
		</div>
		<input type="hidden" id="bi_no" name="bi_no" value="${board.bi_no}">
		<textarea class="form-control"  id="editor" name="bi_content">${board.bi_content}</textarea>
		<button type="submit" class="btn btn-success">수정하기</button>
	</form>
</div>
<script> ClassicEditor .create( document.querySelector( '#editor' ) ) .catch( error => { console.error( error ); } ); </script>