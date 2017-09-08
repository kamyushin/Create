import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BackGroundBuffer extends BufferedImage {
	Graphics g;
	FontMetrics fob;
	Font font = new Font("Gothic",Font.PLAIN,15);
	public BackGroundBuffer(int width,int height, int type){
		super(width, height, type);
		g = this.createGraphics();
		g.setFont(font);
		fob = g.getFontMetrics();

		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		for (int i = 1;i<=9;i++){
			g.drawLine(width/10*i, 0, width/10*i, 10);
		}
		for (int i = 4;i>0;i--)
			g.drawString(Integer.toString(i), width/10*(5-i)-fob.stringWidth(Integer.toString(i))/2, 30);
		for(int i = 0;i<5;i++)
			g.drawString(Integer.toString(i), width/10*(i+5)-fob.stringWidth(Integer.toString(i))/2, 30);
		for (int i = 1;i<=3;i++){
			g.drawLine(width-10, height/4*i, width, height/4*i);
			g.drawString(Integer.toString(i), width-25, height/4*i + fob.getAscent()/2);
		}
	}

}
