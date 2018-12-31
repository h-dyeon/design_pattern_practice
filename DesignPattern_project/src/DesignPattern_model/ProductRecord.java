package DesignPattern_model;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.Vector;

import DesignPattern_FileType.*;
import DesignPattern_view.ProductListTable;

public class ProductRecord extends Observable{
	FileIO fileIO=new FileIO();
	private Boolean Isfile;

	FileType whatfile;
	//저장소
	public ArrayList<Vector<String>> totalData;// = new ArrayList<Vector<String>>();
	// vector value form : productName/productId/category/value/cStock/sStock/Memo
	String strFileType=new String();
	public String[] columnName= {"헤헿"};
	public Vector<String> originData;
	public int modifyRow;
	
	public ProductRecord() {
		System.out.println("모델-productrecord 생성");
		Isfile=false;
	}
	public void Model_fileOpen() {
		System.out.println("불러올 파일 고르기");
		String fileStr=fileIO.selectOpenFile();
		if(fileStr==null) {
			System.out.println("파일경로 없음");
		}
		else {
			System.out.println("model_ProductRecord_Model_fileOpen() : 파일 열기 시작");
			totalData = new ArrayList<Vector<String>>();//새로 초기화하고
			ArrayList<String> openRecord=fileIO.openAndReadFile(fileStr);
			System.out.println("model_ProductRecord_Model_fileOpen() : openReadfile함수 완료");
			FileFactory fF=new FileFactory();
			this.whatfile=fF.createFile(fileIO.menuStr);
			this.strFileType=this.whatfile.getFileType();
			
			//this.whatfile=fF.createFile(openRecord.get(0));
			//this.strFileType=openRecord.get(0);
			
			
			System.out.println("model_ProductRecord_Model_fileOpen() : 파일하나 생성");
			this.totalData=whatfile.String2Data(openRecord);
			System.out.println("model_ProductRecord_Model_fileOpen() : 스트링2데이터 완료");
			
			//메뉴목록만들기
			System.out.println("model_ProductRecord_Model_fileOpen() : 컬럼네임 받아오기");
			this.columnName=whatfile.makeColumnName(this.strFileType);
			//옵저버?
			setChanged();
			notifyObservers("MainTable");
			//view에 전시
			
			Isfile=true;
		}		
	}

	public void Model_fileSave() {
		System.out.println("저장할 파일 경로 고르기");
		String fileStr=fileIO.selectSaveFile();
		if(fileStr==null) {
			System.out.println("파일경로 없음");
		}
		else {
			System.out.println("파일저장 시작");
			FileFactory fF=new FileFactory();
			FileType whatfile=fF.createFile(this.strFileType);
			ArrayList<String> saveRecord=whatfile.Data2String(this.totalData);
			fileIO.openAndWriteFile(saveRecord,fileStr);
		}		
	}
	public Boolean Model_isFileThere() {
		return Isfile;		
	}
	public void Model_addBtn() {
		System.out.println("model_ProductRecord_addBtn()_추가");
		setChanged();
		notifyObservers("addBtn");
	}
	public void Model_deleteBtn(int[] rows) {
		System.out.println("model_ProductRecord_deleteBtn()_추가");
		int num=rows.length;
		for(int i=num-1;i>=0;i--) {
			System.out.println("model_ProductRecord_deleteBtn()_지울번호 : "+rows[i]);
			totalData.remove(rows[i]);
		}
		setChanged();
		notifyObservers("deleteBtn");
	}
	public void Model_modifyBtn(int row) {
		System.out.println("model_ProductRecord_modifyBtn()_수정");
		
		//원래 항목id를 저장해야함 originID
		this.modifyRow=row;
		originData=new Vector<String>();
		originData=totalData.get(this.modifyRow);
		System.out.println("model_ProductRecord_modifyBtn() : originData 저장함");
		setChanged();
		notifyObservers("modifyBtn");
	}
	public void Model_doneAddBtn(Vector<String> rowData_ForAdd) {
		System.out.println("model_ProductRecord_doneAddBtn()_추가완료버튼");
		
		Vector<String> returnData_ForAdd=new Vector<String>();
		for(String str:whatfile.addDataCheck(rowData_ForAdd)){
			returnData_ForAdd.add(str);
		}
		
		totalData.add(returnData_ForAdd);
		setChanged();
		notifyObservers("doneAddBtn");
	}
	public void Model_doneModifyBtn(Vector<String> rowData_ForModify) {
		System.out.println("model_ProductRecord_doneModifyBtn()_수정완료버튼");
		this.originData=this.whatfile.modifyDataCheck(this.totalData.get(modifyRow),rowData_ForModify);
		this.totalData.remove(this.modifyRow);
		this.totalData.add(this.modifyRow,this.originData);
		//this.totalData.get(this.modifyRow).clear();
		//this.totalData.get(this.modifyRow).addAll(this.modifyData);
		setChanged();
		notifyObservers("doneModifyBtn");
	}
	
}
