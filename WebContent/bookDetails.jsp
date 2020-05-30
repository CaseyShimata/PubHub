
<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
	function hideDeleteShowEdit() {
		var x = document.getElementById("edit");
		var y = document.getElementById("delete");
		x.style.display = "inline";
		y.style.display = "none";
	}
	function showDeleteHideEdit() {
		var x = document.getElementById("edit");
		var y = document.getElementById("delete");
		x.style.display = "none";
		y.style.display = "inline";
	}
	
	
</script>

<!-- 	Just some stuff you need -->
<header>
	<div class="container">

		<c:choose>
			<c:when test="${not empty message }">
				<p class="alert ${messageClass}">${message }</p>
				<%
					session.setAttribute("message", null);
				session.setAttribute("messageClass", null);
				%>
			</c:when>
		</c:choose>

		<h1>
			PUBHUB <small>Book Details - ${book.isbn13 }</small>
		</h1>
		<hr class="book-primary">

		<form action="UpdateBook" method="post" class="form-horizontal">

			<input type="hidden" class="form-control" id="isbn13" name="isbn13"
				required="required" value="${book.isbn13 }" />

			<div class="form-group">
				<label for="title" class="col-sm-4 control-label">Title</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="title" name="title"
						placeholder="Title" required="required" value="${book.title }" />
				</div>
			</div>
			<div class="form-group">
				<label for="author" class="col-sm-4 control-label">Author</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="author" name="author"
						placeholder="Author" required="required" value="${book.author }" />
				</div>
			</div>
			<div class="form-group">
				<label for="price" class="col-sm-4 control-label">Price</label>
				<div class="col-sm-5">
					<input type="number" step="0.01" class="form-control" id="price"
						name="price" placeholder="Price" required="required"
						value="${book.price }" />
				</div>
			</div>
			<div style="padding: 2px;" class="tags-form-group">
				<ul style="list-style: none; display: flex; flex-direction: row;">
					<c:forEach var="tag" items="${tags}">
						<li
							style="border-radius: 15px; background-color: grey; display: table; margin-right: 20px">
							<input onfocus="hideDeleteShowEdit()" onfocusout="showDeleteHideEdit()"
							size="${tag.getTagName().length()}"
							style="background-color: grey; border-radius: 15px; margin: 0"
							type="text" name="${tag.getTagName()}"
							value="${tag.getTagName()}" />
							<button id="delete"
								style="display: inline; background-color: grey; border-radius: 15px; margin: 0"
								type="button" name="deleteTagButton"
								value="${tag.getIsbn13()}">X</button>
							<button id="edit"
								style="display: none; background-color: grey; border-radius: 15px; margin: 0"
								type="button" name="editTagButton"
								value="${tag.getIsbn13()}">></button>
						</li>
					</c:forEach>
					<li style="display: inline; margin-left: 0"><img
						style="width: 30px; height: 30px; object-fit: cover;"
						alt="add a tag"
						src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Plus_symbol.svg/640px-Plus_symbol.svg.png" />

					</li>
				</ul>

			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-1">
					<button type="submit" class="btn btn-info">Update</button>
				</div>
			</div>
		</form>

	</div>
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />
