package langparser;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public final class Builder {


    // No need to instantiate this class.
    private Builder() {
    }

    public static final class Lexer {

        private ECMAScriptLexer lexer;

        public Lexer(String input) {
            this(new ANTLRInputStream(input));
        }

        public Lexer(ANTLRInputStream input) {
            this.lexer = new ECMAScriptLexer(input);
            this.lexer.removeErrorListeners();
            
        }

        public Lexer withStrictMode(boolean strictMode) {
            this.lexer.setStrictMode(strictMode);
            return this;
        }

        public Lexer withErrorListener(ANTLRErrorListener listener) {
            this.lexer.removeErrorListeners();
            this.lexer.addErrorListener(listener);
            return this;
        }

        public ECMAScriptLexer build() {
            return this.lexer;
        }
    }

    public static final class Parser {

        private ECMAScriptParser parser;

        public Parser(String input) {
            this(new ANTLRInputStream(input));
        }

        public Parser(ANTLRInputStream input) {
            ECMAScriptLexer lexer = new ECMAScriptLexer(input);
            lexer.removeErrorListeners();
            
            this.parser = new ECMAScriptParser(new CommonTokenStream(lexer));
            
        }

        public Parser(ECMAScriptLexer lexer) {
            this.parser = new ECMAScriptParser(new CommonTokenStream(lexer));
            
        }

        public Parser withErrorListener(ANTLRErrorListener listener) {
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(listener);
            return this;
        }

        public ECMAScriptParser build() {
            return this.parser;
        }
    }

    public static final class Tree {

        public static String toStringASCII(String input) {

            ECMAScriptParser parser = new Builder.Parser(input).build();
            ParseTree tree = parser.program();

            StringBuilder builder = new StringBuilder();

            walk(tree, builder);

            return builder.toString();
        }

        @SuppressWarnings("unchecked")
        private static void walk(ParseTree tree, StringBuilder builder) {

            List<ParseTree> firstStack = new ArrayList<ParseTree>();
            firstStack.add(tree);

            List<List<ParseTree>> childListStack = new ArrayList<List<ParseTree>>();
            childListStack.add(firstStack);

            while (!childListStack.isEmpty()) {

                List<ParseTree> childStack = childListStack.get(childListStack.size() - 1);

                if (childStack.isEmpty()) {
                    childListStack.remove(childListStack.size() - 1);
                }
                else {
                    tree = childStack.remove(0);

                    String node = tree.getClass().getSimpleName().replace("Context", "");
                    node = Character.toLowerCase(node.charAt(0)) + node.substring(1);

                    String indent = "";

                    for (int i = 0; i < childListStack.size() - 1; i++) {
                        indent += (childListStack.get(i).size() > 0) ? "|  " : "   ";
                    }

                    builder.append(indent)
                            .append(childStack.isEmpty() ? "'- " : "|- ")
                            .append(node.startsWith("terminal") ? tree.getText() : node)
                            .append("\n");

                    if (tree.getChildCount() > 0) {
                        List<ParseTree> children = new ArrayList<ParseTree>();
                        for (int i = 0; i < tree.getChildCount(); i++) {
                            children.add(tree.getChild(i));
                        }
                        childListStack.add(children);
                    }
                }
            }
        }
    }
}
