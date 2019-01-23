package semiProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class UserImpl implements User {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private UserDAO dao = new UserDAO();

	@Override
	public void signUP() {
		System.out.println("[사용자 회원가입]");
		
		try {
			UserDTO dto = new UserDTO();
			Random rnd = new Random();
			StringBuffer sb = new StringBuffer();
			
			String userNum = null;
			String userCode = "USER";
			
			System.out.println("[필수] 아이디를 입력해주세요.");
			dto.setId(br.readLine());
			
			System.out.println("[필수] 비밀번호를 입력해주세요.");
			dto.setPassword(br.readLine());
			
			System.out.println("전화번호를 입력해주세요.");
			dto.setTel(br.readLine());
			
			// UserNum 자동 생성
			for (int i = 0; i < 5; i++) {
				sb.append(Integer.toString(rnd.nextInt(10))); 
			}
			
			userNum = sb.toString();
			
			int result = dao.insertEmployee(dto, userNum, userCode);
			
			if (result == 1) {
				System.out.println(dto.getId() + "(님) 회원가입을 축하합니다.");
			} else {
				System.out.println("회원가입에 실패했습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void logIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	
}
