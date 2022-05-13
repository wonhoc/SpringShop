<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div>
		<select style="width : 10%" class="custom-select" id="searchtype" name="searchtype">
			<option value="ALL">전체</option>
			<option value="bi_title">제목</option>
			<option value="bi_writer">작성자</option>
		</select>
		<input type="text" name="keyword" id="keyword">
		<button class="btn btn-primary" type="button" id="search_btn">검색</button>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead class="table-primary">
					<tr>
						<th>Title</th>
						<th>Writer</th>
						<th>W-date</th>
					</tr>
				</thead>
				<tbody class="list">
				
				</tbody>
			</table>
		</div>
		<div id="pageNav"></div>
		<a type="button" class="btn btn-primary" href="/boardWriteForm">글작성</a>
	</div>
</div>
<script>
$(document).ready(function(){
	callList(1);
	
	$("#search_btn").click(function(){
		callList(1);
	});

	$(document).on('keyup','#keyword', function(key){
		if(key.keyCode == 13){
			callList(1)
		}
	});
});
let data = {};
function callList(selPage){
	data.pagePerCnt = 10;
	data.curPage = selPage;
	data.keyword = $('#keyword').val();
	data.searchtype = $('#searchtype').val();
	$.ajax({
		type: 'get',
		url: '/getBoardList',
		data: data,
		success: function(value){
			let str = '';
			$(".list").children().remove();
			if(value.list.length == 0){
				str += '<tr><td colspan="3">게시글이 없습니다</td></tr>';
			}else{
				for (var i = 0; i < value.list.length; i++) {
		        	list = value.list[i];
		        	str += '<tr>';
		        	str += '<td><a href="/detailBoard/'+list.bi_no+'">'+list.bi_title+'</a></td>';
		        	str += '<td>'+list.bi_writer+'</td>';
					str += '<td>'+list.bi_insertdt+'</td>';
					str += '</tr>';
				}
			}
			$('.list').append(str);
			$("#pageNav").paging({
		       	pageSize: data.pagePerCnt, 
				currentPage: data.curPage, 
				pageTotal: value.paging.totalCnt
		    });
		},
		error: function(err){
			console.log(err);
		}
	})
}
function goPage(selPage){
	callList(selPage);
}
</script>