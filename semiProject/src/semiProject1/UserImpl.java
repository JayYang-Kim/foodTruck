package semiProject1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserImpl implements User {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//주변검색
	@Override
	public void searchAround() {
		try {
			System.out.print("주소 입력 : ");
			String addr = br.readLine();
			/*
			 * 1. xml에서 x,y값 추출 받아서
			 * 2. 가까운 푸드트럭 가져오기(푸드트럭 주소로)
			 * 2-1. 근처 정보 가져오는 xml이용? or DB에 있는 푸드트럭 주소로 가져오는 방법?
			 * 2-2. 근처에 있는 푸드드럭 정보 받아온다.(ArrayList<FoodTruckDTO> 5개)
			 * 
			 * 
			 * 
			 * */
		} catch (Exception e) {
			
		}
		
	}

	//키워드 검색
	@Override
	public void searchKeyWord() {
		
	}

	//즐겨찾기
	@Override
	public void bookMark() {
	
		
	}

	//예약확인
	@Override
	public void confirmBook() {
		
		
	}

	
	
	
	
	
	
	
//-----------------------------------------------------------------------	
	@Override
	public void userInfo() {
		
		
	}
	
	@Override
	public void signUP() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logIn() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void logOut() {
		
		
	}

}
