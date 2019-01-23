package semiProject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserServiceImpl implements UserService{

	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public boolean userLogin() throws IOException{
		System.out.println("선택 1.로그인 성공 2.로그인 실패");
		int ch = Integer.parseInt(br.readLine());
		if(ch == 1)
		{
			System.out.println("로그인 성공했다!");
			return true;
		}			
		else
		{
			System.out.println("로그인 실패했다!");
			return false;
		}
			
	}

	@Override
	public void userRegister() throws IOException{
		System.out.println("선택 1.회원가입 성공 2.회원가입 실패");
		int ch = Integer.parseInt(br.readLine());
		if(ch == 1)
		{
			System.out.println("회원가입 성공했다!");
			
		}			
		else
		{
			System.out.println("회원가입 실패했다!");			
		}
	}

	@Override
	public void aroundSearch() throws IOException {
		System.out.println("주소입력: "); //주소 입력받는 것만 따로 메소드로 빼야함.
		System.out.println("상호명 위치 평점 리스트 출력 (평점 위치 순)");
		
		System.out.println("0번이면 뒤로가기");
		System.out.println("선택 => ");
		int a = Integer.parseInt(br.readLine()); //선택하기.
		
		if(a==0)
			return; //다시 userServiceMenu로.
		
		System.out.println("상세보기");	
		
	

				
	}

	@Override
	public void kewordSearch() throws IOException {
		System.out.println("키워드 검색:(예시) 0이면 뒤로가기.");
		System.out.println("입력 => ");
		String keyword = br.readLine(); //선택하기.
		
		if(keyword.equals("0"))
			return; //다시 userServiceMenu로.
		
		System.out.println("keyword");	
		
	}

	@Override
	public void bookMark() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reservationCheck() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void userInfo() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean logout() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
