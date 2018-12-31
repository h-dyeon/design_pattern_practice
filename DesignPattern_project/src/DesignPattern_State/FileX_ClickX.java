package DesignPattern_State;

import java.awt.Component;

import javax.swing.JOptionPane;

public class FileX_ClickX implements State{
	GumballMachine gumball;
	public FileX_ClickX(GumballMachine gumball) {
		this.gumball=gumball;
	}
	public void addBtn() {
		System.out.println("FileX_ClickX   add");
		Component frame = null;
		JOptionPane.showMessageDialog(frame,"파일을 부르시오.","경고",2);
	}
	public void deleteBtn(int rows[]){
		System.out.println("FileX_ClickX   delete");
		Component frame = null;
		JOptionPane.showMessageDialog(frame,"파일을 부르시오.","경고",2);
	}
	public void modifyBtn(int row){
		System.out.println("FileX_ClickX   modify");
		Component frame = null;
		JOptionPane.showMessageDialog(frame,"파일을 부르시오.","경고",2);
	}
	public void fileOpenBtn(){
		System.out.println("FileX_ClickX   fileopen");
		gumball.eventhandler.modelProductRecord.Model_fileOpen();
		if(gumball.eventhandler.modelProductRecord.Model_isFileThere()) {
			gumball.setState(gumball.getStatefileO_clickX());
		}
	}
	public void fileSaveBtn(){
		System.out.println("FileX_ClickX   filesave");
		Component frame = null;
		JOptionPane.showMessageDialog(frame,"파일이 없습니다.","경고",2);
	}
}
