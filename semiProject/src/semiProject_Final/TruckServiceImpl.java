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
	private TruckUserDTO loginUserDTO = null; // 트럭 회원 번호 로그인시 받아주기.
	private TruckUserDAO dao = new TruckUserDAO();
	private TruckMenuDAO mdao = new TruckMenuDAO();
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
			// 마감 처리 안하면 로그아웃 안됨.
			if (open) {
				System.out.println("======================================");
				System.out.println("마감처리를 한 후 로그아웃 해주십시오. ");
				return false;
			}

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
		System.out.println("======================================");
		System.out.println("음식 메뉴");

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
			System.out.println("음식 메뉴 추가");
			TruckMenuDTO mdto = new TruckMenuDTO();

			System.out.print("메뉴명을 입력하세요.");
			mdto.setMenuName(br.readLine());

			System.out.print("가격을 입력하세요.");
			mdto.setPrice(Integer.parseInt(br.readLine()));

			System.out.print("메뉴설명을 입력하세요.");
			mdto.setAboutMenu(br.readLine());

			int result = mdao.insertMenu(mdto, loginUserDTO.getTruckNum());

			if (result == 1) {
				System.out.println("메뉴추가를 성공했습니다.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("메뉴추가를 실패했습니다. 다시 입력하세요.");
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println("메뉴추가를 실패했습니다. 다시 입력하세요.");
		}
	}

	@Override
	public void updateMenu() {
		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("음식 메뉴 수정");

			TruckMenuDTO mdto = new TruckMenuDTO();
			int menuCode;
			do {
				System.out.print("수정할 메뉴명을 입력하세요");
				String menuName = br.readLine();

				menuCode = mdao.readMenuCode(menuName, loginUserDTO.getTruckNum());

				if (menuCode == -1) {
					System.out.println("존재하지 않는 메뉴명입니다. 다시 입력하세요.");
				}

				mdto.setMenuCode(menuCode);
			} while (menuCode == -1);

			System.out.print("새로운 메뉴명을 입력하세요.");
			mdto.setMenuName(br.readLine());

			System.out.print("가격을 입력하세요.");
			mdto.setPrice(Integer.parseInt(br.readLine()));

			System.out.print("메뉴설명을 입력하세요.");
			mdto.setAboutMenu(br.readLine());

			int result = mdao.updateMenu(mdto);

			if (result == 1) {
				System.out.println("메뉴수정을 성공했습니다.");
			}

		} catch (Exception e) {
			System.out.println("메뉴수정을 실패했습니다. 다시 입력하세요.");
		}
	}

	@Override
	public void deleteMenu() {
		try {
			int result;
			do {
				Clear.clearScreen();
				System.out.println("======================================");
				System.out.println("음식 메뉴 삭제");
				System.out.print("삭제할 메뉴명을 입력하세요.");
				String menuName = br.readLine();

				result = mdao.deleteMenu(menuName, loginUserDTO.getTruckNum());

				if (result != 1) {
					System.out.println("존재하지 않는 메뉴명입니다. 다시 입력하세요.");
				}

			} while (result != 1);

			System.out.println("메뉴 삭제가 완료되었습니다.");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void updateReservation() {
		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("예약 여부 변경");

			int result = -1;
			String s;
			System.out.print("예약가능 [Y], 예약불가[N] => ");
			s = br.readLine();
			if (s.equals("Y") || s.equals("y") || s.equals("N") || s.equals("n")) {
				loginUserDTO.setReserveOK(s.toUpperCase());
				result = dao.updateReservation(loginUserDTO);
			}

			if (result != 1) {
				System.out.println("변경을 실패하였습니다. 다시 시도해주세요.");
			} else if (result == 1) {

				if (s.equals("Y") || s.equals("y")) {
					System.out.println("[예약가능] 으로 변경되었습니다.");
				} else if (s.equals("N") || s.equals("n")) {
					System.out.println("[예약불가] 로 변경되었습니다.");
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
			System.out.println("공지사항 내용 변경");

			int result;
			do {
				System.out.println("변경할 내용을 입력하세요.");
				String memo = br.readLine();
				loginUserDTO.setMemo(memo);
				result = dao.updateMemo(loginUserDTO);

				if (result != 1) {
					System.out.println("변경을 실패하였습니다. 다시 입력하세요.");

				}
			} while (result != 1);

			System.out.println("공지사항 변경이 완료되었습니다.");

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
				System.out.println("뒤로가기[0]: ");
				ch = br.readLine().charAt(0);
			} catch (IOException e) {
			}
		} while (ch != '0');
	}

	private void printReview() {
		try {
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
				System.out.println("이미 개점처리가 완료되었습니다.");
				return;
			}
			System.out.println("======================================");
			System.out.println("개점");
			System.out.println("장소를 입력하여 주십시오.");
			place = br.readLine();

			truckNum = loginUserDTO.getTruckNum();
			analysisData.put("place", place);
			analysisData.put("truckNum", truckNum);

			if (place == null || dao.open(analysisData) != 1) {
				System.out.println("개점 처리에 실패했습니다. 다시 실행하여 주십시오.");
				open = false;
			} else {
				System.out.println("개점 처리가 완료 되었습니다.");
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
				System.out.println("개점처리가 되지 않았습니다. 개점부터 해주십시오.");
				return;
			}

			System.out.println("======================================");
			System.out.print("이동하기 전 매출액: ");
			sale = Integer.parseInt(br.readLine());
			if (sale < 0)
				new NumberFormatException("0이나 양수만 입력 가능합니다.");

			System.out.println("이동한 장소를 입력하여 주십시오.");
			place = br.readLine();

			truckNum = loginUserDTO.getTruckNum();
			analysisData.put("place", place);
			analysisData.put("truckNum", truckNum);
			analysisData.put("sale", sale);

			if (place == null || !dao.move(analysisData))
				System.out.println("이동 처리에 실패하였습니다. 다시 실행하여 주십시오.");
			else
				System.out.println("이동 처리가 완료 되었습니다.");

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
				System.out.println("개점처리가 되지 않았습니다. 개점부터 해주십시오.");
				return;
			}

			System.out.println("======================================");
			System.out.print("마감하기 전 매출액: ");
			sale = Integer.parseInt(br.readLine());
			if (sale < 0)
				new NumberFormatException("0이나 양수만 입력 가능합니다.");

			truckNum = loginUserDTO.getTruckNum();
			analysisData.put("truckNum", truckNum);
			analysisData.put("sale", sale);

			if (!dao.close(analysisData))
				System.out.println("마감 처리에 실패하였습니다. 다시 실행하여 주십시오.");
			else {
				System.out.println("마감 처리가 완료 되었습니다.");
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
				System.out.println("유저님의 가게는 예약을 받지 않습니다.");
				return;
			}
			// 수정하다 말음.
			List<ReservationDTO> rlist = new ArrayList<>();
			System.out.println("\n예약 확인....");

			rlist = dao.confirmBookDAO(loginUserDTO.getTruckNum());

			System.out.println("상호명\t메뉴\t결제금액\t날짜");
			for (ReservationDTO rdto : rlist) {
				System.out.println("유저ID : " + rdto.getUserId());
				System.out.println("메뉴 : " + rdto.getMenu());
				System.out.println("결제금액 : " + rdto.getTotalPay());
				System.out.println("날짜 : " + rdto.getPayDate());
			}

			char ch = '1';

			do {
				try {
					System.out.println("뒤로가기[0]: ");
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
			System.out.println("사용자 정보");
			String truckNum = loginUserDTO.getTruckNum();

			TruckUserDTO dto = new TruckUserDTO();

			dto = dao.showUserInfo(truckNum);

			if (dto == null) {
				System.out.println("등록된 아이디가 없습니다.");
				return;
			}

			System.out.println("아이디: " + dto.getId());
			System.out.println("전화번호: " + dto.getTel());
			System.out.println("상호명: " + dto.getTruckName());
			System.out.println("대표자: " + dto.getOwner());

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void updateUserInfo() {

		try {
			Clear.clearScreen();
			System.out.println("======================================");
			System.out.println("사용자 정보 수정");

			System.out.println("변경할 비밀번호를 입력하세요.");
			loginUserDTO.setPassword(br.readLine());

			System.out.println("변경할 전화번호를 입력하세요.");
			loginUserDTO.setTel(br.readLine());

			int result = dao.updateUserInfo(loginUserDTO);

			if (result == 1) {
				System.out.println("회원정보 수정을 성공했습니다.");
			} else {
				System.out.println("회원정보 수정 실패했습니다.");

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
			System.out.println("회원 탈퇴");
			System.out.println("회원탈퇴 하시겠습니까?[y/n]");
			ch = br.readLine().charAt(0);

			if (ch == 'y' || ch == 'Y') {
				String userNum = loginUserDTO.getTruckNum();

				int result = dao.deleteUser(userNum);

				if (result == 1) {
					System.out.println("회원탈퇴 완료");
					loginUserDTO = null;
					return true;
				} else {
					System.out.println("회원탈퇴 실패");
					return false;
				}
			} else {
				System.out.println("회원탈퇴 취소되었습니다.");
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
					"가게이름 : " + loginUserDTO.getTruckName() + " (전체평점 : " + loginUserDTO.getReviewScoreAve() + ")");
			System.out.println("메뉴");
			showFoodMenu();
			System.out.println("======================================");
			System.out.println("영업시간 :" + loginUserDTO.getOpenHour().substring(0, 2) + ":"
					+ loginUserDTO.getOpenHour().substring(2) + "~" + loginUserDTO.getCloseHour().substring(0, 2) + ":"
					+ loginUserDTO.getCloseHour().substring(2));
			if (loginUserDTO.getReserveOK().equals("Y"))
				System.out.println("예약 가능 여부 : 예약이 가능합니다. ");
			else
				System.out.println("예약 가능 여부 : 예약이 불가능합니다. ");
			System.out.println("전화 번호 : " + loginUserDTO.getTel());
			System.out.println("위치 : " + loginUserDTO.getAddress());
			System.out.println("공지사항 : " + loginUserDTO.getMemo());
			// 리뷰내용
			System.out.println("======================================");
			System.out.println("사용자 리뷰");
			printReview();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
