package DesignPattern_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import DesignPattern_State.GumballMachine;
import DesignPattern_model.ProductRecord;
import DesignPattern_view.AddProductTable;
import DesignPattern_view.ModifyProductTable;
import DesignPattern_view.ProductListTable;


public class EventHandler implements ActionListener {
	public JButton addBtn=new JButton("Add");
	public JButton deleteBtn=new JButton("Delete");
	public JButton modifyBtn=new JButton("Modify");
	public JButton fileSaveBtn=new JButton("File save");
	public JButton fileOpenBtn=new JButton("File open");
	public JButton doneAddBtn=new JButton("addDone");
	public JButton doneModifyBtn=new JButton("modifyDone");
	
	public ProductRecord modelProductRecord;
	ProductListTable viewProductListTable;
	AddProductTable viewAddProductTable;
	ModifyProductTable viewModifyProductTable;
	
	GumballMachine gumball=new GumballMachine(this);
	
	public EventHandler(ProductRecord model) {
		System.out.println("컨트롤러-모델셋팅");
		this.modelProductRecord=model;
		System.out.println("컨트롤러-뷰생성 this랑 모델 넘겨줌");
		viewProductListTable=new ProductListTable(this,modelProductRecord);
		viewAddProductTable=new AddProductTable(this,modelProductRecord);
		viewModifyProductTable=new ModifyProductTable(this,modelProductRecord);

		System.out.println("컨트롤러-옵저버 추가");
		modelProductRecord.addObserver(viewProductListTable);
		modelProductRecord.addObserver(viewAddProductTable);
		modelProductRecord.addObserver(viewModifyProductTable);
		
		addBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		modifyBtn.addActionListener(this);
		fileSaveBtn.addActionListener(this);
		fileOpenBtn.addActionListener(this);
		doneAddBtn.addActionListener(this);
		doneModifyBtn.addActionListener(this);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("====================버튼클릭전 스테이트 확인");
		if(modelProductRecord.Model_isFileThere()) {
			if(viewProductListTable.table.getSelectedRow()!=-1) {
				int rows[]=viewProductListTable.table.getSelectedRows();
				if(rows.length==1) {
					System.out.println("====================파일있 클릭1");
					gumball.setState(gumball.getStatefileO_click1());
				}else {
					System.out.println("====================파일있 클릭많음");
					gumball.setState(gumball.getStatefileO_clickMany());
				}
			}else {
				System.out.println("=============파일있 클릭없");
				gumball.setState(gumball.getStatefileO_clickX());
			}
		}else {
			System.out.println("=============파일없 클릭없");
			gumball.setState(gumball.getStatefileX_clickX());
		}
		
		if(e.getSource().equals(addBtn)){
			System.out.println("애드버튼");
			gumball.addBtn();
		}
		else if(e.getSource().equals(deleteBtn)){
			int rows[]=viewProductListTable.table.getSelectedRows();
			gumball.deleteBtn(rows);
		}
		else if(e.getSource().equals(modifyBtn)){
			gumball.modifyBtn(viewProductListTable.table.getSelectedRow());
		}
		else if(e.getSource().equals(fileSaveBtn)){
			gumball.fileSaveBtn();
		}
		else if(e.getSource().equals(fileOpenBtn)){
			gumball.fileOpenBtn();
		}
		else if(e.getSource().equals(doneAddBtn)){
			modelProductRecord.Model_doneAddBtn(viewAddProductTable. rowData_ForAdd);
		}
		else if(e.getSource().equals(doneModifyBtn)) {
			System.out.println("controller_Eventhandler_actionPerformed : doneModifyBtn 일 경우");
			modelProductRecord.Model_doneModifyBtn(viewModifyProductTable.rowData_ForModify);
		}
	}
}
