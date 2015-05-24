import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import org.iiitb.es103_15.traffic.Car;
import org.iiitb.es103_15.traffic.Coords;
import org.iiitb.es103_15.traffic.Intersection;
import org.iiitb.es103_15.traffic.Road;
import org.iiitb.es103_15.traffic.RoadGrid;
import org.iiitb.es103_15.traffic.TrafficControl;
import org.iiitb.es103_15.traffic.TrafficSignal;
import org.iiitb.es103_15.traffic.TrafficSignal.SignalListener;

public class Vi60 extends Car implements SignalListener {
    public Road currentRoad;
    public boolean redLight;
    public Intersection nextIntersection;
    float speedLimit;
    float currentAc;
    Car carAhead; //Stores the car just next to the present car

    public Vi60(){
        this.currentRoad = null;
        this.redLight = false;
        this.speedLimit = 0F;
        this.carAhead = null;
        this.currentAc = 0;
        this.nextIntersection = null;
    }

    public void onChanged(int ls) {
        if(ls == 0) {
            this.redLight = true;
        } else {
            this.redLight = false;
        }
    }

    protected void updatePos() {
        super.updatePos();
        float curspeed = this.getSpeed();	
        float spl = (float)this.getRoad().getSpeedLimit();
        int interDist = this.getDistancefromNextInter();
        double obstacleDist = interDist;
        double carDist = interDist;
        if(this.carAhead != null) {
            carDist = this.getDistance(this, this.carAhead);
            if(carDist < interDist) {
                obstacleDist = carDist;
            }
        }

        float changespeed;
        if((float)obstacleDist > 4.0F * curspeed) {
            changespeed = 0.0F;
            if((double)curspeed < 0.8D * (double)spl) {
                changespeed = 50.0F;
            } else if(curspeed < spl) {
                changespeed = 5.0F;
            } else if(curspeed > spl) {
                changespeed = 10.0F * (spl - curspeed);
            }

            if((double)(curspeed + changespeed) < 10.0D) {
                changespeed = 10.0F - curspeed;
            }

            this.accelerate(changespeed);
        } else if(interDist < 6) {
            boolean sp21 = false;
            if(this.redLight || this.carAhead != null) {
                this.accelerate(-50.0F);
                sp21 = true;
            }

            if(!sp21) {
                this.accelerate(-55.0F);
                turn();
            }
        } else if(this.carAhead != null && (float)carDist < 3.0F * curspeed) {
            changespeed = this.carAhead.getSpeed();
            if(changespeed < curspeed) {
                this.accelerate((changespeed - curspeed) / 2.0F);
            }

        }
        else if(this.carAhead != null && (float)carDist > 20.0F * curspeed) {
            changespeed = this.carAhead.getSpeed();
            if(changespeed < curspeed) {
                this.accelerate((changespeed - curspeed) / 1.0F);
            }

        }
        else if(curspeed > 12.0F) {
            this.accelerate(-(curspeed * curspeed) / (float)(4 * interDist));
        } else {
            this.accelerate(10.0F - curspeed);
        }
    }


    public void checkOverspeed(){
        float currentSpeed = this.getSpeed();
        float maxSpeed = 0.95F*speedLimit;
        float reachSpeed = 0.8F*speedLimit;

        if(this.getSpeed()>reachSpeed && this.getSpeed()<maxSpeed)
            this.accelerate(0);
        else
        {
            this.accelerate(-100);
            //System.out.println("Decelerated");
        }

    }

    public void smoothStop(){
        this.accelerate(-20-square(this.getSpeed()/(2*this.getDistancefromNextInter())), 1);
    }


    public String toString() {
        String s = "Vi60 " ;
        if(this.carAhead != null) {
            s = s + "->" + this.carAhead.getId();
        }

        return s;
    }

    public void carInFront(Car c2) {
        this.carAhead = c2;
    }

    public int getDistancefromNextInter(){
        Coords pos = this.getPos();
        Coords ipos = this.nextIntersection.getCoords();
        int dist = 0;
        switch(this.getDir()) {
            case 0:
                dist = pos.y - ipos.y;
                break;
            case 1:
                dist = ipos.x - pos.x;
                break;
            case 2:
                dist = ipos.y - pos.y;
                break;
            case 3:
                dist = pos.x - ipos.x;
        }

        return dist - 12;
    }

    private Intersection getNextInter(){
        Intersection startIntersection = this.currentRoad.getStartIntersection();
        Intersection endIntersection = this.currentRoad.getEndIntersection();

        Intersection returnInter;

        if(this.getDir() == currentRoad.getDir()) {
            //If the direction of the current road is same as the direction
            //in which we are travelling
            this.nextIntersection = currentRoad.getEndIntersection();
            returnInter = currentRoad.getEndIntersection();
        } else {
            //If the direction of the current road is different than the direction
            //in which we are travelling
            this.nextIntersection = currentRoad.getStartIntersection();
            returnInter = currentRoad.getStartIntersection();
        }

        TrafficControl tc = this.nextIntersection.getTrafficControl();
        if(tc != null && tc.getType() == 0) {
            int oppDir = RoadGrid.getOppDir(this.getDir());
            synchronized(tc) {
                ((TrafficSignal)tc).addListener(this, oppDir);
            }

            this.redLight = ((TrafficSignal)tc).getSignalState(oppDir) == 0;
        }

        return returnInter;
    }

    public TrafficControl getTrafficControl() {
        //Returns the traffic control at next signal
        TrafficControl trafficControl = this.nextIntersection.getTrafficControl();

        if (trafficControl != null && trafficControl.getType() == 0) {

            int oppDir = RoadGrid.getOppDir(this.getDir());

            synchronized (trafficControl) {
                //We are locking the traffic Control at the intersection  so that the state of the signal doesn't change
                //during the time it attains the lock
                ((TrafficSignal) trafficControl).addListener(this, oppDir);
                //Attaching a listener which listens for the change in the traffic light.
            }

            if( ((TrafficSignal) trafficControl).getSignalState(oppDir)==0) {
                this.redLight=true;
            }
            //
        }
        return trafficControl;
    }

    private void turn() {
        TrafficControl tc = this.nextIntersection.getTrafficControl();
        int oppDir = RoadGrid.getOppDir(this.getDir());
        boolean hasListener = false;
        if(tc != null && tc.getType() == 0) {
            hasListener = true;
        }

        if(!this.redLight && !this.nextIntersection.isOccupied()) {
            if(hasListener) {
                synchronized(tc) {
                    ((TrafficSignal)tc).removeListener(this, oppDir);
                }
            }

            {
                //if there is no red light
                //and the Intersection is not occupied

                Random rand = new Random();


                Road[] roads = this.nextIntersection.getRoads();
                int i = Math.abs(rand.nextInt()%(roads.length));

                int odir = RoadGrid.getOppDir(this.getDir());

                int flag=0;

            /*for(dirs[2] = RoadGrid.getRightDir(dir); roads[dirs[i]] == null; i = rand.nextInt(3)) {
                ;
            }*/
                this.accelerate(-100.0F);

                while(flag==0){
                    if(roads[i]!=null && roads[i]!=currentRoad){
                        //System.out.println("HERE");
                        int dir;
                        if(roads[i].getStartIntersection()==this.nextIntersection)
                            dir = roads[i].getDir();
                        else
                            dir= RoadGrid.getOppDir(roads[i].getDir());

                        synchronized (this.nextIntersection){
                            this.crossIntersection(nextIntersection, dir);
                        }

                        this.currentRoad = this.getRoad();
                        this.getNextInter();
                        speedLimit = this.getRoad().getSpeedLimit();
                        this.accelerate(5);
                        flag=1;
                    }
                    i = Math.abs(rand.nextInt()%(roads.length));
                }
            }

        }

    }

    public double getDistance(Car c1, Car c2){
        Coords car1 = c1.getPos();
        Coords car2 = c2.getPos();
        //This function returns the distance between 2 cars;
        double distance = Math.sqrt(square(car1.x-car2.x)*square(car1.y-car2.y));
        Car car3 = new Car();


        return distance-c2.getLength();
    }




    private int square(int val){
        return val*val;
    }

    private float square(float val){
        return val*val;
    }

    public void setInitialPos(SafeRoad r, Coords loc, int dir) {
        super.setInitialPos(r, loc, dir);
        this.currentRoad = r;
        this.speedLimit = r.getSpeedLimit(); //Getting the speed limit
        this.getNextInter();
    }

    public void drive() {
        super.drive();
        nextIntersection = this.getNextInter();
        int interDist = this.getDistancefromNextInter();
        this.accelerate((float)interDist / 8.0F); //Change this
        this.currentAc = (float)interDist/8.0F;
    }


    public void setInitialPos(Road r, Coords loc, int dir) {
        super.setInitialPos(r, loc, dir);
        this.currentRoad = r;
        this.speedLimit = r.getSpeedLimit(); //Getting the speed limit
        this.getNextInter();
    }


    public void paint(Graphics g) {
        Color carColor = Color.RED;
        g.setColor(carColor);
        super.paint(g);
        g.setColor(RoadGrid.DEFAULT_COLOR);
    }


}
