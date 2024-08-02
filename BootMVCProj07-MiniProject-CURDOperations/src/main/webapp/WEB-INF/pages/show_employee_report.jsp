<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!-- <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/> -->
<c:choose>
<c:when test="${!empty empData.getContent()}">
<h1 style="color:red;text-align:center">Employee Report</h1>
<table border="2" align="center" bgcolor="cyan">
<tr style="color:red"><th>empno</th>
                                      <th>emp name</th>
                                      <th>Job</th>
                                      <th>salary</th>
                                      <th>deptno</th>
                                      <th>Edit</th>
                                      <th>Delete</th>
                                      </tr>
<c:forEach var="emp" items="${empData.getContent()}">
<tr style="color:blue">
<td>${emp.empno}</td>
<td>${emp.ename}</td>
<td>${emp.job}</td>
<td>${emp.sal}</td>
<td>${emp.deptno}</td>
<td><a href="emp_edit?no=${emp.empno}"><img src="images/edit.jpg" width="30px" height="30px"></a></td>
<td><a href="emp_delete?no=${emp.empno}"
  onclick="return confirm('Do you want to delete the employee ?')"><img src="images/delete.jpg" width="30px" height="30px"></a></td>
</tr>
</c:forEach>
</table>
<p style="text-align:center">
<c:if test="${empData.hasPrevious() }">
<a href="report?page=${empData.getPageable().getPageNumber()-1} ">previous</a> &nbsp; &nbsp; 
</c:if>
<c:if test="${!empData.isFirst()}">
<a href="report?page=0">first</a> &nbsp; &nbsp;
</c:if>
<c:forEach var="i" begin="1" end="${empData.getTotalPages()}" step="1">
[<a href="report?page=${i-1}">${i }</a>] &nbsp; &nbsp;
</c:forEach>
<c:if test="${!empData.isLast() }">
<a href="report?page=${empData.getTotalPages()-1 }">Last</a> &nbsp; &nbsp;
</c:if>
<c:if test="${empData.hasNext() }">
<a href="report?page=${empData.getPageable().getPageNumber()+1 }">next</a>
</c:if>
</p>
</c:when>
<c:otherwise>
<h1 style="color:red;text-align:center">Employees Not found !</h1>
</c:otherwise>
</c:choose>

<br><br>
<center>
<a href="emp_add"><img src="images/add.jpg" width="40px" height="50px">Add employee</a>&nbsp;&nbsp;&nbsp;
<a href="./"><img src="images/home.jpg" width="40px" height="50px">Home</a>
</center>
<c:if test="${!empty  resultMsg}">
<h3 style="color:green;text-align:center">${resultMsg}</h3>
</c:if>
