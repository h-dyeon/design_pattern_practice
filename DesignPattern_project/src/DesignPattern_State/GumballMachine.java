package DesignPattern_State;

import DesignPattern_controller.EventHandler;

public class GumballMachine {	
	EventHandler eventhandler;
	State state;
	State statefileO_clickX=new FileO_ClickX(this);
	State statefileO_click1=new FileO_Click1(this);
	State statefileO_clickMany=new FileO_ClickMany(this);
	State statefileX_clickX=new FileX_ClickX(this);
	public GumballMachine(EventHandler eventhandler) {
		this.eventhandler=eventhandler;
		state=statefileX_clickX;
	}
	
	
	public void setState(State setstate) {
		if(setstate==statefileO_clickX)
			System.out.println("0_x스테이트 변경");
		else if(setstate==statefileO_click1)
			System.out.println("0_1스테이트 변경");
		else if(setstate==statefileO_clickMany)
			System.out.println("0_Many스테이트 변경");
		else if(setstate==statefileX_clickX)
			System.out.println("x_x스테이트 변경");
		
		this.state=setstate;
	}
	public State getStatefileO_clickX() {
		return this.statefileO_clickX;
	}
	public State getStatefileO_click1() {
		return this.statefileO_click1;
	}
	public State getStatefileO_clickMany() {
		return this.statefileO_clickMany;
	}
	public State getStatefileX_clickX() {
		return this.statefileX_clickX;
	}
	
	
	public void addBtn() {
		System.out.println("state   add");
		state.addBtn();		
	}
	public void deleteBtn(int rows[]){
		System.out.println("state   delete");
		state.deleteBtn(rows);
	}
	public void modifyBtn(int row){
		System.out.println("state   modify");
		state.modifyBtn(row);
	}
	public void fileOpenBtn(){
		System.out.println("state   fileopen");
		state.fileOpenBtn();
	}
	public void fileSaveBtn(){
		System.out.println("state   filesave");
		state.fileSaveBtn();
	}
}
