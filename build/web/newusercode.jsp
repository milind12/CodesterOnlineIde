<%--
    Document   : newusercode
    Created on : Jan 23, 2017, 11:00:58 PM
    Author     : Milind Ghiya
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${sessionScope.user.name} | Work</title>
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
        <script>
            function toggle(id) {
                if (document.getElementById(id).style.display == 'none') {
                    document.getElementById(id).style.display = 'block';
                } else {
                    document.getElementById(id).style.display = 'none';
                }
            }
        </script>

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
        <script>
            window.onload = function () {
                var d = new Date();
                document.getElementById("demo").innerHTML = d.toDateString();

            <c:if test="${sessionScope.code==null}">

                if (document.getElementById('s').selectedIndex == 0)
                    document.getElementById('tA').value = "Please select a programming language from below and start coding!";
            </c:if>
                document.getElementById("sub").addEventListener("submit", mySubmit);
                document.getElementById("save").addEventListener("submit", mySubmit1);

            <c:if test="${sessionScope.code!=null}">

                document.getElementById('s').value =${sessionScope.lang};
            </c:if>


            };
        </script>
        <script>
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
                    document.myForm.action = "CompileRun";

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
        <script src='js/autosize.js'></script>
        <script>
            autosize(document.querySelectorAll('textarea'));
        </script>

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
            .button1 {
                background-color: white;
                color: black;
                border: 2px solid #F25C27;
            }

            .button1:hover {
                background-color: #F25C27;
                color: white;
            }
            textarea {
                padding: 10px;
                vertical-align: top;
                width: 53%;
                margin-left: 9%;
                margin-top:0%;

            }
            textarea:focus {
                /*//			outline-style: solid;
                //			outline-width: 2px;*/

            }
        </style>

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

    <div class="" >
        <!-- Content -->
        <body >














            <form  method="post" id="myForm" name="myForm">
                <div class='span8 offset1' style='margin-top:3%'>


                    <font style='color:gray'> | </font>                    <i class="icon-calendar "></i> <span style='color:gray' id="demo"></span>
                    <font style='color:gray'> | </font> <a  style='color:gray' href='#' onclick="report(document.getElementById('s').value)"> Insert <font style='color:#F25C27;'>template</font>  </a>
                        <c:if test="${sessionScope.output != null}">
                        <font style='color:gray'> | </font>
                        <a style="color:#F25C27" href="#headline1">Go to Output</a>
                    </c:if>
                    <span style="margin-left: 33%" > ${sessionScope.message}
                    </span>
                    <div class="name-author"><i class="icon-user "></i> <a href="profile.jsp"><font style='color:gray;font-size: 13px;margin-right: 2px'> ${sessionScope.user.name} </font> </a> </div>


                </div>


                <textarea rows="18" name="tA" id="tA"  autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" style="margin-left: 10%">${sessionScope.code}</textarea>
                <br>
                <div class="span4 offset1" style="margin-right:20%;margin-left: 10%">
                    <font  style="color:#F25C27;font-size: 16px;"> Select language:</font> <select id='s'  name='s' class="span2" onchange="report(this.value)">
                        <option value="">None</option>

                        <option value="0">Java</option>

                        <option value="1">C</option>
                        <option value="2">C++</option>
                    </select><input style="position:absolute;margin-left: 5%" type="text" name="filename" value="${filename}" placeholder="Enter Filename"> <input id="sub" style="margin-left:28%" class="button1 w3-button" type="button"  value=" Run " onclick="mySubmit()" >
                    <input type="button" id="save" style='margin-left:22.5%' class="w3-button button1" onclick="mySubmit1()" value="Save" ></div>

                <div style="color:#F25C27;font-size: 15.5px;margin-left:10%;padding-bottom: 1%;padding-top: 6%">
                    Custom Input <input type="checkbox" onclick="toggle('Comments')"> <br/>
                </div>
                <div id="Comments" style="display:none;">
                    <textarea rows="5" style="margin-left: 10%" name="tA2" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">${sessionScope.input}</textarea>

                </div>

            </form>
            <c:if test="${sessionScope.output != null}">


                <div  style="color:#F25C27;font-size: 16px;margin-left:10%;padding-bottom: 1%">
                    <a name="headline1" style="color:#F25C27">Output</a><br>
                </div>
                <textarea style="margin-left: 10%;background-color: white" readonly   autocomplete="off" autocorrect="off"  autocapitalize="off" spellcheck="false">${sessionScope.output}</textarea>
            </c:if>



    </div>
    <c:remove var="input"></c:remove>

    <c:remove var="output"></c:remove>

    <c:remove var="code"></c:remove>

    <c:remove var="lang"></c:remove>

    <c:remove var="message" />

    <c:remove var="filename" />
    <c:set var="filename" value=""></c:set>
    <c:set var="output" value=""></c:set>
    <c:set var="code" value=""></c:set>
    <c:set var="lang" value=""></c:set>
    <c:set var="message" value=""></c:set>
        <script src='js/autosize.js'></script>
        <script>
                        autosize(document.querySelectorAll('textarea'));
        </script>








    <%@include file="footer.html" %>
