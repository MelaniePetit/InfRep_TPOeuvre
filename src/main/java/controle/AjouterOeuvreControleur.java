package controle;

import dao.Service;
import erreurs.MonException;
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
@WebServlet("/AjouterOeuvre")
public class AjouterOeuvreControleur extends HttpServlet {

    private static final String ACTION_TYPE = "action";

    private static final String AJOUTER_OEUVRE = "ajouterOeuvre";
    private static final String INSERER_OEUVRE = "insererOeuvre";

    private static final String ERROR_KEY = "messageErreur";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterOeuvreControleur() {
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
        if (AJOUTER_OEUVRE.equals(actionName)) {

            try {
                Service unService = new Service();
                request.setAttribute("mesProprietaires", unService.consulterListeProprietaire());
            } catch (MonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            destinationPage = "/ajouterOeuvre.jsp";
        }
        else if (INSERER_OEUVRE.equals(actionName)) {
            try {
                Oeuvrevente uneOeuvre = new Oeuvrevente();
                uneOeuvre.setTitreOeuvrevente(request.getParameter("txttitre"));
                uneOeuvre.setPrixOeuvrevente(Integer.parseInt(request.getParameter("txtprix")));
                uneOeuvre.getProprietaire().setNomProprietaire(request.getParameter("txtnomproprio"));

                Service unService = new Service();
                unService.insertOeuvre(uneOeuvre);


            } catch (MonException e) {
                // TODO Auto-generated catch block
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
