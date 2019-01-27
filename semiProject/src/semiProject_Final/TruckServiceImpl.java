package semiProject_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TruckServiceImpl implements TruckService {
	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private TruckUserDTO loginUserDTO = null; // Ʈ�� ȸ�� ��ȣ �α��ν� �޾��ֱ�.
	private TruckUserDAO dao = new TruckUserDAO();
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
		System.out.println("���� �޴��� �����ݴϴ�.");

	}

	@Override
	public void insertMenu() {
		System.out.println("���� �޴��� �߰��մϴ�. �߰� ���� ����");

	}

	@Override
	public void updateMenu() {
		System.out.println("���� �޴��� �����մϴ�. ���� ���� ����");

	}

	@Override
	public void deleteMenu() {
		System.out.println("���� �޴��� �����մϴ�. ���� ���� ����");

	}

	@Override
	public void updateReservation() {
		System.out.println("���� ���ɿ��� �ٲٱ�");
		System.out.println("�ٲٽðڽ��ϱ�? ����Ͻðڽ��ϱ�?");

	}

	@Override
	public void updateMemo() {
		System.out.println("ȫ�� �Խ��� ���� �ٲٱ�");
		System.out.println("�ٲٽðڽ��ϱ�? ����Ͻðڽ��ϱ�?");
	}

	@Override
	public void showReview() {
		try {
			Clear.clearScreen();
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
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} catch (IndexOutOfBoundsException e) {
			return;
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
	public void open() {

		open = true;
	}

	@Override
	public void move() {

		if (!open) {
			System.out.println("����ó���� ���� �ʾҽ��ϴ�. �������� ���ֽʽÿ�.");
			return;
		}

	}

	@Override
	public void close() {
		if (!open) {
			System.out.println("����ó���� ���� �ʾҽ��ϴ�. �������� ���ֽʽÿ�.");
			return;
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
			List<ReservationDTO> reservationList = null;

		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} catch (IndexOutOfBoundsException e) {
			return;
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
	public void showUserInfo() {
		System.out.println("���� ���� ���");

	}

	@Override
	public void updateUserInfo() {
		System.out.println("���� ���� ����");

	}

	@Override
	public boolean withdraw() {
		System.out.println("Ż���ϸ� true, �ƴϸ� false");

		return true;
	}
}
