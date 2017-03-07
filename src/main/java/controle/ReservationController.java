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

/**
 * Created by jeremy on 07/03/2017.
 */
@WebServlet("/Reservation")
public class ReservationController extends HttpServlet {
    //FIELDS
    private static final String ACTION_TYPE = "action";

    private static final String LIST_RESERVATION = "listReservation";
    private static final String ADD_RESERVATION = "addReservation";
    private static final String INSERT_RESERVATION = "insertReservation";
    private static final String REMOVE_RESERVATION = "removeReservation";
    private static final String EDIT_RESERVATION = "editReservation"; // open edit page
    private static final String SUBMIT_EDIT = "submitEdit";
    private static final String ID = "id";

    private static final String ERROR_KEY = "errorMessage";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequete(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequete(request, response);
    }

    private void adherentsComboBox(HttpServletRequest request){
        try {
            Service unService = new Service();
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void oeuvresComboBox(HttpServletRequest request){
        try {
            Service unService = new Service();
            request.setAttribute("mesOeuvres", unService.consulterListeOeuvresDisponibles());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void processRequete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;
        boolean redirect = false;
        if (ADD_RESERVATION.equals(actionName)){
            adherentsComboBox(request);
            oeuvresComboBox(request);
            destinationPage = "/actionReservation.jsp";
        }
        else if (INSERT_RESERVATION.equals(actionName)) {
            try {
                Reservation uneResa = new Reservation();
                uneResa.getOeuvrevente().setTitreOeuvrevente(request.getParameter("txttitre"));
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());
                uneResa.setDate(date);
                uneResa.getAdherent().setNomAdherent(request.getParameter("txtadherent"));

                Service unService = new Service();
                unService.insertReservation(uneResa);

                request.setAttribute("flashMessage_success", "The reservation has been successfully added");
                redirect = true;

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The reservation can't be add");
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            adherentsComboBox(request);
            oeuvresComboBox(request);
            destinationPage = "/actionReservation.jsp";
        }
        else if (LIST_RESERVATION.equals(actionName)) {
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
        if (REMOVE_RESERVATION.equals(actionName)) {
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

        if (EDIT_RESERVATION.equals(actionName)) {
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

        else if(SUBMIT_EDIT.equals(actionName)) {
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
            request.getRequestDispatcher("/Reservation?action=listReservation").forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }
    }
}
