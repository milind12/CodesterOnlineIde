<%--
    Document   : signup
    Created on : Dec 19, 2016, 3:56:49 PM
    Author     : Milind Ghiya
--%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/WEB-INF/tlds/newtag_library" prefix="d"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Codester | Sign Up</title>
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
        <script type="text/javascript">if ($(window).width() > 1024) {
                document.write("<" + "script src='js/jquery.preloader.js'></" + "script>");
            }</script>


        <script>
            window.onload = function () {
            <c:if test="${sessionScope.yoj!=null}">

                document.getElementById('yoj').value =${sessionScope.yoj};
                document.getElementById('collegename').value =${sessionScope.collegename};
            </c:if>


            };
        </script>




        <script>
            jQuery(window).load(function () {
                $x = $(window).width();
                if ($x > 1024)
                {
                    jQuery("#content .row").preloader();
                }

                jQuery('.magnifier').touchTouch();
                jQuery('.spinner').animate({'opacity': 0}, 1000, 'easeOutCubic', function () {
                    jQuery(this).css('display', 'none')
                });
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

            (function () {
                var ga = document.createElement('script');
                ga.type = 'text/javascript';
                ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();
        </script>
        <script>
            $(document).ready(function () {
                $('[data-toggle="popover"]').popover();
            });
        </script>

    </head>
    <d:DisplayCollegeList></d:DisplayCollegeList>
        <body class="bg-content">
            <div class="spinner"></div>
            <!-- header start -->
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
                                            <li><a href="newcode.jsp">New Code</a></li>
                                            <li><a href="signin.jsp">Sign in</a></li>

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
            <div >
                <div class="container">
                    <div class="row">

                        <div class="span12" >
                            <div style="margin:1%">
                                <div class="container clearfix">

                                    <form class="form-horizontal" method="post" action="SignUpServlet" >
                                        <h5 align="center">${sessionScope.message}</h5>
                                    <c:remove var="message" scope="session"/>
                                    <div style="margin-left: 30%">
                                        <li>
                                            <h3 style="margin-left: 10%">
                                                Be a part of {<font style="color:white"> codester </font>}
                                            </h3>
                                            <br>
                                            <br>
                                            <div class="control-group">
                                                <strong style="color:whitesmoke"><label class="control-label" for="inputEmail">Student Name</label></strong>
                                                <div class="controls">
                                                    <input type="text" id="inputEmail" value="${sessionScope.name}" name="name" placeholder="Student Name">
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <strong style="color:whitesmoke"><label class="control-label" for="inputEmail">Username</label></strong>
                                                <div class="controls">
                                                    <input type="text" id="inputEmail" name="username" placeholder="Username">
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <strong style="color:whitesmoke"><label class="control-label" for="inputEmail">College Name</label></strong>
                                                <div class="controls">
                                                    <select name="collegename" id="collegename">
                                                        <c:forEach items="${al}" var="current" >
                                                            <option name="${current}" value="${current}">
                                                                ${current}
                                                            </option>

                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <strong style="color:whitesmoke"><label class="control-label" for="inputEmail">College Joining Year</label></strong>
                                                <div class="controls">
                                                    <select name="yoj" id="yoj">
                                                        <c:forEach items="${al2}" var="current" >
                                                            <option name="${current}" value="${current}">
                                                                ${current}
                                                            </option>

                                                        </c:forEach>
                                                    </select>

                                                </div>
                                            </div>



                                            <div class="control-group">
                                                <strong style="color:whitesmoke"><label class="control-label" for="inputEmail">Email</label></strong>
                                                <div class="controls">
                                                    <input type="text" id="inputEmail" value="${sessionScope.email}" name="email" placeholder="Email">
                                                </div>
                                            </div>
                                           <%-- <div class="control-group">
                                                <strong style="color:whitesmoke"> <label class="control-label" for="inputPassword">Password</label></strong>
                                                <div class="controls">
                                                    <input type="password" id="inputPassword" name="password" value="${sessionScope.password}" placeholder="Password">
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <strong style="color:whitesmoke"> <label class="control-label" for="inputPassword">Confirm Password</label></strong>
                                                <div class="controls">
                                                    <input type="password" id="inputPassword" placeholder="Confirm Password" value="${sessionScope.password}">
                                                </div>
                                            </div>
                                            --%>



                                            <div class="control-group">
                                                <div class="controls" >
                                                    <button  type="submit" class="btn btn-1 input-large">Sign Up</button>
                                                </div>
                                            </div>
                                            <c:remove var="name" scope="session"/>
                                            <c:remove var="password" scope="session"/>
                                            <c:remove var="yoj" scope="session"/>
                                            <c:remove var="collegename" scope="session"/>
                                            <c:remove var="email" scope="session"/>


                                            <section id="soc-ic" >
                                                <div style="margin-top: 5%;margin-left: 8%">
                                                    <font style="color:whitesmoke;margin-top: 5%;margin-left: 8%;margin-bottom: 2%">Sign in with third party account</font>
                                                    <div style="margin-top: 3%;margin-left: 10%">
                                                        <img alt="" src="assets/img/icons/35x35/blip.png">
                                                        <img alt="" src="assets/img/icons/35x35/vimeo.png">
                                                        <img alt="" src="assets/img/icons/35x35/yt.png">
                                                        <img alt="" src="assets/img/icons/35x35/picasa.png">
                                                        <img alt="" src="assets/img/icons/35x35/wordpress.png">
                                                    </div>
                                                </div> </section>
                                    </div>
                            </div>
                        </div>

                        </form>
                    </div>
                </div>
            </div>
            <%@include file="footer.html" %>
            <script type="text/javascript" src="js/bootstrap.js"></script>
    </body>
</html>