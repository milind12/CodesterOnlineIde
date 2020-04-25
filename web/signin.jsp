<%-- 
    Document   : signin.jsp
    Created on : Dec 21, 2016, 6:48:58 PM
    Author     : Milind Ghiya
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
	<head>
	<title>Codester | Sign in</title>
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
	<link rel="stylesheet" href="css/kwicks-slider.css" type="text/css" media="screen">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/superfish.js"></script>
	<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
	<script type="text/javascript" src="js/jquery.kwicks-1.5.1.js"></script>
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
	  <script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});
</script>

</head>

	<body style="background-color:#f9f9f9"> 
 <div id="content" ><div class="ic"></div>
    <div class="container " >
          <div class="row">
        <article class="span4 offset4" style='background-color: white;margin-top: 10%;margin-bottom: 5%;padding-left: 6%;border: 0.5px solid lightgray'  >
             <h4 style="margin-left:10%"> {Codester}</h4>
              <form  action='SigninServlet' method="post">
                 
                 <div style="margin-left:11%;color:red">${sessionScope.message}</div>
          
                 <c:set var="message" scope="session" value=""/>
                 <div style="margin-left:11%;margin-top:10%"  class='input-prepend input-large'><span class='add-on'><i class='icon-envelope'></i></span><input placeholder="Email" autofocus="autofocus" name="email" class='span2 input-xlarge' id='inputIcon' type='text'></div><div style="margin-left:11%;margin-top:10%" class='input-prepend input-large'><span class='add-on'><i class='icon-lock'></i></span>
                
                     <input  class='span2 input-large'  id='inputIcon' name="password" type='password' placeholder="Password"></div><div><input   style="margin-left:11%;margin-top:10%;width:53%" type='submit' class='btn btn-1 input-large'/>
			
                         <br><br><!-- <br><br> <a href='signup.jsp' style='color:white;background-color:darkcyan;padding-top:4px; padding-bottom:4px;padding-left:3px;padding-right: 4px; color:white;'> Student <i class="icon-chevron-right" ></i></a><br>
                          <div style='margin-top:10px'></div>
                           <a href='signup2.jsp' style='color:white;background-color:darkcyan;padding-top:4px; padding-bottom:4px;padding-left:8px;padding-right: 4px; color:white;bord'>  College <i class="icon-chevron-right" ></i></a>
                       -->   <br>
                          </div> </form>
            </article>
        <div class="clear"></div>
        </div>
	    </div>

		</div>
    </div>
	<div class="row1">
		 <div class="container">
        <div class="row">
        <article class="span12">

		
		</div>
		</div>
</div>
<!-- footer -->
<%@include file="footer.html" %>
<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>


