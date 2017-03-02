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
@WebServlet("/AjouterAdherent")
public class AjouterAdherentControleur extends HttpServlet {
    private static final String ACTION_TYPE = "action";

    private static final String AJOUTER_ADHERENT = "ajouterAdherent";
    private static final String INSERER_ADHERENT = "insererAdherent";

    private static final String ERROR_KEY = "messageErreur";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterAdherentControleur() {
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
        boolean redirect = false;
        // execute l'action

        if (AJOUTER_ADHERENT.equals(actionName)) {

            destinationPage = "/actionMember.jsp";
        }
        else if (INSERER_ADHERENT.equals(actionName)) {
            try {
                Adherent unAdherent = new Adherent();
                unAdherent.setNomAdherent(request.getParameter("nom"));
                unAdherent.setPrenomAdherent(request.getParameter("prenom"));
                unAdherent.setVilleAdherent(request.getParameter("ville"));
                Service unService = new Service();
                unService.insertAdherent(unAdherent);

                request.setAttribute("flashMessage_success", "The Member " + unAdherent.getPrenomAdherent() + " " + unAdherent.getNomAdherent().toUpperCase() + " has been added successfully");
                redirect = true;
            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The Member can't be add");
                e.printStackTrace();
            }
            destinationPage = "/actionMember.jsp";
        }
        else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
        }
        // Redirection vers la page jsp appropriee
        if(redirect)
        {
            request.getRequestDispatcher("/ListeAdherents?action=listerAdherent").forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }

    }

}
