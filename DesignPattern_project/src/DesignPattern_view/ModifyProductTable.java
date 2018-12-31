package DesignPattern_view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DesignPattern_controller.EventHandler;
import DesignPattern_model.ProductRecord;

public class ModifyProductTable extends JFrame implements Observer{
	private JFrame modifyProductFrame = new JFrame("Modify Product Window");
	private JPanel MbuttonPanel = new JPanel();
	
	EventHandler controlEventHandler;
	ProductRecord modelProductRecord;

	DefaultTableModel ModifydefaultTablemodel;
	JTable Mtable;
	private JScrollPane Mscroll;
	
	public ModifyProductTable(EventHandler cont,ProductRecord model) {
		System.out.println("view_ModifyProductTable_생성자 ");
		
		System.out.println("view_ModifyProductTable_생성자 : 뷰-컨트롤러랑 모델셋팅");
		this.controlEventHandler=cont;
		this.modelProductRecord=model;
		
		setDefaultFrame();
		MbuttonPanel.add(controlEventHandler.doneModifyBtn);
	}

	private void setDefaultFrame(){
		//Frame Size, Create Location set
		modifyProductFrame.setLocation(800,0);
		modifyProductFrame.setSize(800, 150);
			
		//Frame & Panel Layout set
		modifyProductFrame.setLayout(new BorderLayout());
		MbuttonPanel.setLayout(new GridLayout(0,1));
				
		//button allocate on mainFrame
		modifyProductFrame.add(MbuttonPanel, BorderLayout.EAST);
				
		//create JTable for enter the product info
		ModifydefaultTablemodel = new DefaultTableModel();
		ModifydefaultTablemodel.setColumnIdentifiers(modelProductRecord.columnName);
		Mtable= new JTable(ModifydefaultTablemodel);
		Mscroll = new JScrollPane(Mtable);
		modifyProductFrame.add(Mscroll);
				
		Mtable.setRowHeight(20);
				
		//Frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modifyProductFrame.setVisible(false);
	}
	
	public Vector<String> rowData_ForModify;// = new Vector<String>();//추가할 정보를 여기다 저장	
	
	public void update(Observable o, Object arg) {
		if(arg=="modifyBtn") {
			System.out.println("view_ModifyProductTable_update : 수정버튼 modifyBtn 업데이트");
			ModifydefaultTablemodel.setColumnIdentifiers(modelProductRecord.columnName);
			
			rowData_ForModify=new Vector<String>();
			for(String forModify:modelProductRecord.totalData.get(modelProductRecord.modifyRow)) {
				rowData_ForModify.add(forModify);
			}
			//rowData_ForModify=modelProductRecord.totalData.get(modelProductRecord.modifyRow);
			int modifyRow=modelProductRecord.modifyRow;
			
			if(ModifydefaultTablemodel.getRowCount()>0)
				ModifydefaultTablemodel.removeRow(0);
			ModifydefaultTablemodel.addRow(rowData_ForModify);
			
			modifyProductFrame.setVisible(true);
		}
		else if(arg=="doneModifyBtn") {
			modifyProductFrame.setVisible(false);
		}
	}
}
