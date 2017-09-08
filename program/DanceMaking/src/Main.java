import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class Main  {

	public static void main(String[] args) {

		// TODO 自動生成されたメソッド・スタブ
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		// 変数desktopBoundsにデスクトップ領域を表すRectangleが代入される
		Rectangle desktopBounds = env.getMaximumWindowBounds();

		Frame f = new Frame(desktopBounds.width,desktopBounds.height);


	}

}
