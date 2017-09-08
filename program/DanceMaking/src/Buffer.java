import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Buffer extends BufferedImage{
	Graphics g;
	BackGroundBuffer bGroundBuffer;
	JPanel observer;

	ArrayList<CircleBuffer> cBuffers = new ArrayList<CircleBuffer>();
	ArrayList<ArrowBuffer> aBuffers = new ArrayList<ArrowBuffer>();

	public boolean circlemoveflag = false;

	public int appearC;

	public CircleBuffer moveCircle;
	int Bwidth;
	int type;

	public Buffer(int width,int height,int Bwidth,int Bheight,int type,JPanel observer){

		super(width, height, type);
		this.observer = observer;
		this.Bwidth = Bwidth;
		this.type = type;


		bGroundBuffer = new BackGroundBuffer(width,height,type);
		g= this.createGraphics();
		g.drawImage(bGroundBuffer, 0, 0, observer);


	}


	public void newname(String s) {
		CircleBuffer cBuffer;
		cBuffer = new CircleBuffer(Bwidth, type,s);
		addcBuffers(cBuffer, 0, 0);
		appearC++;

	}

	public void  addcBuffers(CircleBuffer cBuffer,int X,int Y){
		if (cBuffer.active)
			g.drawImage(cBuffer, X, Y, observer);
		cBuffer.setcenter(cBuffer.getWidth()/2 ,cBuffer.getHeight()/2);
		cBuffers.add(cBuffer);
		observer.repaint();

	}

	public void addaBuffers(ArrowBuffer aBuffer){
		if (aBuffer.active)
			g.drawImage(aBuffer,0,0,observer);
		aBuffers.add(aBuffer);
		observer.repaint();
	}

	public void reset() {
		g.drawImage(bGroundBuffer, 0, 0, observer);
		for (CircleBuffer circleBuffer : cBuffers) {
			if (circleBuffer != moveCircle && circleBuffer.active)
				g.drawImage(circleBuffer, circleBuffer.centerX-circleBuffer.getWidth()/2, circleBuffer.centerY- circleBuffer.getHeight()/2, observer);

		}
		for (ArrowBuffer arrowBuffer : aBuffers){
			if (arrowBuffer.active)
				g.drawImage(arrowBuffer,0,0,observer);
		}
	}


	public void circleJudge(int X,int Y){
		double clickdistance;
		for (CircleBuffer circleBuffer : cBuffers) {
			if ( circleBuffer.active){
				clickdistance = Math.sqrt(Math.pow(circleBuffer.centerX - X,2) + Math.pow(circleBuffer.centerY-Y, 2));
				if (clickdistance < circleBuffer.getWidth()/2 && circlemoveflag == false){
					moveCircle = circleBuffer;
					circlemoveflag = true;
				}
			}
		}
	}

	public void circleMove(int X,int Y){
		reset();
		g.drawImage(moveCircle, X-moveCircle.getWidth()/2, Y-moveCircle.getHeight()/2, observer);
		moveCircle.setcenter(X, Y);

	}

	public void resetMove() {
		moveCircle = null;
		circlemoveflag = false;
	}

	public void removename(int i){
		cBuffers.remove(i);
		appearC--;
		reset();
		observer.repaint();
	}

	public void draw(int startX,int startY,int dragX,int dragY){
		reset();
		g.setColor(Color.black);
		drawArrow(startX, startY, dragX, dragY,this.g);
		observer.repaint();
	}

	public void drawArrow(int startX,int startY,int dragX,int dragY,Graphics g){

		    int[] rx={-20,-20};
		    int[] ry={10,-10};
		    double cr = (double)(dragX-startX)/Math.pow(Math.pow(dragX-startX,2)+Math.pow(dragY-startY,2),0.5);
		    double sr = (double)(dragY-startY)/Math.pow(Math.pow(dragX-startX,2)+Math.pow(dragY-startY,2),0.5);

		    g.drawLine(startX,startY,dragX,dragY);
		    g.drawLine(dragX,dragY,dragX+(int)(cr*rx[0]-sr*ry[0]),dragY+(int)(sr*rx[0]+cr*ry[0]));
		    g.drawLine(dragX,dragY,dragX+(int)(cr*rx[1]-sr*ry[1]),dragY+(int)(sr*rx[1]+cr*ry[1]));

	}
}
