
<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
	function hideDeleteShowEdit(index) {
		var x = document.getElementById("edit" + index);
		var y = document.getElementById("delete" + index);
		x.style.display = "inline";
		y.style.display = "none";
	}
	function showDeleteHideEdit(index) {
		var delayInMilliseconds = 1000; //1 second

		setTimeout(function() {
			var x = document.getElementById("edit" + index);
			var y = document.getElementById("delete" + index);

			x.style.display = "none";
			y.style.display = "inline";
		}, delayInMilliseconds);
	}
	function showAddTextShowEnterHidePlus() {
		var x = document.getElementById("addText");
		var y = document.getElementById("newTagButton");
		var z = document.getElementById("plus");
		x.style.display = "inline";
		y.style.display = "inline";
		z.style.display = "none";
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
			PUBHUB <small>Book Details - ${book.isbn_13 }</small>
		</h1>
		<hr class="book-primary">

		<form action="UpdateBook" method="post" class="form-horizontal">

			<input type="hidden" class="form-control" id="isbn_13" name="isbn_13"
				required="required" value="${book.isbn_13 }" />

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
					<c:forEach var="tag" items="${tags}" varStatus="loop">
						<li
							style="border-radius: 15px; background-color: grey; display: table; margin-right: 20px">
							<input onfocus="hideDeleteShowEdit('${loop.index}')"
							onfocusout="showDeleteHideEdit('${loop.index}')"
							size="${tag.getTagName().length()}"
							style="background-color: grey; border-radius: 15px; margin: 0"
							type="text" name="tagTextBox" value="${tag.getTagName()}" />
							<button id="delete${loop.index}"
								style="display: inline; background-color: grey; border-radius: 15px; margin: 0"
								type="submit" name="deleteTagButton"
								value="${tag.getIsbn13()}/${tag.getTagName()}">X</button>
							<button id="edit${loop.index}"
								style="display: none; background-color: grey; border-radius: 15px; margin: 0"
								type="submit" name="editTagButton"
								value="${tag.getIsbn13()}/${tag.getTagName()}">></button>
						</li>
					</c:forEach>
					<li style="display: inline; margin-left: 0">

						<input id="addText"
						style="display: none; background-color: grey; border-radius: 15px; margin: 0"
						type="text" size="25" name="addText" value="" />

						<button id="newTagButton"
							style="display: none; width: 30px; height: 30px; background-color: white; border-radius: 15px; margin: 0"
							type="submit" name="newTagButton" value="${book.isbn_13}">></button>

						<button id="plus"
							style="display: inline; width: 30px; height: 30px; background-color: white; border-radius: 15px; margin: 0"
							type="button" name="plus" onclick="showAddTextShowEnterHidePlus()"
							value="">+</button>
					</li>
				</ul>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-1">
					<button name="update" type="submit" class="btn btn-info">Update</button>
				</div>
			</div>
		</form>
	</div>
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />
