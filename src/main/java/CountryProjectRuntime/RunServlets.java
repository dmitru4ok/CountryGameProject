package CountryProjectRuntime;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;



public class RunServlets {

	public static void main(String[] args) throws Exception {
		
		ServletHandler handler = new ServletHandler();
		handler.addServletWithMapping(GetCountryCode.class, "/getRandomCountry");
		handler.addServletWithMapping(GetCountryInfo.class, "/getCountryInfo");
		
		Integer port = Integer.valueOf(System.getenv("PORT"));
		Server server = new Server(port);
		server.setHandler(handler);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			server.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}	
	}

}

