import java.io.*;
import java.util.*;
import java.lang.*;


class HybridNetwork 
{
 static int v ;
   private static Node[] nodes;
   static Map<String,Integer> map ;
   static Map<Integer,String> map2 ;
   static String output ;
   static String finalresult ;
   static Map<String,String> stationlatlon ;
    public static void setMap() throws IOException {
         map = new HashMap<String, Integer>();
         map2 = new HashMap<Integer, String>() ;
	   InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("unqbusstopddf.csv");

        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        String line = "";
             v = 0 ;
        while ((line = in.readLine()) != null) {
            String parts[] = line.split(",");
             String s1 = parts[1] ;
               int len = s1.length() ;
             String query = s1.substring(0,len) ;
          
      //    System.out.println(query) ;

            map.put(query, v);
            map2.put(v,query) ;
    //        System.out.println(query+"  "+v) ;
       v++ ;
        }
        in.close();

          boolean check[][] = new boolean[v][v] ;
     double latitude[] = new double[v] ;
     double longitude[] = new double[v] ;
     int V = v ;
	stationlatlon = new HashMap<String,String>() ;

	   InputStream input2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("MetroStation.csv");

		finalresult = "" ;
    BufferedReader inn = new BufferedReader(new InputStreamReader(input2)) ; 
    line = "" ;
    Map<Integer,String> route = new HashMap<Integer,String>() ;

     while ((line = inn.readLine()) != null) {
            String parts[] = line.split(",");
             String s1 = parts[3] ;
               int len = s1.length() ;
             String query = s1.substring(0,len) ;
          
      //    System.out.println(query) ;

            map.put(query, v);
            map2.put(v,query) ;
    //        System.out.println(query+"  "+v) ;
       v++ ;
        }
        inn.close();



    nodes = new Node[v]; 
    String prev = "" ;
    int prevstop = 5500 ; 

     Double lat1,lon1,lat2,lon2,latDistance,lonDistance,a,c ;
    final int R = 6371;
    lat1 = 0.0 ;
    lat2 = 0.0 ;
    lon1 = 0.0 ;
    lon2  = 0.0 ;
  
	   InputStream input3 = Thread.currentThread().getContextClassLoader().getResourceAsStream("buslatlon.csv");

  BufferedReader br = new BufferedReader(new InputStreamReader(input3)) ; 
  line = "" ;

  while ((line = br.readLine()) != null) {
  	String parts[] = line.split(",");
  	String next = parts[4] ;
  	String s1 = parts[3] ;
    int len = s1.length() ;
    String query = s1.substring(0,len) ;
    int nextstop = map.get(query) ;
    String latlon = parts[1]+" "+parts[2] ;
    stationlatlon.put(query,latlon) ;
 double l1 = Double.parseDouble(parts[1]);
    double l2 = Double.parseDouble(parts[2]);
    latitude[nextstop] = l1 ;
    longitude[nextstop] = l2 ;


 if((prevstop<v)&&(check[prevstop][nextstop]==true))
 {
    Node previous = nodes[prevstop] ;
    Node nxt = nodes[nextstop] ;
	previous.setroute(next) ;
	nxt.setroute(next) ;
   LinkedList<Edge> edges = previous.getEdges();

   for(int i=0;i<edges.size();i++)
   {
    if(edges.get(i).getTo()==nxt)
        edges.get(i).setroute(next);

   }
 edges = nxt.getEdges();
   for(int i=0;i<edges.size();i++)
   {
    if(edges.get(i).getTo()==previous)
        edges.get(i).setroute(next);

   }

 }




  	 if((next.equals(prev))&&(check[prevstop][nextstop]==false))
  	 {
           check[prevstop][nextstop] = true ;
           check[nextstop][prevstop] = true ;

          lat2 = Double.parseDouble(parts[1]);
          lon2 = Double.parseDouble(parts[2]);

  	 	   latDistance = Math.toRadians(lat2-lat1);
         lonDistance = Math.toRadians(lon2-lon1);
         a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
         c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;
        Node s = new Node(prevstop) ;


        if (nodes[prevstop] == null)
    				nodes[prevstop] = s;
    			else
    				s = nodes[prevstop];
    			Node t = new Node(nextstop);
    			if (nodes[nextstop] == null)
    				nodes[nextstop] = t;
    			else
    				t = nodes[nextstop];
    			LinkedList<Edge> edges = nodes[prevstop].getEdges();
    			LinkedList<Edge> edges2 = nodes[nextstop].getEdges();
    			edges.add(new Edge(s,t,distance,next));
    			edges2.add(new Edge(t,s,distance,next)) ;

	Node previous = nodes[prevstop] ;
           previous.setroute(next) ;
       Node nxt = nodes[nextstop] ;
      nxt.setroute(next) ;

       lat1 = lat2 ;
       lon1 = lon2 ;
       prevstop = nextstop ;
}
else
{
	 lat1 = Double.parseDouble(parts[1]);
  lon1 = Double.parseDouble(parts[2]);
  prev = next ;
  prevstop = nextstop ;
}
    

  }



   InputStream input4 = Thread.currentThread().getContextClassLoader().getResourceAsStream("MetroStation.csv");

 BufferedReader inp = new BufferedReader(new InputStreamReader(input4)) ; 
    line = "" ;
     prev = "" ;
     prevstop = 5500 ; 
     lat1 = 0.0 ;
    lat2 = 0.0 ;
    lon1 = 0.0 ;
    lon2  = 0.0 ;
    long variable = 9000000 ;
     
while ((line = inp.readLine()) != null) {
    String parts[] = line.split(",");
    String next = parts[4] ;
    String s1 = parts[3] ;
    int len = s1.length() ;
    String query = s1.substring(0,len) ;
    int nextstop = map.get(query) ;
   String latlon = parts[1]+" "+parts[2] ;
   stationlatlon.put(query,latlon) ; 
    
     if((next.equals(prev)))
     {
           

          lat2 = Double.parseDouble(parts[1]);
          lon2 = Double.parseDouble(parts[2]);


           latDistance = Math.toRadians(lat2-lat1);
         lonDistance = Math.toRadians(lon2-lon1);
         a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
         c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;
        distance = distance/4 ;
        Node s = new Node(prevstop) ;


        if (nodes[prevstop] == null)
                    nodes[prevstop] = s;
                else
                    s = nodes[prevstop];
                Node t = new Node(nextstop);
                if (nodes[nextstop] == null)
                    nodes[nextstop] = t;
                else
                    t = nodes[nextstop];
                LinkedList<Edge> edges = nodes[prevstop].getEdges();
                LinkedList<Edge> edges2 = nodes[nextstop].getEdges();
                edges.add(new Edge(s,t,distance,next));
                edges2.add(new Edge(t,s,distance,next)) ;

	Node previous = nodes[prevstop] ;
           previous.setroute(next) ;
       Node nxt = nodes[nextstop] ;
      nxt.setroute(next) ;


       lat1 = lat2 ;
       lon1 = lon2 ;
       prevstop = nextstop ;
}
else
{
     lat1 = Double.parseDouble(parts[1]);
  lon1 = Double.parseDouble(parts[2]);
  prev = next ;
  prevstop = nextstop ;
}



for(int i=0;i<V;i++)
{
    lat2 = latitude[i] ;
lon2 = longitude[i] ;
nextstop = i ;

           latDistance = Math.toRadians(lat2-lat1);
         lonDistance = Math.toRadians(lon2-lon1);
         a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
         c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;
          
          if(distance<=0.20)
        	distance = distance ;
      
       else if(distance<=0.4)
        distance = distance*2 ;
	
	else
	continue;

        Node s = new Node(prevstop) ;


        if (nodes[prevstop] == null)
                    nodes[prevstop] = s;
                else
                    s = nodes[prevstop];
                Node t = new Node(nextstop);
                if (nodes[nextstop] == null)
                    nodes[nextstop] = t;
                else
                    t = nodes[nextstop];
                LinkedList<Edge> edges = nodes[prevstop].getEdges();
                LinkedList<Edge> edges2 = nodes[nextstop].getEdges();
                edges.add(new Edge(s,t,distance,"walk"));
                edges2.add(new Edge(t,s,distance,"walk")) ;

Node previous = nodes[prevstop] ;
	String temporary =  Long.toString(variable) ;
           previous.setroute(temporary) ;
       Node nxt = nodes[nextstop] ;
      nxt.setroute(temporary) ;
//	System.out.println(prevstop+" "+nextstop+" "+variable);
	variable++ ;
       
}

    

  }

 

}


public String callingBiDijkstra(String s1,String s2)

{


//  BufferedReader brr = new BufferedReader(new InputStreamReader(System.in)) ;

 //System.out.println("Enter your source") ;
//String s1 = brr.readLine() ; 
//System.out.println("Enter your destination") ;
//String s2 = brr.readLine()  ;
int source = map.get(s1) ;
int stop = map.get(s2) ;

System.out.println("Bidirectional Dijkstra Starting ") ;
 output = ""  ;
String answer = "" ;


biDijkstra(nodes[source], nodes[stop]);

String array[] = output.split(" ") ;
int len = array.length ;
int arraylen = len/2 ;
int arr[] = new int[arraylen] ;
int s = 0 ;
for(int i=1;i<len;i+=2)
{
    arr[s++] = Integer.parseInt(array[i]) ;
}

int u = 0 ;
int v = 0 ;
int counter = 0 ;
int array2[] = new int[arraylen] ;
array2[0] = arr[0] ;
String routes[] = new String[arraylen] ;
//System.out.println("Here1") ;
int xyz = 0 ;
for( u=0;u<arraylen-1;)
 {
//      System.out.println(u+" "+v+" "+"Here2") ;

        for(v=arraylen-1;v>=0;v--)
        {
        Node first = nodes[arr[u]] ;
        Node last = nodes[arr[v]] ;
        HashSet<String> fhash = new HashSet(first.route) ;  
         HashSet<String> lhash =new HashSet(last.route) ;
        HashSet<String> intersection = new HashSet(fhash) ;
        intersection.retainAll(lhash) ;
//      System.out.println("v value "+v) ;

        if(intersection.size()!=0)
        {

        array2[counter+1] = arr[v] ;
        routes[counter] = "" ;
        routes[counter]+=intersection ;

        counter++ ;
u = v ;
        break ;
        }
}
}
String temp="" ;String finalans = stationlatlon.get(map2.get(array2[0]))+"\n" ; 
for(int i=0;i<counter;i++)
{
 String first = map2.get(array2[i]);
 String last = map2.get(array2[i+1]);
String templatlon = stationlatlon.get(first) ;
String templatlon2 = stationlatlon.get(last) ;
//finalans+=templatlon+"\n";
finalans+=templatlon2+"\n";
temp+="Travel from "+first+" to "+last+"\n";
if(routes[i].charAt(1)!='9')
temp+="Routes present Here"+routes[i]+"\n";

else
temp+="You have to walk"+"\n";

}
finalans+="0"+"\n";
finalans+=temp ;
return finalans ;


/*
int iterate = 0 ;
HashSet<String> res1 = new HashSet<String>() ;
//res1.add("0") ;
HashSet<String> res2 =new HashSet<String>();
//res2.add("0") ;
boolean flag = false ;

while(iterate!=arraylen-2)
{


    Node ss = nodes[arr[iterate]];
Node destination = nodes[arr[iterate+1]] ;
Node destination2 = nodes[arr[iterate+2]] ;

LinkedList<Edge> e=new LinkedList<Edge>();

if(flag==false)
{
e=ss.getEdges();
//System.out.println(e.size());

for(int i=0;i<e.size();i++)
{
//System.out.println("here2s");

if(e.get(i).getTo()==destination)
{
   
     res1 = e.get(i).getroutelist();
  //res1.add("0") ;
     break;
}
}
}


if(flag==false)
{
    String result = map2.get(arr[iterate]) ;
	 String templatlon = stationlatlon.get(result) ;
    finalresult+=templatlon+"\n" ;
    answer+=" Take a bus from  " ;
    answer+=" "+result; ;
    flag = true ;

}



e = destination.getEdges() ;

for(int i=0;i<e.size();i++)
{
    if(e.get(i).getTo()==destination2)
{
    
     res2 = e.get(i).getroutelist();
  // res1.add("0") ;
     break;
}
}

HashSet<String> intersection = new HashSet<String>(res1); // use the copy constructor
intersection.retainAll(res2);

if((intersection.size()==0)||((intersection.size()!=00)&&(iterate==arraylen-3)))
   { 
    flag = false ;
    answer+="  Routes present Here ";
    answer+=res1 ;
    answer+="  Stop here " ;
    if(iterate!=arraylen-3)
    {
    String result = map2.get(arr[iterate+1]) ;
    answer+=" "+result;
}
   }

else if(iterate==arraylen-3)
{
    String result = map2.get(arr[iterate+1]) ;
    answer+=" Take bus from  ";
    answer+=result ;
	 String templatlon = stationlatlon.get(result) ;
    finalresult+=templatlon+"\n" ;

    answer+=" Routes present  ";
    answer+=res2 ;
}


else
{
   res1 = intersection ;
}

iterate++ ;

}

String result = map2.get(arr[arraylen-1]) ;
answer+=" "+result; 
String templatlon = stationlatlon.get(result) ;
    finalresult+=templatlon+"\n";
   finalresult+="0"+"\n" ;
    finalresult+=answer;


return finalresult ;
//System.out.println(output) ;
*/


}


 public static void biDijkstra(Node start, Node end) {
    	
    	long startTime = System.currentTimeMillis();
    	
       	int qcount = 0; //keeps track of total number of nodes enqueued
    	//set up two queues
       	//queue 1 will correspond to each node's info object 1
       	//queue 2 will correspond to each node's info object 2
    	Info si = start.getD1();
    	Info ei = end.getD2();
    	si.setDist(0);
    	ei.setDist(0);
    	PQ q1 = new PQ(nodes.length);
    	PQ q2 = new PQ(nodes.length);	
    	q1.add(si);
    	q2.add(ei);
    	si.setStatus("enqueued");
    	qcount++;
    	ei.setStatus("enquered");
    	qcount++;
    	
    	PQ[] queues = new PQ[2];
    	queues[0] = q1;
    	queues[1] = q2;
    	int qpointer = 0; //keeps track of which queue's turn it is
    	
    	boolean intersect = false; //keeps track of whether the two Dijkstras have met
    	Node intersection = null;
    	
    	//toggle between two instances of Dijkstra
        while (!intersect) {
        	PQ q = queues[qpointer];
            Info vi = q.poll();
            Node v = vi.getNode();
        	LinkedList<Edge> edges = v.getEdges();
            for (Edge e: edges){
                    Node w = e.getTo();
                    Info wi = null;
                    if (qpointer == 0)
                    	wi = w.getD1(); 
                    else
                    	wi = w.getD2();
                    qcount+=checkNeighbor(v, vi, w, wi, e, q);
            }
            vi.setStatus("done");
            if (v.done()) { //if the node is done from both queues, stop
            	intersect = true;   
            	intersection = v;
            }
            qpointer = (qpointer + 1) % 2;
        }
        //calculate potential path using first node done from both sides
        Info a = intersection.getD1();
        Info b = intersection.getD2();
        double path = a.getDist() + b.getDist();
        
        //examine fringe nodes left in each queue
        ArrayList<Info> fringe1 = new ArrayList<Info>();
        ArrayList<Info> fringe2 = new ArrayList<Info>();
        
        while (!q1.isEmpty()) 
        	fringe1.add(q1.poll());
        
        while (!q1.isEmpty()) 
        	fringe2.add(q1.poll());

        //if an enqueued node offers a new best path, update path
        for (Info ci: fringe1) {
        	Node c = ci.getNode();
			double possiblePath = ci.getDist() + c.getD2().getDist();
			if (possiblePath < path) {
				path = possiblePath;
				intersection = c;
			}
        }

        for (Info ci: fringe2) {
        	Node c = ci.getNode();
			double possiblePath = ci.getDist() + c.getD1().getDist();
			if (possiblePath < path) {
				path = possiblePath;
				intersection = c;
			}
        }
        //print results
        System.out.println("Shortest path found!");
        long finishTime = System.currentTimeMillis();
        System.out.println("Elapsed time in millisecs: " + (finishTime - startTime) );
        System.out.println("Length from " + start+ " to " +end+ ": " + path);
        System.out.println("Number of nodes enqueued: " + qcount);
        System.out.println("The path is:");
        printForwardVia(intersection, start);
        printReverseVia(intersection.getD2().getVia(),end);

    }





    public static int checkNeighbor(Node v, Info vi, Node w, Info wi, Edge e, PQ q) {
    	//examines neighboring node
    	//if unvisited, enqueue; if enqueued, potentially update dist
        if (wi.getStatus().equals("unvisited")) {
            wi.setStatus("enqueued");
            wi.setDist(vi.getDist() + e.getLength());
            wi.setVia(v);
            q.add(wi);
            return 1;
        }
        else if (wi.getStatus().equals("enqueued")) {
        	double newDist=vi.getDist() + e.getLength();
        	if(newDist<wi.getDist()) {
        		wi.setDist(newDist);
        		wi.setVia(v);
                q.decreaseKey(wi);
        	} 
        }
		return 0;   	
    }


    public static void printForwardVia(Node start, Node end) {
    	//recursively print path created using forward Dijkstra
    	Info nexti = start.getD1();
    	
    	if (nexti == null || start == end) 
    		output+=start+" ";//System.out.println(start);
    	else {
    		printForwardVia(nexti.getVia(),end);
    		output+=start+" ";//System.out.println(start);
    	}
    }
    
    public static void printReverseVia(Node start, Node end) {
    	//recursively print path created using backwards Dijkstra
    	Info nexti = start.getD2();
    	if (nexti == null || start == end) 
    		output+=start+" ";//System.out.println(start);
    	else {
    		output+=start+" ";//System.out.println(start);
    		printReverseVia(nexti.getVia(),end);
    	}
    }



}



