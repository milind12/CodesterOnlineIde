<%--
    Document   : contestresults
    Created on : Feb 27, 2017, 1:48:58 PM
    Author     : Milind Ghiya
--%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="d" uri="/WEB-INF/tlds/newtag_library" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${sessionScope.user.name} | Submissions</title>
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
        <style >
            .tr1{
                height:80px;
                border-bottom: 0.5px solid #f0f0f0;
            }
            .anch{
                padding: 0px 5px;
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
    </head>
    <body >
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
        <d:DisplayContestResult></d:DisplayContestResult>

            <div style='text-align:center;font-size: 2em;margin-top: 100px'>
            ${contest.name}
        </div>
        <div style='text-align:center;font-size: 0.8em;margin-top:20px;margin-bottom: 50px'>
            ${contest.startingDateString} to ${contest.endDateString}
        </div>


        <div class='offset1 span11' >
            <div>
                <c:if test="${user.userID eq contestsetterid}">
                    <c:if test="${emailSend eq 0}">
                        <strong>   <a style="color:#0044cc " href='<c:url value="SendContestEmail" ><c:param name="contestid" value="${param.contestid}" /></c:url>'>Send email to top 10 contestants!</a></strong>
                    </c:if>
                    <c:if test="${emailSend eq 1}">
                        <strong>   <a style="color:#0044cc" >Emails have been sent to top 10 contestants!</a></strong>
                    </c:if>
                </c:if>
            </div>
            <div style="margin-top:20px">
                <table class='table table-hover' >
                    <tr>
                        <td>Rank</td> <td>Name</td><td style='padding-left: 50px'>Email id</td><td>Username</td><td>Score</td><td>Correct Answers</td><td>Max score</td>
                    </tr>
                    <c:forEach items="${contestresults}" var="current" varStatus="o">
                        <tr>
                            <td>${o.count}</td>
                            <td><strong><a style="color:#F25C27" href='<c:url value="showprofile.jsp"><c:param name="username" value="${current.username}"></c:param></c:url>'> ${current.name}</a></strong></td>
                            <td>${current.emailid}</td>
                            <td> <strong><a style="color:#F25C27" href='<c:url value="showprofile.jsp"><c:param name="username" value="${current.username}"></c:param></c:url>'>${current.username}</a></strong></td>
                            <td>${current.score}</td>

                            <td>${current.correctAns}</td>

                            <td>${current.maxscore}<td>
                            <td><strong><a style="color:#F25C27" href='<c:url value="submissiondetail.jsp"><c:param name="username" value="${current.username}"></c:param></c:url>'> View Submission Details</a></strong></td>
                                </tr>

                    </c:forEach>

                </table>
            </div>
        </div>
    </body>
</html>
