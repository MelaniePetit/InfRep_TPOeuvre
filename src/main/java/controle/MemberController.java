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

@WebServlet("/Member")
public class MemberController extends HttpServlet {

    //FIELDS
    private static final String ACTION_TYPE = "action";

    private static final String LIST_MEMBER = "listMember";
    private static final String ADD_MEMBER = "addMember";
    private static final String INSERT_MEMBER = "insertMember";
    private static final String REMOVE_MEMBER = "removeMember";
    private static final String EDIT_MEMBER = "editMember"; // open edit page
    private static final String SUBMIT_EDIT = "submitEdit";
    private static final String ID = "id";

    private static final String ERROR_KEY = "errorMessage";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
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
        // execute action
        if (ADD_MEMBER.equals(actionName)) {
            destinationPage = "/actionMember.jsp";
        }
        else if (INSERT_MEMBER.equals(actionName)) {
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
        else if (LIST_MEMBER.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeAdherentsCRUD());
                request.setAttribute("title", "MembersList");
                request.setAttribute("contentTitle", "Members List");


            } catch (MonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        else if (REMOVE_MEMBER.equals(actionName)) {
            String id = request.getParameter(ID);
            try {

                Service unService = new Service();
                unService.supprimerAdherent(id);
                request.setAttribute("flashMessage_success", "The Member has been successfully removed");

                unService = new Service();
                request.setAttribute("myEntities", unService.consulterListeAdherentsCRUD());
                request.setAttribute("title", "MembersList");
                request.setAttribute("contentTitle", "Members List");

            } catch (MonException e) {
                request.setAttribute("flashMessage_error", "Error : The Member can't be remove");
                e.printStackTrace();
            }

            destinationPage = "/list.jsp";
        }
        String id = request.getParameter(ID);
        if (EDIT_MEMBER.equals(actionName)) {
            try {
                Service unService = new Service();
                request.setAttribute("monAdherent", unService.consulterAdherent(id));
                request.setAttribute("edit", true);
            } catch (MonException e) {
                e.printStackTrace();
            }
            destinationPage = "/actionMember.jsp";
        } else if (SUBMIT_EDIT.equals(actionName)) {
            try {
                Adherent unAdherent = new Adherent();
                unAdherent.setNomAdherent(request.getParameter("nom"));
                unAdherent.setPrenomAdherent(request.getParameter("prenom"));
                unAdherent.setVilleAdherent(request.getParameter("ville"));

                Service unService = new Service();
                unService.editAdherent(unAdherent, request.getParameter("id"));

                request.setAttribute("flashMessage_success", "The Member " + unAdherent.getPrenomAdherent() + " " + unAdherent.getNomAdherent().toUpperCase() + " has been modified successfully");

                redirect = true;
            } catch (MonException e) {
                e.printStackTrace();
            }
            destinationPage = "/list.jsp";

        } else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
        }
        // Redirection vers la page jsp appropriee
        if (redirect) {
            request.getRequestDispatcher("/Member?action=listMember").forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }
    }
}
