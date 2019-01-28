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
	private UserDTO udto; // 로그인된 유저 dto
	private TruckUserDTO tdto; // 선택된 트럭 dto

	// 주변검색
	@Override
	public void searchAround() {
		int ch;

		try {

			System.out.print("나의 주소 입력 : ");
			String addr = br.readLine();

			List<PlaceVO> tlist = new ArrayList<>();
			tlist = dao.searchNearTruck(addr); // 트럭의 (트럭번호, 상호명, 위치, 평점)
			System.out.println("상호명\t위치\t평점");
			for (int i = 0; i < tlist.size(); i++) {
				// 숫자. 상호명 위치 평점
				System.out.println((i + 1) + ". " + tlist.get(i).getTname() + "\t" + tlist.get(i).getPlace() + "\t"
						+ tlist.get(i).getAvgscore() + "\n");
			}

			// 1.선택하기[상호명 입력]
			// 2.뒤로가기
			while (true) {
				try {
					System.out.println("1. 선택하기[상호명을 입력하세요]");
					System.out.println("2. 뒤로가기");

					String truckName = br.readLine();
					if (truckName.equals("2"))
						return;
					for (int i = 0; i < tlist.size(); i++) {
						// 같은 이름이 있다면
						if (tlist.get(i).getTname().equals(truckName)) {
							/**
							 * 입력후 상세 트럭 리스트로 들어가기 위해 필드인 TruckUserDTO에 삽입
							 * 
							 */

							/**
							 * 선택한 트럭 값의 정보를 긁어와서 저장해야 한다. UserDAO에서 SELECT문 메소드 생성 해야한다.
							 */
							// foodTruckList(tdto);
							return;
							// 여기서 이동
							// 상세 리스트로 이동 메소드 생성해야함
						} else {
							throw new Exception("상호명이 존재하지 않습니다.");
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

	// 키워드 검색
	@Override
	public void searchKeyWord() {

	}

	// 예약확인
	@Override
	public void confirmBook() {
		List<ReservationDTO> rlist = new ArrayList<>();
		System.out.println("\n예약 확인....");

		try {
			rlist = dao.confirmBookDAO(udto.getUserNum());

			System.out.println("상호명\t메뉴\t결제금액\t날짜");
			for (ReservationDTO rdto : rlist) {
				System.out.print(rdto.getTruckName() + "\t");
				// System.out.print(rdto.get); //메뉴가져오기
				System.out.println(rdto.getTotalPay());
				System.out.println(rdto.getPayDate());
			}

			System.out.println("메인메뉴로['1'입력]");
			String to_main = br.readLine();
			if (to_main.equals("1"))
				return;

		} catch (Exception e) {

		}
	}

	// 유저 조회
	@Override
	public void userInfo() {
		System.out.println("사용자 정보");

		try {
			int ch;

			String userNum = udto.getUserNum();

			UserDTO dto = new UserDTO();

			dto = dao.searchMyInfo(userNum);

			if (dto == null) {
				System.out.println("==================================================");
				System.out.println("[Error] 등록된 아이디가 없습니다.");
				System.out.println("==================================================");
				return;
			}

			System.out.println("아이디 : " + dto.getId());
			System.out.println("전화번호 : " + dto.getTel());

			/*do {
				System.out.println("1.뒤로가기");
				ch = Integer.parseInt(br.readLine());

				return;
			} while (ch != 1);*/
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// 유저 정보 수정
	public void updateUserInfo() {
		System.out.println("사용자 정보 수정");

		try {
			int ch;

			String userNum = udto.getUserNum();

			UserDTO dto = new UserDTO();

			System.out.println("변경할 비밀번호?");
			dto.setPassword(br.readLine());

			System.out.println("변경할 전화번호?");
			dto.setTel(br.readLine());

			int result = dao.updateMyInfo(dto, userNum);

			if (result == 1) {
				System.out.println("회원정보 수정 성공");
			} else {
				System.out.println("회원정보 수정 실패");
				//return;
			}

			/*do {
				System.out.println("1.뒤로가기");
				ch = Integer.parseInt(br.readLine());

				return;
			} while (ch != 1);*/
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// 포인트 조회
	public void myPoint() {
		System.out.println("포인트 조회");

		try {
			PointDTO dto = new PointDTO();
			int ch;

			String userNum = udto.getUserNum();

			dto = dao.searchMyPoint(userNum);

			if (dto == null) {
				System.out.println("==================================================");
				System.out.println("[Error] 등록된 포인트가 없습니다.");
				System.out.println("==================================================");
				return;
			}

			System.out.println("포인트 : " + dto.getPoint());
			System.out.println("적립날짜 : " + dto.getDate());

			/*do {
				System.out.println("1.뒤로가기");
				ch = Integer.parseInt(br.readLine());

				return;
			} while (ch != 1);*/
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// 회원 탈퇴
	public boolean deleteUser() {
		System.out.println("회원 탈퇴");

		try {
			int ch;
			
			System.out.println("회원탈퇴 하시겠습니까?[y/n]");
			ch = br.readLine().charAt(0);
			
			if (ch == 'y' || ch == 'Y') {
				String userNum = udto.getUserNum();
				
				int result = dao.deleteUser(userNum);
				
				if (result == 1) {
					System.out.println("회원탈퇴 완료");
					udto = null;
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
		}
		
		return false;
	}

	// 회원가입
	@Override
	public void signUP() {
		System.out.println("[사용자 회원가입]");

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
				System.out.println("[필수] 아이디를 입력해주세요.");
				String id = br.readLine();

				if (dao.checkUserID(id)) {
					System.out.println("중복된 아이디가 존재합니다");
				} else {
					dto.setId(id);
					break;
				}
			}

			System.out.println("[필수] 비밀번호를 입력해주세요.");
			dto.setPassword(br.readLine());

			System.out.println("전화번호를 입력해주세요.");
			dto.setTel(br.readLine());

			try {
				int result = dao.insertUser(dto, userCode);

				if (result == 1) {
					System.out.println(dto.getId() + "(님) 회원가입을 축하합니다.");
					return;
				} else {
					System.out.println("회원가입에 실패했습니다.");
					return;
				}
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("[Error] 주문번호가 중복되었습니다.");
				System.out.println("회원가입에 실패했습니다.");

				return;
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// 로그인
	@Override
	public boolean logIn() {
		String id;
		String pwd;

		System.out.println("[로그인]");
		try {
			System.out.println("아이디를 입력해주세요.");
			id = br.readLine();

			System.out.println("비밀번호를 입력해주세요.");
			pwd = br.readLine();

			udto = dao.loginUser(id, pwd);
			
			if (udto != null) {
				if (udto.getBlacklist().equals("Y")) {
					System.out.println("차단된 유저입니다. 관리자에게 문의바랍니다.");
					return false;
				} else {
					System.out.println("로그인 되었습니다.");
					return true;
				}
			} else {
				System.out.println("로그인에 실패했습니다.");
				return false;
			}
			
			/*if (udto != null) {
				System.out.println("로그인 되었습니다.");
				return true;
			} else {
				System.out.println("로그인에 실패했습니다.");
				return false;
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 로그아웃
	@Override
	public boolean logOut() {
		char ch;

		try {
			System.out.println("로그아웃하시겠습니까?[y/n]");
			ch = br.readLine().charAt(0);

			if (ch == 'Y' || ch == 'y') {
				System.out.println("로그아웃 되셨습니다.");
				udto = null;
				return true;
			} else {
				System.out.println("로그아웃이 취소되었습니다.");
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	// 즐겨찾기 추가
	public void addBookmark() {
		char ch;

		try {
			System.out.println("즐겨찾기에 등록하시겠습니까?");
			ch = br.readLine().charAt(0);

			if (ch == 'y' || ch == 'Y') {
				dao.insertBookmark(udto.getUserNum(), tdto.getTruckNum());
			} else {
				System.out.println("즐겨찾기 등록에 실패했습니다.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 즐겨찾기 리스트
	@Override
	public void bookMark() {
		System.out.println("[즐겨찾기 리스트]");

		List<UserDTO> list = dao.printBookmark(udto.getUserNum());

		Iterator<UserDTO> itu = list.iterator();

		while (itu.hasNext()) {
			System.out.print(tdto.getTruckName() + "\t");
			System.out.print(tdto.getReviewScoreAve() + "\t");
			System.out.print(tdto.getAddress() + "\n");

		}

	}

	// 즐겨찾기 삭제
	public void deletebookMark() {
		char ch;

		try {
			System.out.println("즐겨찾기에서 삭제하시겠습니까?[y/n]");
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

	// 푸드트럭 상세리스트
	/*
	 * public void foodTruckList(TruckUserDTO dto) { int ch; try {
	 * System.out.println("가게이름" + dto.getName() + " (전체평점 : " + dto.getScore() +
	 * ")"); for(MenuDTO menu : dto.menuList()) { //dto.menuList() 메뉴 리스트
	 * System.out.println("메뉴 이름 : "menu.getName() + "\t가격 : " + menu.getPrice() +
	 * "\t설명: " + menu.getDemons()); } System.out.println(dto.getStart() + "~" +
	 * dto.getClose()); if(dto.getbookPossible()) System.out.println("예약 가능합니다.");
	 * else System.out.println("예약 불가능합니다."); System.out.println("전화번호 :" +
	 * dto.getTel()); System.out.println("위치 : " + dto.getLocation());
	 * System.out.println("공지사항 : " + dto.getMenu()); //사용자 리뷰 //tb_review에서 트럭 이름으로
	 * 검색 후 날짜 순으로 order by 해서 5개 가져와서 Map<String:사용자, String: 사용자 리뷰> 으로 삽입
	 * 
	 * iterator 사용해서 map 출력 하기
	 * 
	 * 
	 * do { System.out.print("1.예약 2.즐겨찾기등록 3.후기등록 4.뒤로가기"); ch =
	 * Integer.parseInt(br.readLine()); }while(ch<1 || ch>4);
	 * 
	 * if(ch==4) return; switch (ch) { case 1: break; //예약하기 case 2: break; //즐겨찾기
	 * 등록 case 3: break; //후기 등록
	 * 
	 * 
	 * } } catch (Exception e) {
	 * 
	 * } }
	 */
}
