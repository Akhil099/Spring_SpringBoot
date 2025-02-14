        <%@ include file = "common/header.jspf" %>
        <%@ include file = "common/navigation.jspf" %>

        <div class = "container">
            <h1> Enter To todos page </h1>
            <form:form method = "post" modelAttribute = "todo" >

                <fieldset class = "mb-3">
                    <form:label path= "description">Description</form:label>
                    <form:input type = "text" path = "description" />
                    <form:errors path= "description" cssClass = "text-danger"/>
                </fieldset>

                <fieldset class = "mb-3">
                    <form:label path= "targetDate">Target Date</form:label>
                    <form:input type = "text" path = "targetDate" class="datepicker" data-date-format="yyyy-MM-dd"/>
                    <form:errors path= "targetDate" cssClass = "text-danger"/>
                </fieldset>

                <form:input type = "hidden" path = "id" />
                <form:input type = "hidden" path = "done" />
                <input type = "submit" class = "btn btn-success"/>
                <input type = "button" class = "btn btn-success" value = "showTodos"/>
            </form:form>
        </div>

        <script src= "webjars/jquery/3.6.0/jquery.min.js"></script>
        <script src= "webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src= "webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

        <script type = "text/javascript" >
        $('#targetDate').datepicker({
            format: 'yyyy-mm-dd',
            startDate: '-3d'
        });
        </script>

<%@ include file = "common/footer.jspf" %>
