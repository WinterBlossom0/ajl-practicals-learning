public class Q13_InterpreterPatternDemo {
    public static void main(String[] args) {
        Expression isMale = new OrExpression(new TerminalExpression("John"), new TerminalExpression("Robert"));
        System.out.println("John is male? " + isMale.interpret("John")); // true
    }
}

interface Expression {
    boolean interpret(String context);
}

class TerminalExpression implements Expression {
    private final String data;
    public TerminalExpression(String data) { this.data = data; }
    public boolean interpret(String context) { return context.contains(data); }
}

class OrExpression implements Expression {
    private final Expression expr1;
    private final Expression expr2;
    public OrExpression(Expression e1, Expression e2) { expr1 = e1; expr2 = e2; }
    public boolean interpret(String context) { return expr1.interpret(context) || expr2.interpret(context); }
}
