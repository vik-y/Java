
import java.util.ArrayList;
import java.util.Collections;

import org.iiitb.es103_15.traffic.Car;
import org.iiitb.es103_15.traffic.Coords;
import org.iiitb.es103_15.traffic.Intersection;
import org.iiitb.es103_15.traffic.Road;
import org.iiitb.es103_15.traffic.RoadGrid;


public class SafeRoad extends Road {

    public SafeRoad(int dir, Intersection start, Intersection end) {
        super(dir, start, end);
    }


    public void checkCollisions() {
        for (int i = 0; i < 2; i++) {
            int dir;
            if(i==0)
                dir =this.getDir();
            else
                dir = RoadGrid.getOppDir(getDir());
            ArrayList<Car> cars = this.getCarsL(dir);
            System.out.println(cars);
            if (cars.size()!=0){
                cars.get(0).carInFront(null);
                for (int j = 1; j < cars.size(); j++) {
                    Car c1 = (Car)cars.get(j-1);
                    Car c2 = (Car)cars.get(j);
                    c2.carInFront(c1);
                }
            }
        }
    }
}