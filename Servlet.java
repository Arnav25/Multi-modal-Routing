import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class Servlet extends HttpServlet {
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException  {
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
	double latitude = Double.parseDouble(request.getParameter("latitude")) ;
	double longitude = Double.parseDouble(request.getParameter("longitude")) ;
	String dest =  request.getParameter("destination") ;
	String option = request.getParameter("dropdown_option") ;
	String source = request.getParameter("source") ;
	
//	out.println(dest+" "+option) ;
//	out.println(latitude+" "+longitude) ;	

	
	if(option.equals("BusOnlyNetwork")==true)
	{
		try{
		if(source.equals("000")==true)
		{
		NearestBusStop obj1 = new NearestBusStop() ;
	   	obj1.setMap() ;
	String ans = obj1.nearestcalculate(latitude,longitude,dest) ;
		out.println(ans) ;
		}
		else
		{  BusOnlyNetwork ans = new BusOnlyNetwork();
            ans.setMap();

                out.println(ans.callingBiDijkstra(source,dest)) ;
		}
	
	}
	catch(Exception e)
	{
		out.println(e) ;
	}
	}
	
	 else 
        {

                try{
		if(source.equals("000")==true)
		{
                NearestHybridStop obj1 = new NearestHybridStop();
         obj1.setMap() ;
                String ans = obj1.nearestcalculate(latitude,longitude,dest) ;

        out.println(ans) ;
	}
	else
	{
		 HybridNetwork ans = new HybridNetwork();
            ans.setMap();

                out.println(ans.callingBiDijkstra(source,dest)) ;

	}
        }
        catch(Exception e)
        {
                out.println(e) ;
        }
        }

  }
}
