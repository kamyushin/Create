import java.awt.Font;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class Text extends JTextArea{
	Font sfont = new Font("Gothic",Font.PLAIN,14);
	NameList nameList;
	FileSystem fileSystem ;
	Drawing drawing;

	public Text(Drawing drawing,NameList nameList){
		this.nameList = nameList;
		this.drawing = drawing;

		setBounds(0, drawing.height, drawing.width, drawing.Bheight*3);
		setLineWrap(true);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setMargin(new Insets(5, 10, 5, 10));
		setFont(sfont);
		setFocusable(true);

		addKeyListener(new KeyMotion());
	}

	class KeyMotion extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			JOptionPane dialog = new JOptionPane();
		int k = e.getKeyCode();
	    if (k == 78){
	    	int mod = e.getModifiersEx();
	    	if ((mod & InputEvent.CTRL_DOWN_MASK) != 0){
	    		nameList.newnamedialog();
	    	}
	    }else if (k == 83){
	    	int mod = e.getModifiersEx();
	    	if ((mod & InputEvent.CTRL_DOWN_MASK) != 0){
	    		fileSystem.save();
	    	}
	    }
		}
	}

	public void linkFileSystem(FileSystem fileSystem){
		this.fileSystem = fileSystem;
	}

}
