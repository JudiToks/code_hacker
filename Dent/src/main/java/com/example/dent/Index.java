package com.example.dent;

import Model.ConnexionBDD;
import Model.Dent;
import Model.Patient;
import Model.PatientEtatDent;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Index", value = {"", "/Index"})
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient patient = new Patient();
        Dent dent = new Dent();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        try {
            Connection connection = connexionBDD.connect();
            List<Dent> dentList = dent.AllDent(connection);
            request.setAttribute("dentList" , dentList);
            if (request.getParameter("Nom") != null && request.getParameter("date") != null){
                PatientEtatDent patientEtatDent = new PatientEtatDent();
                String nomPatient = request.getParameter("Nom");
                Date dtn = Date.valueOf(request.getParameter("date"));
                patient.setNom(nomPatient);
                patient.setDtn(dtn);
                patient.inserPatient(patient , connection);
                int idMaxPatient = patient.MaxPatient(connection);
                if (request.getParameter("dent") != null && request.getParameter("note") != null){
                    if (request.getParameter("dent").contains(";") && request.getParameter("note").contains(";")){
                        String[] dentP = request.getParameter("dent").split(";");
                        String[] NoteP = request.getParameter("note").split(";");
                        for (int i = 0 ; i<dentP.length ; i++){
                            //System.out.println("Dent :"+dentP[i]+" etat :"+Double.parseDouble(NoteP[i]));
                            patientEtatDent.setCode(dentP[i]);
                            patientEtatDent.setEtat(Double.parseDouble(NoteP[i]));
                            patientEtatDent.setIdPatient(idMaxPatient);
                            patientEtatDent.inserPatientEtatDent(patientEtatDent , connection);
                        }
                    }
                    if (request.getParameter("dent").contains("-")){
                        String[] dentP = request.getParameter("dent").split("-");
                        double NoteP = Double.parseDouble(request.getParameter("note"));
                        int a = dent.DentByCode(dentP[0] , connection).getIdDent();
                        int b = dent.DentByCode(dentP[1] , connection).getIdDent();
                        //System.out.println(a+" "+b);
                        List<Dent> dents = dent.AllDentEntreById(a , b , connection);
                        for (Dent dent1 : dents){
                            //System.out.println("Dent :"+dent1.getCode()+" etat :"+NoteP);
                            patientEtatDent.setCode(dent1.getCode());
                            patientEtatDent.setEtat(NoteP);
                            patientEtatDent.setIdPatient(idMaxPatient);
                            patientEtatDent.inserPatientEtatDent(patientEtatDent , connection);
                        }
                    }
                    if(!request.getParameter("dent").contains("-") && !request.getParameter("dent").contains(";") && !request.getParameter("note").contains(";")) {
                        String dentP = request.getParameter("dent");
                        double NoteP = Double.parseDouble(request.getParameter("note"));
                        patientEtatDent.setCode(dentP);
                        patientEtatDent.setEtat(NoteP);
                        patientEtatDent.setIdPatient(idMaxPatient);
                        patientEtatDent.inserPatientEtatDent(patientEtatDent , connection);
                    }
                }
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
