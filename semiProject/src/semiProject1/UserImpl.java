package semiProject1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserImpl implements User {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//�ֺ��˻�
	@Override
	public void searchAround() {
		try {
			System.out.print("�ּ� �Է� : ");
			String addr = br.readLine();
			/*
			 * 1. xml���� x,y�� ���� �޾Ƽ�
			 * 2. ����� Ǫ��Ʈ�� ��������(Ǫ��Ʈ�� �ּҷ�)
			 * 2-1. ��ó ���� �������� xml�̿�? or DB�� �ִ� Ǫ��Ʈ�� �ּҷ� �������� ���?
			 * 2-2. ��ó�� �ִ� Ǫ��巰 ���� �޾ƿ´�.(ArrayList<FoodTruckDTO> 5��)
			 * 
			 * 
			 * 
			 * */
		} catch (Exception e) {
			
		}
		
	}

	//Ű���� �˻�
	@Override
	public void searchKeyWord() {
		
	}

	//���ã��
	@Override
	public void bookMark() {
	
		
	}

	//����Ȯ��
	@Override
	public void confirmBook() {
		
		
	}

	
	
	
	
	
	
	
//-----------------------------------------------------------------------	
	@Override
	public void userInfo() {
		
		
	}
	
	@Override
	public void signUP() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logIn() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void logOut() {
		
		
	}

}
