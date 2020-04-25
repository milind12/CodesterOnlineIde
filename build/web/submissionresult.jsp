<%--
    Document   : submissionresult
    Created on : Feb 25, 2017, 11:34:46 AM
    Author     : Milind Ghiya
--%>


<%@taglib prefix="d" uri="/WEB-INF/tlds/newtag_library" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${sessionScope.user.name} | View Contests</title>
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
        <style>

            .tr1{
                height:80px;
                border-bottom: 0.5px solid #f0f0f0;
            }
            .tr1:hover{
                background-color: #F9F9F9;

            }
            .anch{
                color:#F25C27;
            }


        </style>
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
    <body style="background-color: #F9F9F9">
        <%@include file="contestheader.html" %>


        <div class="pagination  offset1">
            <font class=""  style="font-size: 3em;color:#F25C27;margin-right: 30%;margin-left: 1%"> ${contest.name}</font>

            <ul>

                <c:if test='${param.problemnum ne "1"}'>
                    <li><a  href="conteston.jsp?problemnum=${param.problemnum-1}" style="color:#F25C27">Prev</a></li>
                    </c:if>
                    <c:forEach items="${contest.problems}" var="currentname" varStatus="o"  >

                    <li>   <a  href="conteston.jsp?problemnum=${o.count}" style="color:#F25C27"> ${o.count} </a></li>
                    </c:forEach>

                <c:if test='${param.problemnum ne numberofproblems }'>
                    <li><a href="conteston.jsp?problemnum=${param.problemnum+1}" style="color:#F25C27">Next</a></li>
                    </c:if>
            </ul>

        </div>

        <table class="offset1" style="width:80%;background-color: white">
            <tr>
                <c:forEach items="${contest.problems}" var="currentname" varStatus="o" >
                    <c:if test="${problemnum eq o.count}">
                        <td style="padding-top: 20px;padding-left: 20px"><font style="font-size: 1.5em;" > ${o.count}. </font><a href="conteston.jsp?problemnum=${problemnum}"  ><font style="font-size: 2em;color:#F25C27"> ${currentname.name}</font></a></td>
                        <td> <font style="color:#F25C27;font-size: 1.3em">Max score:</font> <font style="font-size: 1.3em">${currentname.score}</font><td><a style="font-size: 1.3em;color:#F25C27" href="contestsubmissions.jsp?problemnum=${param.problemnum}">MY SUBMISSIONS</a></td>
                        </c:if>
                    </c:forEach>

            </tr>
        </table>
        <div style="width:80%;background-color: white;padding-bottom: 10%;padding-top: 5%" class="offset1">
            <table   style='width:50%;background-color: white;margin-left: 30%;' >

                <th style='height: 60px;text-align: center;padding-left: 20%;'>
                    <c:if test='${result eq "Correct Answer"}'><a style="background-color: #00FF00;margin-right: 2%;padding-bottom:1%;padding-left:1%;padding-right:1%;padding-top:1%;" ><i class="icon-ok icon-white" style="color:lightgreen" ></i> </a> <font style="font-size:1.5em"> Congratulations !!</font></c:if>
                    <c:if test='${result eq "Wrong Answer"}'><a style="background-color:lightcoral;padding-bottom:1%;margin-right: 2%;padding-left:1%;padding-right:1%;padding-top:1%" ><i class="icon-remove icon-white" ></i></a></c:if>
                    <font style="font-size:1.5em">${result}</font>
                </th>

                <c:if test='${result =="Time Limit Exceeded"}'><tr><td style="text-align: center;height:60px">Your code didn't completed running for 3 sec</td></tr>  </c:if>
                <c:forEach var="current" items="${timetaken}" varStatus="o" >
                    <c:if test='${resultoftc[o.index] eq "Correct Answer"}'><tr style="height: 60px;border:lightgreen 2px solid;"></c:if>
                    <c:if test='${resultoftc[o.index] eq "Wrong Answer"}'><tr style="height: 60px;border:lightcoral 2px solid;"></c:if>
                        <td>Test-case ${o.count}</td>  <td>     ${resultoftc[o.index]} </td><td>   ${current/1000} sec</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
