import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar implements ActionListener{
	Font mFont = new Font("Gothic",Font.PLAIN,20);
	Font miFont = new Font("Gothic",Font.PLAIN,15);

	public JMenuBar menuBar;
	JMenu file,tool,name;

	JMenuItem newname,move,save;
	ArrayList<JMenu> nList = new ArrayList<JMenu>();
	ArrayList<JMenuItem> niList = new ArrayList<JMenuItem>();

	NameList nameList;
	Drawing drawing;
	FileSystem fileSystem;


	public MenuBar(NameList nameList,Drawing drawing){
		this.nameList = nameList;
		this.drawing = drawing;
		menuBar = new JMenuBar();

		file = new JMenu("File");
		file.setFont(mFont);
		tool = new JMenu("Tool");
		tool.setFont(mFont);

		name = new JMenu("Name");
		name.setFont(miFont);
		move = new JMenuItem("Move");
		move.setFont(miFont);
		move.addActionListener(this);
		save = new JMenuItem("Save (s)");
		save.setFont(miFont);
		save.addActionListener(this);

		newname = new JMenuItem("New Name (n)");
		newname.setFont(miFont);
		newname.addActionListener(this);


		name.add(newname);
		tool.add(name);
		tool.add(move);
		file.add(save);

		menuBar.add(file);
		menuBar.add(tool);
	}

	public void actionPerformed(ActionEvent e){

		JOptionPane dialog = new JOptionPane();
		if (e.getSource() == move){
			drawing.MODE = 1;
		}else if (e.getSource() == save) {
			fileSystem.save();
		}
		else if(e.getSource() == newname){
			nameList.newnamedialog();
		}
		else  {
			for (int i=0; i<niList.size();i++) {

				if (e.getSource() == niList.get(i)){
					int p;
					p = JOptionPane.showConfirmDialog(dialog, nameList.get(i), "確認", JOptionPane.YES_NO_OPTION);
					if (p==0)
						nameList.removename(i);
				}
			}
		}






	}

	public void newname(String s){
		JMenu naMenu = new JMenu(s);
		nList.add(naMenu);
		JMenuItem deleteName = new JMenuItem("Delete Name");
		deleteName.addActionListener(this);
		niList.add(deleteName);

		naMenu.add(deleteName);
		name.add(naMenu);

	}

	public void removename(int i){
		nList.get(i).remove(niList.get(i));
		niList.remove(i);
		name.remove(nList.get(i));
		nList.remove(i);
	}

	public void linkFileSystem(FileSystem fileSystem){
		this.fileSystem = fileSystem;
	}

}
