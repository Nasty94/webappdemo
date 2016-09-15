package com.demo.calculator;

import java.io.File;

public class FileDemo {
   public static void main(String[] args) {
      
      File f = null;
      String path = "";
      boolean bool = false;
      
      try{
         // create new files
         f = new File("calc.txt");
         
         // returns true if the file exists
         bool = f.exists();
         
         // if file exists
         if(bool)
         {
            // get absolute path
            path = f.getAbsolutePath();
            
            // prints
            System.out.print("Absolute Pathname "+ path);
         }
      }catch(Exception e){
         // if any error occurs
         e.printStackTrace();
      }
   }
}