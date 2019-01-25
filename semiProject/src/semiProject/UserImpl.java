package semiProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Random;

public class UserImpl implements User {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private UserDAO dao = new UserDAO();
	private UserDTO udto; // �α��ε� ���� ����� dto

	@Override
	public void signUP() {
		System.out.println("[����� ȸ������]");
		
		try {
			UserDTO dto = new UserDTO();
			Random rnd = new Random();
			StringBuffer sb = new StringBuffer();
			
			// UserNum �ڵ� ����
			for (int i = 0; i < 5; i++) {
				sb.append(Integer.toString(rnd.nextInt(10))); 
			}
			
			//dto.setUserNum(sb.toString());
			dto.setUserNum("12022");
			
			System.out.println("[�ʼ�] ���̵� �Է����ּ���.");
			dto.setId(br.readLine());
			
			System.out.println("[�ʼ�] ��й�ȣ�� �Է����ּ���.");
			dto.setPassword(br.readLine());
			
			System.out.println("��ȭ��ȣ�� �Է����ּ���.");
			dto.setTel(br.readLine());
			
			String userCode = "USER";
			
			try {
				int result = dao.insertEmployee(dto, userCode);
				
				if (result == 1) {
					System.out.println(dto.getId() + "(��) ȸ�������� �����մϴ�.");
					return;
				} else {
					System.out.println("ȸ�����Կ� �����߽��ϴ�.");
					return;
				}
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("[Error] �ֹ���ȣ�� �ߺ��Ǿ����ϴ�.");
				System.out.println("ȸ�����Կ� �����߽��ϴ�.");
				
				return;
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public boolean logIn() {
		String id;
		String pwd;
		boolean status = false;
		
		System.out.println("[�α���]");
		try {
			System.out.println("���̵� �Է����ּ���.");
			id = br.readLine();

			System.out.println("��й�ȣ�� �Է����ּ���.");
			pwd = br.readLine();

			udto = dao.loginUser(id, pwd);

			if (udto != null) {
				System.out.println("�α��� �Ǿ����ϴ�.");
				status = true;
			} else {
				System.out.println("�α��ο� �����߽��ϴ�.");
				status = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return status;	
	}

	@Override
	public void logOut() {
		char ch;

		try {
			System.out.println("�α׾ƿ��Ͻðڽ��ϱ�?[y/n]");
			ch = br.readLine().charAt(0);

			if (ch == 'Y' || ch == 'y') {
				udto = null;
				return;
			} else {
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
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
		System.out.println("����� ����");
		
		try {
			int ch;
			
			udto = dao.searchMyInfo(udto);
			
			if (udto == null) {
				System.out.println("==================================================");
				System.out.println("[Error] ��ϵ� ���̵� �����ϴ�.");
				System.out.println("==================================================");
				return;
			}
			
			System.out.println("���̵� : " + udto.getId());
			System.out.println("��ȭ��ȣ : " + udto.getTel());
			System.out.println("����Ʈ : " + udto.getPoint());
			
			do {
				System.out.println("1.�ڷΰ���");
				ch = Integer.parseInt(br.readLine());
				
				return;
			} while(ch != 1);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
}
