package semiProject1;

import java.io.IOException;

interface UserService {

	public boolean userLogin() throws IOException;
	public void userRegister() throws IOException;
	
	public void aroundSearch()throws IOException;
	public void kewordSearch()throws IOException;
	public void bookMark()throws IOException;
	public void reservationCheck()throws IOException;
	public void userInfo()throws IOException;
	public boolean logout()throws IOException;
	
	
}
