package lex;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Stack;
import java.util.Arrays;
import java.util.List;

public class Lex {

  static String[] allowed_tokens = {"+","-","*","/",")","("};
  static String[] operator_tokens = {"+","-","*","/"};
  static Pattern validator = Pattern.compile("\\)|\\(|\\+|\\-|\\*|\\/|[^\\s\\w]+|\\w+");
  static Pattern isNumPattern = Pattern.compile("\\d+");

  public static Stack lex(String exp) {
    Matcher tokens_matcher = validator.matcher(exp);
    Stack<String> tokens = new Stack<>();
    while(tokens_matcher.find()) {
      String token = tokens_matcher.group();
      if(!is_valid_token(token)) {
        System.out.println(token);
        throw new RuntimeException("Invalid token in the expression");
      }
      tokens.push(token);
    }
    isValidExp(tokens);
    return tokens;
  }

  public static void isValidExp(Stack<String> tokens) {
    // to count the occurence of brackets, numbers, and operators
    int paran = 0;
    int nums = 0;
    int oper = 0;

    for(int i=0;i<tokens.size();i++) {
      String token = tokens.get(i);
      List<String> operators_allowed = Arrays.asList(operator_tokens);
      if (token.equals("(")) paran++; 
      if (token.equals(")")) paran--; 
      if(isNumPattern.matcher(token).matches()) nums++;
      if(operators_allowed.contains(token)) oper++;
      if(paran < 0) {
        System.out.printf("paran = %d, nums = %d, oper = %d\n",paran,nums,oper);
        throw new RuntimeException("Paranthesis Error");
      }
      if(oper > nums) {
        System.out.printf("paran = %d, nums = %d, oper = %d\n",paran,nums,oper);
        throw new RuntimeException("Operator Error");
      }
      if(nums == 2 && oper == 0 ) {
        System.out.printf("paran = %d, nums = %d, oper = %d\n",paran,nums,oper);
        throw new RuntimeException("Operand Error");
      }
      if(i == tokens.size()-1) {
        // last token cannot be a operator or a opening bracket
        if (token.equals("(") || operators_allowed.contains(token)) {
          System.out.printf("paran = %d, nums = %d, oper = %d\n",paran,nums,oper);
          throw new RuntimeException("Last token is invalid");
        }
      }
    }
    System.out.printf("paran = %d, nums = %d, oper = %d\n",paran,nums,oper);
    if(paran != 0) throw new RuntimeException("Paranthesis is not matching.");
    if(nums != oper+1) throw new RuntimeException("Operand and Operator is not matching");
  }

  static boolean is_valid_token(String token) {
    if(Arrays.asList(allowed_tokens).contains(token) ||
        isNumPattern.matcher(token).matches()) {
      return true;
    }
    return false;
  }
}
