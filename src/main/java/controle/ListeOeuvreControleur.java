package controle;

import dao.Service;
import erreurs.MonException;

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
@WebServlet("/ListeOeuvres")

public class ListeOeuvreControleur extends HttpServlet {
    private static final String ACTION_TYPE = "action";

    private static final String LISTER = "listerOeuvre";
    private static final String SUPPRIMER = "suppOeuvre";
    private static final String ID = "titre";

    private static final String ERROR_KEY = "messageErreur";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeOeuvreControleur() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processusTraiteGet(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processusTraitePost(request, response);
    }

    protected void processusTraiteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;
        // execute l'action
        if (LISTER.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("mesOeuvres", unService.consulterListeOeuvres());

            } catch (MonException e) {
                e.printStackTrace();
            }

            destinationPage = "/listerOeuvre.jsp";
        }
        if (SUPPRIMER.equals(actionName)) {
            String titre = request.getParameter(ID);
            try {

                Service unService = new Service();
                unService.supprimerOeuvre(titre);

                unService = new Service();
                request.setAttribute("mesOeuvres", unService.consulterListeOeuvres());

            } catch (MonException e) {
                e.printStackTrace();
            }

            destinationPage = "/listerOeuvre.jsp";
        }
        else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
        }
        // Redirection vers la page jsp appropriee
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
        dispatcher.forward(request, response);

    }

    protected void processusTraitePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;
        // execute l'action
    }
}