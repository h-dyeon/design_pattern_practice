package DesignPattern_FileType;

public class FileFactory {
	public FileType createFile(String filetype) {
		if(filetype.equals("제품명:제품ID:카테고리:가격:재고수:최소재고량:기타메모:비고")) {
			System.out.println("파일1 생성됨");
			return new File1();
		}			
		else if(filetype.equals("이름:학번:성별:졸업여부:비고")) {
			System.out.println("파일2 생성됨");
			return new File2();
		}
		return null;
	}
}
