<%--
    Document   : contestsubmissions
    Created on : Feb 25, 2017, 7:23:35 PM
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

                <c:if test='${param.problemnum ne numberofproblems}'>
                    <li><a href="conteston.jsp?problemnum=${param.problemnum+1}" style="color:#F25C27">Next</a></li>
                    </c:if>
            </ul>

        </div>


        <table class="offset1" style="width:80%;background-color: white">
            <tr>
                <c:forEach items="${contest.problems}" var="currentname" varStatus="o" >
                    <c:if test="${problemnum eq o.count}">
                        <td style="padding-top: 20px;padding-left: 20px"><font style="font-size: 1.5em;" > ${o.count}. </font><a href="conteston.jsp?problemnum=${problemnum}"  ><font style="font-size: 2em;color:#F25C27"> ${currentname.name}</font></a></td>
                            </c:if>
                        </c:forEach>
            </tr>
            <!--      <tr><td style="color:#F25C27"><strong>No.</strong></td><td style="color:#F25C27;text-align:center"><strong>Code</strong></td><td style="color:#F25C27;text-align:center"><strong>size</strong></td><td style="color:#F25C27;text-align:center"><strong>Date Modified</strong></td><td  style="color:#F25C27;text-align:center"><strong>Time</strong></td><td style="color:#F25C27;text-align:center"><strong>Action</strong></td></tr>
            -->  <c:forEach items="${listOfSubmissions}" var="currentname" varStatus="o">
                <tr class="tr1" >
                    <td style="text-align:center" >
                        submission${fn:length(listOfSubmissions)-o.index}
                    </td>

                    <td style="text-align:center"> ${currentname.lang}</td>
                    <td style="text-align:center">
                        ${resultlist[fn:length(resultlist)-o.count]}
                    </td>
                    <td style="text-align:center">
                        ${currentname.date}
                    </td>



                    <td>

                        <button type="button" class="btn btn-info " data-toggle="modal" data-target="#myModal${o.count}">View</button>

                    </td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(listOfSubmissions) eq 0}">
                <tr><td style="text-align: center;padding: 50px 0px">
                        No submissions

                    </td></tr>
                </c:if>

        </table>


        <c:forEach items="${listOfSubmissions}" var="currentname" varStatus="o">

            <div class="modal fade" id="myModal${o.count}" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h5 id="myModalLabel"> ${currentname.name}</h5>

                </div>
                <div class="modal-body" >
                    <p >
                        ${content[o.index]}
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn" data-dismiss="modal">Close</button>
                </div>
            </div>
        </c:forEach>





    </body>


</html>
<script>
    /*
     function unhide(element)
     {
     var str = "my" + element;
     document.getElementById(str).className = "modal ";
     }
     function hide(element)
     {
     var str = "m" + element;
     document.getElementById(str).className = "modal fade";
     }
     */
</script>
<script type="text/javascript" src="js/bootstrap.js"></script>
