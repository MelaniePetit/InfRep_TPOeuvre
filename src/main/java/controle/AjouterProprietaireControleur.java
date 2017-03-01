package controle;

/**
 * Created by Mel on 01/03/2017.
 */

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

@WebServlet("/AjouterProprietaire")
public class AjouterProprietaireControleur extends HttpServlet{
    private static final String ACTION_TYPE = "action";

    private static final String AJOUTER_PROPRIETAIRE = "ajouterProprio";
    private static final String INSERER_PROPRIETAIRE = "insererProprio";

    private static final String ERROR_KEY = "messageErreur";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterProprietaireControleur() {
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

        if (AJOUTER_PROPRIETAIRE.equals(actionName)) {

            destinationPage = "/actionOwner.jsp";
        }
        else if (INSERER_PROPRIETAIRE.equals(actionName)) {
            try {
                Proprietaire unProprio = new Proprietaire();
                unProprio.setNomProprietaire(request.getParameter("nom"));
                unProprio.setPrenomProprietaire(request.getParameter("prenom"));
                Service unService = new Service();
                unService.insertProprietaire(unProprio);

                request.setAttribute("flashMessage_success", "The Owner called " + unProprio.getPrenomProprietaire() + " " + unProprio.getNomProprietaire().toUpperCase() + " has been added successfully");

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The owner can't be add");
                e.printStackTrace();
            }
            destinationPage = "/actionOwner.jsp";
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
