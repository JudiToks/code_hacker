<%@ page import="Model.Route" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Priorisation" %>
<%@ page import="Model.Devise" %>
<%
    List<Route> routeList = (List<Route>)request.getAttribute("routeList");
    List<Priorisation> priorisationList = (List<Priorisation>)request.getAttribute("priorisationList");
    List<Devise> deviseList = (List<Devise>)request.getAttribute("devises");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>RN</title>
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/ruang-admin.min.css" rel="stylesheet">
</head>
<body id="page-top" style="background-color: antiquewhite;">
<center>
    <div class="card-body" style="display: flex;justify-content: center; text-align: center; background-color: white; width: 60%; box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;">
        <form action="${pageContext.request.contextPath}/Rn" method="get">
        <div class="form-group">
            <label >Lalana</label>
            <% if (routeList != null && priorisationList != null){%>
            <div class="row row-cols-1 row-cols-md-2">
                <div class="col">
                    <select class="form-control" name="rn" style="width: 100px;">
                        <% for (Route route : routeList) {%>
                        <option class="form-control" name="" style="width: 100px;" value="<%= route.getIdRoute()%>"><%= route.getIdRoute()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col">
                    <select class="form-control" name="priorite" style="width: 100px;">
                        <% for (Priorisation priorisation : priorisationList) {%>
                        <option class="form-control" name="" style="width: 135px;" value="<%= priorisation.getId()%>"><%= priorisation.getNom()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col"><input class="form-control" type="number" id="name-8" name="vola" style="width: 135px;" placeholder="Vola"></div>

            </div>

                <div class="col"><button type="submit" class="btn btn-primary">Submit</button></div>
            <% } %>
        </div>
        </form>
    </div>
    <% if (deviseList != null){ %>
    <div class="row" style="width: 90%;display: flex;justify-content: center; text-align: center;">
        <!-- Datatables -->
        <div class="col-lg-12">
            <div class="card mb-4">
                <div class="container-fluid" id="container-wrapper">
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="m-0 font-weight-bold text-primary">Construction</h1>
                    </div>
                </div>
                <div class="table-responsive p-3">
                    <form action="${pageContext.request.contextPath}/#" method="get">
                        <table class="table align-items-center table-flush" id="dataTable">
                            <thead class="thead-light">
                            <tr>
                                <th>Rn</th>
                                <th>Pk debut</th>
                                <th>Pk fin</th>
                                <th>Action</th>
                                <th>Etat actuel</th>
                                <th>Prix Action</th>
                                <th>Prix Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%for (Devise devise : deviseList){%>
                            <tr>
                                <th><%= devise.getRn() %></th>
                                <th><%= devise.getPkDebut() %></th>
                                <th><%= devise.getPkFin() %></th>
                                <th><%= devise.getAction() %></th>
                                <th><%= devise.getEtat() %></th>
                                <th><%= devise.getPrix() %></th>
                                <th><%= devise.getPrixTotal() %></th>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</center>
</body>