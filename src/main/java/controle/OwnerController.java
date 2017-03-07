package controle;

import dao.Service;
import erreurs.MonException;
import metier.Proprietaire;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jeremy on 07/03/2017.
 */
@WebServlet("/Owner")
public class OwnerController extends HttpServlet {
    //FIELDS
    private static final String ACTION_TYPE = "action";

    private static final String LIST_OWNER = "listOwner";
    private static final String ADD_OWNER = "addOwner";
    private static final String INSERT_OWNER = "insertOwner";
    private static final String REMOVE_OWNER = "removeOwner";
    private static final String EDIT_OWNER = "editOwner"; // open edit page
    private static final String SUBMIT_EDIT = "submitEdit";
    private static final String ID = "id";

    private static final String ERROR_KEY = "errorMessage";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerController() {
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

    private void processRequete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;
        boolean redirect = false;
        if (ADD_OWNER.equals(actionName)) {

            destinationPage = "/actionOwner.jsp";
        }
        else if (INSERT_OWNER.equals(actionName)) {
            try {
                Proprietaire unProprio = new Proprietaire();
                unProprio.setNomProprietaire(request.getParameter("nom"));
                unProprio.setPrenomProprietaire(request.getParameter("prenom"));
                Service unService = new Service();
                unService.insertProprietaire(unProprio);

                request.setAttribute("flashMessage_success", "The Owner called " + unProprio.getPrenomProprietaire() + " " + unProprio.getNomProprietaire().toUpperCase() + " has been added successfully");
                redirect = true;

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The owner can't be add");
                e.printStackTrace();
            }
            destinationPage = "/actionOwner.jsp";
        }
        else if (LIST_OWNER.equals(actionName)) {
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
        if (REMOVE_OWNER.equals(actionName)) {
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
        if (EDIT_OWNER.equals(actionName)) {
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

        else if(SUBMIT_EDIT.equals(actionName)) {
            try {
                Proprietaire unProprio = new Proprietaire();
                unProprio.setNomProprietaire(request.getParameter("nom"));
                unProprio.setPrenomProprietaire(request.getParameter("prenom"));

                Service unService = new Service();
                unService.editProprietaire(unProprio, request.getParameter("id"));

                request.setAttribute("flashMessage_success", "The Owner called " + unProprio.getPrenomProprietaire() + " " + unProprio.getNomProprietaire().toUpperCase() + " has been modified successfully");

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
            request.getRequestDispatcher("/Owner?action=listOwner").forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }
    }
}
