package osproj;

import java.util.ArrayList;
import java.util.List;

class readyq {  

    List<processercreat> list = new ArrayList<processercreat>();  
    int index = 0;  //下标
    //入队
    public void in(processercreat n){  
        list.add(n);  
        index++;  
    } 
    //出队
    public void  out(){  
       if(!list.isEmpty()){  
           index--;  
           int i= 0;
           list.remove(i);  
       }  
      
    }  
    public void reset(){
    	list.clear();
    }
    public int getnumber(){
    	return index;
    }
    public processercreat reflectop(){
    	int i=0;
    	return list.get(i);
    }
} 