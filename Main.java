
import lex.Lex;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while(true) {
      String exp = scanner.nextLine();
      if(exp.equals("exit")) {
        scanner.close();
        break;
      }
      try {
        Lex.lex(exp);
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }
}
