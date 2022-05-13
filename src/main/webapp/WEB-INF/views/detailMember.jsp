<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

	<div class="card o-hidden border-0 shadow-lg my-5">
		<div class="card-body p-0">
			<!-- Nested Row within Card Body -->
			<div class="row">
				<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
				<div class="col-lg-7">
					<div class="p-5">
						<div class="text-center">
							<h1 class="h4 text-gray-900 mb-4">Detail Member</h1>
						</div>
							<div class="form-group row">
								<span>ID :&nbsp;</span><div id="mi_id">${user.mi_id}</div>
							</div>
							<div class="form-group row">
								<span>E-Mail :&nbsp;</span><div>${user.mi_email}</div>
							</div>
							<div class="form-group row">
								<span>휴대폰 :&nbsp;</span><div>${user.mi_mobile}</div>
							</div>
							<c:if test="${user.mi_tell ne null}"><div class="form-group row">
								<span>전화번호 :&nbsp;</span><div>${user.mi_tell}</div>
							</div>
							</c:if>
							<div class="form-group row">
								<span>주소 :&nbsp;</span><div>${user.mi_addr}</div>
							</div>
							<div class="form-group row">
								<span>가입일 :&nbsp;</span><div>${user.mi_insertdt}</div>
							</div>
							<div class="form-group row">
                                <button class="btn btn-primary" type="button" id="editBtn">정보수정</button>
                                <button class="btn btn-danger" type="submit" id="deleteBtn">회원탈퇴</button>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function(){
		let user = "${user.mi_id}";
		console.log(user);
		$('#editBtn').click(function(){
			location.href = "/editForm";
		});
		
		$('#deleteBtn').click(function(){
			var delConfirm = confirm('정말 탈퇴 하시겠습니까?.');
			if(delConfirm){
				location.href = "/deleteMember";
			}
		});
	});
</script>