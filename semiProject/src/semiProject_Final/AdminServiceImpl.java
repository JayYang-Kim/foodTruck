package semiProject_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import semiProject_Final.UserDAO.PlaceVO;

public class AdminServiceImpl implements AdminService {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private AdminDAO dao = new AdminDAO();
	private AdminDTO adto;
	
	@Override
	public boolean logIn() {
		try {
			String id;
			String pw;
			
			System.out.println("���̵� �Է����ּ���.");
			id = br.readLine();
			
			System.out.println("��й�ȣ�� �Է����ּ���.");
			pw = br.readLine();
			
			adto = dao.loginUser(id, pw);
			
			if (adto != null) {
				System.out.println("�α��� �Ǿ����ϴ�.");
				return true;
			} else {
				System.out.println("�α��ο� �����߽��ϴ�.");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean logOut() {
		char ch;

		try {
			System.out.println("�α׾ƿ��Ͻðڽ��ϱ�?[y/n]");
			ch = br.readLine().charAt(0);

			if (ch == 'Y' || ch == 'y') {
				System.out.println("�α׾ƿ� �Ǽ̽��ϴ�.");
				adto = null;
				return true;
			} else {
				System.out.println("�α׾ƿ��� ��ҵǾ����ϴ�.");
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public void listUser() {
		try {
			List<UserDTO> list = dao.searchUser();
			//List<UserDTO> list = null;
			
			if (list.isEmpty()) {
				System.out.println("��ϵ� ���� ������ �����ϴ�.");
				return;
			}
			
			System.out.println("������ȣ\t���̵�\t��ȭ��ȣ\t���ܿ���");
			for (UserDTO dto : list) {		
				System.out.printf("%s\t %s\t %s\t %s\n", dto.getUserNum(), dto.getId(), dto.getTel(), dto.getBlacklist());
			}
		} catch (Exception e) {
			
		} finally {
			char ch = '1';
			
			do {
				try {
					System.out.println("�ڷΰ���[0]: ");
					ch = br.readLine().charAt(0);
				} catch (IOException e) {
				}
			} while (ch != '0');
		}
	}
	@Override
	public void listFoodTruck() {
		try {
			List<UserDTO> list = dao.searchFoodTruck();
			
			if (list.isEmpty()) {
				System.out.println("��ϵ� ���� ������ �����ϴ�.");
				return;
			}
			
			System.out.println("���ֹ�ȣ\t���̵�\t��ȭ��ȣ\t���ܿ���");
			for (UserDTO dto : list) {		
				System.out.printf("%s\t %s\t %s\t %s\n", dto.getUserNum(), dto.getId(), dto.getTel(), dto.getBlacklist());
			}
		} catch (Exception e) {
			
		} finally {
			char ch = '1';
			
			do {
				try {
					System.out.println("�ڷΰ���[0]: ");
					ch = br.readLine().charAt(0);
				} catch (IOException e) {
				}
			} while (ch != '0');
		}
		
	}
	@Override
	public void setBlackUser() {
		System.out.println("���� ������Ʈ ����");
		
		try {
			List<UserDTO> list = dao.searchUser();
			
			if (list.isEmpty()) {
				System.out.println("��ϵ� ���� ������ �����ϴ�.");
				return;
			}
			
			System.out.println("������ȣ\t���̵�\t��ȭ��ȣ\t���ܿ���");
			for (UserDTO dto : list) {		
				System.out.printf("%s\t %s\t %s\t %s\n", dto.getUserNum(), dto.getId(), dto.getTel(), dto.getBlacklist());
			}
			
			System.out.println("���ܿ��θ� ������ ���̵� �Է����ּ���.");
			String id = br.readLine();
			
			UserDTO dto = new UserDTO();
			
			dto = dao.searchBlackUser(id);
			
			if (dto == null) {
				System.out.println("��ϵ� ���̵� �����ϴ�.");
				return;
			}
			
			System.out.println("������ȣ\t���̵�\t��ȭ��ȣ\t���ܿ���");
			System.out.printf("%s\t %s\t %s\t %s\n", dto.getUserNum(), dto.getId(), dto.getTel(), dto.getBlacklist());
			
			System.out.println("���ܿ��θ� �Է����ּ���.[y/n]");
			int ch = br.readLine().charAt(0);
			
			if (ch == 'y' || ch == 'Y') {
				
			} else {
				System.out.println("���ܿ��� ������ ����߽��ϴ�.");
				return;
			}
		} catch (Exception e) {
			
		} finally {
			char ch = '1';
			
			do {
				try {
					System.out.println("�ڷΰ���[0]: ");
					ch = br.readLine().charAt(0);
				} catch (IOException e) {
				}
			} while (ch != '0');
		}
		
	}
	@Override
	public void setBlackFoodTruck() {
		System.out.println("���� ������Ʈ ����");
		
	}
}
