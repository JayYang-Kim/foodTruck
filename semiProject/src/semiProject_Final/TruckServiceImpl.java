package semiProject_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TruckServiceImpl implements TruckService {
	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private TruckUserDTO loginUserDTO = null; // 트럭 회원 번호 로그인시 받아주기.
	private TruckUserDAO dao = new TruckUserDAO();
	private boolean open = false; // 개점하면 true 개점하지 않으면 이동이나 마감 못함.

	@Override
	public boolean login() {
		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("로그인");
			System.out.print("아이디: ");
			String id = br.readLine();
			System.out.print("비밀번호: ");
			String password = br.readLine();

			loginUserDTO = dao.readUser(id);

			if (loginUserDTO == null) {
				System.out.println("존재하지 않는 ID입니다.");
				return false;
			}

			if (loginUserDTO.getBlaklist().equals("Y")) {
				System.out.println("차단된 유저입니다. 관리자에게 문의바랍니다.");
				return false;
			}

			if (password.equals(loginUserDTO.getPassword())) {
				System.out.println(loginUserDTO.getId() + "님 환영합니다.");
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
				sb = new StringBuffer(); // 시작할 때마다 새로 받아줘야함. 안그러면 계속 이어붙여짐.
				for (int i = 0; i < 5; i++)
					sb.append((int) (Math.random() * 10)); // 랜덤 5자리 생성.
			} while (dao.checkUserNum(sb.toString())); // userNum 중복 체크

			dto.setTruckNum(sb.toString());

			while (true) {
				Clear.clearScreen();
				System.out.println("======================================");
				System.out.println("회원가입");
				System.out.print("아이디 ? ");
				String id = br.readLine();

				if (dao.checkUserID(id)) { // 아이디 중복 체크
					System.out.println("중복된 id가 존재합니다. 다시 입력해주세요.");
				} else {
					dto.setId(id);
					break;
				}
			}

			System.out.print("비밀번호 ? ");
			dto.setPassword(br.readLine());

			System.out.print("상호명 ? ");
			dto.setTruckName(br.readLine());

			System.out.print("대표자 ? ");
			dto.setOwner(br.readLine());

			System.out.print("전화번호 ? ");
			dto.setTel(br.readLine());

			int cartegoryIndex;
			Cartegory cartegory = new Cartegory();
			int cartegoryLength = cartegory.getCartegoryName().size();
			System.out.println("사이즈 : " + cartegoryLength);
			do {

				System.out.println("음식 대분류 선택 ");
				new Cartegory().showCartegory();
				System.out.print("번호 입력: ");
				cartegoryIndex = Integer.parseInt(br.readLine());
				Clear.clearScreen();
			} while (cartegoryIndex < 1 || cartegoryIndex > cartegoryLength);

			dto.setCartegoryCode(cartegory.getCartegoryCode().get(cartegoryIndex - 1));

			if (dao.insertTruckInfo(dto)) // 등록되면
				System.out.println("회원가입이 완료되셨습니다.");
			else
				System.out.println("회원가입 도중 오류가 있었습니다. 다시 입력하여주십시오.");

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
			System.out.println("로그아웃 하시겠습니까?[y/n] ");
			char a = br.readLine().charAt(0);

			if (a == 'y' || a == 'Y') {
				System.out.println("로그아웃 되셨습니다.");
				loginUserDTO = null;
				return true;
			}

			System.out.println("로그아웃이 취소되었습니다.");

		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public void showFoodMenu() {
		System.out.println("음식 메뉴를 보여줍니다.");

	}

	@Override
	public void insertMenu() {
		System.out.println("음식 메뉴를 추가합니다. 추가 여부 묻기");

	}

	@Override
	public void updateMenu() {
		System.out.println("음식 메뉴를 수정합니다. 수정 여부 묻기");

	}

	@Override
	public void deleteMenu() {
		System.out.println("음식 메뉴를 삭제합니다. 삭제 여부 묻기");

	}

	@Override
	public void updateReservation() {
		System.out.println("예약 가능여부 바꾸기");
		System.out.println("바꾸시겠습니까? 취소하시겠습니까?");

	}

	@Override
	public void updateMemo() {
		System.out.println("홍보 게시판 내용 바꾸기");
		System.out.println("바꾸시겠습니까? 취소하시겠습니까?");
	}

	@Override
	public void showReview() {
		try {
			Clear.clearScreen();
			List<ReviewDTO> reviewList = new ReviewDAO().readReviews(loginUserDTO.getTruckNum());

			if (reviewList.isEmpty()) {
				System.out.println("리뷰가 존재하지 않습니다.");
				return;
			}

			for (int i = 0; i < 3; i++) {
				ReviewDTO dto = reviewList.get(i);
				System.out.println("======================================");
				System.out.print("유저ID: " + dto.getUserid() + "\t");
				System.out.print("작성일: " + dto.getDate() + "\t");
				System.out.println("평점: " + dto.getReviewScore() + "/10");
				System.out.println("내용: " + dto.getReviewContent());
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
					System.out.println("뒤로가기[0]: ");
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
			System.out.println("개점처리가 되지 않았습니다. 개점부터 해주십시오.");
			return;
		}

	}

	@Override
	public void close() {
		if (!open) {
			System.out.println("개점처리가 되지 않았습니다. 개점부터 해주십시오.");
			return;
		}

	}

	@Override
	public void checkReservation() {
		try {
			Clear.clearScreen();
			if (loginUserDTO.getReserveOK().equals("N")) {
				System.out.println("유저님의 가게는 예약을 받지 않습니다.");
				return;
			}
			// 수정하다 말음.
			List<ReservationDTO> reservationList = null;

		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} catch (IndexOutOfBoundsException e) {
			return;
		} finally {
			char ch = '1';
			do {
				try {
					System.out.println("뒤로가기[0]: ");
					ch = br.readLine().charAt(0);
				} catch (IOException e) {
				}
			} while (ch != '0');
		}

	}

	@Override
	public void showUserInfo() {
		System.out.println("점주 정보 출력");

	}

	@Override
	public void updateUserInfo() {
		System.out.println("점주 정보 수정");

	}

	@Override
	public boolean withdraw() {
		System.out.println("탈퇴하면 true, 아니면 false");

		return true;
	}
}
