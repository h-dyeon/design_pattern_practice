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
		JOptionPane.showMessageDialog(frame,"삭제할 항목을 선택하십시오.","경고",2);
	}
	public void modifyBtn(int row){
		System.out.println("FileO_ClickX   modify");
		Component frame = null;
		JOptionPane.showMessageDialog(frame,"수정할 항목을 고르시오.","경고",2);
	}
	public void fileOpenBtn(){
		System.out.println("FileO_ClickX   fileopen");
		Component frame=null;
		Object[] options = {"네","아니오"}; 
		int n = JOptionPane.showOptionDialog(frame,
				"파일을 변경하시겠습니까?",
				"파일 열기",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, //do not use a custom Icon
				options, //the titles of buttons
				options[0]); //default button title
		if(n==0) {
			System.out.println("파일변경창");
			gumball.eventhandler.modelProductRecord.Model_fileOpen();
		}
	}
	public void fileSaveBtn(){
		System.out.println("FileO_ClickX   filesave");
		gumball.eventhandler.modelProductRecord.Model_fileSave();
	}
}
