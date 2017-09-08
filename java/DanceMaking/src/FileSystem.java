import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSystem implements ActionListener {
	File f;

	File savef = new File("C:\\Users\\Documents");
	JFileChooser fc = new JFileChooser(savef);
	FileNameExtensionFilter fe = new FileNameExtensionFilter(".jpeg", "jpeg");
	Drawing drawing;
	Text text;

	public FileSystem(Drawing drawing,Text text){
		this.drawing = drawing;
		this.text = text;
		fc.setFileFilter(fe);

		fc.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if ( e.getSource() == fc){
			f = fc.getSelectedFile();
		}

	}
	public void save(){

		File ff;
		String name;
		while (true) {
			if (fc.showSaveDialog(drawing) != JFileChooser.APPROVE_OPTION) {
				return;
			}
			f = fc.getSelectedFile();
			name = f.getName();
			if (name.indexOf('.') >= 0){
				String[] s= name.split("\\.");
				int l = s.length;
				if (!s[l-1].equals("jpeg")){
					ff = new File(f + ".jpeg");
				}
				else {
					ff = f;
				}

			}else {
				ff = new File(f + ".jpeg");
			}
			if (ff.exists()) {
				switch (JOptionPane.showConfirmDialog(fc,
						ff.getName() + " は既に存在します。\n上書きしますか？",
						"上書き保存の確認", JOptionPane.YES_NO_CANCEL_OPTION)) {
					case JOptionPane.YES_OPTION:
						break;
					case JOptionPane.NO_OPTION:
						continue;
					case JOptionPane.CANCEL_OPTION:
						return;
				}
				break;
			} else {
				break;
			}
		}
		if(ff != null){
		    BufferedImage bif1 = new BufferedImage(drawing.width,drawing.height + drawing.Bheight*3,BufferedImage.TYPE_INT_RGB);
		    BufferedImage bif2 = new BufferedImage(drawing.width,drawing.Bheight*3,BufferedImage.TYPE_INT_RGB);
		    Graphics fg1 = bif1.createGraphics();
		    Graphics fg2 = bif2.createGraphics();
		    fg1.setColor(Color.white);
		    fg1.fillRect(0,0,bif1.getWidth(),bif1.getHeight());
		    fg1.drawImage(drawing.bi,0,0,drawing);
		    text.paint(fg2);
		    fg1.drawImage(bif2,0,drawing.height,drawing);
		    try{
			ImageIO.write(bif1,"jpeg",ff);
		    }catch(IOException error){System.out.println("error in write");}
		    fg1.dispose();
		    fg2.dispose();
		}

	}


}
