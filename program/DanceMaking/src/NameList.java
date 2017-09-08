import java.util.ArrayList;

import javax.swing.JOptionPane;

public class NameList {
	public ArrayList<String> nameList = new ArrayList<String>();


	MenuBar menuBar;
	Name name;
	Buffer bi;

	public void link(MenuBar menuBar,Name name, Buffer bi){
		this.menuBar = menuBar;
		this.name = name;
		this.bi = bi;
	}
	public void newname(String s){
		nameList.add(s);
		menuBar.newname(s);
		name.newname(s);
		bi.newname(s);
	}

	public void newnamedialog(){
		String s;
		JOptionPane dialog = new JOptionPane();
		if (nameList.size() <= 24){
			do{
				s = JOptionPane.showInputDialog("新しい名前を入力してください");
				if (s != null)
					if (s.length() ==0)
						JOptionPane.showMessageDialog(dialog, "1文字以上で入力してください");
			}while(s != null && s.length() == 0);
			if (s != null){
				newname(s);
			}
		}else {
			JOptionPane.showMessageDialog(dialog, "これ以上登録できません。名前を削除してください");
		}
	}

	public void removename(int i){
		nameList.remove(i);
		menuBar.removename(i);
		name.removename(i);
		bi.removename(i);
	}

	public int size() {
		return nameList.size();
	}
	public String get(int i) {
		return nameList.get(i);
	}

	public void disAppear(int i){
		if (bi.cBuffers.get(i).active == true){
			bi.cBuffers.get(i).active = false;
			bi.appearC--;
		}
	}

	public void Appear(int i){
		if(bi.cBuffers.get(i).active == false){
			bi.cBuffers.get(i).active = true;
			bi.appearC++;
		}
	}

}
