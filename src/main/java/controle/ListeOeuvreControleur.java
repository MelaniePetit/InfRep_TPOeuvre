package controle;

import dao.Service;
import erreurs.MonException;
import metier.Adherent;
import metier.Oeuvrevente;

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
    private static final String EDIT = "editOeuvre";
    private static final String MODIFIER = "modifierOeuvre";
    private static final String ID = "id";

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
        processusTraite(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processusTraite(request, response);
    }

    protected void processusTraite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;
        boolean redirect = false;

        // execute l'action
        if (LISTER.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeOeuvresCRUD());
                request.setAttribute("title", "WorksOfArtList");
                request.setAttribute("contentTitle", "Works Of Art List");

            } catch (MonException e) {
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        if (SUPPRIMER.equals(actionName)) {
            int id = Integer.parseInt(request.getParameter(ID));
            try {

                Service unService = new Service();
                unService.supprimerOeuvre(id);
                request.setAttribute("flashMessage_success", "The Work of art has been successfully removed");

                unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeOeuvresCRUD());
                request.setAttribute("title", "WorksOfArtList");
                request.setAttribute("contentTitle", "Works Of Art List");

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The Work of art can't be remove");
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        String id = request.getParameter(ID);
        if (EDIT.equals(actionName)) {
            try {
                Service unService = new Service();
                request.setAttribute("monOeuvre", unService.consulterOeuvre(id));
                request.setAttribute("mesProprietaires", unService.consulterListeProprietaire());
                request.setAttribute("edit", true);

            } catch (MonException e) {
                e.printStackTrace();
            }

            destinationPage = "/actionWorkOfArt.jsp";
        }
        else if(MODIFIER.equals(actionName)) {
            try {

                Oeuvrevente uneOeuvre = new Oeuvrevente();
                uneOeuvre.setTitreOeuvrevente(request.getParameter("titre"));
                uneOeuvre.getProprietaire().setNomProprietaire(request.getParameter("nomproprio"));
                uneOeuvre.setPrixOeuvrevente(Integer.parseInt(request.getParameter("prix")));

                Service unService = new Service();

                unService.editOeuvre(uneOeuvre, request.getParameter("id"));

                request.setAttribute("flashMessage_success", "The Work of art '" + uneOeuvre.getTitreOeuvrevente() + "' has been modified successfully");

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
            request.getRequestDispatcher("/ListeOeuvres?action=listerOeuvre").forward(request, response);
        }
        else
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }

    }
}
