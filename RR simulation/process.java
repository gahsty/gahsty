package osproj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class process {
	static String address;
	int timequa;
	double switchs=0;
	String [] T= new String[10];
	static Map<Integer, processercreat> maps = new HashMap<>();
    readyq rdq = new readyq();
	public process(String address, int timequa){
		process.address=address;
		this.timequa=timequa;
	}
	public void runprocess()  {		
		 Clock clk = new Clock();
		 clk.reset();
		 rdq.reset();
		 maps.clear();
		 readfile();	
		 
		while(maps.isEmpty()!=true){ 
			
			int time = clk.gettime();
			for (Entry<Integer, processercreat> entry : maps.entrySet()) {
				 processercreat O =  (processercreat) entry.getValue();
				   if(time== O.getarrive()){
					   rdq.in(O);
				   }
				         }
		
			if(((clk.gettime())%timequa)==0){
				
				switchs++;
				processercreat o =  new processercreat(Integer.toString(rdq.reflectop().getpid())+","+Integer.toString(rdq.reflectop().getarrive())+","+Integer.toString(rdq.reflectop().getburst()));
				o.setfinishing(rdq.reflectop().getfinish());
				o.setrunning(rdq.reflectop().getrunning());
				rdq.out();
				System.out.println("program:"+o.getpid()+" is running");
				System.out.println("remain time is "+o.getrunning());
				System.out.println("");
			    o.refreshfinish(timequa);
			    o.refreshrunning(timequa);
			    
			    if(o.getrunning()<=0){	    	
			        o.setfinishing(time+timequa+o.getrunning());
			        maps.put(o.getpid(), o);
			    }
			    else{
			    	rdq.in(o);
			    	maps.put(o.getpid(), o);
			    	}		  		    
			}			
			if(rdq.getnumber()<=0)
				break;
				else
				clk.refresh();	
		}
		caclulate(maps);		
	}
	private void caclulate(Map<Integer, processercreat> maps2) {
		// TODO Auto-generated method stub
		double CPU_U=0,AWT=0,ATT=0;
		for (Entry<Integer, processercreat> entry : maps2.entrySet()) {
			 processercreat o =  (processercreat) entry.getValue();
			 CPU_U = CPU_U+o.getrunning();
			 ATT=ATT+(o.getfinish()-o.getarrive());
			 AWT=AWT+o.getburst();
			 			         }
		System.out.println("-----------OUT PUT----------");
		System.out.println("Context switch is "+(switchs-1));
		System.out.println("CPU utiliazation is "+(((switchs-1)*timequa)+CPU_U)/((switchs-1)*timequa)*100+"%");
		System.out.println("Average Turnaround time is "+ATT/maps.size());
		System.out.println("Average waiting time is "+(ATT-AWT)/maps.size());
		System.out.println("The thoughput is "+(double)maps2.size()/((switchs-1)*timequa)*100+"%");
	}
	public  static void readfile() {
		File file = new File(address);
		BufferedReader read =null;
		try{
			read = new BufferedReader(new FileReader(file));
			String T= null;
			read.readLine();
			while((T=read.readLine())!=null){
				processercreat p = new processercreat(T);
				maps.put(p.getpid(),p);
			}
			read.close();
		}catch(IOException e){
			e.getStackTrace();
		}		
	}	
}
