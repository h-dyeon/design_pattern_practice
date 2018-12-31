package DesignPattern_view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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

public class ProductListTable extends JFrame implements Observer{

	EventHandler controlEventHandler;
	ProductRecord modelProductRecord;

	private JFrame mainFrame = new JFrame("Main Table Window");
	private JPanel rightBtnPanel = new JPanel();
	DefaultTableModel defaultTablemodel;
	public JTable table;
	private JScrollPane scroll;
	
	public ProductListTable(EventHandler cont,ProductRecord model) {
		System.out.println("뷰-컨트롤러랑 모델셋팅");
		this.controlEventHandler=cont;
		this.modelProductRecord=model;	
			
		//frame 크기 설정
		mainFrame.setSize(800, 500);
					
		//layout 설정
		mainFrame.setLayout(new BorderLayout());
		rightBtnPanel.setLayout(new GridLayout(0,1));
					
		//panel에 버튼 추가 
		rightBtnPanel.add(controlEventHandler.addBtn);
		rightBtnPanel.add(controlEventHandler.deleteBtn);
		rightBtnPanel.add(controlEventHandler.modifyBtn);
		rightBtnPanel.add(controlEventHandler.fileSaveBtn);
		rightBtnPanel.add(controlEventHandler.fileOpenBtn);
				
		//table생성 
		defaultTablemodel = new DefaultTableModel();
		table = new JTable(defaultTablemodel);
		scroll = new JScrollPane(table);
		
		table.setRowHeight(20);
					
		mainFrame.add(scroll);
					
		//frame에 panel추가
		mainFrame.add(rightBtnPanel, BorderLayout.WEST);
					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
		
	}
	@Override
	public void update(Observable o, Object arg) {		
		if(arg=="MainTable") {
			System.out.println("옵저버, 업데이트됨");
			
			defaultTablemodel.setColumnIdentifiers(modelProductRecord.columnName);
			System.out.println("컬럼셋팅완료");
			//지우고
			while(defaultTablemodel.getRowCount()>0) {
				defaultTablemodel.removeRow(0);
			}
			//다시 넣음
			for(Vector<String> vecStr: modelProductRecord.totalData) {
				defaultTablemodel.addRow(vecStr);
			}
		}
		else if(arg=="doneAddBtn") {
			if(modelProductRecord.totalData.size()>0) {
				defaultTablemodel.addRow(modelProductRecord.totalData.get(modelProductRecord.totalData.size()-1));
			}
		}
		else if(arg=="deleteBtn") {
			System.out.println("view_ProductListTable_update 삭제버튼 이벤트");
			int num=defaultTablemodel.getRowCount();
			System.out.println("view_ProductListTable_update 지우기전에 row 개수 : "+num);
			//다시 넣음
			for(Vector<String> vecStr: modelProductRecord.totalData) {
				defaultTablemodel.addRow(vecStr);
				System.out.println("view_ProductListTable_update_vecStr의 0번값: "+vecStr.get(0));
			}
			//싹다지우고
			for(int i=num-1;i>=0;i--) {
				System.out.println("view_ProductListTable_update_지워야할row :"+i);
				defaultTablemodel.removeRow(0);
			}
		}
		else if(arg=="doneModifyBtn") {
			if(modelProductRecord.totalData.size()>0) {
				defaultTablemodel.removeRow(modelProductRecord.modifyRow);
				defaultTablemodel.insertRow(modelProductRecord.modifyRow, 
				modelProductRecord.totalData.get(modelProductRecord.modifyRow));
			}
		}
		
		
	}
}
