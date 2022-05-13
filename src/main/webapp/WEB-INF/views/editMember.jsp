<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%


 %>

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
						<form class="user" method="post" action="/editMember">
							<input type="hidden" name="mi_id" value="${user.mi_id}">
							<div class="form-group row">
								<label for="mi_pw">Password</label>
								<input type="password" class="form-control form-control-user" id="mi_pw" name="mi_pw">
							</div>
							<div class="form-group row">
								<label for="pwRepeat">PasswordRepeat</label>
								<input type="password" class="form-control form-control-user" id="pwRepeat" name="pwRepeat">
							</div>
							<div class="form-group row">
								<label for="mi_email">E-Mail</label>
								<input type="text" class="form-control form-control-user" id="mi_email" name="mi_email" value="${user.mi_email}">
							</div>
							<div class="form-group row">
								<label for="mi_mobile">Phone</label>
								<input type="text" class="form-control form-control-user" id="mi_mobile" name="mi_mobile" value="${user.mi_mobile}">
							</div>
							<div class="form-group row">
								<label for="mi_tell">Tell</label>
								<input type="text" class="form-control form-control-user" id="mi_tell" name="mi_tell" value="${user.mi_tell}">
							</div>
							<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
                                        <input class="form-control form-control-user" type="text" id="sample5_address" name="mi_addr1" value="${user.mi_addr1}">
                                    </div>
                                    <div class="col-sm-6">
                                       <input class="form-control form-control-user" type="text" id="sample5_address" name="mi_addr2" value="${user.mi_addr2}">
                                    </div>
								<input class="btn btn-primarry" type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
								<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
							</div>
							<button class="btn btn-primary btn-user btn-block" type="submit">회원정보 수정</button>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 API KEY를 사용하세요&libraries=services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });


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
</script>