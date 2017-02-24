package controle;

import erreurs.MonException;
import dao.Service;
import metier.Reservation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Servlet implementation class ReservationControleur
 */
@WebServlet("/Reservation")
public class ReservationControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";

	private static final String RESERVER_OEUVRE = "reserverOeuvre";
	private static final String INSERER_RESERVATION = "insererReservation";

	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationControleur() {
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
		if (RESERVER_OEUVRE.equals(actionName)){

			try {
				Service unService = new Service();
				request.setAttribute("mesAdherents", unService.consulterListeAdherents());
                request.setAttribute("mesOeuvres", unService.consulterListeOeuvresDisponibles());

            } catch (MonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			destinationPage = "/reservationOeuvre.jsp";
		}
		else if (INSERER_RESERVATION.equals(actionName)) {

			try {
				Reservation uneResa = new Reservation();
				uneResa.getOeuvrevente().setTitreOeuvrevente(request.getParameter("txttitre"));
				DateFormat df = new SimpleDateFormat("dd/mm/yyyy", Locale.FRENCH);
				Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());
				uneResa.setDate(date);
				uneResa.getAdherent().setNomAdherent(request.getParameter("txtadherent"));

				Service unService = new Service();
				unService.insertReservation(uneResa);

			} catch (MonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
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
