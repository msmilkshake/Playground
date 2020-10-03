package experiments.evaluator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionSolver {
    
    public static String innerExpression = "\\([^(]+?\\)";
    public static String number =
            "(?<!\\d)([+-]?\\d+(?>\\.\\d+)?(?>[eE]-?\\d+)?)";
    public static String multiplyDivideRegex =
            number + "([*/])" + number + "(?=[+\\-*/]|\\b)";
    public static String addSubtractRegex =
            number + "([+-])" + number + "(?=[+\\-*\\/]|\\b)";
    
    public static void main(String[] args) {
        System.out.println(calcExpression("(3)/-+2"));
        System.out.println(calcExpression("-1"));
        System.out.println(calcExpression("----1"));
        System.out.println(calcExpression("--++-+++-+1"));
        System.out.println(calcExpression("3+2*-4"));
        System.out.println(calcExpression("(3+2)*-4"));
        System.out.println(calcExpression("-3+2*-4"));
        System.out.println(calcExpression("-(3+2)*-4"));
        System.out.println(calcExpression("-(3+2/(3-1))*-4"));
        System.out.println(calcExpression("-((3+2)/(3-1))*-4"));
        System.out.println(calcExpression("-(3+2/---(3-1))*-4"));
        System.out.println(calcExpression("-((3+2)/---(3-1))*-4"));
        System.out.println(calcExpression("-((3+2)*---(3-1))*-4"));
        System.out.println(calcExpression("-  (-5.5 + 3) / (2 + (3 * 5.2 / 1.5))"));
        System.out.println(calcExpression("(2.5*2)+---(12.4 / 4)"));
    }
    
    public static double calcExpression(String expression) {
        
        expression = expression.replaceAll("\\s", "")
                .replaceAll("(?<=^|[+\\-*/])\\++", "")
                .replaceAll("--", "");
        
        while (true) {
            expression = expression.replaceAll("\\)\\(", ")*(");
            Matcher matcher = Pattern.compile(innerExpression).matcher(expression);
            if (!matcher.find()) {
                break;
            }
            double num = calcSimpleExpression(matcher.group().replaceAll("[()]", ""));
            expression = expression.substring(0, matcher.start()) +
                    num + expression.substring(matcher.end());
        }
        return calcSimpleExpression(expression);
    }
    
    public static double calcSimpleExpression(String expression) {
        double num;
        
        while (true) {
            Matcher matcher = Pattern.compile(multiplyDivideRegex).matcher(expression);
            if (!matcher.find()) {
                break;
            }
            String left = solveChainedSigns(matcher.group(1));
            String right = solveChainedSigns(matcher.group(3));
            if (matcher.group(2).equals("*")) {
                num = Double.parseDouble(left) *
                        Double.parseDouble(right);
            } else {
                num = Double.parseDouble(left) /
                        Double.parseDouble(right);
            }
            expression = expression.substring(0, matcher.start()) +
                    num + expression.substring(matcher.end());
        }
        
        while (true) {
            Matcher matcher = Pattern.compile(addSubtractRegex).matcher(expression);
            if (!matcher.find()) {
                break;
            }
            String left = solveChainedSigns(matcher.group(1));
            String right = solveChainedSigns(matcher.group(3));
            if (matcher.group(2).equals("+")) {
                num = Double.parseDouble(left) +
                        Double.parseDouble(right);
            } else {
                num = Double.parseDouble(left) -
                        Double.parseDouble(right);
            }
            expression = expression.substring(0, matcher.start()) +
                    num + expression.substring(matcher.end());
        }
        expression = solveChainedSigns(expression);
        
        return Double.parseDouble(expression);
    }
    
    public static String solveChainedSigns(String expression) {
        double num;
        while (true) {
            Matcher matcher = Pattern.compile(number).matcher(expression);
            matcher.find();
            String m = matcher.group();
            if (matcher.group().equals(expression)) {
                break;
            }
            num = Double.parseDouble(matcher.group());
            String signs = expression.substring(0, matcher.start());
            if (signs.charAt(signs.length() - 1) == '-') {
                num = -num;
            }
            expression = signs.substring(0, signs.length() - 1) + num;
        }
        return expression;
    }
}
