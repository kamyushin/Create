import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Name extends JPanel implements ActionListener{
	JButton Pclear,Mclear,Tclear;
	NameList nameList;
	int Bwidth,Bheight;

	ArrayList<JButton> nButtons = new ArrayList<JButton>();

	Drawing drawing;
	Text text;
	public Name(Drawing drawing, NameList nameList,Text text){
		this.Bwidth = drawing.Bwidth;
		this.Bheight = drawing.Bheight;

		this.nameList = nameList;
		this.drawing = drawing;
		this.text = text;
		setLayout(null);
		setPreferredSize(new Dimension(drawing.width,Bheight*3));
		setBounds(0, drawing.height+Bheight*3, drawing.width, Bheight*3);
		Pclear = new JButton("Pclear");
		Pclear.setBounds(Bwidth*9,0, Bwidth, Bheight);
		Pclear.addActionListener(this);
		add(Pclear);
		Mclear = new JButton("Mclear");
		Mclear.setBounds(Bwidth*9,Bheight,Bwidth,Bheight);
		Mclear.addActionListener(this);
		add(Mclear);
		Tclear = new JButton("Tclear");
		Tclear.setBounds(Bwidth*9,Bheight*2,Bwidth,Bheight);
		Tclear.addActionListener(this);
		add(Tclear);

		Pclear.addKeyListener(text.new KeyMotion());
		Mclear.addKeyListener(text.new KeyMotion());
		Tclear.addKeyListener(text.new KeyMotion());


	}

	public void newname(String s){
		JButton n = new JButton(s);
		n.addActionListener(this);
		n.addKeyListener(text.new KeyMotion());
		nButtons.add(n);
		if (nameList.size()<=8)
			n.setBounds((nameList.size()-1)*Bwidth,0, Bwidth, Bheight);
		if (8<nameList.size() && nameList.size()<=16)
			n.setBounds((nameList.size()-9)*Bwidth, Bheight, Bwidth, Bheight);
		add(n);
		repaint();
	}

	public void removename(int i) {
		remove(nButtons.get(i));
		nButtons.remove(i);
		for (int j = 0;j<nButtons.size();j++ ) {
			nButtons.get(j).setBounds(j*Bwidth, 0, Bwidth, Bheight);
		}
		repaint();
	}

	public void actionPerformed(ActionEvent e){
		JOptionPane dialog = new JOptionPane();
		int s;
		if (e.getSource() == Pclear){
			if (nameList.bi.appearC>0){
			s = JOptionPane.showConfirmDialog(dialog,"人のサークルを全て消去します\n(下のボタンで再度現れます)","確認",JOptionPane.YES_NO_OPTION);
			if (s == 0)
				for (int i=0; i<nameList.size();i++)
					nameList.disAppear(i);
			}
		}else if (e.getSource() == Mclear) {
			if (nameList.bi.aBuffers.size()>0){
			s = JOptionPane.showConfirmDialog(dialog, "矢印を全て消去します","確認",JOptionPane.YES_NO_OPTION);
			if(s == 0){
				for(int i = nameList.bi.aBuffers.size()-1;i>=0;i--){
					nameList.bi.aBuffers.remove(i);
				}
				drawing.MODE = 0;
			}
			}
		}else if (e.getSource() == Tclear) {
			if (text.getText().length() >0){
			s = JOptionPane.showConfirmDialog(dialog, "テキストを消去します","確認",JOptionPane.YES_NO_OPTION);
			if (s == 0) {
				text.setText("");
			}
			}
		}
		else {
			for ( int i =0 ;i<nameList.size();i++)
				if (e.getSource() == nButtons.get(i))
						nameList.Appear(i);
		}
		drawing.bi.reset();
		drawing.repaint();
	}
}
