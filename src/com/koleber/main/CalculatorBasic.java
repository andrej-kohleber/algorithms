package com.koleber.main;

public class CalculatorBasic {

    public static void main(String[] args) {
        String str = "(1 + (( 2 + 3) * (4 * 5)))";
        
        Stack<String> ops = new Stack<>();
        Stack<Double> val = new Stack<>();
        
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if (s.equals("("));
            else if (s.equals(" "));
            else if (s.equals("+")) ops.push("+");
            else if (s.equals("-")) ops.push("-");
            else if (s.equals("*")) ops.push("*");
            else if (s.equals("/")) ops.push("/");
            else if (s.equals(")")) {
                String op = ops.pop();
                double v = val.pop();
                if (op.equals("+")) v = val.pop() + v;
                else if (op.equals("-")) v = val.pop() - v;
                else if (op.equals("*")) v = val.pop() * v;
                else if (op.equals("/")) v = val.pop() / v;
                val.push(v);
            } else {
                val.push( Double.parseDouble(s));
            }
        }
        double result = val.pop();
    }
}
