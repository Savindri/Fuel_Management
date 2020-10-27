package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Fuel;
import service.FuelServiceImpl;
import service.IFuelService;
import util.CommonUtil;

/**
 * Servlet implementation class UpdateFuelDataServlet
 */
@WebServlet("/UpdateFuelDataServlet")
public class UpdateFuelDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFuelDataServlet() {
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

		Fuel fuel = new Fuel();
		String tankID = request.getParameter("tankID");	
		
		fuel.setDate(request.getParameter("date"));
		fuel.setTankID(tankID);
		fuel.setFuelName(request.getParameter("name"));
		fuel.setTankCapacity(Double.parseDouble(request.getParameter("tankCapacity")));
		fuel.setFuelUsage(Double.parseDouble(request.getParameter("fuelUsage")));
		fuel.setRemaining(Double.parseDouble(request.getParameter("remaining")));
		
		IFuelService iFuelService = new FuelServiceImpl();
		iFuelService.updateFuel(tankID, fuel);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/ListFuel.jsp");
		dispatcher.forward(request, response);
	}

}
