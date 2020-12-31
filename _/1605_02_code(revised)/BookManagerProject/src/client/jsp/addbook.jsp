<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="style.css" />
        <title>Book Manager App v0.1</title>
	</head>
	<body>
		<div class="welcome" align="left">
		<div class='breadcrumb'>	
			<a href="welcome.jsp">Home</a>
		</div>
		<div class='breadcrumb'>	
			<a href="">-> Add Book</a>
		</div>
		<s:form action="addbook">
			<s:textfield label="Catalog" name="catalog"/>
			<s:textfield label="Title" name="title"/>
			<s:textfield label="Author" name="author"/>
			<s:textfield label="Copyright" name="copyright"/>
			<s:radio label="Binding" list="bindings" name="binding"/>
			<s:submit value="Add" align="center"/>
		</s:form>
		</div>
	</body>
</html>