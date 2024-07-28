$(function(){
	$("#btn").click(function(){
		var $data = new Array();
		$data[0] = "20";
		$data[1] = "30";
		$data[2] = "40";
		//console.log($data); //배열 출력
		//console.log($data.join(",")); //20,30,40 문자로 출력
		
		$.ajax({
			url:"./ajaxok.do",
			cache:false,
			dataType:"json",
			contentType:"application/json",
			type:"get",
			data:{
				//key
				"all_data":$data.join(",")
			},
			success:function($result){
				console.log($result)
			},
			error:function(){
				console.log("에러발생")
			}
		});
	});
	
	
	$("#btn2").click(function(){
		var $data = new Array();
		$data[0] = "홍길동";
		$data[1] = "강감찬";
		$data[2] = "이순신";
		
		$.ajax({
			url:"./ajaxok2.do",
			type:"post",
			cache:false,
			dataType: "json",
			contentType:"application/json",
			data:JSON.stringify({
				"all_data":$data
			}),
			success:function($result){
				console.log($result);
			},
			error:function(){
				console.log("에러발생")
			}
		})
	})
	
	//front 배열값 응용편
	$("#btn3").click(function(){
		var arr = [["10%","20%","30%"],["30","40","50"]];
		
		$.ajax({
			url:"./ajaxok3.do",
			type:"post",
			cache:false,
			dataType: "text",
			contentType:"application/json; charset:UTF-8",
			data:JSON.stringify(arr),
			success:function($result){
				console.log($result);
			},
			error:function(){
				console.log("에러발생33")
			}
		})
		
	})
	
	
	$("#btn4").click(function(){
		var arr = [["10%","20%","30%"],["30","40","50"]];
		$.ajax({
			url:"./ajaxok3.do",
			type:"get",
			cache:false,
			dataType: "text",
			contentType:"application/json",
			data:JSON.stringify(arr),
			success:function($result){
				console.log($result);
			},
			error:function(){
				console.log("에러발생33")
			}
		})
		
	})
	
		$("#btn5").click(function(){
		var basket = [
			{"seq":"1" ,"product":"냉장고" , "price":"195000"},
			{"seq":"2" ,"product":"세탁기" , "price":"287000"},
			{"seq":"10" ,"product":"에어프라이어" , "price":"97000"},
		];
		$.ajax({
			url:"./ajaxok5.do",
			type:"post",
			cache:false,
			dataType: "json",
			contentType:"application/json; charset:UTF-8",
			data:JSON.stringify(basket),
//			data:{
//				"all_data":basket.join(",")
//			},
			success:function($result){
				console.log($result);
			},
			error:function(){
				console.log("에러발생33")
			}
		})
		
	})
	
})

