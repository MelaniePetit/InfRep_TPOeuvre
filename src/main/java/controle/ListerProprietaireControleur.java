package controle;

import dao.Service;
import erreurs.MonException;
import metier.Adherent;
import metier.Proprietaire;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mel on 01/03/2017.
 */
@WebServlet("/ListeProprietaires")
public class ListerProprietaireControleur extends HttpServlet {
    private static final String ACTION_TYPE = "action";

    private static final String LISTER_PROPRIETAIRE= "listerProprio";
    private static final String SUPPRIMER = "suppProprio";
    private static final String EDIT = "editProprio"; // ouvre la page d'édition
    private static final String MODIFIER = "modifierProprio"; //gère l'envoi des données
    private static final String ID = "id";

    private static final String ERROR_KEY = "messageErreur";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerProprietaireControleur() {
        super();
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
        boolean redirect = false;
        // execute l'action
        if (LISTER_PROPRIETAIRE.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeProprietairesCRUD());
                request.setAttribute("title", "OwnersList");
                request.setAttribute("contentTitle", "Owners List");

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
                unService.supprimerProprietaire(id);
                request.setAttribute("flashMessage_success", "The owner has been removed successfully");

                unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeProprietairesCRUD());
                request.setAttribute("title", "OwnersList");
                request.setAttribute("contentTitle", "Owners List");

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The owner can't be remove");
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        String id = request.getParameter(ID);
        if (EDIT.equals(actionName)) {
            try {
                System.out.println(id);
                Service unService = new Service();
                request.setAttribute("monProprio", unService.consulterProprietaire(id));
                request.setAttribute("edit", true);
            } catch (MonException e) {
                e.printStackTrace();
            }
            destinationPage = "/actionOwner.jsp";
        }

        else if(MODIFIER.equals(actionName)) {
            try {
                Proprietaire unProprio = new Proprietaire();
                unProprio.setNomProprietaire(request.getParameter("nom"));
                unProprio.setPrenomProprietaire(request.getParameter("prenom"));

                Service unService = new Service();
                unService.editProprietaire(unProprio, request.getParameter("id"));
                redirect = true;
            } catch (MonException e) {
                e.printStackTrace();
            }
            destinationPage = "/list.jsp";

        }
        else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
        }
        // Redirection vers la page jsp appropriee
        if(redirect)
        {
            request.getRequestDispatcher("/ListeProprietaires?action=listerProprio").forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }
    }

}
