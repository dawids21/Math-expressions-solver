public class App {
    public static void main(String[] args) {

        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: java App <expression>");
        }

        System.out.println(MathExpression.solve(args[0]));
    }
}
