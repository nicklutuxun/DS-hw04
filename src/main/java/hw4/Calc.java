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
    LinkedStack<Integer> stack = new LinkedStack<>();
    Scanner inputScanner = new Scanner(System.in);
    String input;
    boolean end = false;
    
    while (!end) {
      input = inputScanner.nextLine();
      Scanner commandScanner = new Scanner(input);
      while (commandScanner.hasNext() && !end) {
        String command = commandScanner.next();
        if (!validToken(command)) {
          System.out.println("ERROR: bad token");
        } else if (validSpecialCommand(command)) {
          end = evalSpecialCommand(command, stack);
        } else if (validOperator(command)) {
          evalOperator(command, stack);
        } else if (validInt(command)) {
          evalInt(command, stack);
        }
      }
    }
  }
  
  private static boolean validToken(String command) {
    return validInt(command) || validOperator(command) || validSpecialCommand(command);
  }
  
  private static boolean validInt(String command) {
    if (command.isEmpty()) {
      return false;
    }
    
    for (int i = 0; i < command.length(); i++) {
      char cur = command.charAt(i);
      if (i == 0 && cur == '-') {
        if (command.length() == 1) {
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
  
  private static void evalInt(String command, LinkedStack<Integer> stack) {
    stack.push(Integer.parseInt(command));
  }
  
  private static boolean validSpecialCommand(String command) {
    String[] specialCommands = {"?", ".", "!"};
    boolean valid = false;
    for (int i = 0; i < 3; i++) {
      if (command.equals(specialCommands[i])) {
        valid = true;
        break;
      }
    }
    return valid;
  }
  
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
        return true;
      default:
    }
    return false;
  }
  
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
  
  private static void evalOperator(String command, LinkedStack<Integer> stack) {
    if (stack.empty()) {
      System.out.println("ERROR: empty stack");
      return;
    }
    int op2 = stack.top();
    if ("/".equals(command) && op2 == 0) {
      System.out.println("ERROR: cannot divide by zero");
      return;
    }
    stack.pop();
    if (stack.empty()) {
      System.out.println("ERROR: operator need 2 operands but 1 is given");
      stack.push(op2);
      return;
    }
    int op1 = stack.top();
    stack.pop();
  
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
