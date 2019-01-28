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

public class UserServiceImpl implements UserService {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private UserDAO dao = new UserDAO();
	private UserDTO udto; // �α��ε� ���� dto
	private TruckUserDTO tdto; // ���õ� Ʈ�� dto

	// �ֺ��˻�
	@Override
	public void searchAround() {
		int ch;

		try {

			System.out.print("���� �ּ� �Է� : ");
			String addr = br.readLine();

			List<PlaceVO> tlist = new ArrayList<>();
			tlist = dao.searchNearTruck(addr); // Ʈ���� (Ʈ����ȣ, ��ȣ��, ��ġ, ����)
			System.out.println("��ȣ��\t��ġ\t����");
			for (int i = 0; i < tlist.size(); i++) {
				// ����. ��ȣ�� ��ġ ����
				System.out.println((i + 1) + ". " + tlist.get(i).getTname() + "\t" + tlist.get(i).getPlace() + "\t"
						+ tlist.get(i).getAvgscore() + "\n");
			}

			// 1.�����ϱ�[��ȣ�� �Է�]
			// 2.�ڷΰ���
			while (true) {
				try {
					System.out.println("1. �����ϱ�[��ȣ���� �Է��ϼ���]");
					System.out.println("2. �ڷΰ���");

					String truckName = br.readLine();
					if (truckName.equals("2"))
						return;
					for (int i = 0; i < tlist.size(); i++) {
						// ���� �̸��� �ִٸ�
						if (tlist.get(i).getTname().equals(truckName)) {
							/**
							 * �Է��� �� Ʈ�� ����Ʈ�� ���� ���� �ʵ��� TruckUserDTO�� ����
							 * 
							 */

							/**
							 * ������ Ʈ�� ���� ������ �ܾ�ͼ� �����ؾ� �Ѵ�. UserDAO���� SELECT�� �޼ҵ� ���� �ؾ��Ѵ�.
							 */
							// foodTruckList(tdto);
							return;
							// ���⼭ �̵�
							// �� ����Ʈ�� �̵� �޼ҵ� �����ؾ���
						} else {
							throw new Exception("��ȣ���� �������� �ʽ��ϴ�.");
						}

					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Ű���� �˻�
	@Override
	public void searchKeyWord() {

	}

	// ����Ȯ��
	@Override
	public void confirmBook() {
		List<ReservationDTO> rlist = new ArrayList<>();
		System.out.println("\n���� Ȯ��....");

		try {
			rlist = dao.confirmBookDAO(udto.getUserNum());

			System.out.println("��ȣ��\t�޴�\t�����ݾ�\t��¥");
			for (ReservationDTO rdto : rlist) {
				System.out.print(rdto.getTruckName() + "\t");
				// System.out.print(rdto.get); //�޴���������
				System.out.println(rdto.getTotalPay());
				System.out.println(rdto.getPayDate());
			}

			System.out.println("���θ޴���['1'�Է�]");
			String to_main = br.readLine();
			if (to_main.equals("1"))
				return;

		} catch (Exception e) {

		}
	}

	// ���� ��ȸ
	@Override
	public void userInfo() {
		System.out.println("����� ����");

		try {
			int ch;

			String userNum = udto.getUserNum();

			UserDTO dto = new UserDTO();

			dto = dao.searchMyInfo(userNum);

			if (dto == null) {
				System.out.println("==================================================");
				System.out.println("[Error] ��ϵ� ���̵� �����ϴ�.");
				System.out.println("==================================================");
				return;
			}

			System.out.println("���̵� : " + dto.getId());
			System.out.println("��ȭ��ȣ : " + dto.getTel());

			/*do {
				System.out.println("1.�ڷΰ���");
				ch = Integer.parseInt(br.readLine());

				return;
			} while (ch != 1);*/
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// ���� ���� ����
	public void updateUserInfo() {
		System.out.println("����� ���� ����");

		try {
			int ch;

			String userNum = udto.getUserNum();

			UserDTO dto = new UserDTO();

			System.out.println("������ ��й�ȣ?");
			dto.setPassword(br.readLine());

			System.out.println("������ ��ȭ��ȣ?");
			dto.setTel(br.readLine());

			int result = dao.updateMyInfo(dto, userNum);

			if (result == 1) {
				System.out.println("ȸ������ ���� ����");
			} else {
				System.out.println("ȸ������ ���� ����");
				//return;
			}

			/*do {
				System.out.println("1.�ڷΰ���");
				ch = Integer.parseInt(br.readLine());

				return;
			} while (ch != 1);*/
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// ����Ʈ ��ȸ
	public void myPoint() {
		System.out.println("����Ʈ ��ȸ");

		try {
			PointDTO dto = new PointDTO();
			int ch;

			String userNum = udto.getUserNum();

			dto = dao.searchMyPoint(userNum);

			if (dto == null) {
				System.out.println("==================================================");
				System.out.println("[Error] ��ϵ� ����Ʈ�� �����ϴ�.");
				System.out.println("==================================================");
				return;
			}

			System.out.println("����Ʈ : " + dto.getPoint());
			System.out.println("������¥ : " + dto.getDate());

			/*do {
				System.out.println("1.�ڷΰ���");
				ch = Integer.parseInt(br.readLine());

				return;
			} while (ch != 1);*/
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// ȸ�� Ż��
	public boolean deleteUser() {
		System.out.println("ȸ�� Ż��");

		try {
			int ch;
			
			System.out.println("ȸ��Ż�� �Ͻðڽ��ϱ�?[y/n]");
			ch = br.readLine().charAt(0);
			
			if (ch == 'y' || ch == 'Y') {
				String userNum = udto.getUserNum();
				
				int result = dao.deleteUser(userNum);
				
				if (result == 1) {
					System.out.println("ȸ��Ż�� �Ϸ�");
					udto = null;
					return true;
				} else {
					System.out.println("ȸ��Ż�� ����");
					return false;
				}
			} else {
				System.out.println("ȸ��Ż�� ��ҵǾ����ϴ�.");
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		return false;
	}

	// ȸ������
	@Override
	public void signUP() {
		System.out.println("[����� ȸ������]");

		try {
			UserDTO dto = new UserDTO();
			Random rnd = new Random();
			StringBuffer sb = null;

			do {
				sb = new StringBuffer();
				
				for (int i = 0; i < 5; i++) {
					sb.append(Integer.toString(rnd.nextInt(10)));
				}
			} while (dao.checkUserNum(sb.toString()));

			dto.setUserNum(sb.toString());

			String userCode = "USER";

			while (true) {
				System.out.println("[�ʼ�] ���̵� �Է����ּ���.");
				String id = br.readLine();

				if (dao.checkUserID(id)) {
					System.out.println("�ߺ��� ���̵� �����մϴ�");
				} else {
					dto.setId(id);
					break;
				}
			}

			System.out.println("[�ʼ�] ��й�ȣ�� �Է����ּ���.");
			dto.setPassword(br.readLine());

			System.out.println("��ȭ��ȣ�� �Է����ּ���.");
			dto.setTel(br.readLine());

			try {
				int result = dao.insertUser(dto, userCode);

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

	// �α���
	@Override
	public boolean logIn() {
		String id;
		String pwd;

		System.out.println("[�α���]");
		try {
			System.out.println("���̵� �Է����ּ���.");
			id = br.readLine();

			System.out.println("��й�ȣ�� �Է����ּ���.");
			pwd = br.readLine();

			udto = dao.loginUser(id, pwd);
			
			if (udto != null) {
				if (udto.getBlacklist().equals("Y")) {
					System.out.println("���ܵ� �����Դϴ�. �����ڿ��� ���ǹٶ��ϴ�.");
					return false;
				} else {
					System.out.println("�α��� �Ǿ����ϴ�.");
					return true;
				}
			} else {
				System.out.println("�α��ο� �����߽��ϴ�.");
				return false;
			}
			
			/*if (udto != null) {
				System.out.println("�α��� �Ǿ����ϴ�.");
				return true;
			} else {
				System.out.println("�α��ο� �����߽��ϴ�.");
				return false;
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	// �α׾ƿ�
	@Override
	public boolean logOut() {
		char ch;

		try {
			System.out.println("�α׾ƿ��Ͻðڽ��ϱ�?[y/n]");
			ch = br.readLine().charAt(0);

			if (ch == 'Y' || ch == 'y') {
				System.out.println("�α׾ƿ� �Ǽ̽��ϴ�.");
				udto = null;
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

	// ���ã�� �߰�
	public void addBookmark() {
		char ch;

		try {
			System.out.println("���ã�⿡ ����Ͻðڽ��ϱ�?");
			ch = br.readLine().charAt(0);

			if (ch == 'y' || ch == 'Y') {
				dao.insertBookmark(udto.getUserNum(), tdto.getTruckNum());
			} else {
				System.out.println("���ã�� ��Ͽ� �����߽��ϴ�.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ���ã�� ����Ʈ
	@Override
	public void bookMark() {
		System.out.println("[���ã�� ����Ʈ]");

		List<UserDTO> list = dao.printBookmark(udto.getUserNum());

		Iterator<UserDTO> itu = list.iterator();

		while (itu.hasNext()) {
			System.out.print(tdto.getTruckName() + "\t");
			System.out.print(tdto.getReviewScoreAve() + "\t");
			System.out.print(tdto.getAddress() + "\n");

		}

	}

	// ���ã�� ����
	public void deletebookMark() {
		char ch;

		try {
			System.out.println("���ã�⿡�� �����Ͻðڽ��ϱ�?[y/n]");
			ch = br.readLine().charAt(0);

			if (ch == 'Y' || ch == 'y') {
				dao.deleteBookmark(udto.getUserNum(), tdto.getTruckNum());
				return;
			} else {
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void searchCode() {
		// TODO Auto-generated method stub
		
	}

	// Ǫ��Ʈ�� �󼼸���Ʈ
	/*
	 * public void foodTruckList(TruckUserDTO dto) { int ch; try {
	 * System.out.println("�����̸�" + dto.getName() + " (��ü���� : " + dto.getScore() +
	 * ")"); for(MenuDTO menu : dto.menuList()) { //dto.menuList() �޴� ����Ʈ
	 * System.out.println("�޴� �̸� : "menu.getName() + "\t���� : " + menu.getPrice() +
	 * "\t����: " + menu.getDemons()); } System.out.println(dto.getStart() + "~" +
	 * dto.getClose()); if(dto.getbookPossible()) System.out.println("���� �����մϴ�.");
	 * else System.out.println("���� �Ұ����մϴ�."); System.out.println("��ȭ��ȣ :" +
	 * dto.getTel()); System.out.println("��ġ : " + dto.getLocation());
	 * System.out.println("�������� : " + dto.getMenu()); //����� ���� //tb_review���� Ʈ�� �̸�����
	 * �˻� �� ��¥ ������ order by �ؼ� 5�� �����ͼ� Map<String:�����, String: ����� ����> ���� ����
	 * 
	 * iterator ����ؼ� map ��� �ϱ�
	 * 
	 * 
	 * do { System.out.print("1.���� 2.���ã���� 3.�ı��� 4.�ڷΰ���"); ch =
	 * Integer.parseInt(br.readLine()); }while(ch<1 || ch>4);
	 * 
	 * if(ch==4) return; switch (ch) { case 1: break; //�����ϱ� case 2: break; //���ã��
	 * ��� case 3: break; //�ı� ���
	 * 
	 * 
	 * } } catch (Exception e) {
	 * 
	 * } }
	 */
}
