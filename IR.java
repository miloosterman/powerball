/*******************************************************************************************************************
  * InputRoutinesWithScannerV4.java
  * Author: David A. Freitag
  * CIS 129 - Programming and Problem Solving I 
  * Pima Community College 
  ****************************************************************************************************************** 
  * This program gets input from a user using Scanner. 
  * Use at your own risk. Test your program well. No guarantee this code works in all situations. 
  ******************************************************************************************************************/ 

import java.util.Scanner;

public class IR {
   //Putting the Scanner object here makes it global so it does not have to be passed to modules.
   static Scanner keyboard = new Scanner(System.in);
   
   public static void main(String[] args) {
      /** Main () - 
        * This program demonstrates various generalized input routines. 
        * This program also demonstrates generating a random number.
        * @param args Arguments can be passed to this program but they are not used. 
        */
      
      do {
         String stringData = getString("Please enter a word or two");
         System.out.println("This is what you entered: " + stringData);
         
         int intData = getInteger("Please enter an integer");     
         System.out.println("This is what you entered: " + intData);
         
         //Generating random numbers. 
         for (int i = 0; i < 5; i++) {
            System.out.println("random number " + (i + 1) + ": " + getRandomNumber(0, 100));
         }
         
      } while (getYorN("\nDo you want to start over? (y/n)"));
      
      //close the Scanner
      closeScanner();
      
      System.out.println("Program Terminating Normally");
   }//end of main
   
   public static void displayGoodbye(){
       System.out.println("Goodbye!");
   }
 
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   // GENERALIZED INPUT FUNCTIONS 
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /** Gets a String from the keyboard. Rejects null entry or any number of spaces.
     * @param msg is the text that will be displayed the user to ask them to enter a value.
     * @return Returns a String from the keyboard. 
     */ 
   public static String getString(String msg) {
      String answer = "";
      System.out.println(msg);
      try {
         answer = keyboard.nextLine(); 
      }
      catch (Exception e) {
         System.err.println("Error reading input from user. Ending program.");
         System.exit(-1);
      } 
      
      while (answer.replaceAll(" ", "").equals("")) {
         System.err.println("Error: Missing input.");
         try {
            System.out.println(msg);
            answer = keyboard.nextLine(); 
         }
         catch (Exception e) {
            System.err.println("Error reading input from user. Ending program.");
            System.exit(-1);
         } 
      }
      return answer;            
   }
   
   //------------------------------------------------------------------------------------------------------------------
   /** Gets an Integer from the keyboard. Rejects null, spaces and non-integers.
     * @param msg is the text that will be displayed the user to ask them to enter a number.
     * @return Returns an int from the keyboard. 
     */  
   public static int getInteger(String msg) {
      System.out.println(msg);
      while (!keyboard.hasNextInt()) {
         keyboard.nextLine();
         System.err.println("Invalid integer. Try again.");
      }
      int number = keyboard.nextInt();
      keyboard.nextLine(); //flushes the buffer
      return number;
   }
   
   //------------------------------------------------------------------------------------------------------------------
   /** Gets a Double from the keyboard. Rejects null, spaces and non-numbers.
     * @param msg is the text that will be displayed the user to ask them to enter a number.
     * @return Returns a double from the keyboard. 
     */ 
   public static double getDouble(String msg) {
      System.out.println(msg);
      while (!keyboard.hasNextDouble()) {
         keyboard.nextLine();
         System.err.println("Invalid number. Try again.");
      }
      double number = keyboard.nextDouble();
      keyboard.nextLine(); //flushes the buffer
      return number;
   }
   
   //------------------------------------------------------------------------------------------------------------------
   /** Gets a Yes or No answer from the keyboard. Calls getString to rejects null input and spaces.
     * @param msg is the text that will be displayed the user.
     * @return Returns a boolean value. True = yes; False = no. 
     */ 
   public static boolean getYorN(String msg) {
      String answer = getString(msg);
      
      while (answer.compareToIgnoreCase("y")   != 0 
          && answer.compareToIgnoreCase("yes") != 0 
          && answer.compareToIgnoreCase("n")   != 0 
          && answer.compareToIgnoreCase("no")  != 0) {
         
         if (answer.replaceAll(" ", "").equals("")) {
            System.err.println("Error: Missing y/n input.");
         } else {
            if (answer.compareToIgnoreCase("y")   != 0 
             && answer.compareToIgnoreCase("yes") != 0 
             && answer.compareToIgnoreCase("n")   != 0 
             && answer.compareToIgnoreCase("no")  != 0) {
               System.err.println("Error: Unexpected input.");
            }
         }
         answer = getString(msg);
      } 
      
      if  (answer.compareToIgnoreCase("y")   == 0  
        || answer.compareToIgnoreCase("yes") == 0) {
         return true;
      } 
      else {
         return false;
      }
   }
   
   //------------------------------------------------------------------------------------------------------------------
   /** Closes the scanner.
     */ 
   public static void closeScanner() {
      try { 
         if(keyboard != null) {
            keyboard.close(); 
         }
      } 
      catch (Exception e) { // (Exception) catches all errors java might throw here
         System.err.println("Error closing reader.");
      }
   }
   //------------------------------------------------------------------------------------------------------------------
   /** Generates a random number between low and high, inclusive.
     * @param low is the smallest number that will be randomly generated.
     * @param high is the largest number that will be randomly generated.
     * @return Returns the random number as an integer.
     */
   public static int getRandomNumber (int low, int high) {
      return (int)(Math.random() * ((high + 1) - low)) + low;
   }
   
}//end of class