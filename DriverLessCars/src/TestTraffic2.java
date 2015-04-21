import org.iiitb.es103_15.traffic.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GraphicAttribute;

/**
 * Created by Vikas on 4/21/15.
 */



public class TestTraffic2 {
    public static void main(String[] args){
        final RoadGrid rg = new RoadGrid();

        Intersection i1 = new Intersection(500, 500);
        Intersection i2 = new Intersection(500, 200);

        Road r1 = new Road(RoadGrid.SOUTH, i1, i2);
        SmartCar car1 = new SmartCar();
        car1.setInitialPos(r1, new Coords(500, 300), RoadGrid.SOUTH);

        JFrame frame = new JFrame("Fleet Map");
        rg.add(car1);
        rg.add(i1);
        rg.add(i2);
        rg.add(r1);

        car1.setaccelerate(1000, 5);

        final JPanel canvas = new JPanel(){
            public void paintComponent(Graphics g){
                rg.paint(g);
            }
        };

        canvas.setPreferredSize(new Dimension(1000, 1000));
        frame.add(canvas, BorderLayout.LINE_START);

        //Action Listeners why ?
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                canvas.repaint();
            }
        };

        new Timer(500, taskPerformer).start();


        frame.pack();
        frame.setVisible(true);
    }


}
