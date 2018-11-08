/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webbrowser;

import java.util.Stack;

/**
 *
 * @author ARKO
 */

/*
here two stack habe been used to save the back histry and forword histry.......





*/

public class BackForwordControl {
    
Stack<String> st_b= new Stack<String>();
Stack<String> st_f=new Stack<String>();

public String get_back(String string){
 

String st=null;
boolean x=true;
    while(x){
        if(st_b.empty()){set_forword(string);return string;}
      st=st_b.firstElement();
     st_b.remove(0);
         if(st.compareTo(string)!=0){
           //  System.out.println("this is st  -->  "+st);
            // System.out.println("this is string  -->  "+string);
             x=false;
             set_forword(st);
        return st;
         }
         if(st_b.empty()&&st!=null){set_forword(st);return st;}
         if(st_b.empty()&&st==null){set_forword(string);return string;}
    }
    set_forword(st);
return st;
}
    


//this function is to give the forword URL.......
public String get_forword(String string){
//set_back(string);
String s=null;
if(!st_f.empty()){
s=st_f.lastElement();
st_f.remove(st_f.size()-1);
}
if(!st_f.empty()){
if(s==string){
s=st_f.lastElement();
st_f.remove(st_f.size()-1);
} 
}
if(s==null){s=string;}
    set_back(s);
return s;
}


//it restors the previous URL....
public void set_back(String string){
st_b.insertElementAt(string, 0);
} 

//this is to set the forword URL....
public void set_forword(String string){
st_f.add(string);

}
//it is to delete the forword stack.....
public void set_forword(){
st_f.clear();
}
}
