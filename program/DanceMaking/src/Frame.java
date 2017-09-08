import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Frame{


	int width,height;
	MenuBar menu;
	NotMenuBarPanel notMenuBarPanel;
	NameList nameList;
	FileSystem fileSystem;




	public Frame(int desktopwidth,int desktopheight) {
		// TODO 自動生成されたコンストラクター・スタブ
		nameList = new NameList();
		notMenuBarPanel = new NotMenuBarPanel(nameList);
		menu = new MenuBar(nameList,notMenuBarPanel.drawing);
		nameList.link(menu,notMenuBarPanel.name,notMenuBarPanel.drawing.bi);
		fileSystem = new FileSystem(notMenuBarPanel.drawing, notMenuBarPanel.text);
		notMenuBarPanel.text.linkFileSystem(fileSystem);
		menu.linkFileSystem(fileSystem);

		JFrame frame = new JFrame("DANCE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setJMenuBar(menu.menuBar);
		frame.add(notMenuBarPanel,BorderLayout.CENTER);
		frame.pack();
		width = frame.getWidth();
		height = frame.getHeight();
		frame.setBounds(desktopwidth/2 - width/2, desktopheight/2 - height/2, width, height);
		frame.setVisible(true);






	}




}
