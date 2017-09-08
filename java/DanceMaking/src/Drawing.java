import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Drawing extends JPanel{
	public int width = 800;
	public int height = 480;
	public int Bwidth = width/10;
	public int Bheight = width/30;
	int mousewidth = Bwidth/4 ;
	int mouseheight = height/16;

	int clickX,clickY;
	int dragX,dragY;
	int releaseX,releaseY;
	int setX,setY; //circlesetの際のX,Y

	int mousePointX,mousePointY;


	int startX,startY;

	Buffer bi;
	Drawing drawing = this;
	public int  MODE=0; //0...PEOPLE_MODE,1...ARROW_MODE

	public Drawing() {
		// TODO 自動生成されたコンストラクター・スタブ
		setBackground(Color.white);
		setBounds(0, 0, width, height);
		addMouseListener(new MousePosition());
		addMouseMotionListener(new MouseMotion());
		bi = new Buffer(width, height, Bwidth,Bheight,BufferedImage.TYPE_INT_ARGB,this);

	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bi, 0, 0, this);
	}


	class MousePosition extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			clickX = e.getX();
			clickY = e.getY();

			if (MODE == 0 )
				bi.circleJudge(clickX,clickY);
			else if (MODE == 1){
				startX = clickX;
				startY = clickY;
			}

		}

		public void mouseReleased(MouseEvent e) {
			releaseX = e.getX();
			releaseY = e.getY();
			if (releaseX >width)
				releaseX = width;
			else if (releaseX<0) {
				releaseX = 0;
			}
			if (releaseY >height)
				releaseY = height;
			else if (releaseY<0) {
				releaseY = 0;
			}

			if (MODE == 0 ){
			if (bi.circlemoveflag)
				bi.resetMove();
			}
			else if (MODE == 1) {

				ArrowBuffer arrowBuffer;
				/*
				int awidth,aheight;
				int left,up;
				int startcorner = 0; //0...左上,1...左下,2...右上,3...右下
				if (releaseX>clickX){
					awidth = releaseX-clickX;
					left = clickX;
				}else{
					awidth = clickX - releaseX;
					left = releaseX;
					startcorner +=2;
				}

				if (releaseY>clickY){
					aheight = releaseY-clickY;
					up = clickY;

				}else{
					aheight = clickY - releaseY;
					up = releaseY;
					startcorner ++;
				}
				*/
				arrowBuffer = new ArrowBuffer(width,height,drawing,BufferedImage.TYPE_INT_ARGB);
				bi.addaBuffers(arrowBuffer);
			}
		}
	}

	class MouseMotion extends MouseMotionAdapter{
		public void mouseDragged(MouseEvent e){
			dragX = e.getX();
			dragY = e.getY();
			if (dragX >width)
				dragX = width;
			else if (dragX<0) {
				dragX = 0;
			}
			if (dragY >height)
				dragY = height;
			else if (dragY<0) {
				dragY = 0;
			}

			if (MODE == 0){
			if (bi.circlemoveflag){
				double dxrate,dyrate;
				int ixrate,iyrate;
				dxrate = (double)dragX / (double)mousewidth;
				dyrate = (double)dragY / (double)mouseheight;
				ixrate = dragX/mousewidth;
				iyrate = dragY/mouseheight;
				if (dxrate-ixrate<0.5)
					setX = ixrate * mousewidth;
				else {
					setX = (ixrate+1)*mousewidth;
				}
				if (dyrate-iyrate<0.5)
					setY = iyrate * mouseheight;
				else {
					setY = (iyrate+1)*mouseheight;
				}
				if (bi.moveCircle.centerX != setX || bi.moveCircle.centerY != setY){
					bi.circleMove(setX,setY);
					repaint();
				}
			}
			}
			else if (MODE == 1) {
				bi.draw(startX,startY,dragX,dragY);
				repaint();
			}
		}
	}

}
