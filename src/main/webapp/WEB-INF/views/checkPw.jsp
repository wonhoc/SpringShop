<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<form role="form" method="post" action="/checkPw">
		<div class="form-group row">
			<input type="password" class="form-control form-control-user" id="mi_pw" name="mi_pw" placeholder="비밀번호를 입력해주세요">
		</div>
		<button type="submit" class="btn btn-success">작성</button>
	</form>

</div>
