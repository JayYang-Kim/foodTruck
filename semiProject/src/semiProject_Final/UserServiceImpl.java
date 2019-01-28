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
					System.out.println("1. �����ϱ�[0���� �Է½� �ڷΰ���]");

					String truckNum = br.readLine();
					if (truckNum.equals("0"))
						return;
					else if (tlist.size() < Integer.parseInt(truckNum) || 0 > Integer.parseInt(truckNum))
						continue; // ���� �Է��� �ٽ� �޵��� �Ѵ�.
					else { // ����� �Է����� ��
						tdto = dao.selectTruck(tlist.get(Integer.parseInt(truckNum)).getTnum()); // ���õ� TruckDTO ����
						foodTruckList(tdto); // �� ���� , Ǫ��Ʈ�� �� ����Ʈ ���
					}

				} catch (Exception e) {
					System.out.println(e);

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//�з��� ã��
	@Override
	public void searchCode() {
		// TODO Auto-generated method stub

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
			for(ReservationDTO rdto : rlist) {
				System.out.println("��ȣ�� : " + rdto.getTruckName());
				System.out.println("�޴� : " + rdto.getMenu());
				System.out.println("�����ݾ� : " + rdto.getTotalPay());
				System.out.println("��¥ : " + rdto.getPayDate());
			}
			
			System.out.println("���θ޴���['1'�Է�]");
			if(br.readLine().equals("1"))
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
		} catch (Exception e) {
			System.out.println(e.toString());
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
			}
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
		} catch (Exception e) {
			System.out.println(e.toString());
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
	
	//�ı���
	public void insertReview() {
		ReviewDAO rdao = new ReviewDAO();
		System.out.println("[�ı���]");
		int ch;
		int result = 0;
		try {
			ReviewDTO rdto = new ReviewDTO();

			System.out.println("�ı⸦ ������ּ���");
			rdto.setReviewContent(rdto.getReviewContent());
			System.out.println("������ �Է����ּ���");
			rdto.setReviewScore(rdto.getReviewScore());
			System.out.println("1.��� 2.���");
			ch = Integer.parseInt(br.readLine());
			if (ch == 1) {
				result = rdao.insertReview(udto.getUserNum(), tdto.getTruckNum());
			} else {
				System.out.println("����� ��ҵǾ����ϴ�.");
				return;
			}
			if (result != 0) {
				System.out.println("�ıⰡ ��ϵǾ����ϴ�.");
			} else {
				System.out.println("�ı� ��Ͽ� �����Ͽ����ϴ�.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ���� �� �ı� ����
	public void printReview() {
		List<ReviewDTO> vlist = new ArrayList<>();
		System.out.println("���� �� �ı�");

		try {
			ReviewDAO rdao = new ReviewDAO();
			vlist = rdao.userReview(udto.getUserNum());

			System.out.println("Ʈ���̸�\t��¥\t����");
			for (ReviewDTO vdto : vlist) {
				System.out.print(tdto.getTruckName() + "\t");
				System.out.print(vdto.getDate() + "\t");
				System.out.print(vdto.getReviewScore() + "\n");
				System.out.println("�ı�:" + vdto.getReviewContent());
			}
			System.out.println("1.���θ޴�");
			String to_main = br.readLine();
			if (to_main.equals("1")) {
				return;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void foodTruckList(TruckUserDTO dto) {
		int ch;
		try {
			System.out.println("�����̸� : " + dto.getTruckName() + " (��ü���� : " + dto.getReviewScoreAve() + ")");
			System.out.println("�޴� : ");
			
			List<TruckMenuDTO> mlist = new ArrayList<>();
			mlist = dao.selectTruckMenu(dto.getTruckName());
			for(TruckMenuDTO menu : mlist) {
				System.out.println("->" + menu.getMenuName() + "\t" + menu.getPrice() + "\t" + menu.getAboutMenu());
			}
			
			System.out.println("�����ð� :" + dto.getOpenHour() + "~" + dto.getCloseHour());
			if(dto.getReserveOK().equals("Y"))
				System.out.println("���� ���� ���� : ������ �����մϴ�. " );
			else
				System.out.println("���� ���� ���� : ������ �Ұ����մϴ�. " );
			System.out.println("��ȭ ��ȣ : " + dto.getTel());
			System.out.println("��ġ : " + dto.getAddress());
			System.out.println("�������� : " + dto.getMemo());
			
			//���䳻��
			System.out.println("����� ����");
			List<ReviewDTO> rlist = new ArrayList<>();
			rlist = dao.selectTruckReview(dto.getTruckName());
			for(ReviewDTO review : rlist) {
				System.out.println(review.getUserid() + ":" + review.getReviewContent() );
			}
			
			do {
				System.out.print("1.���� 2.���ã���� 3.�ı��� 4.�ڷΰ���");
				ch = Integer.parseInt(br.readLine());
			}while(ch<1 || ch>4);
			
			if(ch==4) return;
			switch (ch) {
			case 1:  break; //�����ϱ�
			case 2:  break;	//���ã�� ���
			case 3:  break;	//�ı� ���
			}
		} catch (Exception e) {
			
		}
	}
}
