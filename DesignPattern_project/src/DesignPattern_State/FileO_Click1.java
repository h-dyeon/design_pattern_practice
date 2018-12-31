package DesignPattern_State;

import java.awt.Component;

import javax.swing.JOptionPane;

public class FileO_Click1 implements State{
	GumballMachine gumball;
	public FileO_Click1(GumballMachine gumball) {
		this.gumball=gumball;
	}
	public void addBtn() {
		System.out.println("FileO_Click1   add");
		gumball.eventhandler.modelProductRecord.Model_addBtn();
	}
	public void deleteBtn(int[] rows){
		System.out.println("FileO_Click1   delete");
		gumball.eventhandler.modelProductRecord.Model_deleteBtn(rows);
	}
	public void modifyBtn(int row){
		System.out.println("FileO_Click1   modify");
		gumball.eventhandler.modelProductRecord.Model_modifyBtn(row);
	}
	public void fileOpenBtn(){
		System.out.println("FileO_Click1   fileopen");
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
			if(gumball.eventhandler.modelProductRecord.Model_isFileThere()) {
				gumball.setState(gumball.getStatefileO_clickX());
			}
		}
	}
	public void fileSaveBtn(){
		System.out.println("FileO_Click1   filesave");
		gumball.eventhandler.modelProductRecord.Model_fileSave();
	}
}
