import java.io.*;
import java.util.*;

class Buscaller
{
public static void main(String args[])throws Exception
{
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
//	double lat = Double.parseDouble(br.readLine()) ;
//	double lon = Double.parseDouble(br.readLine()) ;
//	String destination = br.readLine() ;
	NearestBusStop obj = new NearestBusStop() ;
	obj.setMap() ;
	String ans = obj.nearestcalculate(
12.8881949882659,77.5819944897381,"kempegowda bus station") ;
	System.out.println(ans) ;
}
}
