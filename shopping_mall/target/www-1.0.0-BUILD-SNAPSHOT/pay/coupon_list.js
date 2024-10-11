var uri = window.location.search; //웹 URL에 있는 ? 파라미터 값 가져오기
var pageno = ""; //페이지 번호 - 최초는 1부터
if(uri==""){ //최초 접속시
	pageno = 1;
}else{ //page번호 클릭시
	pageno = uri.split("?page=")[1]; //페이지번호만 추출
}

function ajax_data(){
	var http;
	http = new XMLHttpRequest;
	http.onreadystatechange = function(){
		if(http.readyState==4&&http.status==200){
			html_code(JSON.parse(this.response));
		}
	}
	http.open("GET","./coupon_api.do",true); //true:비동기처리
	http.send();
}
ajax_data(); //즉시 실행


//JSON data를 HTML로 출력시키는 함수
function html_code(data){
	var datano = 3; //1페이지당 3개의 data - 고정값
	
	var startpg = (pageno-1)*datano; //data배열의 시작 node번호
	var endpg = datano*pageno; //data배열의 끝나는 node번호
	
	var ea = data.length; //api data의 총 배열 갯수
	
	var result = document.getElementById("list");
	var pagehtml = document.getElementById("pages");
	
	document.getElementById("total").append(ea);
	
	//paging출력 - 반복문을 이용하여 page번호를 출력
	//Math.ceil사용 -> 무조건 올려야함 (3.5가 나오면 페이지 갯수 4개)
	var pgtotal = Math.ceil(ea/datano);
	 
	for(var p=1; p<=pgtotal; p++){ 
		pagehtml.innerHTML += `
			<td><a href="./coupon_list.jsp?page=${p}">${p}</a></td>
		`;
	}
	
	//data출력
	data.forEach(function(value,idx,all){
		if(idx >= startpg && idx < endpg){
			result.innerHTML += `
				<tr>
					<td>${ea-idx}</td>
					<td>${value.cpname}</td>
					<td>${value.cprate}</td>
					<td>${value.cpuse}</td>
					<td>${value.cpdate}</td>
				</tr>
			`;
		}
	})

}