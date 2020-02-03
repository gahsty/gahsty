package osproj;

public class Clock {

	public int time;
	
	public Clock(){
	}
	public void  refresh (){
		time =time +1;
	}
	public int gettime(){
		return time;
	}
	public void reset(){
		time =0;
	}
}
