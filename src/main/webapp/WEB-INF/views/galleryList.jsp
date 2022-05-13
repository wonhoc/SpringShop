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
	<div class="card-group">
		<div id="list"></div>
	</div>
	<div id="pageNav"></div>
	<a type="button" class="btn btn-primary" href="/galleryWriteForm">글작성</a>
</div>
<script>
$(document).on('ready',function(){
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
		url: '/getGalleryList',
		data: data,
		success: function(value){
			let str = '';
			$("#list").children().remove();
			if(value.galleryList.length == 0){
				str += '<div>등록된 계시글이 없습니다.</div>';
			}else{
				for (var i = 0; i < value.galleryList.length; i++) {
	        		list = value.galleryList[i];
	        		str += '<div class="crad">';
	        		str += '<a href="/galleryDetail/'+list.gi_no+'">';
	        		str += '<div class="card-body">';
	        		if(list.saved_file_name != null){
	        			str += '<img style="width:150px;" src="/resources/file/' + list.saved_file_name + '" class="card-img-top">';
	        		}else{
	        			str += '<img src="/resources/img/noimage.jpg" style="width:150px;" class="card-img-top">';
	        		}
	        		str += '<h5 class="card-title">' + list.gi_title + '</h5>';
	        		str += '<p class="card-text">'+list.gi_writer+'</p>';
					str += '<p class="card-text">'+list.gi_insertdt+'</p>';
	        		str += '<p class="card-text">'+list.gi_content+'</p>';
					str += '</div>';
					str += '</a>';
					str += '</div>';
				}
			}
			$('#list').append(str);
			$('#pageNav').paging({
		       	pageSize: data.pagePerCnt, 
				currentPage: data.curPage, 
				pageTotal: value.paging.totalCnt
		    });
		},
		error: function(err){
			console.log(err);
		}
	});
};
function goPage(selPage){
	callList(selPage);
}
</script>