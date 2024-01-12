package com.example.rn;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Rn", value = {"", "/Rn"})
public class Rn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Route route = new Route();
        Priorisation priorisation = new Priorisation();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        DegatRoute degatRoute = new DegatRoute();
        Devise devise = new Devise();
        try {
            Connection connection = connexionBDD.connect();
            List<Route> routeList = route.AllRoute(connection);
            List<Priorisation> priorisationList = priorisation.AllPriorisation(connection);
            request.setAttribute("routeList" , routeList);
            request.setAttribute("priorisationList" , priorisationList);
            if (request.getParameter("rn") != null && request.getParameter("priorite") != null && request.getParameter("vola") != null){
                int rn = Integer.parseInt(request.getParameter("rn"));
                int priorite = Integer.parseInt(request.getParameter("priorite"));
                double vola = Double.parseDouble(request.getParameter("vola"));
                List<DegatRoute> degatRouteList = degatRoute.DegatRouteByIdRouteByPriorite(rn , priorite ,  connection);
                List<DegatRoute> degatRoutes = degatRoute.DegatRouteNotImportantByIdRouteByPriorite(rn , priorite ,  connection);
                if (degatRoutes.size() != 0){
                    for (int i = 0 ; i < degatRoutes.size() ; i++){
                        degatRouteList.add(degatRoutes.get(i));
                    }
                }
                List<Devise> deviseList = devise.DeviseByListDegat(degatRouteList , connection);
                List<Devise> devises = devise.DeviseFinal10(deviseList , vola , connection);
                request.setAttribute("devises" , devises);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
