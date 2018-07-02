/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente;

/**
 *
 * @author Marvian
 */
// implementacion sacada de:https://www.javatpoint.com/sleep()-method 
public class sleep extends Thread {
    
    public void run(int s){  
        for(int i=1;i<s;i++){  
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}  
        System.out.println(i);  
        }
    }
    
    public void run10s(){  
        for(int i=1;i<10;i++){  
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}  
        System.out.println(i);  
        }
    }
     
     public void run20s(){  
        for(int i=1;i<20;i++){  
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}  
        System.out.println(i);  
        }
    }
}
