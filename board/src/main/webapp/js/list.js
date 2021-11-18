/**
 * qna_board_list.jsp
 */
$(function(){
	//페이지 번호를 클릭하면 actionForm 보내기
	$(".move").click(function(e){
		e.preventDefault();
		
		let href=$(this).attr('href');
		
		$("#actionForm").find("[name='page']").val(href);
		$("#actionForm").submit();
	})
	
	
	
	//검색어에서 엔터 치는 것 방지
	$(":text").keydown(function(e){
		if(e.keyCode == 13){
			e.preventDefault();
		}
	})
	
	
	//검색버튼을 누르면
	//검색조건(criteria), 검색어(keyword) 가져온 후
	//내용이 안들어있으면 메세지 띄우기
	$(".btn-primary").click(function(){
		let criteria = $("[name='criteria']");
		let keyword = $("[name='keyword']");

		if(criteria.val() == "n"){
			alert("검색조건이 없습니다.");
			criteria.focus();
			return;
		}else if(keyword.val() == ""){
			alert("검색어가 없습니다.");
			keyword.focus();
			return;
		}
		
		$("#search").submit();
	})

})