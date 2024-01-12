package com.example.dent;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Consultation", value = "/Consultation")
public class Consultation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient patient = new Patient();
        Priorisation priorisation = new Priorisation();
        PatientEtatDent patientEtatDent = new PatientEtatDent();
        Controle controle = new Controle();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        try {
            Connection connection = connexionBDD.connect();
            List<Patient> patientList = patient.AllPatient(connection);
            List<Priorisation> priorisationList = priorisation.AllPriorisation(connection);
            request.setAttribute("patientList" , patientList);
            request.setAttribute("priorisationList" , priorisationList);
            if (request.getParameter("patient") != null && request.getParameter("priorisation") != null && request.getParameter("vola") != null){
                int idPatient = Integer.parseInt(request.getParameter("patient"));
                int idPriorisation = Integer.parseInt(request.getParameter("priorisation"));
                double vola = Double.parseDouble(request.getParameter("vola"));
                List<PatientEtatDent> patientEtatDentList = patientEtatDent.DentPatienbyIdPriorite(idPriorisation , idPatient , connection);
                List<Controle> controleList = controle.Traitement(patientEtatDentList ,  connection);
                for (Controle controles : controleList){
                    System.out.println(controles.getDent());
                }
                List<Controle> controles = controle.TraitementFinal10(controleList , vola , connection);
                request.setAttribute("controles" , controles);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("consultation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
