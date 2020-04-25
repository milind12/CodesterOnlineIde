<%--
    Document   : get_code
    Created on : Jan 24, 2017, 5:47:55 PM
    Author     : Milind Ghiya
--%>

<%@page import="java.io.File"%>
<%@page import="general.User"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/WEB-INF/tlds/newtag_library" prefix="d"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.user.name} | Code Archive</title>
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
        <script type="text/javascript" src="assets/js/bootstrap-typeahead.js"></script>

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
            .w3-button {
                position:absolute;
                background-color: #F25C27; /* Green */
                border: none;
                color: white;
                padding: 3px 12px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                -webkit-transition-duration: 0.4s; /* Safari */
                transition-duration: 0.4s;
                cursor: pointer;
            }
            .button2 {
                background-color: #F25C27;
                color: white;
                border: 2px solid #F25C27;
            }
            .button1 {
                background-color: white;
                color: #F25C27;
                border: 2px solid #F25C27;
            }
            .anch{
                padding: 0px 5px;
            }

            .button1:hover {
                background-color: #F25C27;
                color: white;
            }
            .tr1{
                height:80px;
                border-bottom: 0.5px solid #f0f0f0;
            }
            .tr1:hover{
                background-color: #F9F9F9;

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


        <d:DisplayCodes></d:DisplayCodes>


        <form action="get_code.jsp" method="post">
            <pre>

                <input autocomplete="off" autocorrect="off" type="text" name="search" data-provide="typeahead" data-items="3" data-source='${list}' placeholder="Search code" ><input type="submit" class="w3-button button2" value="Search">
        </pre>


        <div class="offset6 " style="margin-top:3%">
            <c:if test="${sessionScope.message != null}" >
                ${sessionScope.message}
                <c:remove var="message"></c:remove>
            </c:if>
        </div>

    </form>
    <c:if test="${requestScope.search != null}" >
        <div class="span12">
            <h3 style="margin-bottom:5%">Search result:</h3>
        </div>
    </c:if>

    <c:set var="lastpage" scope="session" value="code"></c:set>
        <div class="offset1" >

        <c:if    test="${requestScope.search == null}">        <h5 style="text-align:center;font-size: 2em;color:#F25C27;margin-top:5%;;border-bottom: 0.5px solid #f0f0f0;padding-bottom: 7%;width: 90%">Saved Code Files</h5>
        </c:if>

        <table class="  " style="width:90%">
            <!--      <tr><td style="color:#F25C27"><strong>No.</strong></td><td style="color:#F25C27;text-align:center"><strong>Code</strong></td><td style="color:#F25C27;text-align:center"><strong>size</strong></td><td style="color:#F25C27;text-align:center"><strong>Date Modified</strong></td><td  style="color:#F25C27;text-align:center"><strong>Time</strong></td><td style="color:#F25C27;text-align:center"><strong>Action</strong></td></tr>
            -->  <c:forEach items="${al}" var="currentname" varStatus="o">
                <tr class="tr1">
                    <td style="text-align:center">
                        ${currentname.name}
                    </td>

                    <td style="text-align:center"> ${currentname.lang}</td>
                    <td style="text-align:center">
                        ${currentname.size} KB
                    </td>
                    <td style="text-align:center">
                        ${currentname.date}
                    </td>

                    <td style="text-align:center">
                        ${currentname.time}
                    </td>


                    <td style="text-align:center">   <a  class="anch" title="Download" href="<c:url value="DownloadServlet"><c:param name="v" value="${currentname.name}"/></c:url>"><i class="icon-download"></i></a>
                        <a  title="view in ide" class="anch" href="<c:url value="ViewInIde"><c:param name="v" value="${currentname.name}"/></c:url>"><i class="icon-eye-open "></i></a>
                        <a  title="Delete" class="anch" href="<c:url value="DeleteFileServlet"><c:param name="v" value="${currentname.name}"/></c:url>"><i class="icon-trash"></i></a>
                        </td>
                    </tr>
            </c:forEach>
        </table>
    </div>
    <c:if test="${requestScope.noitem != null}" >
        <div class="span12 offset6" style="margin-bottom: 10%">
            ${noitem}
        </div>
    </c:if>

    <%@include file="footer.html" %>

</html>
