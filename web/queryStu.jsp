<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		td{
			text-align: center;
			width: 125px;
		}
		.c2{
			margin-left: 40px;
			margin-bottom: 40px;
		}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>

	<script type="text/javascript">
	    $(function () {
	    	// 全选和全不选
			$("#checkAll").click(function () {
				var check = $("#checkAll").prop("checked");
				$("input:checkbox:gt(0)").prop("checked",check);
			})
			//批量删除
			$("#batchDel").click(function () {
				var length = $("input:checkbox:gt(0):checked").length;
				console.log(length)
				if(length <= 0){
					alert("请选择要删除的学生!!!")
					return;
				}
				var b = confirm("你确定要删除吗?");
				if (!b){ // 选择取消,就不删除
					return;
				}
				// 完成获取需要删除的学生编号,实现删除
				var ids = "";
				$("input:checkbox:gt(0):checked").each(function () {
						// 获取每个被选中的复选框的值
						let val = $(this).val();
						ids += "," + val;   // ,1,2,3,4
				})
				//取出字符串前面的第一个逗号
				var id = ids.substring(1);
				console.log(id);
				// 将需要删除的学生编号提交给后台
				location.href = "stu?mark=batchDel&id=" + id;
			})

		})



	</script>
</head>
<body>
	<br/>
	<center>
		<form action="stu" method="get">
			<input type="hidden" name="mark" value="getStudentPageInfo">
			姓名<input name="sname" value="${student.sname}" />&nbsp;&nbsp;&nbsp;
			性别
			<select name="gender">
				<option value="-1">请选择</option>
				<option value="男" <c:if test="${student.gender == '男'}">selected="selected"</c:if>  >男</option>
				<option value="女" <c:if test="${student.gender == '女'}">selected="selected"</c:if>  >女</option>
			</select>&nbsp;&nbsp;&nbsp;
			<input type="submit" value="查询"/>
		</form>
	<br/>
	</center><br/>
	<input type="button" id="batchDel" value="批量删除" style="margin-left: 150px"/>
	<br/>
	<table border="1px" width="80%" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<th><input type="checkbox" id="checkAll" />全选/全不选</th>
			<th>学号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>生日</th>
			<th>爱好</th>
			<th>头像</th>
			<th>操作</th>
		</tr>
		<!-- 遍历学生的信息 -->
		<c:forEach items="${page.pageList}" var="stu">
			<tr>
				<td><input type="checkbox" name="id" value="${stu.id}" /></td>
				<td>${stu.id}</td>
				<td>${stu.sname}</td>
				<td>${stu.gender}</td>
				<td>${stu.birthday}</td>
				<td>${stu.hobby}</td>
				<td><img src="/img/${stu.photo}" width="60px" height="60px"></td>
				<td><a href="stu?mark=updateToJsp&id=${stu.id}">修改</a></td>
			</tr>
		</c:forEach>
		
	</table>
	<br/><br/>
	<center>
		<a href="stu?mark=getStudentPageInfo&sname=${student.sname}&gender=${student.gender}&currentPage=1" class="c2">首页</a>
		<a href="stu?mark=getStudentPageInfo&sname=${student.sname}&gender=${student.gender}&currentPage=${page.prePage}" class="c2">上一页</a>
		<a href="stu?mark=getStudentPageInfo&sname=${student.sname}&gender=${student.gender}&currentPage=${page.nextPage}" class="c2">下一页</a>
		<a href="stu?mark=getStudentPageInfo&sname=${student.sname}&gender=${student.gender}&currentPage=${page.countPage}" class="c2">尾页</a>
		<span class="c2">当前页码<input size="4" value="${page.currentPage}"/></span>
		<span class="c2">总记录数<input size="4" value="${page.countNum}"/></span>
	</center>
</body>
</html>