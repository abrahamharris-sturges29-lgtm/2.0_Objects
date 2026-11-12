
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class BasicGameApp implements Runnable {

	public int randDX;
	public int randDY;


 	final int WIDTH = 1000;
	final int HEIGHT = 700;

	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;

	public Astronaut astro;

	public Astronaut astro2;

	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();
		new Thread(ex).start();
	}

	public BasicGameApp() {

      setUpGraphics();


		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png");
		astro = new Astronaut(10,100);
		astro2=new Astronaut(20,200);

		randDX = ((int) (Math.random()*10) +1);
		randDX = ((int) (Math.random()*10) +1);
		astro2.dy = randDY;
		astro2.dx=randDX;
}

	public void run() {

		while (true) {

         moveThings();
         render();
         pause(2);
		}
	}


	public void moveThings()
	{

		astro.move();
		astro2.move();
		astro2.dx=2;
	}

   public void pause(int time ){

			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }


   private void setUpGraphics() {
      frame = new JFrame("Application Template");
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

		g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
		g.drawImage(astroPic, astro2.xpos, astro2.ypos, astro2.width, astro2.height, null);


		g.dispose();
		bufferStrategy.show();
	}
}