package semiProject_Final;

public class TruckMenuDTO {
	private String menuCode;  //�޴� �ڵ�
	private String menuName;  //�޴���
	private String aboutMenu; //�޴�����
	private int price;        //����
	
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getAboutMenu() {
		return aboutMenu;
	}
	
	public void setAboutMenu(String aboutMenu) {
		this.aboutMenu = aboutMenu;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
