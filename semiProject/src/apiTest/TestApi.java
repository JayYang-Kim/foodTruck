package apiTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TestApi {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br2 = null;

		try {
			
			System.out.print("주소 입력:  ");
			String addr = URLEncoder.encode(br.readLine(), "UTF-8") ;
			String apiurl = "https://dapi.kakao.com/v2/local/search/address.xml?query=" + addr;
			String key = "5SX2XMaPX0iEUalUDJHjn2Dn_iqcXoHO8UdGMQo8BhkAAAFoekf-FQ";
			URL url = new URL(apiurl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization","KakaoAK " + key);
			
			System.out.println("1");
			int responseCode = con.getResponseCode();
			System.out.println("2");
			if(responseCode==200) {
				//정상호출
				System.out.println("3");
				br2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
			}
			else {
				//에러발생
				System.out.println("4");
				br2 = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			System.out.println("5");
			String inputLine;
			StringBuffer response = new StringBuffer();
	        while ((inputLine = br2.readLine()) != null) {
	        	System.out.println("6");
	             response.append(inputLine);
	        }
	        br.close();
	        System.out.println(response.toString());

			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
