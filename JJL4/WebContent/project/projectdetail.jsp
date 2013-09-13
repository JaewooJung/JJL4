<%@page import="net.bitacademy.java41.vo.Member"%>
<%@page import="net.bitacademy.java41.vo.Project"%>
<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="member" class="net.bitacademy.java41.vo.Member" 
		scope="session"></jsp:useBean>   
<jsp:useBean id="member_project" type="java.util.Collection<net.bitacademy.java41.vo.Project>" 
		scope="session"></jsp:useBean>  
<jsp:useBean id="project_detail" type="net.bitacademy.java41.vo.Project" 
		scope="session"></jsp:useBean>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title>JJL Project - Project Detail</title>
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
            <div class="stats">
    
</div>

            <h1 class="page-title">Project Detail</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="../auth/main">Home</a> <span class="divider">/</span></li>
            <li class="active">ProjectDetail</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    


<div class="row-fluid">
    <div class="block span6">
        <a href="#tablewidget" class="block-heading" data-toggle="collapse"><h2><%=project_detail.getTitle() %></h2></a>
        <div id="tablewidget" class="block-body collapse in">
            <h2>프로젝트 번호 </h2>
            <%= project_detail.getPno() %>
            
            <h2>프로젝트 시작일  </h2>
            <%= project_detail.getStartDate() %><br>
            
            <h2>프로젝트 종료일 </h2>
            <%= project_detail.getEndDate() %><br>
            
            <h2>프로젝트 상세정보  </h2>
            <%= project_detail.getContent() %><br>
            
            <h2>TAG </h2>
            <%= project_detail.getTag() %><br>
            
            <br><br><h2> 멤버 목록</h2>
            
            <table class="table list">
					<tr>
					<th>이름</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>블로그</th>
					<th>권한</th>
					</tr>
					
            <c:forEach var="member" items="${project_detail_members}">
					<tr>
					<td>${member.name}</td>
					<td>${member.email}</td>
					<td>${member.tel}</td>
					<td>${member.blog}</td>
					<c:choose>
					<c:when test="${member.level == 0}">
						<td>관리자</td>
					</c:when>
					<c:when test="${member.level == 1}">
						<td>일반 멤버</td>
					</c:when>
					<c:otherwise>
						<td>설정 안된 값</td>
					</c:otherwise>
					</c:choose>
					</tr>
					
					
			</c:forEach>
			</table>
                        
        </div>
    
    
    
    </div>
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
    
    


    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>


