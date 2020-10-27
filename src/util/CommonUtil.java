package util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.CommonConstants;
import util.QueryUtil;

import service.IFuelService;

public class CommonUtil {
	
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IFuelService.class.getName());

	public static final Properties properties = new Properties();

	static {
		try {
			
			// Read the property only once when load the class
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
			
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add new tank ID
	 * 
	 * @param arrayList
	 * @return
	 */
	public static String generateIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.TANK_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.TANK_ID_PREFIX + next;
		}
		return id;
	}

}
