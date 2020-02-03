package osproj;

public class processercreat {
	int pid,arrive,burst,finish,running=0;
	String [] info ;
	public processercreat(String T){
		info= T.split(",");
		pid= Integer.parseInt(info[0]);
		arrive=Integer.parseInt(info[1]);
		burst = Integer.parseInt(info[2]);
		running=burst;
	}
	public int getpid(){
		return pid;
	}
	public int getarrive(){
		return arrive;
	}
	public int getburst(){
		return burst;
	}
	public int getfinish(){
		return finish;
	}
	public void refreshfinish(int i){
		finish = finish+i;
	}
	public int getrunning(){
		return running;
	}

	public void refreshrunning(int i){
		running = running-i;
	}
	public void setrunning(int i){
		running= i;
	}
	public void setfinishing(int i){
		finish= i;
	}
	
	
}
