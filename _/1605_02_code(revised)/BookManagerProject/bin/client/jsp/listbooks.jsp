<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="style.css" />
		<title>Book Manager App v0.1</title>
	</head>
	<body>
		<div class="welcome" align="left">
		<a href="welcome.jsp">Home</a> -> List Books
		<table width="100%">
			<tr>
				<th/>
				<th>Catalog</th>
				<th>Title</th>
				<th>Author</th>
				<th>Copyright</th>
				<th>Binding</th>
			</tr>	
	    	<s:iterator value="books" status="rowstatus">
	    		<s:if test="#rowstatus.odd == true">
	    			<tr class="odd">
	    		</s:if>
	    		<s:else>
	    			<tr class="even">
	    		</s:else>
	    			<td align="center">
	    				<s:form theme="simple">
	    					<s:hidden name="book_id" />
	    					<s:submit action="editbookscreen" value="Edit"/>
	    					<s:submit action="deletebook" value="Delete"/>
	    				</s:form>
	    			</td>
	    			<td><s:property value="catalog" /></td>
	    			<td><s:property value="title" /></td>
        			<td><s:property value="author" /></td>
        			<td><s:property value="copyright" /></td>
        			<td><s:property value="binding" /></td>
	  			</tr>
	  		</s:iterator>
	  	</table>
	</body>
</html>