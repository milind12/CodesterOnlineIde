<%--
    Document   : conteston
    Created on : Feb 22, 2017, 6:11:19 PM
    Author     : Milind Ghiya
--%>

<%@page import="general.Contest"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            window.onload = function () {
                document.getElementById('tA').value = "Please select a programming language from below and start coding!";
                document.getElementById("sub").addEventListener("submit", mySubmit);
                document.getElementById("save").addEventListener("submit", mySubmit1);
            };</script>
        <style>

            .loader {
                border: 8px solid #f3f3f3; /* Light grey */
                border-top: 8px solid #F25C27; /* Blue */
                border-radius: 50%;
                width: 30px;
                height: 30px;
                animation: spin 2s linear infinite;
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
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
            function report(period)
            {
                if (document.getElementById('s').selectedIndex == 0)
                    document.getElementById('tA').value = "Please select a programming language from below and start coding!";
                if (period == "0")
                    document.getElementById('tA').value = "//Usage of package keyword is not allowed \n //Alteration of class name 'JavaApp' is not allowed \n import java.util.*;\n import java.lang.*;\n import java.io.*;\n class JavaApp{\npublic static void main (String[] args) throws java.lang.Exception{\n// your code goes here \n}}"

                if (period == "1")
                    document.getElementById('tA').value = "#include <stdio.h>\n\nint main(void) {\n// your code goes here\nreturn 0;\n}";
                if (period == "2")
                    document.getElementById('tA').value = "#include <iostream>\nusing namespace std;\nint main() {\n// your code goes here\nreturn 0;\n}";
                if (period == "3")
                    document.getElementById('tA').value = "# your code goes here";
            }
            function mySubmit() {
                if (document.getElementById('s').selectedIndex == 0)
                {
                    confirm("Please select any programming language.");
                }
                else {
                    document.getElementById('question').style.display = 'none';
                    document.getElementById('loading').style.display = 'block';
                    document.myForm.action = "submissionresult.jsp?problemnum=${param.problemnum}";
                    document.getElementById("myForm").submit();
                }


            }

            function mySubmit1() {
                if (document.getElementById('s').selectedIndex == 0)
                {
                    confirm("Please select any programming language.");
                }
                else {
                    if (myForm.filename.value == "")
                    {
                        alert('Please enter the Filename');
                        myForm.filename.focus();
                    }
                    else {
                        document.myForm.action = "SaveCode";
                        document.getElementById("myForm").submit();
                    }
                }
            }

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
            textarea {

                width: 95%;
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
            })();</script>
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



        <c:forEach items="${contest.problems}" var="currentname" varStatus="o" >
            <c:if test="${param.problemnum eq o.count}">
                <div id="question">
                    <table width="80%" class="offset1" style="background-color:#FFFFFF;">

                        <tr ><td style="padding-left: 20px;padding-top:20px;"><font style="font-size: 1.5em;" > ${o.count}. </font><font style="font-size: 2em;color:#F25C27"> ${currentname.name}</font></td><td> <font style="color:#F25C27;font-size: 1.3em">Max score:</font> <font style="font-size: 1.3em">${currentname.score}</font><td><a style="font-size: 1.3em;color:#F25C27" href="contestsubmissions.jsp?problemnum=${param.problemnum}">MY SUBMISSIONS</a></td></td> </tr>
                        <tr><td colspan="4" style="padding:20px">  </td>
                        <tr>
                        <tr class="anch"><td colspan="4" style="padding-left: 20px;">Description</td></tr>
                        <tr><td colspan="4" style="padding-left: 20px"> <p ><font style="font-size:1.3em"> ${currentname.detail}</p></td>
                        <tr>

                            <c:if test='${currentname.inputformat ne ""}'>
                            <tr class="anch"><td colspan="4" style="padding-left: 20px;">Input Format</td></tr>
                            <tr><td colspan="4" style="padding-left: 20px;font-size: 1.2em"> <p> ${currentname.inputformat}</p></td>
                            </tr>
                        </c:if>
                        <c:if test='${currentname.constraint ne ""}'>

                            <tr class="anch"><td colspan="4" style="padding-left: 20px"> Constraints</td></tr>
                            <tr><td colspan="4" style="padding-left: 20px;font-size: 1.2em"> <p> ${currentname.constraint}</p></td>
                            </tr>
                        </c:if>
                        <c:if test='${currentname.outputformat ne ""}'>
                            <tr class="anch"><td colspan="4" style="padding-left: 20px">Output Format</td></tr>
                            <tr><td colspan="4" style="padding-left: 20px;font-size: 1.2em"> <p> ${currentname.outputformat}</p></td>
                            </tr >
                        </c:if>
                        <c:if test="${currentname.sampleinput !=null}">
                            <tr class="anch"><td colspan="4" style="padding-left: 20px">  Sample Input</td></tr>
                            <tr><td colspan="4" style="padding-left: 20px;font-size: 1.2em"> <p> ${currentname.sampleinput} </p></td>
                            </tr>
                        </c:if>
                        <c:if test="${currentname.sampleoutput !=null}">
                            <tr class="anch"><td colspan="4" style="padding-left: 20px">Sample Output</td></tr>
                            <tr><td colspan="4" style="padding-left: 20px;font-size: 1.2em"> <p> ${currentname.sampleoutput}</p></td>
                            </tr>
                        </c:if>
                    </table>

                    <c:set var="problemnum" scope="session" value="${param.problemnum}"></c:set>
                        <form  method="post" id="myForm" name="myForm">

                            <div  style="background-color: white;width:78.5%;padding-left: 20px" class="offset1">
                                <p class="anch">   Write your code:
                                </p>
                                <textarea rows="18" name="tA" id="tA"  autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" ></textarea>

                                <div class="" >

                                    <span>     <font  style="color:#F25C27;font-size: 16px;"> Select language:</font> <select style="margin-right:55%;" id='s'  name='s'  onchange="report(this.value)">
                                            <option value="">None</option>

                                            <option value="0">Java</option>

                                            <option value="1">C</option>
                                            <option value="2">C++</option>
                                        </select>
                                    <c:if test='${over eq "false"}'>
                                        <input type="button"  value="submit" class="btn-1"  onclick="mySubmit()" ></span>
                                    </c:if>

                            </div>
                        </div></form>

                </div>
                <div id="loading" style="background-color: white;display:none;width:78.5%;height:70%;padding-left: 20px" class="offset1">

                    <table width="80%" class="offset1" style="background-color:#FFFFFF;">
                        <tr ><td style="padding-left: 20px;padding-top:20px;"><font style="font-size: 1.5em;" > ${o.count}. </font><font style="font-size: 2em;color:#F25C27"> ${currentname.name}</font></td><td> <font style="color:#F25C27;font-size: 1.3em">Max score:</font> <font style="font-size: 1.3em">${currentname.score}</font><td><a style="font-size: 1.3em;color:#F25C27" href="contestsubmissions.jsp?problemnum=${param.problemnum}">MY SUBMISSIONS</a></td> </tr>
                    </table>
                    <div class="loader" style="margin-top:8%;margin-left: 50%" ></div>
                    <div style="margin-top:3%;margin-left:50%;font-size: 1.6em;margin-bottom:10%">Loading...</div>
                </div>
            </c:if>
        </c:forEach>


        <script src='js/autosize.js'></script>
        <script>
                                            autosize(document.querySelectorAll('textarea'));
        </script>

    </div>




    <%@include file="footer.html" %>


</body>
</html>

