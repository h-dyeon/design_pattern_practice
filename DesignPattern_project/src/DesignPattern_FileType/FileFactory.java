package DesignPattern_FileType;

public class FileFactory {
	public FileType createFile(String filetype) {
		if(filetype.equals("��ǰ��:��ǰID:ī�װ�:����:����:�ּ����:��Ÿ�޸�:���")) {
			System.out.println("����1 ������");
			return new File1();
		}			
		else if(filetype.equals("�̸�:�й�:����:��������:���")) {
			System.out.println("����2 ������");
			return new File2();
		}
		return null;
	}
}
