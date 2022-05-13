<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="table-responsive">
		<form method="get" action="/editBoardForm">
		<input type="hidden" name="bi_no" value="${board.bi_no}">
		<table class="table table-bordered" id="dataTable" width="100%"cellspacing="0">
			<tbody>
				<tr>
					<td class="table-success" colspan="2">${board.bi_title}</td>
				</tr>
				<tr>
					<td>${board.bi_writer}</td>
					<td>${board.bi_insertdt}</td>
				</tr>
				<tr>
					<td colspan="2">${board.bi_content}</td>
				</tr>			
			</tbody>
		</table>
		<c:if test="${board.fileList ne null}">
			<table>
				<thead>
					<tr>
						<td>파일명</td>
						<td>파일크기</td>
					</tr>
				</thead>
				<c:forEach var="file" items="${board.fileList}">
					<tbody>
						<tr>
							<td><a href="/fileDownload/${file.file_num}">${file.file_name}</a></td>
							<td>${file.file_size}</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</c:if>
		<button type="submit" class="btn btn-warning">수정</button>
		</form>
		<button class="btn btn-danger" id="delBtn" >삭제</button>
	</div>
	<div>
		<table>
			<thead>
				<th>작성자</th>
				<th>내용</th>
				<th>작성일</th>
			</thead>
			<tbody id="comment">
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4"><input type="text" id="commentContent" placeholder="댓글을 입력해주세요."></td>
					<td><button class="btn btn-primary" id="commentWrite" type="button">댓글 작성</td>
				</tr>
			</tfoot>
		</table>
		<div id="pageNav"></div>
	</div>
</div>
<script>
$(document).ready(function(){
	$('#delBtn').click(function(){
		deleteBoard();
	});
	
	commentList(1);
	
	$(document).on('click', '#commentWrite', function(){
		writeComment();
	})
	$(document).on('click', '.comDelete', function(){
		let ci_no = $(this).parents("tr").attr("id");
		deleteComment(ci_no);
	})
	$(document).on('click', '.comEdit', function(){
		$(this).parents('tr').next().show();
	})
	$(document).on('click', '.doComEdit', function(){
		let ci_no = $(this).parents("tr").prev().attr("id");
		let ci_content = $(this).parents("tr").children().children('input').val();
		editComment(ci_no, ci_content);
	})
});

function deleteBoard(){
	$.ajax({
		type: 'post',
		url: '/deleteBoard',
		data: ({
			'bi_no' : ${board.bi_no}
		}),
		success: function(value){
			if(value > 0){
				alert("삭제성공");
				location.href = "/boardlist";
			}else{
				alert("삭제실패");
			}
		},
		error: function(err){
			console.log(err);
		}
	});
}

function commentList(selPage){
	let data = {};
	data.pagePerCnt = 10;
	data.curPage = selPage;
	data.board_id = ${board.bi_no};
	$.ajax({
		type:'get',
		url:'/commentBoardList',
		data: data,
		success: function(value){
			let str = '';
			$("#comment").children().remove();
			if(value == null){
				str += '<td colspan="5">등록된 댓글이 없습니다.</td>'
			}else{
				for (var i = 0; i < value.list.length; i++) {
				list = value.list[i];
				str += '<tr id="' + list.ci_no + '">';
				str += '<td>' + list.ci_writer +'</td>'
				str += '<td>' + list.ci_content +'</td>'
				str += '<td>' + list.ci_insertdt +'</td>'
				str += '<td><button type="button" class="comEdit btn btn-outline-warning">댓글 수정</button></td>'
				str += '<td><button type="button" class="comDelete btn btn-outline-danger">댓글 삭제</button></td>'
				str += '</tr>';
				str += '<tr style="display: none;">';
				str += '<td colspan="4"><input class="editArea form-control form-control-user"></td>';
				str += '<td><button type="button" class="doComEdit btn btn-outline-secondary">수정하기</button></td>';
				str += '</tr>'
				}
			}
			$('#comment').append(str);
			$("#pageNav").paging({
		       	pageSize: data.pagePerCnt, 
				currentPage: data.curPage, 
				pageTotal: value.paging.totalCnt
		    });
		}
	});
}

function goPage(selPage){
	commentList(selPage);
}

function writeComment(){
	$.ajax({
		type: 'POST',
		url:'/writeBoardComment',
		data: ({
			"board_id": ${board.bi_no},
			"ci_content": $('#commentContent').val(),
			"ci_writer": ${user.mi_id}
		}),
		success: function(value){
			if(value > 0){
				commentList(1);
				
				var el = $('#commentContent');
                for(var i=0; i<el.length; i++){
                    el[i].value = '';
                }
			}
		}
	});
}

function deleteComment(ci_no){
	$.ajax({
		type: 'POST',
		url:'/deleteBoardComment',
		data: ({
			"ci_no": ci_no
		}),
		success: function(value){
			if(value > 0){
				alert("댓글이 삭제되었습니다.");
				commentList(1);
			}
		}
	});
}

function editComment(ci_no, ci_content){
	$.ajax({
		type: 'POST',
		url:'/editBoardComment',
		data: ({
			"ci_no": ci_no,
			"ci_content": ci_content
		}),
		success: function(value){
			if(value > 0){
				alert("댓글이 수정되었습니다.");
				commentList(1);
			}
		}
	})
}

</script>