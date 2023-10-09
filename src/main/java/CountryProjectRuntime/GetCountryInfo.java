package CountryProjectRuntime;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import com.google.gson.JsonObject;

import org.oorsprong.websamples.TCountryInfo;
import org.oorsprong.websamples_countryinfo.CountryInfoService;

@WebServlet("/getCountryInfo")
public class GetCountryInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetCountryInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject dataToSend = new JsonObject();
		response.setContentType("application/json");
		String countryCode = request.getParameter("countryCode").toString();
		if (countryCode == "" || countryCode == null) {
			response.setStatus(400);
			dataToSend.addProperty("errorMessage", "Invalid country code!");
		} else {
			CountryInfoService tst = new CountryInfoService();
			TCountryInfo info;
			try {
				info = tst.getCountryInfoServiceSoap().fullCountryInfo(countryCode);
				if (info.getSCapitalCity() == "" && info.getSContinentCode() == "") {
					dataToSend.addProperty("errorMessage", info.getSName());
					response.setStatus(404);
				} else {
					dataToSend.addProperty("capitalCity", info.getSCapitalCity());
					dataToSend.addProperty("continentCode", info.getSContinentCode());
					dataToSend.addProperty("countryFlag", info.getSCountryFlag());
					dataToSend.addProperty("currencyISOCode", info.getSCurrencyISOCode());
					dataToSend.addProperty("ISOCode", info.getSISOCode());
					dataToSend.addProperty("name", info.getSName());
					dataToSend.addProperty("phoneCode", info.getSPhoneCode());
					response.setStatus(200);
				}				
			} catch (Exception e) {
				dataToSend.addProperty("errorMessage", "ServerError...");
				response.setStatus(500);
			}
		}
		response.getWriter().write(dataToSend.toString());
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject dataToSend = new JsonObject();
		dataToSend.addProperty("errorMessage", "Post in not supported");
		response.setStatus(405);
		response.setContentType("application/json");
		response.getWriter().write(dataToSend.toString());
	}

}
