<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8" />
<title>주소 API</title>
</head>
<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value="" /> <input type="hidden" id="returnUrl" name="returnUrl" value="" /> 
		<input type="hidden" id="resultType" name="resultType" value="" />

	</form>

	<script>

		function init(){
			var url = location.href;
			var confmKey = "devU01TX0FVVEgyMDIxMDcwNTE3MjgyMzExMTM2MTE=";
			var resultType = "4";  // 도로명주소 검색결과 화면 출력내용, 1 : 도로명, 2 : 도로명+지번+상세보기(관련지번, 관할주민센터), 3 : 도로명+상세보기(상세건물명), 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명)
			var inputYn= "${inputYn}";
			if(inputYn != "Y"){
				document.form.confmKey.value = confmKey;
				document.form.returnUrl.value = url;
				document.form.resultType.value = resultType;
				document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do";
			
				document.form.submit();
			}else{
				opener.jusoCallBack("${roadFullAddr}");
				
					window.close();
				}
			}
	</script>
</body>
</html>