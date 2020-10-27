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

@WebServlet("/AddFuelDataServlet")
public class AddFuelDataServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFuelDataServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Fuel fuel = new Fuel();
		
		fuel.setDate(request.getParameter("date"));
		fuel.setFuelName(request.getParameter("name"));
		fuel.setTankCapacity(Double.parseDouble(request.getParameter("tankCapacity")));
		fuel.setFuelUsage(Double.parseDouble(request.getParameter("fuelUsage")));

		
		IFuelService iFuelService = new FuelServiceImpl();
		ArrayList<String> farray = FuelServiceImpl.getTankIDs();
		fuel.setTankID(CommonUtil.generateIDs(farray));
		iFuelService.addFuel(fuel);

		request.setAttribute("fuel", fuel);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/ListFuel.jsp");
		dispatcher.forward(request, response);
	}

}
