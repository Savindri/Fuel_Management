package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Fuel;
import service.FuelServiceImpl;
import service.IFuelService;

/**
 * Servlet implementation class GetFuelDataServlet
 */
@WebServlet("/GetFuelDataServlet")
public class GetFuelDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFuelDataServlet() {
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
		Fuel fuel = iFuelService.getFuelByTankID(tankID);

		request.setAttribute("fuel", fuel);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/GetFuel.jsp");
		dispatcher.forward(request, response);

	}

}
