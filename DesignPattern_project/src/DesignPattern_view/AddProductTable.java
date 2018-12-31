package DesignPattern_view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DesignPattern_controller.EventHandler;
import DesignPattern_model.ProductRecord;


public class AddProductTable extends JFrame implements Observer{
	private JFrame addProductFrame = new JFrame("Add Product Window");
	private JPanel buttonPanel = new JPanel();
	
	EventHandler controlEventHandler;
	ProductRecord modelProductRecord;

	DefaultTableModel AdddefaultTablemodel;
	JTable table;
	private JScrollPane scroll;
	
	public AddProductTable(EventHandler cont,ProductRecord model) {
		System.out.println("애드프러덕트");
		
		System.out.println("뷰-컨트롤러랑 모델셋팅");
		this.controlEventHandler=cont;
		this.modelProductRecord=model;
		
		setDefaultFrame();
		buttonPanel.add(controlEventHandler.doneAddBtn);
	}
	
	private void setDefaultFrame(){
		//Frame Size, Create Location set
		addProductFrame.setLocation(800,0);
		addProductFrame.setSize(800, 150);
			
		//Frame & Panel Layout set
		addProductFrame.setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(0,1));
				
		//button allocate on mainFrame
		addProductFrame.add(buttonPanel, BorderLayout.EAST);
				
		//create JTable for enter the product info
		AdddefaultTablemodel = new DefaultTableModel();
		AdddefaultTablemodel.setColumnIdentifiers(modelProductRecord.columnName);
		table= new JTable(AdddefaultTablemodel);
		scroll = new JScrollPane(table);
		addProductFrame.add(scroll);
				
		table.setRowHeight(20);
				
		//Frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addProductFrame.setVisible(false);
	}
	
	
	public Vector<String> rowData_ForAdd;
	@Override
	public void update(Observable o, Object arg) {
		if(arg=="addBtn") {
			System.out.println("==================추가버튼 , 업데이트됨");
			rowData_ForAdd = new Vector<String>();//추가할 정보를 여기다 저장
			AdddefaultTablemodel.setColumnIdentifiers(modelProductRecord.columnName);
			
			//빈칸으로 띄우기
			if(AdddefaultTablemodel.getRowCount()>0)
				AdddefaultTablemodel.removeRow(0);
			AdddefaultTablemodel.addRow(rowData_ForAdd);
			
			addProductFrame.setVisible(true);
		}
		else if(arg=="doneAddBtn") {
			addProductFrame.setVisible(false);
		}
	}
}