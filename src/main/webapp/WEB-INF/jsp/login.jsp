<%@ page import="com.epam.java.rt.lab.action.ActionResult" %>
<% ActionResult result = (ActionResult) request.getAttribute("result"); %>
<div class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-xs-offset-1 col-xs-10">
    <div class="panel panel-default">
        <div class="panel-body">
            <% if (result.getProperty("errorArray") != null) { %>
                <% for (String error : (String[])result.getProperty("errorArray")) { %>
            <div class="alert alert-danger" role="alert"><%=error%></div>
                <% } %>
            <% } %>
            <form action="/web/login" method="POST">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-default col-xs-offset-2 col-xs-8">Submit</button>
            </form>
        </div>
    </div>
</div>