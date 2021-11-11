/**
 * 
 */
/**
 * 
 */
$(function(){
	init();
	
	$(":button").click(function(){
		let url ="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=";
		url += $("#txtYear").val() + $("#selMon").val() + $("#selDay").val();
		
		console.log(url);
		
		$.getJSON({
			url:url,
			success:function(data){
				console.log(data);
				
				if($(data.boxOfficeResult.dailyBoxOfficeList) === ""){
					alert("데이터가 없습니다.");
				}else{
					let resData="";
					$(data.boxOfficeResult.dailyBoxOfficeList).each(function(idx,item){
						//순위
						resData += item.rank+" 위";
						
						//증감
						let rankInten = item.rankInten;
						
						if(rankInten>0) resData+="( ▲";
						else if(rankInten<0) resData+= "( ▼";
						else resData += "( ";
						
						resData += rankInten+" )";
						//영화코드
						let movieCd=item.movieCd;
						
						//영화이름
						let movieNm=item.movieNm;
						
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
	let url="http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=";
	url+=movieCd;
	console.log(url);
	
	$.getJSON({
		url:url,
		success:function(data){
			console.log(data);
			let movieInfo= data.movieInfoResult.movieInfo;
			//영화제목
			let movieNm = movieInfo.movieNm;
			//영화제목(영어)
			let movieNmEn=movieInfo.movieNmEn;
			//상영시간
			let showTm=movieInfo.showTm;			
			//감독명
			let directorNm=movieInfo.directors[0].peopleNm;			
			//출연배우명
			let peopleNm="";
			//출연배우의 수
			let length=movieInfo.actors.length;
			
			$(movieInfo.actors).each(function(idx,item){
				if(idx == length-1){
				peopleNm+=item.peopleNm;					
				}else{
				peopleNm+=item.peopleNm+", ";				
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