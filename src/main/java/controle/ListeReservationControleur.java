package controle;

import dao.Service;
import erreurs.MonException;
import metier.Reservation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Mel on 25/02/2017.
 */
@WebServlet("/ListeReservations")
public class ListeReservationControleur extends HttpServlet{

    private static final String LISTER_RESERVATION = "listerReservation";
    private static final String ACTION_TYPE = "action";

    private static final String SUPPRIMER = "suppReservation";
    private static final String EDIT = "editReservation"; // ouvre la page d'édition
    private static final String MODIFIER = "modifierReservation"; //gère l'envoi des données
    private static final String ID = "id";
    private String id ;

    private static final String ERROR_KEY = "messageErreur";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeReservationControleur() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processusTraiteRequete(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processusTraiteRequete(request, response);
    }

    protected void adherentsComboBox(HttpServletRequest request){
        try {
            Service unService = new Service();
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void oeuvresComboBox(HttpServletRequest request){
        try {
            Service unService = new Service();
            request.setAttribute("mesOeuvres", unService.consulterListeOeuvresDisponibles());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;
        boolean redirect = false;
        // execute l'action
        if (LISTER_RESERVATION.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeReservationCRUD());
                request.setAttribute("title", "ReservationsList");
                request.setAttribute("contentTitle", "Reservations List");

            } catch (MonException e) {
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        String id = request.getParameter(ID);
        if (SUPPRIMER.equals(actionName)) {
            try {
                Service unService = new Service();
                unService.updateOeuvreBeforeDeleteReservation(id);
                System.out.println("id=" + id);
                unService.supprimerReservation(id);
                request.setAttribute("flashMessage_success", "The reservation has been successfully removed");

                unService = new Service();
                System.out.println(unService.consulterListeReservationCRUD());

                request.setAttribute("myEntities", unService.consulterListeReservationCRUD());
                request.setAttribute("title", "ReservationsList");
                request.setAttribute("contentTitle", "Reservations List");

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The reservation can't be remove");
                e.printStackTrace();
            }
            destinationPage = "/list.jsp";
        }

        if (EDIT.equals(actionName)) {
            try {
                Service unService = new Service();
                request.setAttribute("maReservation", unService.consulterReservation(id));
                request.setAttribute("mesOeuvres", unService.consulterListeOeuvres());
                request.setAttribute("mesAdherents", unService.consulterListeAdherents());

                request.setAttribute("edit", true);
            } catch (MonException e) {
                e.printStackTrace();
            }
            destinationPage = "/actionReservation.jsp";
        }

        else if(MODIFIER.equals(actionName)) {
            try {
                Reservation uneReservation = new Reservation();
                uneReservation.getOeuvrevente().setTitreOeuvrevente(request.getParameter("txttitre"));
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());
                uneReservation.setDate(date);
                uneReservation.getAdherent().setNomAdherent(request.getParameter("txtadherent"));
                Service unService = new Service();

                unService.editReservation(uneReservation, request.getParameter("txtid"));

                request.setAttribute("flashMessage_success", "The reservation has been successfully modified");

                redirect = true;
            } catch (MonException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            destinationPage = "/actionReservation.jsp";

        }
        else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
        }
        // Redirection vers la page jsp appropriee
        if(redirect)
        {
            request.getRequestDispatcher("/ListeReservations?action=listerReservation").forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }

    }

}
