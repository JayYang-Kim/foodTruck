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
	private UserDTO udto; // 로그인된 정보 저장용 dto

	@Override
	public void signUP() {
		System.out.println("[사용자 회원가입]");
		
		try {
			UserDTO dto = new UserDTO();
			Random rnd = new Random();
			StringBuffer sb = new StringBuffer();
			
			// UserNum 자동 생성
			do {
				for (int i = 0; i < 5; i++) {
					sb.append(Integer.toString(rnd.nextInt(10))); 
				}
			} while(dao.checkUserNum(sb.toString()));
			
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

	@Override
	public boolean logIn() {
		String id;
		String pwd;
		boolean status = false;
		
		System.out.println("[로그인]");
		try {
			System.out.println("아이디를 입력해주세요.");
			id = br.readLine();

			System.out.println("비밀번호를 입력해주세요.");
			pwd = br.readLine();

			udto = dao.loginUser(id, pwd);

			if (udto != null) {
				System.out.println("로그인 되었습니다.");
				status = true;
			} else {
				System.out.println("로그인에 실패했습니다.");
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
			System.out.println("로그아웃하시겠습니까?[y/n]");
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
			
			do {
				System.out.println("1.뒤로가기");
				ch = Integer.parseInt(br.readLine());
				
				return;
			} while(ch != 1);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
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
				return;
			}
			
			do {
				System.out.println("1.뒤로가기");
				ch = Integer.parseInt(br.readLine());
				
				return;
			} while(ch != 1);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
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
			
			do {
				System.out.println("1.뒤로가기");
				ch = Integer.parseInt(br.readLine());
				
				return;
			} while(ch != 1);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public void deleteUser() {
		System.out.println("회원 탈퇴");
		
		try {
			int ch;
			
			String userNum = udto.getUserNum();
			
			int result = dao.deleteUser(userNum);
			
			if (result == 1) {
				System.out.println("회원탈퇴 완료");
				udto = null;
			} else {
				System.out.println("회원탈퇴 실패");
				return;
			}
			
			do {
				System.out.println("1.뒤로가기");
				ch = Integer.parseInt(br.readLine());
				
				return;
			} while(ch != 1);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
}
