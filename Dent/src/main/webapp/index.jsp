<%@ page import="java.util.List" %>
<%@ page import="Model.Dent" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Dent> dentsList = (List<Dent>)request.getAttribute("dentList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Dentiste</title>
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/ruang-admin.min.css" rel="stylesheet">
</head>
<body id="page-top" style="background-color: antiquewhite">
<center>
    <div class="card-body" style="display: flex;justify-content: center; text-align: center; background-color: white; width: 90%; box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;">
        <div class="form-group">
            <form action="${pageContext.request.contextPath}/Index" method="get">
                <label >Dentiste</label>
                <center>
                <div class="row row-cols-1 row-cols-md-2">
                    <div class="col">
                        <p>Patient :</p><input class="form-control" type="text" id="name-7" name="Nom" style="width: 135px;" placeholder="Nom">
                    </div>
                    <div class="col">
                        <p>Date de naissance :</p><input class="form-control" type="date" id="name-6" name="date" style="width: 135px;">
                    </div>
                </div>
                </center>
                </br>
                </br>
                <% if (dentsList != null){ %>
                <div class="row row-cols-1 row-cols-md-2">
                    <p>Dent :</p><input class="form-control" type="text" id="name-11" name="dent" style="width: 135px;" placeholder="Dent">
                    <p>Note :</p><input class="form-control" type="text" id="name-12" name="note" style="width: 135px;" placeholder="Note">
                </div>
                <%}%>
                <div class="col"><button type="submit" class="btn btn-primary">Submit</button></div>
            </form>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-2">
        <div class="col"> <a href="${pageContext.request.contextPath}/Consultation"><button class="btn btn-primary">Consultation</button></a>
        </div>
</center>
</body>