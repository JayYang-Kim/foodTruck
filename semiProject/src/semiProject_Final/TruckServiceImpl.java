package semiProject_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TruckServiceImpl implements TruckService {
	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private TruckUserDTO loginUserDTO = null; // Ʈ�� ȸ�� ��ȣ �α��ν� �޾��ֱ�.
	private TruckUserDAO dao = new TruckUserDAO();
	private TruckMenuDAO mdao = new TruckMenuDAO();
	private boolean open = false; // �����ϸ� true �������� ������ �̵��̳� ���� ����.

	@Override
	public boolean login() {
		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("�α���");
			System.out.print("���̵�: ");
			String id = br.readLine();
			System.out.print("��й�ȣ: ");
			String password = br.readLine();

			loginUserDTO = dao.readUser(id);

			if (loginUserDTO == null) {
				System.out.println("�������� �ʴ� ID�Դϴ�.");
				return false;
			}

			if (loginUserDTO.getBlaklist().equals("Y")) {
				System.out.println("���ܵ� �����Դϴ�. �����ڿ��� ���ǹٶ��ϴ�.");
				return false;
			}

			if (password.equals(loginUserDTO.getPassword())) {
				System.out.println(loginUserDTO.getId() + "�� ȯ���մϴ�.");
				return true;
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public void register() {
		try {
			Clear.clearScreen();
			TruckUserDTO dto = new TruckUserDTO();
			StringBuffer sb = null;

			do {
				sb = new StringBuffer(); // ������ ������ ���� �޾������. �ȱ׷��� ��� �̾�ٿ���.
				for (int i = 0; i < 5; i++)
					sb.append((int) (Math.random() * 10)); // ���� 5�ڸ� ����.
			} while (dao.checkUserNum(sb.toString())); // userNum �ߺ� üũ

			dto.setTruckNum(sb.toString());

			while (true) {
				Clear.clearScreen();
				System.out.println("======================================");
				System.out.println("ȸ������");
				System.out.print("���̵� ? ");
				String id = br.readLine();

				if (dao.checkUserID(id)) { // ���̵� �ߺ� üũ
					System.out.println("�ߺ��� id�� �����մϴ�. �ٽ� �Է����ּ���.");
				} else {
					dto.setId(id);
					break;
				}
			}

			System.out.print("��й�ȣ ? ");
			dto.setPassword(br.readLine());

			System.out.print("��ȣ�� ? ");
			dto.setTruckName(br.readLine());

			System.out.print("��ǥ�� ? ");
			dto.setOwner(br.readLine());

			System.out.print("��ȭ��ȣ ? ");
			dto.setTel(br.readLine());

			int cartegoryIndex;
			Cartegory cartegory = new Cartegory();
			int cartegoryLength = cartegory.getCartegoryName().size();
			System.out.println("������ : " + cartegoryLength);
			do {

				System.out.println("���� ��з� ���� ");
				new Cartegory().showCartegory();
				System.out.print("��ȣ �Է�: ");
				cartegoryIndex = Integer.parseInt(br.readLine());
				Clear.clearScreen();
			} while (cartegoryIndex < 1 || cartegoryIndex > cartegoryLength);

			dto.setCartegoryCode(cartegory.getCartegoryCode().get(cartegoryIndex - 1));

			if (dao.insertTruckInfo(dto)) // ��ϵǸ�
				System.out.println("ȸ�������� �Ϸ�Ǽ̽��ϴ�.");
			else
				System.out.println("ȸ������ ���� ������ �־����ϴ�. �ٽ� �Է��Ͽ��ֽʽÿ�.");

		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public boolean logOut() {
		try {
			Clear.clearScreen();
			// ���� ó�� ���ϸ� �α׾ƿ� �ȵ�.
			if (open) {
				System.out.println("======================================");
				System.out.println("����ó���� �� �� �α׾ƿ� ���ֽʽÿ�. ");
				return false;
			}

			System.out.println("======================================");
			System.out.println("�α׾ƿ� �Ͻðڽ��ϱ�?[y/n] ");
			char a = br.readLine().charAt(0);

			if (a == 'y' || a == 'Y') {
				System.out.println("�α׾ƿ� �Ǽ̽��ϴ�.");
				loginUserDTO = null;
				return true;
			}

			System.out.println("�α׾ƿ��� ��ҵǾ����ϴ�.");

		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public void showFoodMenu() {
		System.out.println("======================================");
		System.out.println("���� �޴�");

		List<TruckMenuDTO> list = mdao.showFoodMenu(loginUserDTO.getTruckNum());

		for (TruckMenuDTO dto : list) {
			System.out.print(dto.getMenuName() + "\t");
			System.out.print(dto.getPrice() + "\t");
			System.out.print(dto.getAboutMenu() + "\n");
		}
	}

	@Override
	public void insertMenu() {
		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("���� �޴� �߰�");
			TruckMenuDTO mdto = new TruckMenuDTO();

			System.out.print("�޴����� �Է��ϼ���.");
			mdto.setMenuName(br.readLine());

			System.out.print("������ �Է��ϼ���.");
			mdto.setPrice(Integer.parseInt(br.readLine()));

			System.out.print("�޴������� �Է��ϼ���.");
			mdto.setAboutMenu(br.readLine());

			int result = mdao.insertMenu(mdto, loginUserDTO.getTruckNum());

			if (result == 1) {
				System.out.println("�޴��߰��� �����߽��ϴ�.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("�޴��߰��� �����߽��ϴ�. �ٽ� �Է��ϼ���.");
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("�޴��߰��� �����߽��ϴ�. �ٽ� �Է��ϼ���.");
		}
	}

	@Override
	public void updateMenu() {
		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("���� �޴� ����");

			TruckMenuDTO mdto = new TruckMenuDTO();
			int menuCode;
			do {
				System.out.print("������ �޴����� �Է��ϼ���");
				String menuName = br.readLine();

				menuCode = mdao.readMenuCode(menuName, loginUserDTO.getTruckNum());

				if (menuCode == -1) {
					System.out.println("�������� �ʴ� �޴����Դϴ�. �ٽ� �Է��ϼ���.");
				}

				mdto.setMenuCode(menuCode);
			} while (menuCode == -1);

			System.out.print("���ο� �޴����� �Է��ϼ���.");
			mdto.setMenuName(br.readLine());

			System.out.print("������ �Է��ϼ���.");
			mdto.setPrice(Integer.parseInt(br.readLine()));

			System.out.print("�޴������� �Է��ϼ���.");
			mdto.setAboutMenu(br.readLine());

			int result = mdao.updateMenu(mdto);

			if (result == 1) {
				System.out.println("�޴������� �����߽��ϴ�.");
			}

		} catch (Exception e) {
			System.out.println("�޴������� �����߽��ϴ�. �ٽ� �Է��ϼ���.");
		}
	}

	@Override
	public void deleteMenu() {
		try {
			int result;
			do {
				Clear.clearScreen();
				System.out.println("======================================");
				System.out.println("���� �޴� ����");
				System.out.print("������ �޴����� �Է��ϼ���.");
				String menuName = br.readLine();

				result = mdao.deleteMenu(menuName, loginUserDTO.getTruckNum());

				if (result != 1) {
					System.out.println("�������� �ʴ� �޴����Դϴ�. �ٽ� �Է��ϼ���.");
				}

			} while (result != 1);

			System.out.println("�޴� ������ �Ϸ�Ǿ����ϴ�.");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void updateReservation() {
		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("���� ���� ����");

			int result = -1;
			String s;
			System.out.print("���డ�� [Y], ����Ұ�[N] => ");
			s = br.readLine();
			if (s.equals("Y") || s.equals("y") || s.equals("N") || s.equals("n")) {
				loginUserDTO.setReserveOK(s.toUpperCase());
				result = dao.updateReservation(loginUserDTO);
			}

			if (result != 1) {
				System.out.println("������ �����Ͽ����ϴ�. �ٽ� �õ����ּ���.");
			} else if (result == 1) {

				if (s.equals("Y") || s.equals("y")) {
					System.out.println("[���డ��] ���� ����Ǿ����ϴ�.");
				} else if (s.equals("N") || s.equals("n")) {
					System.out.println("[����Ұ�] �� ����Ǿ����ϴ�.");
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void updateMemo() {

		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("�������� ���� ����");

			int result;
			do {
				System.out.println("������ ������ �Է��ϼ���.");
				String memo = br.readLine();
				loginUserDTO.setMemo(memo);
				result = dao.updateMemo(loginUserDTO);

				if (result != 1) {
					System.out.println("������ �����Ͽ����ϴ�. �ٽ� �Է��ϼ���.");

				}
			} while (result != 1);

			System.out.println("�������� ������ �Ϸ�Ǿ����ϴ�.");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void showReview() {
		printReview();
		char ch = '1';
		do {
			try {
				System.out.println("�ڷΰ���[0]: ");
				ch = br.readLine().charAt(0);
			} catch (IOException e) {
			}
		} while (ch != '0');
	}

	private void printReview() {
		try {
			List<ReviewDTO> reviewList = new ReviewDAO().readReviews(loginUserDTO.getTruckNum());

			if (reviewList.isEmpty()) {
				System.out.println("���䰡 �������� �ʽ��ϴ�.");
				return;
			}

			for (int i = 0; i < 3; i++) {
				ReviewDTO dto = reviewList.get(i);
				System.out.println("======================================");
				System.out.print("����ID: " + dto.getUserid() + "\t");
				System.out.print("�ۼ���: " + dto.getDate() + "\t");
				System.out.println("����: " + dto.getReviewScore() + "/10");
				System.out.println("����: " + dto.getReviewContent());
				System.out.println("======================================");
			}
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

	@Override
	public void open() {
		Map<String, Object> analysisData = new HashMap<String, Object>();
		String place = null;
		String truckNum = null;

		try {
			Clear.clearScreen();

			if (open) {
				System.out.println("======================================");
				System.out.println("�̹� ����ó���� �Ϸ�Ǿ����ϴ�.");
				return;
			}
			System.out.println("======================================");
			System.out.println("����");
			System.out.println("��Ҹ� �Է��Ͽ� �ֽʽÿ�.");
			place = br.readLine();

			truckNum = loginUserDTO.getTruckNum();
			analysisData.put("place", place);
			analysisData.put("truckNum", truckNum);

			if (place == null || dao.open(analysisData) != 1) {
				System.out.println("���� ó���� �����߽��ϴ�. �ٽ� �����Ͽ� �ֽʽÿ�.");
				open = false;
			} else {
				System.out.println("���� ó���� �Ϸ� �Ǿ����ϴ�.");
				open = true;
			}

		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void move() {
		Map<String, Object> analysisData = new HashMap<String, Object>();
		String place = null;
		String truckNum = null;
		int sale = 0;

		try {
			Clear.clearScreen();

			if (!open) {
				System.out.println("����ó���� ���� �ʾҽ��ϴ�. �������� ���ֽʽÿ�.");
				return;
			}

			System.out.println("======================================");
			System.out.print("�̵��ϱ� �� �����: ");
			sale = Integer.parseInt(br.readLine());
			if (sale < 0)
				new NumberFormatException("0�̳� ����� �Է� �����մϴ�.");

			System.out.println("�̵��� ��Ҹ� �Է��Ͽ� �ֽʽÿ�.");
			place = br.readLine();

			truckNum = loginUserDTO.getTruckNum();
			analysisData.put("place", place);
			analysisData.put("truckNum", truckNum);
			analysisData.put("sale", sale);

			if (place == null || !dao.move(analysisData))
				System.out.println("�̵� ó���� �����Ͽ����ϴ�. �ٽ� �����Ͽ� �ֽʽÿ�.");
			else
				System.out.println("�̵� ó���� �Ϸ� �Ǿ����ϴ�.");

		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void close() {
		Map<String, Object> analysisData = new HashMap<String, Object>();
		String truckNum = null;
		int sale = 0;

		try {
			Clear.clearScreen();

			if (!open) {
				System.out.println("����ó���� ���� �ʾҽ��ϴ�. �������� ���ֽʽÿ�.");
				return;
			}

			System.out.println("======================================");
			System.out.print("�����ϱ� �� �����: ");
			sale = Integer.parseInt(br.readLine());
			if (sale < 0)
				new NumberFormatException("0�̳� ����� �Է� �����մϴ�.");

			truckNum = loginUserDTO.getTruckNum();
			analysisData.put("truckNum", truckNum);
			analysisData.put("sale", sale);

			if (!dao.close(analysisData))
				System.out.println("���� ó���� �����Ͽ����ϴ�. �ٽ� �����Ͽ� �ֽʽÿ�.");
			else {
				System.out.println("���� ó���� �Ϸ� �Ǿ����ϴ�.");
				open = false;
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public void checkReservation() {
		try {
			Clear.clearScreen();
			if (loginUserDTO.getReserveOK().equals("N")) {
				System.out.println("�������� ���Դ� ������ ���� �ʽ��ϴ�.");
				return;
			}
			// �����ϴ� ����.
			List<ReservationDTO> rlist = new ArrayList<>();
			System.out.println("\n���� Ȯ��....");

			rlist = dao.confirmBookDAO(loginUserDTO.getTruckNum());

			System.out.println("��ȣ��\t�޴�\t�����ݾ�\t��¥");
			for (ReservationDTO rdto : rlist) {
				System.out.println("����ID : " + rdto.getUserId());
				System.out.println("�޴� : " + rdto.getMenu());
				System.out.println("�����ݾ� : " + rdto.getTotalPay());
				System.out.println("��¥ : " + rdto.getPayDate());
			}

			char ch = '1';

			do {
				try {
					System.out.println("�ڷΰ���[0]: ");
					ch = br.readLine().charAt(0);
				} catch (IOException e) {
				}
			} while (ch != '0');

		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void showUserInfo() {

		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("����� ����");
			String truckNum = loginUserDTO.getTruckNum();

			TruckUserDTO dto = new TruckUserDTO();

			dto = dao.showUserInfo(truckNum);

			if (dto == null) {
				System.out.println("��ϵ� ���̵� �����ϴ�.");
				return;
			}

			System.out.println("���̵�: " + dto.getId());
			System.out.println("��ȭ��ȣ: " + dto.getTel());
			System.out.println("��ȣ��: " + dto.getTruckName());
			System.out.println("��ǥ��: " + dto.getOwner());

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void updateUserInfo() {

		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("����� ���� ����");

			System.out.println("������ ��й�ȣ�� �Է��ϼ���.");
			loginUserDTO.setPassword(br.readLine());

			System.out.println("������ ��ȭ��ȣ�� �Է��ϼ���.");
			loginUserDTO.setTel(br.readLine());

			int result = dao.updateUserInfo(loginUserDTO);

			if (result == 1) {
				System.out.println("ȸ������ ������ �����߽��ϴ�.");
			} else {
				System.out.println("ȸ������ ���� �����߽��ϴ�.");

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public boolean withdraw() {
		int ch;

		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("ȸ�� Ż��");
			System.out.println("ȸ��Ż�� �Ͻðڽ��ϱ�?[y/n]");
			ch = br.readLine().charAt(0);

			if (ch == 'y' || ch == 'Y') {
				String userNum = loginUserDTO.getTruckNum();

				int result = dao.deleteUser(userNum);

				if (result == 1) {
					System.out.println("ȸ��Ż�� �Ϸ�");
					loginUserDTO = null;
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
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}

		return false;
	}

	public void showFoodTruckInfo() {
		try {
			System.out.println(
					"�����̸� : " + loginUserDTO.getTruckName() + " (��ü���� : " + loginUserDTO.getReviewScoreAve() + ")");
			System.out.println("�޴�");
			showFoodMenu();
			System.out.println("======================================");
			System.out.println("�����ð� :" + loginUserDTO.getOpenHour().substring(0, 2) + ":"
					+ loginUserDTO.getOpenHour().substring(2) + "~" + loginUserDTO.getCloseHour().substring(0, 2) + ":"
					+ loginUserDTO.getCloseHour().substring(2));
			if (loginUserDTO.getReserveOK().equals("Y"))
				System.out.println("���� ���� ���� : ������ �����մϴ�. ");
			else
				System.out.println("���� ���� ���� : ������ �Ұ����մϴ�. ");
			System.out.println("��ȭ ��ȣ : " + loginUserDTO.getTel());
			System.out.println("��ġ : " + loginUserDTO.getAddress());
			System.out.println("�������� : " + loginUserDTO.getMemo());
			// ���䳻��
			System.out.println("======================================");
			System.out.println("����� ����");
			printReview();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
