<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">
</head>
<body>
<div class="container">
<form action="joinProcess.jsp" method="post">
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">아이디</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="userid" name="userid">
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">비밀번호</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="password" name="password">
    </div>
  </div>
    <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">비밀번호 확인</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="password2" name="password2">
    </div>
  </div>
    <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="username" name="username">
    </div>
  </div>
  <fieldset class="form-group row">
    <legend class="col-form-label col-sm-2 float-sm-left pt-0">성별</legend>
    <div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="gender" id="gender" value="man">
  <label class="form-check-label" for="inlineRadio1">남</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="gender2" id="gender2" value="woman">
  <label class="form-check-label" for="inlineRadio2">여</label>
</div>

  </fieldset>
   <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">이메일</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="email" name="email">
    </div>
  </div>
  <div class="form-group row">
	<div>
	<button type="submit" class="btn btn-primary">입력</button>
	</div>
      <div>
      <button type="reset" class="btn btn-secondary">취소</button></div>
      
     
</form>
</div>
</body>
</html>