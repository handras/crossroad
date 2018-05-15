/* made after a template found on the web
 * https://www.ntu.edu.sg/home/ehchua/programming/java/J4b_CustomGraphics.html
 */

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.util.Map;
import javax.swing.*;    // Using Swing's components and containers

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class CrossroadGraphic extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH = 640;
    public static final int CANVAS_HEIGHT = 640;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;
    CrossroadModel model;
    Map<String, Car> cars;

    // Constructor to set up the GUI components and event handlers
    public CrossroadGraphic() {
        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvas);
        // or "setContentPane(canvas);"

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("Crossroad birdview");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                canvas.sizeChanged(e.getComponent().getWidth(), e.getComponent().getHeight());
            }
        });
    }

    public void setModel(CrossroadModel model){
        this.model = model;
        this.cars = model.cars;
        model.graphic = this;
    }

    void modelChanged(){
        canvas.repaint();
    }


    private class DrawCanvas extends JPanel {

        int top, middle, bottom, left, center1, center2, right;
        int verticalline, horizontalline;
        int carRadius;
        int width, height;

        public DrawCanvas(){
            width = CANVAS_WIDTH;
            height = CANVAS_HEIGHT;
            calcCoords();
        }
        void sizeChanged(int w, int h){
            width = w;
            height = h;
            calcCoords();
            repaint();
        }

        public void calcCoords(){
            top    = (int)(height*0.05f/0.7f);
            middle = (int)(height*0.25f/0.7f);
            bottom = (int)(height*0.65f/0.7f);

            left    = (int)(width*0.05f/1.1f);
            center1 = (int)(width*0.45f/1.1f);
            center2 = (int)(width*0.65f/1.1f);
            right   = (int)(width*1.05f/1.1f);

            carRadius = (int)(left*0.9f);

            verticalline = width/2;
            horizontalline = (middle-top)/2+top;
        }

        // Override paintComponent to perform my own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            setBackground(Color.lightGray);  // set background color for this JPanel

            g.setColor(Color.darkGray);    // set the drawing color

            g.fillRect(left, top, right-left, middle-top);
            g.fillRect(center1, middle, center2-center1, bottom-middle);

            //make a copy so that the original graphic context don't get messed up
            Graphics2D gcopy = (Graphics2D) g.create();
            gcopy.setColor(Color.lightGray);
            //set the stroke of the copy, not the original
            Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{35,30}, 0);
            gcopy.setStroke(dashed);
            gcopy.drawLine(left, horizontalline, right, horizontalline);
            gcopy.drawLine(verticalline, horizontalline, verticalline, bottom);

            dashed = new BasicStroke(2*top, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{left/5f,left/5f}, left/4f);
            gcopy.setStroke(dashed);
            gcopy.drawLine(center1, middle+top, center2, middle+top);
            gcopy.dispose();

            drawCars(g);
        }

        public void drawCars(Graphics g){
            for (Map.Entry<String, Car> entry  : cars.entrySet()){
                Car car = entry.getValue();
                g.setColor(Color.blue);
                g.fillOval((int)car.x, (int)car.y, carRadius, carRadius);
            }
        }

//        private void showcase(){
//            g.drawLine(30, 40, 100, 200);
//            g.drawOval(150, 180, 10, 10);
//            g.drawRect(200, 210, 20, 30);
//            g.setColor(Color.RED);       // change the drawing color
//            g.fillOval(300, 310, 30, 50);
//            g.fillRect(400, 350, 60, 50);
//            // Printing texts
//            g.setColor(Color.WHITE);
//            g.setFont(new Font("Monospaced", Font.PLAIN, 12));
//            g.drawString("Testing custom drawing ...", 10, 20);
//        }
    }
}