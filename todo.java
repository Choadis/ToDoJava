import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class todo {

  static String fileName = "todoEntries/todo.txt";

  public static void main (String[] args) throws IOException {

    int menuItem = -1;

    while (menuItem != 0) {
      menuItem = menu();
      switch(menuItem) {
        case 1:
        showList();
        break;
        case 2:
        addItem();
        break;
        case 3:
        removeItem();
        break;
        case 0:
        System.out.println("Goodbye!");
        break;
        default:
        System.out.println("I can't let you do that, Fox(invalid input)");
      }
    }
  }

  static int menu() {

    int choice;

    Scanner input = new Scanner(System.in);

    System.out.println("Main menu");
    System.out.println("0. Exit Program");
    System.out.println("1. Show todo list");
    System.out.println("2. Add todo");
    System.out.println("3. Remove item");
    System.out.println();
    System.out.println("Enter a choice: ");

    choice = input.nextInt();
    System.out.println("-----------------------------");
    return choice;
  }

  static void showList() {
    System.out.println();
    System.out.println("To-do List:");
    try {
      Scanner inFile = new Scanner(new FileReader(fileName));
      String line;
      int number = 1;

      while(inFile.hasNextLine()){
        line = inFile.nextLine();
        System.out.println(number + "." + " " + line);
        ++number;
      }
      System.out.println();
      System.out.println("-----------------------------");
      inFile.close();
    } catch (IOException ioe){
      System.out.println("Can't access file");
    }
  }

  static void addItem() {

    System.out.println("Add item");

    try{
      Scanner input = new Scanner(System.in);
      PrintWriter outFile = new PrintWriter(new FileWriter(fileName, true));
      System.out.println("Enter an item: ");
      String item = input.nextLine();
      outFile.println(item);
      System.out.println("-----------------------------");
      outFile.close();
    } catch (IOException ioe){
      System.out.println("Can't access file");
    }

  }

  static void removeItem() {

    int choice;
    showList();
    Scanner input = new Scanner(System.in);
    System.out.println("Remove which item?");
    choice = input.nextInt();
    ArrayList<String> items = new ArrayList<String>();
    int number = 1;

    try{
      Scanner inFile = new Scanner(new FileReader(fileName));
      String item;
      while (inFile.hasNextLine()) {
        item = inFile.nextLine();
        if (number != choice) {
          items.add(item);
        }
        ++number;
      }
      PrintWriter outFile = new PrintWriter(new FileWriter(fileName));
      for (int i = 0; i < items.size(); i++){
        outFile.println(items.get(i));
      }
      outFile.close();

    } catch (IOException ioe){
      System.out.println("Can't access file");
    }
  }

}
