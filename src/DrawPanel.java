import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	public DrawPanel() {
    	setPreferredSize(new Dimension(1400,1400));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if(!Main.valid) return;
    	Graphics2D g2d = (Graphics2D)g;
    	
    	g2d.fillOval(Main.screenWidth / 2 - 10, Main.screenHeight / 2 - 10, 20, 20);
		
		for(int i = 0; i < Main.threadCount; i++) {
			g2d.setStroke(new BasicStroke(1));		
			g2d.setColor(new Color(Main.colorArr[i]));
			int[] path = Main.paths[Main.currentSample][i];
			
			double x = Main.screenWidth / 2;
			double y = Main.screenHeight / 2;
			double l = Main.screenWidth / 4;
			double a = -Math.PI/2;
			for(int j = 2; j < path.length; j++) {
				if(j == path[0])
					g2d.setStroke(new BasicStroke(3));		
				if(path[j] == -1)
					break;
				a = Math.PI / Main.instanceSize * 1.5 * path[j] + a - Math.PI / 4 * 3;
				double nextX = Math.cos(a) * l + x;
				double nextY = Math.sin(a) * l + y;
				g2d.drawLine((int)x,(int)y,(int)nextX,(int)nextY);
				x = nextX;
				y = nextY;
				l /= 2;
			}
		}
        
    }
}
