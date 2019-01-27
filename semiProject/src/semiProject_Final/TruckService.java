package semiProject_Final;

interface TruckService {

	public boolean login();                //�α���
	public boolean logOut();               //�α׾ƿ�
	public void register();                //ȸ������
	
	public void showFoodMenu();            //���ĸ޴� ���
	public void insertMenu();              //���ĸ޴� �߰�
	public void updateMenu();              //���ĸ޴� ����
	public void deleteMenu();              //���ĸ޴� ����
	
	public void updateReservation();       //���డ�ɿ��� ����
	public void updateMemo();              //ȫ�� �Խ��� ����

	public void showReview();              //���� ����
	public void open();                    //����
	public void move();                    //�̵�
	public void close();                   //����	
	public void checkReservation();        //���� Ȯ��	
	
	public void showUserInfo();            //ȸ������ ���
	public void updateUserInfo();          //ȸ������ ����
	public boolean withdraw();             //ȸ��Ż��
	
}
