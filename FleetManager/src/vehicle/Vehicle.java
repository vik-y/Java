package vehicle;

public class Vehicle {

	/**
	 * @param args
	 */
	private Location currLocation = new Location()
	;
	public Location getLocation() {
		return new Location(currLocation.x, currLocation.y);
	}
	public void setLocation(Location loc) {
		currLocation.x = loc.x;
		currLocation.y = loc.y;
	}

	@Override
	public String toString() {
		return "Vehicle [currLocation=" + currLocation + "]";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
