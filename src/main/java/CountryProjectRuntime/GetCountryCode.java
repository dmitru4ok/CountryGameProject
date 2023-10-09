package CountryProjectRuntime;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import mywsservice.ServiceImplementationService;
import com.google.gson.JsonObject;


@WebServlet("/getRandomCountry")
public class GetCountryCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public GetCountryCode() {}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		ServiceImplementationService client = new ServiceImplementationService();
		String result = client.getServiceImplementationPort().getRandomCountry();
		
		JsonObject dataToSend = new JsonObject();
		dataToSend.addProperty("countryCode", result);
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWriter().write(dataToSend.toString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		JsonObject dataToSend = new JsonObject();
		dataToSend.addProperty("errorMessage", "Post in not supported");
		response.setStatus(405);
		response.setContentType("application/json");
		response.getWriter().write(dataToSend.toString());
		
	}

}
