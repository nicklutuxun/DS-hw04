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
        if ("!".equals(command)) {
          end = true;
          break;
        }
        if (validOperator(command)) {
          evalOperator(command, stack);
          continue;
        }
        if (validSpecialCommand(command)) {
          evalSpecialCommand(command, stack);
          continue;
        }
        if (!validInt(command)) {
          System.out.println("ERROR: bad token");
          continue;
        }
        stack.push(Integer.parseInt(command));
      }
      
      if (end) {
        break;
      }
      
    }
  }
  
  private static void evalSpecialCommand(String command, LinkedStack<Integer> stack) {
    switch (command) {
      case "?":
        System.out.println(stack.toString());
        break;
      case ".":
        System.out.println(stack.top());
        break;
      default:
        break;
    }
  }
  
  private static boolean validSpecialCommand(String command) {
    String[] specialCommands = {"?", "."};
    boolean valid = false;
    for (int i = 0; i < 2; i++) {
      if (command.equals(specialCommands[i])) {
        valid = true;
        break;
      }
    }
    return valid;
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
    int op2 = stack.top();
    stack.pop();
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
}
