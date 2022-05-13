<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<form role="form" method="post" action="/galleryWrite" enctype="multipart/form-data">
		<div class="form-group row">
		<input class="form-control form-control-user" type="text" name="gi_title" id="gi_title" placeholder="제목을 입력해주세요">
		</div>
		<input type="hidden" id="gi_writer" name="gi_writer" value="${user.mi_id}">
		<textarea class="form-control"  id="editor" name="gi_content"></textarea>
		
		<input type="file" id="file" name="file" style="margin: 20px 0 20px 0" multiple="multiple">
		<br>
		<button type="submit" class="btn btn-success"></button>
	</form>
</div>
<script> ClassicEditor 
			.create( document.querySelector( '#editor' ) ) 
			.catch( error => { console.error( error ); } 
			); </script>