package fleet;
import vehicle.*;

public class FleetVehicle extends Vehicle{

	/**
	 * @param args
	 */
	
	Location updatedLocation;
	public int distanceTravelled;
	public int distanceCounter;
	
	FleetVehicle(){
		updatedLocation = new Location(0, 0);
	}
	
	
	public int getDistanceTravelled() {
		return distanceTravelled;
	}



	@Override
	public String toString() {
		return "FleetVehicle [updatedLocation=" + updatedLocation
				+ ", distanceTravelled=" + distanceTravelled
				+ ", distanceCounter=" + distanceCounter + ", toString()="
				+ super.toString() + "]";
	}



	public void increaseDistanceTravelled(int distanceTravelled) {
		this.distanceTravelled += distanceTravelled;
	}



	public int getDistanceCounter() {
		return distanceCounter;
	}



	public void setDistanceCounter(int distanceCounter) {
		this.distanceCounter = distanceCounter;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FleetVehicle test;
		test = new FleetVehicle();
		
		System.out.println(test);
	}

}
