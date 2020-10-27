package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FuelServiceImpl;
import service.IFuelService;


/**
 * Servlet implementation class DeleteFuelDataServlet
 */
@WebServlet("/DeleteFuelDataServlet")
public class DeleteFuelDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFuelDataServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		response.setContentType("text/html");

		String tankID = request.getParameter("tankID");			
		
		IFuelService iFuelService = new FuelServiceImpl();
		iFuelService.removeFuel(tankID);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/ListFuel.jsp");
		dispatcher.forward(request, response);
	}

}
