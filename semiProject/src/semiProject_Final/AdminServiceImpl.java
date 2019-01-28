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

public class AdminServiceImpl implements AdminService {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private AdminDAO dao = new AdminDAO();
	private AdminDTO adto;
	
	@Override
	public boolean logIn() {
		try {
			String id;
			String pw;
			
			System.out.println("아이디를 입력해주세요.");
			id = br.readLine();
			
			System.out.println("비밀번호를 입력해주세요.");
			pw = br.readLine();
			
			adto = dao.loginUser(id, pw);
			
			if (adto != null) {
				System.out.println("로그인 되었습니다.");
				return true;
			} else {
				System.out.println("로그인에 실패했습니다.");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean logOut() {
		char ch;

		try {
			System.out.println("로그아웃하시겠습니까?[y/n]");
			ch = br.readLine().charAt(0);

			if (ch == 'Y' || ch == 'y') {
				System.out.println("로그아웃 되셨습니다.");
				adto = null;
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
	@Override
	public void listUser() {
		try {
			List<UserDTO> list = dao.searchUser();
			//List<UserDTO> list = null;
			
			if (list.isEmpty()) {
				System.out.println("등록된 유저 계정이 없습니다.");
				return;
			}
			
			System.out.println("유저번호\t아이디\t전화번호\t차단여부");
			for (UserDTO dto : list) {		
				System.out.printf("%s\t %s\t %s\t %s\n", dto.getUserNum(), dto.getId(), dto.getTel(), dto.getBlacklist());
			}
		} catch (Exception e) {
			
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
	public void listFoodTruck() {
		try {
			List<UserDTO> list = dao.searchFoodTruck();
			
			if (list.isEmpty()) {
				System.out.println("등록된 점주 계정이 없습니다.");
				return;
			}
			
			System.out.println("점주번호\t아이디\t전화번호\t차단여부");
			for (UserDTO dto : list) {		
				System.out.printf("%s\t %s\t %s\t %s\n", dto.getUserNum(), dto.getId(), dto.getTel(), dto.getBlacklist());
			}
		} catch (Exception e) {
			
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
	public void setBlackUser() {
		System.out.println("유저 블랙리스트 설정");
		
		try {
			List<UserDTO> list = dao.searchUser();
			
			if (list.isEmpty()) {
				System.out.println("등록된 유저 계정이 없습니다.");
				return;
			}
			
			System.out.println("유저번호\t아이디\t전화번호\t차단여부");
			for (UserDTO dto : list) {		
				System.out.printf("%s\t %s\t %s\t %s\n", dto.getUserNum(), dto.getId(), dto.getTel(), dto.getBlacklist());
			}
			
			System.out.println("차단여부를 수정할 아이디를 입력해주세요.");
			String id = br.readLine();
			
			UserDTO dto = new UserDTO();
			
			dto = dao.searchBlackUser(id);
			
			if (dto == null) {
				System.out.println("등록된 아이디가 없습니다.");
				return;
			}
			
			System.out.println("유저번호\t아이디\t전화번호\t차단여부");
			System.out.printf("%s\t %s\t %s\t %s\n", dto.getUserNum(), dto.getId(), dto.getTel(), dto.getBlacklist());
			
			System.out.println("차단여부를 입력해주세요.[y/n]");
			int ch = br.readLine().charAt(0);
			
			if (ch == 'y' || ch == 'Y') {
				
			} else {
				System.out.println("차단여부 수정을 취소했습니다.");
				return;
			}
		} catch (Exception e) {
			
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
	public void setBlackFoodTruck() {
		System.out.println("점주 블랙리스트 설정");
		
	}
}
