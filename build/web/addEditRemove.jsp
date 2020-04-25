<%--
    Document   : addEditRemove
    Created on : Feb 19, 2017, 7:16:08 PM
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
        <c:set var="contestid" value="${param.contestid}" scope="session"/>

        <d:DisplayProblem></d:DisplayProblem>
        <br /><br /><br />
        <font class="offset1" style="color:#F25C27;font-size:2em;margin-right: 30px;"> ${contest.name}</font><a style='color:#F25C27' class="offset1" href='addProblem.jsp'>Add More Problems</a>

    <c:forEach items="${contest.problems}" var="currentname" varStatus="o" >
        <table width="80%" class="offset1" style="margin-top:30px">



            <tr class="anch"><td>Question ${o.count}</td><td>score: ${currentname.score}</td><td colspan="2"><a style="color:#F25C27;margin-right: 4%" href='<c:url value="EditDelete"><c:param name="problemid" value="${currentname.problemid}"></c:param><c:param name="operation" value="edit"></c:param></c:url>'>Edit</a>  <a style="color:#F25C27;margin-right: 4%" href='<c:url value="EditDelete"><c:param name="problemid" value="${currentname.problemid}"></c:param><c:param name="operation" value="delete"></c:param></c:url>'>Delete</a>  <a style="color:#F25C27" href='<c:url value="upload.jsp"><c:param name="problemid" value="${currentname.problemid}"></c:param></c:url>'>Add test Cases</a></td></tr>
            <tr><td colspan="4">  ${currentname.name}</td>
            <tr>
            <tr class="anch"><td colspan="4">description</td></tr>
            <tr><td colspan="4"> <p> ${currentname.detail}</p></td>
            <tr>
            <tr class="anch"><td colspan="4">Input Format</td></tr>
            <tr><td colspan="4"> <p> ${currentname.inputformat}</p></td>
            <tr>
            <tr class="anch"><td colspan="4">Constraints</td></tr>
            <tr><td colspan="4"> <p> ${currentname.constraint}</p></td>
            <tr>
            <tr class="anch"><td colspan="4">Output Format</td></tr>
            <tr><td colspan="4"> <p> ${currentname.outputformat}</p></td>
            <tr >
            <tr class="anch"><td colspan="4">Sample Input</td></tr>
            <tr><td colspan="4"> <p> ${currentname.sampleinput} </p></td>
            <tr>
            <tr class="anch"><td colspan="4">Sample Output</td></tr>
            <tr><td colspan="4"> <p> ${currentname.sampleoutput}</p></td>
            <tr>
            <tr class="anch"><td colspan="4">Editorial</td></tr>
            <tr><td colspan="4"> <p> ${currentname.editorial}</p></td>
            <tr>

        </table>
    </c:forEach>

</html>