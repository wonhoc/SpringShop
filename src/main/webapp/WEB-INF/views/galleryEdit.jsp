<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<form role="form" method="post" action="/galleryEdit" enctype="multipart/form-data">
		<div class="form-group row">
			<input class="form-control form-control-user" type="text" name="gi_title" id="gi_title" value="${gallery.gi_title}">
		</div>
		<input type="hidden" id="gi_no" name="gi_no" value="${gallery.gi_no}">
		<textarea class="form-control"  id="editor" name="gi_content">${gallery.gi_content}</textarea>
		<input type="file" id="file" name="file" style="margin: 20px 0 20px 0" multiple="multiple"><br>
		<label for="fileBox"><strong>기존파일</strong></label>
		<c:if test="${gallery.fileList ne null}">
			<table id="fileBox">
				<thead>
					<th>파일명</th>
					<th></th>
					<th>파일크기</th>
				</thead>
				<c:if test="${gallery.fileList ne null}">
				<c:forEach var="file" items="${gallery.fileList}">
						<tbody>
							<tr id="${file.file_num}">
								<td>${file.file_name}</td>
								<td><input class="saved_file_name" type="hidden" value="${file.saved_file_name}"></td>
								<td>${file.file_size}</td>
								<td><button id="asdds" type="button" class="fileDelBtn btn btn-danger">파일삭제</button></td>
							</tr>
						</tbody>
				</c:forEach>
					</c:if>
			</table>
		</c:if>
		<div id="deletedFile">
			
		</div>
		<button type="submit" class="btn btn-success">수정하기</button>
	</form>
</div>
<script> ClassicEditor 
			.create( document.querySelector( '#editor' ))
			.catch( error => {console.error( error );}); 
$(document).ready(function(){
	delFile();
})
function delFile() {
	$('.fileDelBtn').click(function(){
		let file_num = $(this).parents("tr").attr("id");
		let saved_file_name = $('#'+file_num).find('input').val();
		
		$('#'+file_num).remove();	
		
		let str = '';
		str += '<input type="hidden" name="file_num" value="' + file_num + '">'
		$('#deletedFile').append(str)
		console.log($('#deletedFile'))
	})
}
</script>
