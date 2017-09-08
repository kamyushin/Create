import java.awt.Dimension;

import javax.swing.JPanel;

public class NotMenuBarPanel extends JPanel{
	Drawing drawing;
	Text text;
	Name name;

	int ALLwidth,ALLheight;

	public NotMenuBarPanel(NameList nameList){

		drawing = new Drawing();
		text= new Text(drawing,nameList);
		name = new Name(drawing,nameList,text);


		ALLwidth = drawing.width;
		ALLheight = drawing.height +drawing.Bheight*6;

		setLayout(null);
		setPreferredSize(new Dimension(ALLwidth, ALLheight));
		add(drawing);
		add(text);
		add(name);
	}

}
