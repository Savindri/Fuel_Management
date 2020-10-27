package service;

import model.Fuel;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

//implementation of Fuel Service interface 

public interface IFuelService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IFuelService.class.getName());


	/**
	 * Add fuels for fuel table
	 * @param fuel
	 */
	
	public void addFuel(Fuel fuel);

	/**
	 * Get a particular Fuel
	 * 
	 * @param tankID
	 * @return Fuel
	 */
	public Fuel getFuelByTankID(String tankID);
	
	/**
	 * Get all list of fuels
	 * 
	 * @return ArrayList<Fuel>
	 */
	public ArrayList<Fuel> getFuel();
	
	/**
	 * Update existing fuel
	 * @param tankID
	 * @param fuel
	 * 
	 * @return
	 */
	public Fuel updateFuel(String tankID, Fuel fuel);

	/**
	 * Remove existing fuel
	 * 
	 * @param tankID
	 */
	public void removeFuel(String tankID);

}

