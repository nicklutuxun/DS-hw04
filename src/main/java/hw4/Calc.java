package hw4;


import java.util.Scanner;

/**
 * A program for an RPN calculator that uses a stack.
 */
public final class Calc {
  /**
   * The main function.
   *
   * @param args Not used.
   */
  public static void main(String[] args) {
    LinkedStack<Integer> stack = new LinkedStack<>();
    Scanner inputScanner = new Scanner(System.in);
    String input;
    boolean end = false;
    
    while (true) {
      input = inputScanner.nextLine();
      Scanner commandScanner = new Scanner(input);
      while (commandScanner.hasNext()) {
        String command = commandScanner.next();
        if (command.equals("!")) {
          end = true;
          break;
        }
        if (!validInt(command)) {
          System.out.println("ERROR: bad token");
          continue;
        }
        System.out.println(command);
        
      }
      
      if (end) {
        break;
      }
      
    }
  }
  
  private static boolean validInt(String input) {
    if(input.isEmpty()) return false;
    
    for (int i = 0; i < input.length(); i++) {
      char cur = input.charAt(i);
      if (i == 0 && cur == '-') {
        if (input.length() == 1) {
          return false;
        } else {
          continue;
        }
      }
      if (!Character.isDigit(cur)) {
        return false;
      }
    }
    return true;
  }
}
