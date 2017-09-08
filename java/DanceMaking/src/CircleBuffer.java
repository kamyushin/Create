import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class CircleBuffer extends BufferedImage{
	Graphics2D g;
	public int centerX,centerY;
	public int clickX,clickY;

	FontMetrics fob;

	int size = 20;

	public boolean active = true;

	public CircleBuffer(int Bwidth, int type, String s) {
	// TODO 自動生成されたコンストラクター・スタブ
		super(Bwidth, Bwidth, type);
		g = this.createGraphics();


		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		g.fillRect(0, 0, Bwidth, Bwidth);
		g.setPaintMode();

		g.setColor(Color.black);
		g.drawOval(0, 0,Bwidth,Bwidth);

		do{
			Font font = new Font("Gothic",Font.BOLD,size);
			g.setFont(font);
			fob = g.getFontMetrics();
			if (fob.stringWidth(s) > Bwidth-10)
				size--;
		}while(fob.stringWidth(s) > Bwidth-10);

		g.drawString(s,Bwidth/2- fob.stringWidth(s)/2,Bwidth/2 + fob.getAscent()/2);



	}

	public void setcenter(int X,int Y){
		centerX = X;
		centerY = Y;
	}
}
