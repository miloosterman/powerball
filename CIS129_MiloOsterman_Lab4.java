/*******************************************************************************************************************
  * CIS129_MiloOsterman_Lab4.java
  * Author: Milo Osterman
  * CIS 129 - Programming and Problem Solving I
  * Pima Community College
  ******************************************************************************************************************
  * This program runs a lottery game for the user to play
  * User begins with $1000 and can choose to continue purchasing lottery tickets until finished
  ******************************************************************************************************************/

public class CIS129_MiloOsterman_Lab4{

  //Declare initial user wallet, cost of ticket, and minimum amount wallet can have
  static int INITIAL_CASH = 1000;
  static int TICKET_COST = 2;
  static int MINIMUM_WALLET_AMT = 1;

  //Declare possible lottery numbers high and lows
  static int LOTTERY_NUM_HIGH = 69;
  static int LOTTERY_NUM_LOW = 1;
  static int POWERBALL_NUM_HIGH = 26;
  static int POWERBALL_NUM_LOW = 1;

  //Declare strings of possible winning values
  static String FIVE_NUM_PLUS_BALL_DISPLAY = "$100,000,000";
  static String FIVE_NUM_NO_BALL_DISPLAY = "$5,000,000";
  static String FOUR_NUM_PLUS_BALL_DISPLAY = "$50,000";
  static String FOUR_NUM_NO_BALL_DISPLAY = "$100";
  static String THREE_NUM_PLUS_BALL_DISPLAY = "$100";
  static String THREE_NUM_NO_BALL_DISPLAY = "$7";
  static String TWO_NUM_PLUS_BALL_DISPLAY = "$7";
  static String ONE_NUM_PLUS_BALL_DISPLAY = "$4";
  static String ZERO_NUM_PLUS_BALL_DISPLAY = "$4";

  //Declare integers of possible winning value
  static int FIVE_NUM_PLUS_BALL = convertStringToInt(FIVE_NUM_PLUS_BALL_DISPLAY);
  static int FIVE_NUM_NO_BALL = convertStringToInt(FIVE_NUM_NO_BALL_DISPLAY);
  static int FOUR_NUM_PLUS_BALL = convertStringToInt(FOUR_NUM_PLUS_BALL_DISPLAY);
  static int FOUR_NUM_NO_BALL = convertStringToInt(FOUR_NUM_NO_BALL_DISPLAY);
  static int THREE_NUM_PLUS_BALL = convertStringToInt(THREE_NUM_PLUS_BALL_DISPLAY);
  static int THREE_NUM_NO_BALL = convertStringToInt(THREE_NUM_NO_BALL_DISPLAY);
  static int TWO_NUM_PLUS_BALL = convertStringToInt(TWO_NUM_PLUS_BALL_DISPLAY);
  static int ONE_NUM_PLUS_BALL = convertStringToInt(ONE_NUM_PLUS_BALL_DISPLAY);
  static int ZERO_NUM_PLUS_BALL = convertStringToInt(ZERO_NUM_PLUS_BALL_DISPLAY);


  //Declare numbers on ticket
  static int NUMS_ON_TICKET = 6;

  //Declare sentinel value
  static int SENTINEL_VALUE = 0;


  //Main
  public static void main(String[] args){
    int ticketsPurchased;
    int userWallet = INITIAL_CASH;
    int [] winningLotteryNumbers = new int [NUMS_ON_TICKET];
    int [] userNumbers = new int [NUMS_ON_TICKET];

    do {
      displayMainMenu(userWallet); //Display introduction
      ticketsPurchased = getTicketsPurchased(userWallet); //Get number of tickets user wants
      userWallet = updateWallet(userWallet, ticketsPurchased); //Update wallet to value after ticket purchase
      winningLotteryNumbers = getWinningLottoNumbers(winningLotteryNumbers); //Get the winning lottery numbers
      if (ticketsPurchased != SENTINEL_VALUE){
        displayLottoNumbers("The winning lottery numbers, followed by the Powerball Number, are:",
                            winningLotteryNumbers); //Display winning lotto numbers
        userWallet = getLottoResults(ticketsPurchased, userNumbers, winningLotteryNumbers, userWallet); //Lotto results
      }
    } while (ticketsPurchased != SENTINEL_VALUE && userWallet > MINIMUM_WALLET_AMT);

    displayGoodbye(userWallet); //End of program
  }

  //Intro display
  public static void displayMainMenu(int userWallet){
    System.out.println("**************************************************");
    System.err.println("              Let's play Powerball!");
    System.out.println("**************************************************");
    System.out.println("5 numbers correct plus powerball = " + FIVE_NUM_PLUS_BALL_DISPLAY);
    System.out.println("5 numbers correct, no powerball = " + FIVE_NUM_NO_BALL_DISPLAY);
    System.out.println("4 numbers correct plus powerball = " + FOUR_NUM_PLUS_BALL_DISPLAY);
    System.out.println("4 numbers correct, no powerball = " + FOUR_NUM_NO_BALL_DISPLAY);
    System.out.println("3 numbers correct plus powerball = " + THREE_NUM_PLUS_BALL_DISPLAY);
    System.out.println("3 numbers correct, no powerball = " + THREE_NUM_NO_BALL_DISPLAY);
    System.out.println("2 numbers correct plus powerball = " + TWO_NUM_PLUS_BALL_DISPLAY);
    System.out.println("1 number correct plus powerball = " + ONE_NUM_PLUS_BALL_DISPLAY);
    System.out.println("0 numbers correct plus powerball = " + ZERO_NUM_PLUS_BALL_DISPLAY);
    System.out.println("**************************************************");
    System.out.println("You have $" + userWallet);
  }

  //Display winning ticket numbers
  public static void displayLottoNumbers(String msg, int[] winningLotteryNumbers){
    int numsOnTicket = winningLotteryNumbers.length;
    System.out.println("\n" + msg);
    for (int i = 0; i < (numsOnTicket - 1);i++){
      System.out.print(winningLotteryNumbers[i] + " ");
    }
    System.err.println(winningLotteryNumbers[numsOnTicket - 1] + "\n");
  }

  //Gets the amount of tickets the user wants to buy
  public static int getTicketsPurchased(int userWallet){
    int ticketsPurchased;

    ticketsPurchased = IR.getInteger("How many $" + TICKET_COST
    + " lottery cards do you want to purchase?");
    while (isInvalid(ticketsPurchased, userWallet)){
      ticketsPurchased = IR.getInteger("How many $" + TICKET_COST
    + " lottery cards do you want to purchase?");
    }

    return ticketsPurchased;
  }


  public static boolean isInvalid(int ticketsPurchased, int userWallet){
    if (ticketsPurchased < 0){
      System.out.println("You cannot enter a negative value.");
      return true;
    }
    if ((ticketsPurchased * TICKET_COST) > userWallet){
      System.out.println("That would cost more than you have.");
      return true;
    }

    return false;
  }

  //Subtract tickets purchased from user's wallet.
  public static int updateWallet(int userWallet, int ticketsPurchased){
    int newWalletAmt;

    newWalletAmt = userWallet - (ticketsPurchased * TICKET_COST);

    return newWalletAmt;
  }

  //Generate lottery numbers & send to validation to populate array with non repeating numbers
  public static int[] getWinningLottoNumbers(int[] winningLotteryNumbers){
    for (int i = 0; i < (NUMS_ON_TICKET - 1);i++){
      winningLotteryNumbers[i] = getValidNumber(winningLotteryNumbers);
    }
    winningLotteryNumbers[winningLotteryNumbers.length - 1] = IR.getRandomNumber(POWERBALL_NUM_LOW, POWERBALL_NUM_HIGH);
    sortWinningNumbers(winningLotteryNumbers);

    return winningLotteryNumbers;
  }

  //Get a valid non-repeating integer
    public static int getValidNumber(int [] winningLotteryNumbers){
      int newValue;
      newValue = IR.getRandomNumber(LOTTERY_NUM_LOW, LOTTERY_NUM_HIGH);
      while (isInvalid(winningLotteryNumbers, newValue)){
        newValue = IR.getRandomNumber(LOTTERY_NUM_LOW, LOTTERY_NUM_HIGH);
      }
      return newValue;
    }

    //Check if number is already in array
    public static boolean isInvalid(int [] lottoNumbers, int valueToCheck){
      for (int i = 0; i < lottoNumbers.length; i++){
        if (valueToCheck == lottoNumbers[i]){
            return true;
        }
      }
      return false;
    }

    //Sort elements in array in ascending order
    public static void sortWinningNumbers(int [] winningLotteryNumbers){
      int arrayLength = winningLotteryNumbers.length;

      for (int i = 0; i < arrayLength; i++){
        for (int j = i + 1; j < arrayLength - 1; j++){
          int temp;
          if (winningLotteryNumbers[i] > winningLotteryNumbers[j]){
            temp = winningLotteryNumbers[j];
            winningLotteryNumbers[j] = winningLotteryNumbers[i];
            winningLotteryNumbers[i] = temp;
          }
        }
      }
    }

    //Main lottery function, generates numbers based on tickets bought, checks those, updates wallet based on winnings
    public static int getLottoResults(int ticketsPurchased, int [] userNumbers, int [] winningLotteryNumbers, int userWallet){
      int winnings;

      System.out.println("Your lottery ticket numbers, followed by the Powerball Number, are:");
      //Run main lottery function for however many tickets were purchased, add winnings to user wallet
      for (int i = 0; i < ticketsPurchased; i++){
        //Get numbers for users lottery ticket
        userNumbers = getWinningLottoNumbers(userNumbers);
        //Check users ticket to winning ticket and returns winnings
        winnings = checkIfTicketWon(userNumbers, winningLotteryNumbers);
        //Updates wallet with winnings
        userWallet += winnings;
      }
      System.out.println();

      return userWallet;
    }

    //Check if user ticket matches winning ticket and return winnings to user wallet
    public static int checkIfTicketWon(int [] userNumbers, int [] winningLotteryNumbers){
      int matchCounter = 0;
      int numbersOnLottoTicket = userNumbers.length;
      int userPowerBall = userNumbers[numbersOnLottoTicket - 1];
      int winningPowerBall = winningLotteryNumbers[numbersOnLottoTicket - 1];
      boolean powerBallMatch = false;
      int winnings;
      boolean match = false;
      //Check if userNumbers matches winningLotteryNumbers by comparing the arrays
      for (int i = 0; i < numbersOnLottoTicket - 1; i++){
        for (int j = 0; j < numbersOnLottoTicket - 1; j++){
          if (userNumbers[i] == winningLotteryNumbers[j]){
            matchCounter += 1;
            match = true;
          }
        }
        //Print user numbers in red if matching winning ticket, black if not
        displayNumbersAndMatches(userNumbers[i], match);
        //Reset match boolean
        match = false;
      }
      //Display powerball match in red if matching winning ticket, black if not
      powerBallMatch = displayPowerBall(userPowerBall, winningPowerBall, powerBallMatch);
      //Count winnings
      winnings = getUserWinnings(matchCounter, powerBallMatch);
      //Display user winnings
      displayUserWinnings(matchCounter, powerBallMatch);
      //Reset powerBallMatch
      powerBallMatch = false;

      return winnings;
    }

    //Displays the user numbers
    public static void displayNumbersAndMatches(int userNumbers, boolean match){
      //Space addition if number is single digit for right justification
        if (userNumbers < 10){
          System.out.print(" ");
        }
        //Print letter in red if match, standard if not
        if (match){
          System.err.print(userNumbers + " ");
          match = false;
        }
        else {
          System.out.print(userNumbers + " ");
        }
    }

    //Display user powerball
    public static boolean displayPowerBall(int userPowerBall, int winningPowerBall, boolean powerBallMatch){
       //Space addition if powerball is single digit for right justification
      if (userPowerBall  < 10){
          System.out.print(" ");
        }

      //Check if powerball matches
      if (userPowerBall == winningPowerBall){
        powerBallMatch = true;
      }
      //Print powerball in red if match standard if no match
      if (powerBallMatch){
          System.err.print(userPowerBall + " ");
        }
        else {
          System.out.print(userPowerBall + " ");
        }

        return powerBallMatch;
    }

    //Switch case setup that takes how many matches there are & if powerball was a match for money won, displays it
    public static int getUserWinnings(int matches, boolean powerBall){
      int winnings = 0;

      if(powerBall){
        switch (matches) {
          case 0:
            winnings += ZERO_NUM_PLUS_BALL;
            break;
          case 1:
            winnings += ONE_NUM_PLUS_BALL;
            break;
          case 2:
            winnings += TWO_NUM_PLUS_BALL;
            break;
          case 3:
            winnings += THREE_NUM_PLUS_BALL;
            break;
          case 4:
            winnings += FOUR_NUM_PLUS_BALL;
            break;
          case 5:
            winnings += FIVE_NUM_PLUS_BALL;
            break;
        }
      }
      else {
        switch (matches) {
          case 3:
            winnings += THREE_NUM_NO_BALL;
            break;
          case 4:
            winnings += FOUR_NUM_NO_BALL;
            break;
          case 5:
            winnings += FIVE_NUM_NO_BALL;
            break;
        }
      }
      return winnings;
    }
    
    //Display how much the user won
    public static void displayUserWinnings(int matches, boolean powerBall){
      if(powerBall){
        switch (matches) {
          case 0:
            System.out.println("You won " + ZERO_NUM_PLUS_BALL_DISPLAY);
            break;
          case 1:
            System.out.println("You won " + ONE_NUM_PLUS_BALL_DISPLAY);
            break;
          case 2:
            System.out.println("You won " + TWO_NUM_PLUS_BALL_DISPLAY);
            break;
          case 3:
            System.out.println("You won " + THREE_NUM_PLUS_BALL_DISPLAY);
            break;
          case 4:
            System.out.println("You won " + FOUR_NUM_PLUS_BALL_DISPLAY);
            break;
          case 5:
            System.out.println("You won " + FIVE_NUM_PLUS_BALL_DISPLAY);
            break;
        }
      }
      else {
        switch (matches) {
          case 0:
            System.out.println("");
            break;
          case 1:
            System.out.println("");
            break;
          case 2:
            System.out.println("");
            break;
          case 3:
            System.out.println("You won " + THREE_NUM_NO_BALL_DISPLAY);
            break;
          case 4:
            System.out.println("You won " + FOUR_NUM_NO_BALL_DISPLAY);
            break;
          case 5:
            System.out.println("You won " + FIVE_NUM_NO_BALL_DISPLAY);
            break;
        }
      }
    }

    //Goodbye function
    public static void displayGoodbye(int userWallet){
      System.out.println("You have $" + userWallet);
      System.out.println("Goodbye!");
    }

    //Convert string argument to integer
    public static int convertStringToInt(String value){
      int convertedInt;

      value = value.replaceAll("[$,]", "");

      convertedInt = Integer.parseInt(value);

      return convertedInt;
    }
}
