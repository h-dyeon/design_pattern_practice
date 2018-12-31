package DesignPattern_State;

public interface State {
	public void addBtn();
	public void deleteBtn(int rows[]);
	public void modifyBtn(int row);
	public void fileOpenBtn();
	public void fileSaveBtn();
}
