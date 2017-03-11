<%@ include file="include/IncludeTop.jsp" %>

<h2><c:out value="${book.title}"/></h2>

<p><c:out value="${book.synopsis}"/></p>

<p>
    <a href="writecustomerreview.htm?bookid=<c:out value="${book.id}"/>">Write a review of this book</a>
</p>


</body>
</html>