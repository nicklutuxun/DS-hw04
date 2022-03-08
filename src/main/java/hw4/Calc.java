package hw4;

import exceptions.EmptyException;
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
    // stack for input integer values
    LinkedStack<Integer> stack = new LinkedStack<>();
    // scanner for user input
    Scanner inputScanner = new Scanner(System.in);
    String input;
    // continue to read input
    boolean cont = true;
    
    while (cont) {
      // read a line of user input
      input = inputScanner.nextLine();
      // create scanner of tokens
      Scanner commandScanner = new Scanner(input);
      while (commandScanner.hasNext() && cont) {
        String command = commandScanner.next();
        // continue if true, quit if false
        cont = processCommand(command, stack);
      }
    }
  }
  
  /**
   * Process a string of commands.
   * @param command user input string
   * @param stack holds inputs and intermediate results
   * @return false if evalSpecialCommand() returns false, true otherwise
   */
  private static boolean processCommand(String command, LinkedStack<Integer> stack) {
    // check general validity
    if (!validToken(command)) {
      System.out.println("ERROR: bad token");
    } else if (validSpecialCommand(command)) {
      // return false if "!"
      return evalSpecialCommand(command, stack);
    } else if (validOperator(command) && validStack(command, stack)) {
      // check operator and stack validity
      evalOperator(command, stack);
    } else if (validInt(command)) {
      // check input integer validity
      evalInt(command, stack);
    }
    return true;
  }
  
  /**
   * Check general validity.
   * @param command user input single command
   * @return true if either validInt/validOperator/validSpecialCommand returns true, false otherwise
   */
  private static boolean validToken(String command) {
    return validInt(command) || validOperator(command) || validSpecialCommand(command);
  }
  
  /**
   * Check integer validity.
   * @param command user input single command
   * @return true if valid integer, false otherwise
   */
  private static boolean validInt(String command) {
    try {
      Integer.parseInt(command);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }
  
  /**
   * Push integer input to stack.
   * @param command user input single command
   * @param stack holds inputs and intermediate results
   */
  private static void evalInt(String command, LinkedStack<Integer> stack) {
    stack.push(Integer.parseInt(command));
  }
  
  /**
   * Check if is special commands.
   * @param command user input single command
   * @return true if is special commands, false otherwise
   */
  private static boolean validSpecialCommand(String command) {
    String[] specialCommands = {"?", ".", "!"};
    boolean valid = false;
    // check if in array
    for (int i = 0; i < 3; i++) {
      if (command.equals(specialCommands[i])) {
        valid = true;
        break;
      }
    }
    return valid;
  }
  
  /**
   * Perform tasks with respect to special commands.
   * @param command user input single command
   * @param stack holds inputs and intermediate results
   * @return false if command is "!", true otherwise
   */
  private static boolean evalSpecialCommand(String command, LinkedStack<Integer> stack) {
    switch (command) {
      case "?":
        System.out.println(stack.toString());
        break;
      case ".":
        try {
          int top = stack.top();
          System.out.println(top);
        } catch (EmptyException ex) {
          System.out.println("ERROR: empty stack");
        }
        break;
      case "!":
        return false;
      default:
    }
    return true;
  }
  
  /**
   * Check if is valid operator.
   * @param command user input single command
   * @return true if is valid operator, false otherwise
   */
  private static boolean validOperator(String command) {
    String[] operators = {"+", "-", "*", "/", "%"};
    boolean valid = false;
    for (int i = 0; i < 5; i++) {
      if (command.equals(operators[i])) {
        valid = true;
        break;
      }
    }
    return valid;
  }
  
  /**
   * Check if stack is valid for operations.
   * @param command user input single command
   * @param stack holds inputs and intermediate results
   * @return true if stack is valid, false otherwise
   */
  private static boolean validStack(String command, LinkedStack<Integer> stack) {
    if (stack.empty()) {
      System.out.println("ERROR: operator need 2 operands but 0 is given");
      return false;
    }
    int op2 = stack.top();
    if (("/".equals(command) || "%".equals(command)) && op2 == 0) {
      System.out.println("ERROR: cannot divide by zero");
      return false;
    }
    stack.pop();
    if (stack.empty()) {
      System.out.println("ERROR: operator need 2 operands but 1 is given");
      // push back to remain stack unchanged
      stack.push(op2);
      return false;
    }
    // push back to remain stack unchanged
    stack.push(op2);
    
    return true;
  }
  
  /**
   * Perform task with respect to operators.
   * @param command user input single command
   * @param stack holds inputs and intermediate results
   */
  private static void evalOperator(String command, LinkedStack<Integer> stack) {
    // fetch 2 operands
    int op2 = stack.top();
    stack.pop();
    int op1 = stack.top();
    stack.pop();
    
    // perform calculation
    if ("+".equals(command)) {
      stack.push(op1 + op2);
    } else if ("-".equals(command)) {
      stack.push(op1 - op2);
    } else if ("*".equals(command)) {
      stack.push(op1 * op2);
    } else if ("/".equals(command)) {
      stack.push(op1 / op2);
    } else if ("%".equals(command)) {
      stack.push(op1 % op2);
    }
  }
}
