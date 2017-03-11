<%@ include file="include/IncludeTop.jsp"%>

<h2>
	<c:out value="${command.book.title}" />
</h2>

<h3>
	Hello
	<c:out value="${command.customer.firstName}" />, please enter your review for this book.
</h3>

<form action="<c:url value="writecustomerreview.htm"/>" method="post">
	<input type="hidden" name="bookid"
		value="<c:out value="${command.book.id}"/>" />

	<table>
		<tr>
			<td>Title</td>
			<td>
				<spring:bind path="command.title">
<%-- 					<input type="text" name="title" value="${status.value}" /> --%>
					<input type="text" name="title" value="<c:out value="${status.value}"/>" />
					<span class="error"><c:out value="${status.errorMessage}" /></span>
				</spring:bind>
			</td>
		</tr>

		<tr>
			<td>Your Review</td>
			<td>
				<spring:bind path="command.review">
					<textarea name="review" cols="50" rows="10"><c:out
								value="${status.value}" /></textarea>
					<span class="error"><c:out value="${status.errorMessage}" /></span>
				</spring:bind>
			</td>
		</tr>

		<tr>
			<td>Rating (1-5)</td>
			<td>
				<spring:bind path="command.rating">
					<input type="text" name="rating"
							value="<c:out value="${status.value}"/>" />
					<!--
			        <select name="rating" size="1">
			            <option value="1">1</option>
			            <option value="2">2</option>
			            <option value="3">3</option>
			            <option value="4">4</option>
			            <option value="5" selected>5</option>
			        </select>
        			-->
					<span class="error"><c:out value="${status.errorMessage}" /></span>
				</spring:bind>
			</td>
		</tr>
	</table>

	<input type="submit" value="Save Review" />
</form>
<p>
	<a href="bookdetails.htm?id=<c:out value="${command.book.id}"/>">View
		book details</a>
</p>

</body>
</html>