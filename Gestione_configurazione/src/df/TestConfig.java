/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package df;

/**
 *
 * @author jfunc
 */
public class TestConfig {
    public static void outln(Object obj){
        System.out.println(obj);
    }
    public static void out(Object obj){
        System.out.print(obj);
    }
    public static void outln(){
        System.out.println();
    }
    public static void main(String[] args){
        
        outln(Config.getInstance().getBackgroundColor());
    } 
}
