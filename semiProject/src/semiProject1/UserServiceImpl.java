package semiProject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserServiceImpl implements UserService{

	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public boolean userLogin() throws IOException{
		System.out.println("���� 1.�α��� ���� 2.�α��� ����");
		int ch = Integer.parseInt(br.readLine());
		if(ch == 1)
		{
			System.out.println("�α��� �����ߴ�!");
			return true;
		}			
		else
		{
			System.out.println("�α��� �����ߴ�!");
			return false;
		}
			
	}

	@Override
	public void userRegister() throws IOException{
		System.out.println("���� 1.ȸ������ ���� 2.ȸ������ ����");
		int ch = Integer.parseInt(br.readLine());
		if(ch == 1)
		{
			System.out.println("ȸ������ �����ߴ�!");
			
		}			
		else
		{
			System.out.println("ȸ������ �����ߴ�!");			
		}
	}

	@Override
	public void aroundSearch() throws IOException {
		System.out.println("�ּ��Է�: "); //�ּ� �Է¹޴� �͸� ���� �޼ҵ�� ������.
		System.out.println("��ȣ�� ��ġ ���� ����Ʈ ��� (���� ��ġ ��)");
		
		System.out.println("0���̸� �ڷΰ���");
		System.out.println("���� => ");
		int a = Integer.parseInt(br.readLine()); //�����ϱ�.
		
		if(a==0)
			return; //�ٽ� userServiceMenu��.
		
		System.out.println("�󼼺���");	
		
	

				
	}

	@Override
	public void kewordSearch() throws IOException {
		System.out.println("Ű���� �˻�:(����) 0�̸� �ڷΰ���.");
		System.out.println("�Է� => ");
		String keyword = br.readLine(); //�����ϱ�.
		
		if(keyword.equals("0"))
			return; //�ٽ� userServiceMenu��.
		
		System.out.println("keyword");	
		
	}

	@Override
	public void bookMark() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reservationCheck() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void userInfo() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean logout() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
