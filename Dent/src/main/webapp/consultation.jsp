<%@ page import="java.util.List" %>
<%@ page import="Model.Patient" %>
<%@ page import="Model.Priorisation" %>
<%@ page import="Model.Controle" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Patient> patientList = (List<Patient>)request.getAttribute("patientList");
  List<Priorisation> priorisationList = (List<Priorisation>)request.getAttribute("priorisationList");
  List<Controle> controleList = (List<Controle>)request.getAttribute("controles");
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
      <form action="${pageContext.request.contextPath}/Consultation" method="get">
        <label >Consultation</label>
        <div class="row row-cols-1 row-cols-md-2">
          <% if (patientList != null && priorisationList != null){%>
          <div class="col">
            <select class="form-control" name="patient" style="width: 135px;">
              <% for (Patient patient : patientList) {%>
              <option class="form-control" name="" style="width: 135px;" value="<%= patient.getId()%>"><%= patient.getNom()%></option>
              <%}%>
            </select>
          </div>
          <div class="col">
            <select class="form-control" name="priorisation" style="width: 135px;">
              <% for (Priorisation priorisation : priorisationList) {%>
              <option class="form-control" name="" style="width: 135px;" value="<%= priorisation.getId()%>"><%= priorisation.getNom()%></option>
              <%}%>
            </select>
          </div>
          <div class="col"><input class="form-control" type="number" id="name-5" name="vola" style="width: 135px;" placeholder="Vola"></div>
        </div>
        <%}%>
        <div class="col"><button type="submit" class="btn btn-primary">Submit</button></div>
      </form>
    </div>
  </div>
  <% if (controleList != null){ %>
  <div class="row" style="width: 90%;display: flex;justify-content: center; text-align: center;">
    <!-- Datatables -->
    <div class="col-lg-12">
      <div class="card mb-4">
        <div class="container-fluid" id="container-wrapper">
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="m-0 font-weight-bold text-primary">Controle</h1>
          </div>
        </div>
        <div class="table-responsive p-3">
          <form action="${pageContext.request.contextPath}/#" method="get">
            <table class="table align-items-center table-flush" id="dataTable">
              <thead class="thead-light">
              <tr>
                <th>Dents</th>
                <th>Action</th>
                <th>Etat actuel</th>
                <th>Prix Action</th>
                <th>Prix Total</th>
              </tr>
              </thead>
              <tbody>
              <%for (Controle controle : controleList){%>
              <tr>
                <th><%= controle.getDent() %></th>
                <th><%= controle.getAction() %></th>
                <th><%= controle.getEtatActuel() %></th>
                <th><%= controle.getPrix() %></th>
                <th><%= controle.getPrixTotal() %></th>
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
