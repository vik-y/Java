package fleet;
import java.util.*;
import vehicle.*;
import java.util.Arrays;

public class FleetManager {

	/**
	 * @param args
	 */
	int vehicle_count = 10;
	FleetVehicle[] vehicles;
	
	public FleetManager() {
		// TODO Auto-generated constructor stub
		vehicles = new FleetVehicle[vehicle_count]; //Create reference of FleetVehicle objects
		for(int i=0;i<vehicle_count;i++){
			vehicles[i] = new FleetVehicle();
		}
	}
	
	public void showLocations(){
		for(int i=0;i<vehicle_count;i++){
			System.out.println(vehicles[i]);
		}
	}
	
	public void simulateMoveCars(){
		//loop through all the cars
		//generate a random location of cars 
		for(int i=0;i<vehicle_count;i++){
			Random rand = new Random();
			Location loc = vehicles[i].getLocation();
			loc.x += (rand.nextInt(10)-5);
			loc.y += (rand.nextInt(10)-5);
			vehicles[i].setLocation(loc);
			
			int distanceMoved = distanceMoved(loc, vehicles[i].updatedLocation);
			System.out.println(distanceMoved);
			
			vehicles[i].distanceTravelled += distanceMoved;
			vehicles[i].distanceCounter += distanceMoved;
			
			if(vehicles[i].distanceCounter>10){
				vehicles[i].distanceCounter = 0;
				vehicles[i].updatedLocation = loc;
			}
		}
	}
	
	private int distanceMoved(Location a, Location b){
		return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
	}
	
	@Override
	public String toString() {
		String rstring = new String("");
		for(int i=0;i<vehicle_count;i++){
			rstring +="Vehicle Number: "+i+" "+ vehicles[i]+"\n";
		}
		return rstring;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FleetManager test;
		test = new FleetManager();
		for(int i=0;i<10;i++){
			System.out.print(test);
			test.simulateMoveCars();
			System.out.println("\n\n");
			System.out.println(test);

		}
	}

	

}
