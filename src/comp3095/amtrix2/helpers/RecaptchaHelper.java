package comp3095.amtrix2.helpers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public final class RecaptchaHelper {
	private RecaptchaHelper() { }
	
	public static final boolean Enabled = false;
	
	private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	private static final String SECRET_KEY = "6Ld2DXcUAAAAAJ0YDaWNZroNc0zDJQE1hrcjK3Qy";
	
	public static boolean verifyCaptcha(HttpServletRequest request) {
		if (!Enabled) return true;
		String captcha = request.getParameter("g-recaptcha-response");
		if (captcha == null || captcha.isEmpty())
			return false;
		
		try {
			URL url = new URL(VERIFY_URL);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setRequestMethod("POST");

			String postParams = "secret=" + SECRET_KEY + "&response=" + captcha;
			conn.setDoOutput(true);
			
			DataOutputStream output = new DataOutputStream(conn.getOutputStream());
			output.writeBytes(postParams);
			output.flush();
			output.close();
			
			BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer buf = new StringBuffer();
			String inputLine = null;
			while ((inputLine = input.readLine()) != null) {
				buf.append(inputLine);
			}
			
			String recaptchaResponse = buf.toString();
			JSONObject response = JSON.parseObject(recaptchaResponse);
			return (Boolean)response.get("success");
		} catch (Exception ex) { }
		
		return false;
	}

}
