package model;

public class Fuel {
	
	private String date;
	private String tankID;
	private String fuelName;
	private Double tankCapacity;
	private Double fuelUsage;
	private Double remaining;
	
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date=date;
	}
	
	public String getTankID() {
		return tankID;
	}
	public void setTankID(String tankID) {
		this.tankID = tankID;
	}
	public String getFuelName() {
		return fuelName;
	}
	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	public Double getTankCapacity() {
		return tankCapacity;
	}
	public void setTankCapacity(Double tankCapacity) {
		this.tankCapacity = tankCapacity;
	}
	public Double getFuelUsage() {
		return fuelUsage;
	}
	public void setFuelUsage(Double fuelUsage) {
		this.fuelUsage = fuelUsage;
	}

	public Double getRemaining() {
		return remaining;
	}

	public void setRemaining(Double remaining) {
		this.remaining = remaining;
	}
	
}
	
	
	