<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Welcome to Timesheet app!</title>
</head>
<body>
<h1>Welcome to the Timesheet App!</h1>
<u1>
    <li><a href="managers">List managers</a></li>
    <li><a href="employees">List employees</a></li>
    <li><a href="tasks">List tasks</a></li>
    <li><a href="timesheets">List timesheets</a></li>
</u1>
<h2>Also check out <a href="timesheet-service">extra services!</a></h2><%--
Today is <fmt:formateDate value="${today}" pattern="yyyy-MM-dd"/>
    --%>
<td>Today is: ${today}</td>
</body>
</html>