<%--
    Document   : contests
    Created on : Feb 4, 2017, 6:36:38 PM
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
                padding: 0px 5px;
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


        <d:DisplayContest>
        </d:DisplayContest>
    <div class="offset1">

        <h5 style="text-align:center;font-size: 2em;color:#F25C27;margin-top:5%;;border-bottom: 0.5px solid #f0f0f0;padding-bottom: 7%;width: 90%">All Contest</h5>


        <table style="width:90%">
            <!--      <tr><td style="color:#F25C27"><strong>No.</strong></td><td style="color:#F25C27;text-align:center"><strong>Code</strong></td><td style="color:#F25C27;text-align:center"><strong>size</strong></td><td style="color:#F25C27;text-align:center"><strong>Date Modified</strong></td><td  style="color:#F25C27;text-align:center"><strong>Time</strong></td><td style="color:#F25C27;text-align:center"><strong>Action</strong></td></tr>
            -->  <c:forEach items="${upcoming}" var="currentname" varStatus="o">
                <tr class="tr1">
                    <td style="text-align:right">
                        ${currentname.name}<a href="editcontest.jsp?contestid=${currentname.contestId}" >&nbsp;&nbsp;<i class="icon-edit" ></i></a><a href="DeleteContest?contestid=${currentname.contestId}" >&nbsp;<i class="icon-remove" ></i></a>

                    </td>
                    <td style="text-align:right"><a href='<c:url value="addEditRemove.jsp" ><c:param name="contestid" value="${currentname.contestId}" /></c:url>' style="color: #F25C27;">Add/Edit Problems</a></td>
                    <td style="text-align:right"> ${currentname.startingDateString}  to  ${currentname.endDateString}</td>
                    <td style="text-align:right">
                        Upcoming
                    </td>


                </tr>
            </c:forEach>
            <!--      <tr><td style="color:#F25C27"><strong>No.</strong></td><td style="color:#F25C27;text-align:center"><strong>Code</strong></td><td style="color:#F25C27;text-align:center"><strong>size</strong></td><td style="color:#F25C27;text-align:center"><strong>Date Modified</strong></td><td  style="color:#F25C27;text-align:center"><strong>Time</strong></td><td style="color:#F25C27;text-align:center"><strong>Action</strong></td></tr>
            -->  <c:forEach items="${ongoing}" var="currentname" varStatus="o">
                <tr class="tr1">
                    <td style="text-align:right">
                        ${currentname.name}<a href="editcontest.jsp?contestid=${currentname.contestId}" ></a>
                    </td>
                    <td style="text-align:right"><a href='<c:url value="addEditRemove.jsp" ><c:param name="contestid" value="${currentname.contestId}" /></c:url>' style="color:#F25C27">Add/Edit Problems</a></td>

                        <td style="text-align:right"> ${currentname.startingDateString}  to  ${currentname.endDateString}</td>
                    <td style="text-align:right">
                        Active
                    </td>


                </tr>
            </c:forEach>

            <!--      <tr><td style="color:#F25C27"><strong>No.</strong></td><td style="color:#F25C27;text-align:center"><strong>Code</strong></td><td style="color:#F25C27;text-align:center"><strong>size</strong></td><td style="color:#F25C27;text-align:center"><strong>Date Modified</strong></td><td  style="color:#F25C27;text-align:center"><strong>Time</strong></td><td style="color:#F25C27;text-align:center"><strong>Action</strong></td></tr>
            -->  <c:forEach items="${ended}" var="currentname" varStatus="o">
                <tr class="tr1">
                    <td style="text-align:right">
                        <font style="color:red"> ${currentname.name}</font>
                    </td>
                    <td style="text-align:right"><a style="color:#F25C27" href='<c:url value="contestresults.jsp" ><c:param name="contestid" value="${currentname.contestId}" /></c:url>'>View Results</a></td>
                    <td style="text-align:right"> ${currentname.startingDateString}  to  ${currentname.endDateString}</td>
                    <td style="text-align:right">
                        Ended

                    </td>


                </tr>
            </c:forEach>
        </table>



    </div>

    <%@include file="footer.html" %>
</html>