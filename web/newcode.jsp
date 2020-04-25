<%-- 
    Document   : newcode
    Created on : Dec 16, 2016, 11:44:16 PM
    Author     : Milind Ghiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
	<head>
	<title>Codester | Work</title>
	<meta charset="utf-8">
	<link rel="icon" href="http://dzyngiri.com/favicon.png" type="image/x-icon">
    <link rel="shortcut icon" href="http://dzyngiri.com/favicon.png" type="image/x-icon" />
    <meta name="description" content="Codester is a free responsive Bootstrap template by Dzyngiri">
    <meta name="keywords" content="free, template, bootstrap, responsive">
    <meta name="author" content="Inbetwin Networks">  
	<link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen">
	<link rel="stylesheet" href="css/responsive.css" type="text/css" media="screen">
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
	<link rel="stylesheet" href="css/touchTouch.css" type="text/css" media="screen">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/superfish.js"></script>
    <script type="text/javascript" src="js/jquery.easing.1.3.js"></script>  
    <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/touchTouch.jquery.js"></script> 
    
	<script type="text/javascript">if($(window).width()>1024){document.write("<"+"script src='js/jquery.preloader.js'></"+"script>");}	</script>
	<script>		
		 jQuery(window).load(function() {	
		 $x = $(window).width();		
	if($x > 1024)
	{			
	jQuery("#content .row").preloader();    }			 
	 jQuery('.magnifier').touchTouch();
		 jQuery('.spinner').animate({'opacity':0},1000,'easeOutCubic',function (){jQuery(this).css('display','none')});	
  		  }); 
					
	</script>
 <script>
        function toggle(id) {
            if (document.getElementById(id).style.display == 'none') {
                document.getElementById(id).style.display = 'block';
            } else {
                document.getElementById(id).style.display = 'none';
            }
        }
    </script>
    <script>
</script>
	<!--[if lt IE 8]>
  		<div style='text-align:center'><a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/img/upgrade.jpg"border="0"alt=""/></a></div>  
 	<![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->
	<!--<![endif]-->
	<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link rel="stylesheet" href="css/docs.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400' rel='stylesheet' type='text/css'>
  <![endif]-->
	
    <!--Google analytics code-->	  
	  <script type="text/javascript">
           var _gaq = _gaq || [];
          _gaq.push(['_setAccount', 'UA-29231762-1']);
          _gaq.push(['_setDomainName', 'dzyngiri.com']);
          _gaq.push(['_trackPageview']);
        
          (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
          })();
      </script>
      <script src='js/autosize.js'></script>
      <script>
		autosize(document.querySelectorAll('textarea'));
	</script>
  
	</head>

	<body>
<div class="spinner"></div>
<!-- header -->
<header>
      <div class="container clearfix">
    <div class="row">
          <div class="span12">
        <div class="navbar navbar_">
              <div class="container">
            <h1 class="brand brand_"><a href="index.jsp"><img alt="" src="img/logo.png"> </a></h1>
            <a class="btn btn-navbar btn-navbar_" data-toggle="collapse" data-target=".nav-collapse_">Menu <span class="icon-bar"></span> </a>
            <div class="nav-collapse nav-collapse_  collapse">
                  <ul class="nav sf-menu">
                <li ><a  href="index.jsp">Home</a></li >
                <li class="active"><a href="newcode.jsp">New Code</a></li>
             <li><a  href="signin.jsp">Sign in</a></li>
             <li class="sub-menu"><a href="#">Sign up</a>
                      <ul>
                    <li><a href="signup.jsp">Student</a></li>
                    <li><a href="signup2.jsp">College</a></li>
                      </ul>
             </li>
             
             <li><a href="aboutus.html">About us</a></li>
              
			  </ul>
                </div>
          </div>
            </div>
      </div>
        </div>
  </div>
    </header>
<div class="bg-content" >       
  <!-- Content -->      
   <body >
       
           <script>
    window.onload = function() {
var d = new Date();
document.getElementById("demo").innerHTML = d.toDateString();

    <c:if test="${sessionScope.code==null}">
    
        if(document.getElementById('s').selectedIndex==0)
 document.getElementById('tA').value="Please select a programming language from below and start coding!";
  </c:if>
    document.getElementById("sub").addEventListener("submit", mySubmit);
    <c:if test="${sessionScope.code!=null}">
            
 document.getElementById('s').value=${sessionScope.lang};
    </c:if>
        
        
    };
    </script>
    <script>
function report(period)
{ if(document.getElementById('s').selectedIndex==0)
 document.getElementById('tA').value="Please select a programming language from below and start coding!";
 
    
    if(period=="0")
    document.getElementById('tA').value="import java.util.*;\n import java.lang.*;\n import java.io.*;\n class JavaApp{\npublic static void main (String[] args) throws java.lang.Exception{\n// your code goes here \n}}"

 if(period=="1")
    document.getElementById('tA').value="#include <stdio.h>\n\nint main(void) {\n// your code goes here\nreturn 0;\n}";

        if(period=="2")
    document.getElementById('tA').value="#include <iostream>\nusing namespace std;\nint main() {\n// your code goes here\nreturn 0;\n}";

        if(period=="3")
    document.getElementById('tA').value="# your code goes here";
}
function mySubmit() {
    if(document.getElementById('s').selectedIndex==0)
    {confirm("Please select any programming language.");
    }
    else{
       document.getElementById("myForm").submit();
       }
       
}
</script>

<style>

    
			textarea {
				padding: 10px;
				vertical-align: top;
				width: 53%;
                                margin-left: 9%;
                                margin-top:0%;
                                
			}
			textarea:focus {
				outline-style: solid;
				outline-width: 2px;
			}
		</style>
                <form action='CompileRun' method="post" id="myForm" name="myForm">
                   
                    <div class='span8 offset1' style='margin-top:3%'>  
                        
                        <font style='color:white'> | </font>                    <i class="icon-calendar icon-white"></i> <span style='color:#F9F9F9' id="demo"></span>
                        <font style='color:white'> | </font> <a  style='color:#F9F9F9' href='#' onclick="report(document.getElementById('s').value)"> Insert <font style='color:#F25C27;'>template</font>  </a>
    
                        <div class="name-author"><i class="icon-user icon-white"></i> <font style='color:white;font-size: 13px'> Anonymous</font></div>
     
                    </div>
                    <textarea rows="18" name="tA" id="tA"  autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" style="margin-left: 10%">${sessionScope.code}</textarea>
                <br>
                <div class="span4 offset1" style="margin-right:20%;margin-left: 10%">
                    <font  style="color:#F25C27;font-size: 16px;"> Select language:</font> <select id='s'  name='s' class="span2" onchange="report(this.value)">
                    <option value="">None</option>
                  
                        <option value="0">Java</option>
                    
                    <option value="1">C</option>
                    <option value="2">C++</option>
                    </select><input id="sub"  style="position:absolute;margin-left: 28%;background-color:#F25C27;color:white;border: none;font-size: 16px;padding: 5px 6px" type="button" value=" Run " onClick="mySubmit()"></div>
             
                <div style="color:#F25C27;font-size: 15.5px;margin-left:10%;padding-bottom: 1%;padding-top: 6%">
                 Custom Input <input type="checkbox" onclick="toggle('Comments')"> <br/>
                </div>
        <div id="Comments" style="display:none;">
            <textarea style="margin-left: 10%" name="tA2" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">${sessionScope.input}</textarea>
        
        </div>
             
	</form>
      <c:if test="${sessionScope.output != null}">
             <div style="color:#F25C27;font-size: 16px;margin-left:10%;padding-bottom: 1%">
                 Output<br>
                </div>
            <textarea style="margin-left: 10%"  autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">${sessionScope.output}</textarea>
        </c:if>
 
                
      
        </div>
            </body>
	
  <script src='js/autosize.js'></script>
	<script>
		autosize(document.querySelectorAll('textarea'));
	</script>

        <c:remove var="input"></c:remove>
        
        <c:remove var="output"></c:remove>
        
        <c:remove var="code"></c:remove>
        
        <c:remove var="lang"></c:remove>
<!-- footer -->
<%@include file="footer.html" %>
<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>