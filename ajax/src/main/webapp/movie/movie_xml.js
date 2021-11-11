/**
 * 
 */
$(function(){
	init();
	
	$(":button").click(function(){
		let url ="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=f5eef3421c602c6cb7ea224104795888&targetDt=";
		url += $("#txtYear").val() + $("#selMon").val() + $("#selDay").val();
		
		console.log(url);
		
		$.get({
			url:url,
			success:function(data){
				console.log(data);
				
				if($(data).find("dailyBoxOffice").text() === ""){
					alert("데이터가 없습니다.");
				}else{
					let resData="";
					$(data).find("dailyBoxOffice").each(function(){
						//순위
						resData += $(this).find('rank').text()+" 위";
						
						//증감
						let rankInten = $(this).find('rankInten').text();
						
						if(rankInten>0) resData+="( ▲";
						else if(rankInten<0) resData+= "( ▼";
						else resData += "( ";
						
						resData += rankInten+" )";
						//영화코드
						let movieCd=$(this).find('movieCd').text();
						
						//영화이름
						let movieNm=$(this).find('movieNm').text();
						
						resData +="<a href='#' onclick='javascript:show("+movieCd+")'>"+movieNm+"</a><br>";
						//출력
					})
					$("#msg").html(resData);
				}
			}
		})
	})
})
function show(movieCd){
	//영화상세정보 요청
	let url="http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.xml?key=f5eef3421c602c6cb7ea224104795888&movieCd=";
	url+=movieCd;
	console.log(url);
	
	$.get({
		url:url,
		success:function(data){
			console.log(data);
			
			//영화제목
			let movieNm = $(data).find("movieNm").text();
			//영화제목(영어)
			let movieNmEn=$(data).find("movieNmEn").text();
			//상영시간
			let showTm=$(data).find("showTm").text();			
			//감독명
			let directorNm=$(data).find("director").find("peopleNm").text();			
			//출연배우명
			let peopleNm="";
			//출연배우의 수
			let length=$(data).find("actor").find("peopleNm").length;
			
			$(data).find("actor").each(function(idx){
				if(idx == length-1){
				peopleNm+=$(this).find("peopleNm").text();					
				}else{
				peopleNm+=$(this).find("peopleNm").text()+", ";				
				}
			})
			//보여주기
			let resData = "<ul>";
			resData +="<li>영화제목(국문) :"+movieNm+"</li>";
			resData +="<li>영화제목(영문) :"+movieNmEn+"</li>";
			resData +="<li>상영시간 :"+showTm+"분</li>";
			resData +="<li>감독 :"+directorNm+"</li>";
			resData +="<li>출연배우 :"+peopleNm+"</li></ul>";
			
			$(".box3").html(resData);
			
		}
	})
	
}//show() end
function init(){
	let newDate = new Date();
	let year = newDate.getFullYear();
	let month = newDate.getMonth()+1;
	let day = newDate.getDate()-1;
	
	$("#txtYear").val(year);
	
	if(month<10){
		month= "0"+month;
	}
	if(day<10){
		day="0"+day;
	}
	$("#selMon").val(month);
	$("#selDay").val(day);
}