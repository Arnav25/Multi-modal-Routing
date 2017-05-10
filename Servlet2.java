import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.lang.*;
// Extend HttpServlet class
public class Servlet2 extends HttpServlet {
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException  {
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
	int a = Integer.parseInt(request.getParameter("a")) ;
	int b = Integer.parseInt(request.getParameter("b")) ;
        
	 Map<String ,List<Double>> busstoplatitudelongitude ;
	 busstoplatitudelongitude = new HashMap<String, List<Double>>() ;
	
		String val = "" ;
         		out.println(a+b) ;

		/*
		 BufferedReader br = new BufferedReader(new FileReader("word.text")) ; 
		 String line = "" ;


		  while ((line = br.readLine()) != null) {
  	      String parts[] = line.split(",");
  	      String name = parts[3] ;
  	      Double lon = Double.parseDouble(parts[2]) ;
  	      Double lat = Double.parseDouble(parts[1]) ;
  	      
		}
		*/	
		 try {
			InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("unqbusstopddf.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(input)) ;
		String line = "" ;
			while((line = br.readLine()) != null )
		{
			out.println(line) ;
		//	out.println("kadkush") ;
		}
          
      } catch(Exception e) {
         out.println(e);
      }
	try
	{
	 sample obj = new sample();
	String s = obj.display() ;
		out.println(s);
	}
	catch(Exception e)
	{
	out.println(e) ;
}
 
}
}
