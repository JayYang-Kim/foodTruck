package semiProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class UserImpl implements User {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private UserDAO dao = new UserDAO();

	@Override
	public void signUP() {
		System.out.println("[����� ȸ������]");
		
		try {
			UserDTO dto = new UserDTO();
			Random rnd = new Random();
			StringBuffer sb = new StringBuffer();
			
			String userNum = null;
			String userCode = "USER";
			
			System.out.println("[�ʼ�] ���̵� �Է����ּ���.");
			dto.setId(br.readLine());
			
			System.out.println("[�ʼ�] ��й�ȣ�� �Է����ּ���.");
			dto.setPassword(br.readLine());
			
			System.out.println("��ȭ��ȣ�� �Է����ּ���.");
			dto.setTel(br.readLine());
			
			// UserNum �ڵ� ����
			for (int i = 0; i < 5; i++) {
				sb.append(Integer.toString(rnd.nextInt(10))); 
			}
			
			userNum = sb.toString();
			
			int result = dao.insertEmployee(dto, userNum, userCode);
			
			if (result == 1) {
				System.out.println(dto.getId() + "(��) ȸ�������� �����մϴ�.");
			} else {
				System.out.println("ȸ�����Կ� �����߽��ϴ�.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void logIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchAround() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchKeyWord() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bookMark() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmBook() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void userInfo() {
		// TODO Auto-generated method stub
		
	}
	
}
