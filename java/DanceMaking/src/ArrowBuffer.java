import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ArrowBuffer extends BufferedImage {
	Graphics2D g;
	boolean active = true;

	public ArrowBuffer(int width ,int height,Drawing drawing,int type){
		super(width, height, type);


		g = this.createGraphics();


		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		g.fillRect(0, 0, width, height);
		g.setPaintMode();
		g.setColor(Color.black);
		drawing.bi.drawArrow(drawing.startX, drawing.startY, drawing.releaseX, drawing.releaseY,g);


	}
}
