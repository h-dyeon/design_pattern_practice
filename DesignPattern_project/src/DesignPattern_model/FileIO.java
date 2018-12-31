package DesignPattern_model;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileIO {
	//정보저장공간
	//원래 이거 : ArrayList<ProductRecord> recordInput = new ArrayList<ProductRecord>();
	
	//화면 맨 위 상단에 품목이름, 품목가격 등등 이런것도 가능
	public String menuStr=new String();
	//string별로 읽어들이는 부분
	ArrayList<String> record_strings = new ArrayList<String>();
	
	// 이거를 AddProductTable랑 ProductRecord에서 쓰더라 
	//key : productId / value : product order
	static HashMap<String, String> productIdData = new HashMap<String, String>();	
	
	Scanner input;
	PrintWriter output;
	
	String inputLine;
	String fileName;
	int index = 0;
	//불러올 파일의 경로를 선택
	public String selectOpenFile() {
		String fileStr = null;
		System.out.println("셀렉트오픈파일");
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("text files","txt");
		chooser.addChoosableFileFilter(filter);
		int value=chooser.showOpenDialog(null);
		if(value==JFileChooser.APPROVE_OPTION) {
			File file=chooser.getSelectedFile();
			fileStr=file.getPath();
		}		
		return fileStr;
	}
	//파일을 저장할 경로 선택
	public String selectSaveFile() {
		String fileStr = null;
		System.out.println("셀렉트저장파일");
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("text files","txt");
		chooser.addChoosableFileFilter(filter);
		int value=chooser.showSaveDialog(null);
		if(value==JFileChooser.APPROVE_OPTION) {
			File file=chooser.getSelectedFile();
			fileStr=file.getPath();
			fileStr=fileStr+".txt";
			System.out.println(fileStr);
		}		
		return fileStr;
	}
	//한줄씩 입력
	ArrayList<String> openAndReadFile(String FileName){
		System.out.println("파일경로는"+FileName);
		ArrayList<String> open_strings = new ArrayList<String>();
		this.fileName= FileName;
		try {
			input = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File is not open");
			System.out.println("File Name : "+fileName);
			System.exit(1);
		}
		if(input.hasNextLine())
			menuStr=input.nextLine();
		while(input.hasNextLine()){
			inputLine = input.nextLine();
			if(inputLine.startsWith("//") || inputLine.isEmpty())
				continue;
			else{
				open_strings.add(new String(inputLine));
			}
		}
		input.close();
		return open_strings;
	}
	
	//한줄씩 출력
	public void openAndWriteFile(ArrayList<String> save_records,String fileName){
		ArrayList<String> ar =  new ArrayList<String>();
		try{
			output = new PrintWriter(fileName);
		}
		catch(FileNotFoundException e){
			
		}
		output.println(menuStr);
		for(String str : save_records) {
			output.println(str);
		}
		output.close();
	}

}
