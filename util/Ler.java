package sports_store.util;

import java.io.*;
import java.time.LocalDate;

/**
 * Auxiliar class to read parameters.
 * @author rpaba
 */
public class Ler {
    
        public static String umaString()
        {
               String s = "";
               try{
                   BufferedReader in = new BufferedReader ( new InputStreamReader (System.in));
                   s = in.readLine().trim();
               }
               catch (IOException e){
                   System.out.println("Erro ao ler o fluxo de entrada");
               }
               return s;
        }
        
        public static int umInt() 
        {
            while(true){
                try{
                    return Integer.valueOf(umaString().trim()).intValue();
                }
                catch(Exception e){
                    return -2;
                }               
            }
        }
        
        public static double umDouble(){
            
            while(true){
                try{
                    return Double.valueOf(umaString().trim()).doubleValue();
                }
                catch(Exception e){
                    System.out.println("Não é um valor válido");
                }
            }
                
        }
        
        public static float umFloat(){
            
            while(true)
            {
                   try{
                return Float.valueOf(umaString().trim()).floatValue();
                }
            catch(Exception e){
                   System.out.println("Nao é um float válido");
                }
            }
        }
        
        public static byte umByte(){
            while(true)
            {
                try{
                    return Byte.valueOf(umaString().trim()).byteValue();
                }
                catch(Exception e){
                    System.out.println("Nao é um byte valido");
                }
            }
        }
        
        public static short umShort(){
            while(true)
            {
                try{
                   return  Short.valueOf(umaString().trim()).shortValue();
                }
                catch(Exception e){
                    System.out.println("Nao é um short valido");
                }
            }
        }
        
        public static long umLong(){
            while(true)
            {
                try{
                    return Long.valueOf(umaString().trim()).longValue();
                }
                catch(Exception e){
                     return -1;
                }
            }
        }
        
        public static boolean umBoolean()
        {
            while(true)
            {
                try{
                    return Boolean.valueOf(umaString().trim()).booleanValue();
                }
                catch(Exception e){
                    System.out.println("Nao é um boolean válido");
                }
            }
        }
        
        public static LocalDate umaDate()
        {
            while(true)
            {
                try{
                    return (LocalDate)LocalDate.parse(umaString());
                }
                catch(Exception e){
                    System.out.println("Nao é uma data válida");
                }
            }
        }
}
