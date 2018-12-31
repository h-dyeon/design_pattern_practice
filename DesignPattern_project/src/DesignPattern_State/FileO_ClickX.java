package DesignPattern_State;

import java.awt.Component;

import javax.swing.JOptionPane;

public class FileO_ClickX implements State {
	GumballMachine gumball;
	public FileO_ClickX(GumballMachine gumball) {
		this.gumball=gumball;
	}
	public void addBtn() {
		System.out.println("FileO_ClickX   add");
		gumball.eventhandler.modelProductRecord.Model_addBtn();
	}
	public void deleteBtn(int[] rows){
		System.out.println("FileO_ClickX   delete");
		Component frame = null;
		JOptionPane.showMessageDialog(frame,"������ �׸��� �����Ͻʽÿ�.","���",2);
	}
	public void modifyBtn(int row){
		System.out.println("FileO_ClickX   modify");
		Component frame = null;
		JOptionPane.showMessageDialog(frame,"������ �׸��� ���ÿ�.","���",2);
	}
	public void fileOpenBtn(){
		System.out.println("FileO_ClickX   fileopen");
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
		}
	}
	public void fileSaveBtn(){
		System.out.println("FileO_ClickX   filesave");
		gumball.eventhandler.modelProductRecord.Model_fileSave();
	}
}
