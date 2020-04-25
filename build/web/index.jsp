<%--
    Document   : index
    Created on : Dec 16, 2016, 9:27:26 PM
    Author     : Milind Ghiya
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <title>Codester | Home</title>
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
        <script src="js/bootstrap-scrollspy.js"></script>

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

    <body>
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
                                        <li class="active"><a  href="index.jsp">Home</a></li >
                                        <li><a href="newcode.jsp">New Code</a></li>
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
        <div class="bg-content">
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <!-- slider -->
                        <!--     <div class="flexslider">
                                   <ul class="slides">
                                 <li> <img src="img/slide-2.jpg" alt="" > </li>
                                 <li> <img src="img/slide-6.jpg" alt="" > </li>
                                 <li> <img src="img/slide-3.jpg" alt="" > </li>
                                 <li> <img src="img/slide-1.jpg" alt="" > </li>
                                 <li> <img src="img/slide-7.jpg" alt="" > </li>
                               </ul>
                                 </div>
                        -->

                        <div id="myCarousel" class="carousel slide" style="margin-top:2%;margin-bottom:6%">

                            <div class="carousel-inner">
                                <div class="active item"><img  width="1200px" src="img/slide-1.png" alt="" > </div>
                                <div class="item"><img  width="1200px" src="img/slide-2.png" alt="" > </div>
                            </div>

                            <!-- Carousel nav -->
                            <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                            <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
                        </div>
                        <span id="responsiveFlag"></span>
                        <div class="block-slogan">
                            <h2>Namaskar!</h2>
                            <div>
                                <p>Welcome To Codester! Codester is an online compiler and debugging tool which allows you to compile source code and execute it online in more than 60 programming languages.</p>
                                <p style="text-align:center; padding-top:20px;"><a href="#" class="btn btn-1">Code Now >></a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- content -->


            <div class="row-1">
                <div class="container">
                    <div class="row">
                        <article class="span12">
                            <h4>From blog</h4>
                        </article>
                        <ul class="thumbnails thumbnails-1">
                            <li class="span3">
                                <div class="thumbnail thumbnail-1">
                                    <h3>What is Codester?</h3>
                                    <section>
                                        <div class="clear"></div>
                                        <p>Ideone is an online compiler and debugging tool which allows you to compile source code and execute it online in more than 60 programming languages.</p>
                                        <a href="#" class="btn btn-1">Read More</a> </section>
                                </div>
                            </li>
                            <li class="span3">
                                <div class="thumbnail thumbnail-1">
                                    <h3>Usage of Codester?</h3>
                                    <section>
                                        <p>Choose a programming language, enter the source code with optional input data... and you are ready to go!</p>
                                        <a href="#" class="btn btn-1" style="margin-top:15%">Read More</a> </section>
                                </div>
                            </li>
                            <li class="span3">
                                <div class="thumbnail thumbnail-1">
                                    <h3>Sphere Engine™</h3>
                                    <section>
                                        <div class="clear"></div>
                                        <p>We are proud to present our Sphere Engine™ technology, which allows you to execute programs on a remote server in a secure way within a complete runtime environment. </p>
                                        <a href="#" class="btn btn-1">Read More</a> </section>
                                </div>
                            </li>
                            <li class="span3">
                                <div class="thumbnail thumbnail-1"><H3>Having problems?</h3>
                                    <section>     <div class="clear"></div>
                                        <p>Insert template to see how to write code which works correctly. To find out more visit our FAQ section.</p>
                                        <a href="#" class="btn btn-1" style="margin-top:15%">Read More</a> </section>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>


            <!-- footer -->
            <%@include file="footer.html" %>
            <script type="text/javascript" src="js/bootstrap.js"></script>
    </body>
</html>

