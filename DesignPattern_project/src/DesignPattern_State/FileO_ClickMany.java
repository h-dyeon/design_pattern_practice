package DesignPattern_State;

import java.awt.Component;

import javax.swing.JOptionPane;

public class FileO_ClickMany implements State{
	GumballMachine gumball;
	public FileO_ClickMany(GumballMachine gumball) {
		this.gumball=gumball;
	}
	public void addBtn() {
		System.out.println("FileO_ClickMany   add");
		gumball.eventhandler.modelProductRecord.Model_addBtn();
	}
	public void deleteBtn(int[] rows){
		System.out.println("FileO_ClickMany   delete");
		gumball.eventhandler.modelProductRecord.Model_deleteBtn(rows);
	}
	public void modifyBtn(int row){
		System.out.println("FileO_ClickMany   modify");
		Component frame = null;
		JOptionPane.showMessageDialog(frame,"������ �׸� 1���� ���ʽÿ�.","���",2);
	}
	public void fileOpenBtn(){
		System.out.println("FileO_ClickMany   fileopen");
		Component frame=null;
		Object[] options = {"��","�ƴϿ�"}; 
		int n = JOptionPane.showOptionDialog(frame,
				"������ �����Ͻðڽ��ϱ�?",
				"���� ����",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, //do not use a custom Icon
				options, //the titles of buttons
				options[0]); //default button title
		if(n==0) {
			System.out.println("���Ϻ���â");
			gumball.eventhandler.modelProductRecord.Model_fileOpen();
			if(gumball.eventhandler.modelProductRecord.Model_isFileThere()) {
				gumball.setState(gumball.getStatefileO_clickX());
			}
		}
	}
	public void fileSaveBtn(){
		System.out.println("FileO_ClickMany   filesave");
		gumball.eventhandler.modelProductRecord.Model_fileSave();
	}
}
