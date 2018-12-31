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
	//�����������
	//���� �̰� : ArrayList<ProductRecord> recordInput = new ArrayList<ProductRecord>();
	
	//ȭ�� �� �� ��ܿ� ǰ���̸�, ǰ�񰡰� ��� �̷��͵� ����
	public String menuStr=new String();
	//string���� �о���̴� �κ�
	ArrayList<String> record_strings = new ArrayList<String>();
	
	// �̰Ÿ� AddProductTable�� ProductRecord���� ������ 
	//key : productId / value : product order
	static HashMap<String, String> productIdData = new HashMap<String, String>();	
	
	Scanner input;
	PrintWriter output;
	
	String inputLine;
	String fileName;
	int index = 0;
	//�ҷ��� ������ ��θ� ����
	public String selectOpenFile() {
		String fileStr = null;
		System.out.println("����Ʈ��������");
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
	//������ ������ ��� ����
	public String selectSaveFile() {
		String fileStr = null;
		System.out.println("����Ʈ��������");
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
	//���پ� �Է�
	ArrayList<String> openAndReadFile(String FileName){
		System.out.println("���ϰ�δ�"+FileName);
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
	
	//���پ� ���
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
