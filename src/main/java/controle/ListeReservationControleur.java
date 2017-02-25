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
        // execute l'action
        if (LISTER_RESERVATION.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeReservation());

            } catch (MonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        String id = request.getParameter(ID);
        if (SUPPRIMER.equals(actionName)) {
            try {

                Service unService = new Service();
                unService.supprimerAdherent(id);
                request.setAttribute("flashMessage_success", "The Member has been successfully removed");

                unService = new Service();
                request.setAttribute("myReservation", unService.consulterListeReservation());

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The Member can't be remove");
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }

        if (EDIT.equals(actionName)) {
            try {
                Service unService = new Service();
                request.setAttribute("maReservation", unService.consulterReservation(id));
                request.setAttribute("edit", true);
            } catch (MonException e) {
                e.printStackTrace();
            }
            destinationPage = "/actionMember.jsp";
        }

        else if(MODIFIER.equals(actionName)) {
            try {
                Reservation uneReservation = new Reservation();
                uneReservation.getOeuvrevente().setTitreOeuvrevente(request.getParameter("txttitre"));
                DateFormat df = new SimpleDateFormat("dd/mm/yyyy", Locale.FRENCH);
                Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());
                uneReservation.setDate(date);
                uneReservation.getAdherent().setNomAdherent(request.getParameter("txtadherent"));


                Service unService = new Service();
                unService.editReservation(uneReservation, request.getParameter("id"));

            } catch (MonException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            destinationPage = "/index.jsp";

        }
        else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
        }
        // Redirection vers la page jsp appropriee
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
        dispatcher.forward(request, response);

    }

}
