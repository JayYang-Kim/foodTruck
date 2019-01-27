package semiProject_Final;

interface TruckService {

	public boolean login();                //로그인
	public boolean logOut();               //로그아웃
	public void register();                //회원가입
	
	public void showFoodMenu();            //음식메뉴 출력
	public void insertMenu();              //음식메뉴 추가
	public void updateMenu();              //음식메뉴 수정
	public void deleteMenu();              //음식메뉴 삭제
	
	public void updateReservation();       //예약가능여부 수정
	public void updateMemo();              //홍보 게시판 수정

	public void showReview();              //리뷰 보기
	public void open();                    //개점
	public void move();                    //이동
	public void close();                   //마감	
	public void checkReservation();        //예약 확인	
	
	public void showUserInfo();            //회원정보 출력
	public void updateUserInfo();          //회원정보 수정
	public boolean withdraw();             //회원탈퇴
	
}
