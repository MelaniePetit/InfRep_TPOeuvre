package controle;

import dao.Service;
import erreurs.MonException;
import metier.Adherent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mel on 18/02/2017.
 */
@WebServlet("/ListeAdherents")
public class ListeAdherentControleur extends HttpServlet{

    private static final String LISTER_RADHERENT = "listerAdherent";
    private static final String ACTION_TYPE = "action";

    private static final String SUPPRIMER = "suppAdherent";
    private static final String EDIT = "editAdherent"; // ouvre la page d'édition
    private static final String MODIFIER = "modifierAdherent"; //gère l'envoi des données
    private static final String ID = "id";
    private String id ;

    private static final String ERROR_KEY = "messageErreur";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeAdherentControleur() {
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

    protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;
        // execute l'action
        if (LISTER_RADHERENT.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeAdherentsCRUD());

            } catch (MonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        if (SUPPRIMER.equals(actionName)) {
            String id = request.getParameter(ID);
            try {

                Service unService = new Service();
                unService.supprimerAdherent(id);
                request.setAttribute("flashMessage_success", "The Member has been successfully removed");

                unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeAdherentsCRUD());

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The Member can't be remove");
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        String id = request.getParameter(ID);
        if (EDIT.equals(actionName)) {
            try {
                Service unService = new Service();
                request.setAttribute("monAdherent", unService.consulterAdherent(id));
                request.setAttribute("edit", true);
            } catch (MonException e) {
                e.printStackTrace();
            }
            destinationPage = "/actionMember.jsp";
        }

        else if(MODIFIER.equals(actionName)) {
            try {
                Adherent unAdherent = new Adherent();
                unAdherent.setNomAdherent(request.getParameter("nom"));
                unAdherent.setPrenomAdherent(request.getParameter("prenom"));
                unAdherent.setVilleAdherent(request.getParameter("ville"));

                Service unService = new Service();
                System.out.println(request.getParameter("id"));
                unService.editAdherent(unAdherent, request.getParameter("id"));

            } catch (MonException e) {
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
