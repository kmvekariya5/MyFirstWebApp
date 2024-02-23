	<%@ include file="common/header.jspf" %>
	<%@ include file="common/navigation.jspf" %>
	<div class=container>
			<h1> Enter Your Todos Details </h1>
			<form:form method="post" modelAttribute="data">
			
				<fieldset class="mb-3">
				<form:label path="description">Description:</form:label>
				<form:input type="text" path="description" required="required"/>
				<form:errors path="description" cssClass="text-warning"/>
				</fieldset>
				
				<fieldset class="mb-3">
				<form:label path="targetDate">Target Date:</form:label>
				<form:input type="text" path="targetDate" required="required"/>
				<form:errors path="targetDate" cssClass="text-warning"/>
				</fieldset>
				
				<form:input type="hidden" path="done"/>
				<form:input type="hidden" path="id"/>
				<input type=submit class="btn btn-success" value="Add"/>
			</form:form>
		</div>
		<%@ include file="common/footer.jspf" %>
		<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript">
		$('#targetDate').datepicker({
		    format: 'yyyy-mm-dd',
		});
		</script>
		


