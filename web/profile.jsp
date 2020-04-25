<%--
    Document   : profile
    Created on : Jan 23, 2017, 11:04:32 PM
    Author     : Milind Ghiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="d" uri="/WEB-INF/tlds/newtag_library" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${sessionScope.user.name} | Profile</title>
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
        <style>
            .tr1{
                height:40px;
            }
        </style>
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
        <c:choose>
            <c:when test="${sessionScope.user.role eq 'admin'}">
                <%@include file="adminHeader.html" %>
            </c:when>
            <c:when test="${sessionScope.user.role eq 'student'}">
                <%@include file="studentHeader.html" %>
            </c:when>
            <c:when test="${sessionScope.user.role eq 'contesthost'}">
                <%@include file="contesthostHeader.html" %>
            </c:when>
        </c:choose>

        <d:DisplayProfile></d:DisplayProfile>

        <form name="myForm" id="myForm" method="post" enctype="multipart/form-data">

            <div class=" thumbnail-1" style="position:absolute;margin-left: 7%;margin-top: 4%">
                <div class="">
                    <img src="img/default.jpg" height="500" width="300" alt="" style="visibility: visible; opacity: 1;">
                </div>

                <section style="margin-top:3%">

                </section>
            </div>
        </form>
        <div class="offset6 span10 " style="margin-top: 2%" >
            <div style="margin-bottom:2%;font-size: 2em">
                <strong></strong>
            </div>

            <table>
                <tr class="tr1"><td> Codester:</td><td style="padding-left: 20px;font-size: 1.2em"><strong>${user.name}</strong></td></tr>
            <tr class="tr1"> <td>Username:</td><td style="padding-left: 20px;font-size: 1.2em"><strong> ${user.username}</strong></td></tr>
            <tr class="tr1"> <td>Password:</td><td style="padding-left: 20px;font-size: 1.2em"><strong>${user.password}</strong><td></tr>
            <tr class="tr1">    <td>Email:</td><td style="padding-left: 20px;font-size: 1.2em"> <strong>${user.email}</strong></td></tr>
            <tr class="tr1"> <td>College:</td><td style="padding-left: 20px;font-size: 1.2em"><strong>${user.college}</strong></td></tr>
        </table>
    </div>
    <div class="offset6 span10 " style="margin-top: 6%;margin-bottom: 4%">
        <div style="margin-bottom:3%;font-size: 2em">
            <strong>Recent Activity</strong>
        </div>
        <table>

            <c:forEach items="${al1}" var="current">
                <tr class="tr1"> <td><strong> <a style="color:#003bb3;margin-right: 30px"> ${current}</a></strong> |</td></tr>
            </c:forEach>
        </table>
    </div>


    <%@include file="footer.html" %>