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
							<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
						</div>
						<form class="user" method="post" action="/registerUser">
							<div class="form-group row">
								<input type="text" class="form-control form-control-user" style=" width: 400px;" id="mi_id" name="mi_id"placeholder="아이디를 입력해주세요">
							<button id="idConfirm" class="btn btn-success" type="button">아이디 중복 확인</button>
							</div>
							<div class="form-group row">
								<input type="password" class="form-control form-control-user" id="mi_pw" name="mi_pw" placeholder="비밀번호를 입력해주세요">
							</div>
							<div class="form-group row">
								<input type="password" class="form-control form-control-user" id="mi_pw_repeat" placeholder="다시 한번 입력해주세요">
								<div id= "confirm" style="color: red; display: none;">비밀번호가 다릅니다</div>
							</div>
							<div class="form-group row">
								<input type="text" class="form-control form-control-user" id="mi_email" name="mi_email" placeholder="E-Mail을 입력해주세요">
							</div>
							<div class="form-group row">
								<input type="text" class="form-control form-control-user" id="mi_mobile" name="mi_mobile" placeholder="휴대폰 번호를 입력해주세요">
							</div>
							<div class="form-group row">
								<input type="text" class="form-control form-control-user" id="mi_tell" name="mi_tell" placeholder="번호를 입력해주세요">
							</div>
							<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
                                        <input class="form-control form-control-user" type="text" id="sample5_address" name="mi_addr1" placeholder="주소">
                                    </div>
                                    <div class="col-sm-6">
                                       <input class="form-control form-control-user" type="text" id="mi_addr2" name="mi_addr2" placeholder="상세주소">
                                    </div>
								<input class="btn btn-primarry" type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
								<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
							</div>
							<button id="submitBtn" class="btn btn-primary btn-user btn-block" type="submit">Register Account</button>
						</form>
						<hr>
						<div class="text-center">
							<a class="small" href="/loginForm">Already have an account? Login!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 API KEY를 사용하세요&libraries=services"></script>
<script>
    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample5_address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
    
    $(document).ready(function(){
    	let result = '';
    	$('#idConfirm').click(function(){
    		$.ajax({
    			url: '/idConfirm',
    			type: 'post',
    			data: ({
                    "mi_id" : $('#mi_id').val()
                }),
    			success : function(data){
    				if(data == ''){
    					alert("사용 가능 아이디 입니다");
    					result = 'success';
    				}else{
    					alert("이미 존재하는 아이디 입니다.");
    					result = 'false';
    				}
    			},
    			error : function(err){
    				console.log(err)
    			}
    		});
    });
    	
    //클릭시	
    $('#submitBtn').click(function(){
    	if(result == 'false'){
    		alert("아이디 중복 확인해주세요");
    		return false;
    	}
    	if($('#mi_id').val() == ''){
    		alert("아이디를 입력해주세요");
    		return false;
    	}
    	if($('#mi_pw').val() == ''){
    		alert("비밀번호를 입력해주세요");
    		return false;
    	}
    	if($('#mi_pw_repeat').val() == ''){
    		alert("비밀번호를 다시 입력해주세요");
    		return false;
    	}
    	if($('#mi_email').val() == ''){
    		alert("이메일을 입력해주세요");
    		return false;
    	}
    	if($('#mi_mobile').val() == ''){
    		alert("휴대폰 번호를 입력해주세요");
    		return false;
    	}
    	if($('#sample5_address').val() == ''){
    		alert("주소를 입력해주세요");
    		return false;
    	}
    	if($('#mi_addr2').val() == ''){
    		alert("상세주소를 입력해주세요");
    		return false;
    	}
    })
    
   	$('#mi_pw_repeat').focusout(function(){
	if($('#mi_pw').val() != $('#mi_pw_repeat').val()){
		$(document).on('focusout', '#mi_pw_repeat', function(){
			if($('#mi_pw').val() != $('#mi_pw_repeat').val()){
			$('#confirm').show();
			}else{
			$('#confirm').hide();
			}
	})
	}
})
});
</script>