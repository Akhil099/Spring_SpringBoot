<%@ include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<div class = "container">
    <div>This is the todos list that needs to be performed by the ${name} </div>
    <h1>Your todos are:</h1>
    <hr>
        <table class = "table">
            <thead>
                <tr>

                    <th>username</th>
                    <th>description</th>
                    <th>Target</th>
                    <th>isDone?</th>
                    <th>UpdateTodo</th>
                    <th>DeleteTodo</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items = "${todos}" var="todo">
                <tr>

                    <td>${todo.username} </td>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td><a href = "update-todo?id=${todo.id}" class = "btn btn-success">UPDATE</a></td>
                    <td><a href = "delete-todos?id=${todo.id}" class = "btn btn-warning">DELETE</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    <a href = "add-todo" class = "btn btn-success">Add Todo</a>
</div>
<script src= "webjars/jquery/3.6.0/jquery.min.js"></script>
<script src= "webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<%@ include file="common/footer.jspf" %>
