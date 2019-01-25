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
			for (int i = 0; i < 5; i++) {
				sb.append(Integer.toString(rnd.nextInt(10))); 
			}
			
			//dto.setUserNum(sb.toString());
			dto.setUserNum("12022");
			
			System.out.println("[필수] 아이디를 입력해주세요.");
			dto.setId(br.readLine());
			
			System.out.println("[필수] 비밀번호를 입력해주세요.");
			dto.setPassword(br.readLine());
			
			System.out.println("전화번호를 입력해주세요.");
			dto.setTel(br.readLine());
			
			String userCode = "USER";
			
			try {
				int result = dao.insertEmployee(dto, userCode);
				
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
			
			udto = dao.searchMyInfo(udto);
			
			if (udto == null) {
				System.out.println("==================================================");
				System.out.println("[Error] 등록된 아이디가 없습니다.");
				System.out.println("==================================================");
				return;
			}
			
			System.out.println("아이디 : " + udto.getId());
			System.out.println("전화번호 : " + udto.getTel());
			System.out.println("포인트 : " + udto.getPoint());
			
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
