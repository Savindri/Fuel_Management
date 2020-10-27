package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Fuel;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionUtil;
import util.QueryUtil;

//implementation of Fuel Service 

public class FuelServiceImpl implements IFuelService {
	

	// Initialize logger 
	public static final Logger log = Logger.getLogger(FuelServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;


	private static PreparedStatement preparedStatement;

	/**
	 * Add set of fuels for as a batch from the selected fuel List
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * 
	 */

	
	@Override
	public void addFuel(Fuel fuel) {

		String tankID = CommonUtil.generateIDs(getTankIDs());
		
		//calculate remaining
		fuel.setRemaining(fuel.getTankCapacity()-fuel.getFuelUsage());
		
		try {
			
			connection = DBConnectionUtil.getDBConnection();
			
			//Query is available in FuelQuery.xml file and insert_fuel key to extract value of it
			
			preparedStatement = connection.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_FUEL));
			connection.setAutoCommit(false);
			
			
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, fuel.getDate());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, fuel.getTankID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, fuel.getFuelName());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_FOUR, fuel.getTankCapacity());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_FIVE, fuel.getFuelUsage());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_SIX, fuel.getRemaining());
			
			// Add fuel
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			
			//Close prepared statement and database connectivity at the end of transaction
			 
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}


	/**
	 * fuel details can be retrieved based on the provided tank ID
	 * 
	 * @param tankID
	 *            - fuel details are filtered based on the tank ID
	 * 
	 * @see #actionOnFuel()
	 */
	
	@Override
	public Fuel getFuelByTankID(String tankID) {
		return actionOnFuel(tankID).get(0);
	}
	
	/**
	 * Get all list of fuels
	 * 
	 * @return ArrayList<Fuel> 
	 * 						- Array of fuel list will be return
	 * 
	 * @see #actionOnEmployee()
	 */

	
	@Override
	public ArrayList<Fuel> getFuel() {
		
		return actionOnFuel(null);
	}

	/**
	 * This method delete a fuel based on the provided ID
	 * 
	 * @param tankID
	 *            - Delete tank according to the filtered tank details
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	@Override
	public void removeFuel(String tankID) {

		// Before deleting check whether tank ID is available
		if (tankID != null && !tankID.isEmpty()) {
			
			// Remove fuel query will be retrieved from FuelQuery.xml
		
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_FUEL));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, tankID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				// Close prepared statement and database connectivity at the end of transaction
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * This performs GET fuel by tankID and Display all fuels
	 * 
	 * @param tankID
	 *            ID of the tank to remove or select from the list

	 * @return ArrayList<Fuel> Array of fuel list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 * @see #getFuel()
	 * @see #getFuelByTankID(String)
	 */
	private ArrayList<Fuel> actionOnFuel(String tankID) {

		ArrayList<Fuel> fuelList = new ArrayList<Fuel>();
		
		try {
				
			connection = DBConnectionUtil.getDBConnection();
			
			// Before fetching fuel it checks whether tank ID is available
			 
			if (tankID != null && !tankID.isEmpty()) {
				
				//Get fuel by tankID query will be retrieved from FuelQuery.xml
				 
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_FUEL));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, tankID);
			}
			//If tank ID is not provided for get fuel option it display all fuels
			 
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_FUEL));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Fuel fuel = new Fuel();
				fuel.setDate(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				fuel.setTankID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				fuel.setFuelName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				fuel.setTankCapacity(resultSet.getDouble(CommonConstants.COLUMN_INDEX_FOUR));
				fuel.setFuelUsage(resultSet.getDouble(CommonConstants.COLUMN_INDEX_FIVE));
				fuel.setRemaining(resultSet.getDouble(CommonConstants.COLUMN_INDEX_SIX));
				
				fuelList.add(fuel);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			//Close prepared statement and database connectivity at the end of transaction
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return fuelList;
		
	}

	/**
	 * Get the updated fuel
	 * 
	 * @param tankID
	 *            ID of the tank to remove or select from the list
	 * 
	 * @return return the Fuel object
	 * 
	 */
	@Override
	public Fuel updateFuel(String tankID , Fuel fuel) {

		//Before fetching fuel it checks whether tank ID is available
		if (tankID != null && !tankID.isEmpty()) {
			//Update fuel query will be retrieved from FuelQuery.xml
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_FUEL));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, fuel.getDate());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, fuel.getTankID());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, fuel.getFuelName());
				preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_THREE, fuel.getTankCapacity());
				preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_FOUR, fuel.getFuelUsage());
				preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_FIVE, (fuel.getTankCapacity()-fuel.getFuelUsage()));
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				// Close prepared statement and database connectivity at the end of transaction
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated fuel
		return getFuelByTankID(tankID);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of tank id list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	public static ArrayList<String> getTankIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		//List of tank IDs will be retrieved from FuelQuery.xml
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_FUEL));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			//Close prepared statement and database connectivity at the end of transaction
			 
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return arrayList;
	}
}

