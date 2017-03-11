<%@ include file="include/IncludeTop.jsp" %>

<h2>Welcome to our streamlined Internet Bookstore.</h2>

<p>We offer a comprehensive range of books, from Harry Potter to Harry Potter.</p>
<p>You can <a href="search.htm">search for books</a>; or alternatively browse using our bestseller list below, or 'drill down' through our list of categories.</p>


<p>Top-Rated Bestsellers:</p>

<!--
<lu>
  <c:forEach var="book" items="${topBooks}">
    <li><a href="bookdetails.htm?id=<c:out value="${book.id}"/>"><c:out value="${book.title}"/></a></li>
  </c:forEach>
</lu>
-->

<lu>
  <li><a href="bookdetails.htm?id=101">Harry Potter and the Philosopher's Stone</a></li>
  <li><a href="bookdetails.htm?id=102">Harry Potter and the Chamber of Secrets</a></li>
  <li><a href="bookdetails.htm?id=103">Harry Potter and the Prisoner of Azkaban</a></li>
  <li><a href="bookdetails.htm?id=104">Harry Potter and the Goblet of Fire</a></li>
  <li><a href="bookdetails.htm?id=105">Harry Potter and the Order of the Phoenix</a></li>
  <li><a href="bookdetails.htm?id=106">Harry Potter and the Half-Blood Prince</a></li>
  <li><a href="bookdetails.htm?id=107">Harry Potter and the As-yet Unreleased Novel</a></li>

</lu>

</body>
</html>