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
    public static final int CANVAS_WIDTH = 1280;
    public static final int CANVAS_HEIGHT = 800;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;
    CrossroadModel model;
    Map<String, Car> cars;
	Map<String, Pedestrian> peds;

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
		this.peds = model.peds;
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
            bottom = (int)(height*0.65f/0.7f);

            left    = (int)(width*0.05f/1.1f);
            right   = (int)(width*1.05f/1.1f);

            middle = (int)convWorYtoImgY(6f);
            center1 = (int)convWorXtoImgX(20f);
            center2 = (int)convWorXtoImgX(26f);

            carRadius = (int)((center2-center1)*0.35f);

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
                    0, new float[]{30,25}, 0);
            gcopy.setStroke(dashed);
            gcopy.drawLine(left, horizontalline, center1, horizontalline);
            gcopy.drawLine(center2, horizontalline, right, horizontalline);
            gcopy.drawLine(verticalline, middle, verticalline, bottom);

            dashed = new BasicStroke(2*top, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{left/5f,left/5f}, left/4f);
            gcopy.setStroke(dashed);
            gcopy.drawLine(center1, middle+top, center2, middle+top);
            gcopy.dispose();
            drawCars(g);
			drawPedestrians(g);
        }

        public void drawCars(Graphics g){
            for (Map.Entry<String, Car> entry  : cars.entrySet()){
                Car car = entry.getValue();
                int x = (int)convWorXtoImgX(car.x)-carRadius/2;
                int y = (int)convWorYtoImgY(car.y)-carRadius/2;
//                Crossroad.logger.info(String.format("car  x: %f  y: %f", car.x, car.y));
//                Crossroad.logger.info(String.format("drawing %s to x: %d  y: %d",entry.getKey(), x, y));
                g.setColor(Color.blue);
                g.fillOval(x, y, carRadius, carRadius);
            }
        }
		
		public void drawPedestrians(Graphics g){
            for (Map.Entry<String, Pedestrian> entry  : peds.entrySet()){
                Pedestrian ped = entry.getValue();
				if(ped.arrived){
					int x = (int)convWorXtoImgX(ped.x)-carRadius/4;
					int y = (int)convWorYtoImgY(ped.y)-carRadius/4;
					g.setColor(Color.red);
					g.fillOval(x, y, carRadius/2, carRadius/2);
				}
            }
        }

        float convWorXtoImgX(float x){
            return (right-left)*x/46.f+left;
        }
        float convWorYtoImgY(float y){
            return (bottom-top)*y/26.f+top;
        }
    }
}