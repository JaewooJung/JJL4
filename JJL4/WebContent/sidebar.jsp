<%@page import="net.bitacademy.java41.vo.Member"%>
<%@page import="net.bitacademy.java41.vo.Project"%>
<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${rootPath}/lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="${rootPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${rootPath}/lib/font-awesome/css/font-awesome.css">

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
    <div class="sidebar-nav">
       <center>
        <img src="${rootPath}/images/111.jpeg" >
        </center>
        <table class="table">
              <thead>
                <tr>
                  <th><span class="label label-info">Name</span></th>
                  <th><a href="../auth/mymemberupdate">${member.name}</a></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                <td><span class="label label-info">E-mail </span></td>
                <td>${member.email}</td>
                </tr>
                <tr>
                <td><span class="label label-info">Tel No</span></td>
                <td>${member.tel}</td></tr>
                <tr>
                </tr>
              </table>
        
        <a href="${rootPath }/main.do" class="nav-header"><i class="icon-dashboard"></i>Home</a>
        
        <a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>Account<!-- <span class="label label-info">+3</span> --></a>
        <ul id="accounts-menu" class="nav nav-list collapse in">
            <li ><a href="${rootPath}/auth/loginForm.do">Log In Other</a></li>
            <li ><a href="${rootPath}/member/signinForm.do">Sign In</a></li>
         
        </ul>

 		<c:choose>
	    <c:when test="${member.level != '1'}">
        	<a href="${rootPath}/project/allproject.do" class="nav-header"><i class="icon-legal"></i>View All Projects</a>
        </c:when>
        </c:choose>
        <c:choose>
	        <c:when test="${member.level == '1'}">
		        <a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-user"></i>Manager</a>
		        <ul id="legal-menu" class="nav nav-list collapse in">
		            
		            <li ><a href="${rootPath}/member/allmember.do">Member Control </a></li>
		            <li ><a href="${rootPath}/project/allproject.do">Project Control </a></li>
		        </ul>
		    </c:when>
	    <c:otherwise>
       	 	<a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-legal"></i>Project<span class="label label-info">${fn:length(member_project)}</span></a>
        		<ul id="legal-menu" class="nav nav-list collapse in">
            
            	<c:forEach var="project" items="${member_project}">
					<li><a href="${rootPath}../auth/projectdetail?pno=${project.pno}">${project.title}
						<c:if test="${project.level == 0}">
						<span class="label label-info">Leader</span>
						</c:if>
					</a>
					</li>
				</c:forEach>
            	
        		</ul>
        
        </c:otherwise>
        </c:choose>
    </div>
