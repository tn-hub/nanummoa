/**
 * 
 */
package com.nanum.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Administrator
 *
 */
public class NaverGeo {
	/** 클라이언트 아이디*/
	String clientId = "nqehifvf28";
	/** 클라이언트 비밀키*/
	String clientSecret = "NiNIDkoZhPjcn3jkDOfCoo7J0CpREsa834IFg9rm";
	
	/**
	 * json 으로 검색정보를 반환
	 * @param s 검색할 주소
	 */
	public void getgeo(String s) {
		StringBuffer response = new StringBuffer();
		try {
            String addr = URLEncoder.encode(s, "UTF-8");  
            String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;//네이버로 받아온 JSON데이터를 저장
		JSONArray jsonArray= null;//addresses 내부의 JSON 배열을 저장
		JSONObject jsonObject2 = null;//반복문을 통해서 array내부의 데이터를 하나씩 저장
		String x = null;
		String y = null;
		try {
			jsonObject=(JSONObject) parser.parse(response.toString());
			jsonArray = (JSONArray)jsonObject.get("addresses");
			for(Object object: jsonArray) {
				jsonObject2= (JSONObject)object;
				if(jsonObject2.get("x")!=null) {
					x=jsonObject2.get("x").toString();//위도 저장
				}
				if(jsonObject2.get("y")!=null) {
					y=jsonObject2.get("y").toString();//경도 저장
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("위도(y) : " + y);
		System.out.println("경도(x) : " + x);
	}
	
	public static void main(String[] args) {
		NaverGeo ngeo = new NaverGeo();
		ngeo.getgeo("구월말로91번길 18-4");

	}
}
