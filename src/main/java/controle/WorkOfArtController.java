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
 * Created by jeremy on 05/03/2017.
 */
@WebServlet("/WorkOfArt")
public class WorkOfArtController extends HttpServlet {
    //FIELDS
    private static final String ACTION_TYPE = "action";

    private static final String LIST_WORKOFART = "listWorkOfArt";
    private static final String ADD_WORKOFART = "addWorkOfArt";
    private static final String INSERT_WORKOFART = "insertWorkOfArt";
    private static final String REMOVE_WORKOFART = "removeWorkOfArt";
    private static final String EDIT_WORKOFART = "editWorkOfArt"; // open edit page
    private static final String SUBMIT_EDIT = "submitEdit";
    private static final String ID = "id";

    private static final String ERROR_KEY = "errorMessage";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkOfArtController() {
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

    private void proprietairesComboBox(HttpServletRequest request){
        try {
            Service unService = new Service();
            request.setAttribute("mesProprietaires", unService.consulterListeProprietaire());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void processRequete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String actionName = request.getParameter(ACTION_TYPE);
            String destinationPage = ERROR_PAGE;
            boolean redirect = false;
            // execute action
            if (ADD_WORKOFART.equals(actionName)) {

                proprietairesComboBox(request);
                destinationPage = "/actionWorkOfArt.jsp";
            }
            else if (INSERT_WORKOFART.equals(actionName)) {
                try {
                    Oeuvrevente uneOeuvre = new Oeuvrevente();
                    uneOeuvre.setTitreOeuvrevente(request.getParameter("titre"));
                    uneOeuvre.setPrixOeuvrevente(Integer.parseInt(request.getParameter("prix")));
                    uneOeuvre.getProprietaire().setNomProprietaire(request.getParameter("nomproprio"));

                    Service unService = new Service();
                    unService.insertOeuvre(uneOeuvre);

                    request.setAttribute("flashMessage_success", "The Work of art '" + uneOeuvre.getTitreOeuvrevente() + "' has been added successfully");
                    redirect = true;

                } catch (MonException e) {
                    // TODO Auto-generated catch block
                    request.setAttribute("flashMessage_error", "Error : The Work of art can't be add");
                    e.printStackTrace();
                }
                proprietairesComboBox(request);
                destinationPage = "/actionWorkOfArt.jsp";
            }
            else if (LIST_WORKOFART.equals(actionName)) {
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
            if (REMOVE_WORKOFART.equals(actionName)) {
                int id = Integer.parseInt(request.getParameter(ID));
                try {

                    Service unService = new Service();
                    if(!unService.isOeuvreReserved(id))
                    {
                        unService.supprimerOeuvre(id);
                        request.setAttribute("flashMessage_success", "The Work of art has been successfully removed");
                    }
                    else
                    {
                        request.setAttribute("flashMessage_error", "The Work of art is Reserved, delete the reservation first");
                    }

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
            if (EDIT_WORKOFART.equals(actionName)) {
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
            else if(SUBMIT_EDIT.equals(actionName)) {
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
                request.getRequestDispatcher("/WorkOfArt?action=listWorkOfArt").forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
                dispatcher.forward(request, response);
            }
    }
}
