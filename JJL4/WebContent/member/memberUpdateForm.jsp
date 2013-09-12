<%@page import="net.bitacademy.java41.vo.Member"%>
<%@page import="net.bitacademy.java41.vo.Project"%>
<%@page import="java.util.List"%>


<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="net.bitacademy.java41.vo.Member" 
		scope="session"></jsp:useBean>   
<jsp:useBean id="member_project" type="java.util.Collection<net.bitacademy.java41.vo.Project>" 
		scope="session"></jsp:useBean>  
<jsp:useBean id="allmember" type="java.util.List<net.bitacademy.java41.vo.Member>" 
		scope="session"></jsp:useBean>  
<jsp:useBean id="memberUpdate" type="net.bitacademy.java41.vo.Member" 
		scope="session"></jsp:useBean>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title>Bootstrap Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="../stylesheets/theme.css">
    <link rel="stylesheet" href="../lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    
<jsp:include page="../header.jsp"></jsp:include>
 <jsp:include page="../sidebar.jsp"></jsp:include>
    
    <div class="content">
        
        <div class="header">
            
            <h1 class="page-title">회원 정보 수정 </h1>
        </div>
       
        
            <!-- <li><a href="index.html">Home</a> <span class="divider">/</span></li> 
            <li class="active">Users</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="btn-toolbar">
    <button class="btn btn-primary"><i class="icon-plus"></i> New User</button>
    <button class="btn">Import</button>
    <button class="btn">Export</button>
  <div class="btn-group">
  </div>
</div>-->
<div class="well">
 <form action="memberupdate.do" method="post">
<label>*이메일</label>
<%=memberUpdate.getEmail() %>
<label>*암호</label>
<input type="password" name="password"><br>
<p><a href="../member/passwordresetForm.do" class="btn btn-primary" >암호 재설정<br></a></p>
<label>*이름</label>
<input type="text" name="name" value = "<%=memberUpdate.getName() %>"><br>
<label>*전화</label>
<input type="text" name="tel" value = "<%=memberUpdate.getTel() %>"><br>
<label>블로그</label>
<input type="text" name="blog" value = "<%=memberUpdate.getBlog() %>"><br>
<label>우편번호</label>
<input type="text" name="postno"  value = "<%=memberUpdate.getAddressNo()%>">
<p><input type="button" class="btn btn-primary" value="우편번호찾기"><br></p>
<label>기본주소</label>
<input type="text" name="basicAddr" value = "<%=memberUpdate.getDetailAddress()%>"><br>
<label>상세주소</label>
<input type="text" name="detailAddr" value = "<%=memberUpdate.getDetailAddress()%>"><br>
<label>태그</label>
<input type="text" name="tag" value = "<%=memberUpdate.getTag() %>"><br>
<label>멤버권한</label>
<select name="level">
<option value="0" <%if(memberUpdate.getLevel() == 0){out.println("selected");} %>>일반회원</option>
<option value="1" <%if(memberUpdate.getLevel() == 1){out.println("selected");} %>>관리자</option>
<option value="2" <%if(memberUpdate.getLevel() == 2){out.println("selected");} %>>PM</option>
<option value="9" <%if(memberUpdate.getLevel() == 9){out.println("selected");} %>>손님</option>
</select><br>
<input type="reset" class="btn btn-primary pull-right" value="취소">
<input type="submit" class="btn btn-primary pull-right" value="업데이트">

</form>
</div>

<!-- 
<div class="pagination">
    <ul>
        <li><a href="#">Prev</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">Next</a></li>
    </ul>
</div>
 -->
<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Delete Confirmation</h3>
    </div>
    <div class="modal-body">
        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>Are you sure you want to delete the user?</p>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
        <button class="btn btn-danger" data-dismiss="modal">Delete</button>
    </div>
</div>


                    
                    <footer>
                        <hr>

                        <!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
                        <p class="pull-right">A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free Bootstrap Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a></p>

                        <p>&copy; 2012 <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
                    </footer>
                    
            </div>
        </div>
    </div>
    


    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>


