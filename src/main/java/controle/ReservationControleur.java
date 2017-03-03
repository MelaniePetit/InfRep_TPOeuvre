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

	protected void adherentsComboBox(HttpServletRequest request){
		try {
			Service unService = new Service();
			request.setAttribute("mesAdherents", unService.consulterListeAdherents());
		} catch (MonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void oeuvresComboBox(HttpServletRequest request){
		try {
			Service unService = new Service();
			request.setAttribute("mesOeuvres", unService.consulterListeOeuvresDisponibles());
		} catch (MonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		boolean redirect = false;

		// execute l'action
		if (RESERVER_OEUVRE.equals(actionName)){
			adherentsComboBox(request);
			oeuvresComboBox(request);
			destinationPage = "/actionReservation.jsp";
		}
		else if (INSERER_RESERVATION.equals(actionName)) {
			try {
				Reservation uneResa = new Reservation();
				uneResa.getOeuvrevente().setTitreOeuvrevente(request.getParameter("txttitre"));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());
				uneResa.setDate(date);
				uneResa.getAdherent().setNomAdherent(request.getParameter("txtadherent"));

				Service unService = new Service();
				unService.insertReservation(uneResa);

				request.setAttribute("flashMessage_success", "The reservation has been successfully added");
				redirect = true;

			} catch (MonException e) {
				request.setAttribute("flashMessage_error", "Error : The reservation can't be add");
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			adherentsComboBox(request);
			oeuvresComboBox(request);
			destinationPage = "/actionReservation.jsp";
		}
		else {
			String messageErreur = "[" + actionName + "] n'est pas une action valide.";
			request.setAttribute(ERROR_KEY, messageErreur);
		}
		// Redirection vers la page jsp appropriee
		if(redirect)
		{
			request.getRequestDispatcher("/ListeReservations?action=listerReservation").forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
			dispatcher.forward(request, response);
		}

	}

}
