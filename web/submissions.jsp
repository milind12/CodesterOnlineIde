<%--
    Document   : submissions
    Created on : Feb 2, 2017, 10:15:26 PM
    Author     : Milind Ghiya
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="d" uri="/WEB-INF/tlds/newtag_library" %>
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

        <d:DisplaySubmission></d:DisplaySubmission>
        <c:set var="lastpage" scope="session" value="submissions" ></c:set>
        <div class="offset1">

            <h5 style="text-align:center;font-size: 2em;color:#F25C27;margin-top:5%;;border-bottom: 0.5px solid #f0f0f0;padding-bottom: 7%;width: 90%">Submissions</h5>


            <table class="  " style="width:90%">
                <!--      <tr><td style="color:#F25C27"><strong>No.</strong></td><td style="color:#F25C27;text-align:center"><strong>Code</strong></td><td style="color:#F25C27;text-align:center"><strong>size</strong></td><td style="color:#F25C27;text-align:center"><strong>Date Modified</strong></td><td  style="color:#F25C27;text-align:center"><strong>Time</strong></td><td style="color:#F25C27;text-align:center"><strong>Action</strong></td></tr>
                -->  <c:forEach items="${al}" var="currentname" varStatus="o">
                <tr class="tr1">
                    <td style="text-align:center">
                        ${currentname.name}
                    </td>

                    <td style="text-align:center"> ${currentname.lang}</td>
                    <td style="text-align:center">
                        ${currentname.size} Bytes
                    </td>
                    <td style="text-align:center">
                        ${currentname.date}
                    </td>

                    <td style="text-align:center">
                        ${currentname.time}
                    </td>


                    <td style="text-align:center">   <a  class="anch" title="Download" href="<c:url value="DownloadServlet"><c:param name="v" value="${currentname.name}"/></c:url>"><i class="icon-download"></i></a>
                        <a  title="view in ide" class="anch" href="<c:url value="ViewInIde"><c:param name="v" value="${currentname.name}"/></c:url>"><i class="icon-eye-open "></i></a>
                        <a  title="Delete" class="anch" href="<c:url value="DeleteFileServlet"><c:param name="v"   value="${currentname.name}"/></c:url>"><i class="icon-trash"></i></a>
                        </td>
                    </tr>
            </c:forEach>
        </table>



    </div>

    <%@include file="footer.html" %>
</html>