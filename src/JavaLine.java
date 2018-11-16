/**
 * Stores a line of a Java program for later analysis
 */
class JavaLine
{
    private String java    = ""; // Java code on line
    private String comment = ""; // The single line comment
    private int    lenJava = 0;  // The line length of just the java code
    private int block = 0;

    /**
     * Constructor
     * @param line of a java program
     */
    public JavaLine( String line, int lenJava )
    {
        line = line.trim();
        int PosOfComment = line.length();
        this.lenJava = lenJava;

        for (int i = 0; i < line.length() && PosOfComment == line.length(); i++) {
            switch (line.charAt(i)) {
                case '"':
                    for (i++; i < line.length(); i++) {
                        if (line.charAt(i) == '"' && line.charAt(i - 1) != '\\') {
                            break;
                        }
                    }
                    break;
                case '{':
                    block++;
                    break;
                case '}':
                    block--;
                    break;
                case '/':
                    if (i < line.length()-1 && line.charAt(i+1) == '/') {
                        PosOfComment = i;
                    }
                    break;
                default:
            }
        }
        if (block == -1) {
            this.lenJava--;
        }
        java = line.substring(0,PosOfComment);
        comment = line.substring(PosOfComment);
    }
    /**
     * Return the length of the Java part of the stored line.<PRE>
     * JavaLine j = new JavaLine("int a; // Declaration");
     * int jp = j.getJavaLineLength();
     * Would set jp the be 6</PRE>
     * @return The length of the Java code in the line
     */
    public int getJavaLineLength(){
        return java.length();
    }
    public int getIndentation() {
        return this.lenJava;
    }
    public boolean startOfBlock() {
        return block > 0 && block % 2 == 1;
    }
    /**
     * Return as an 'indented' line with the // comment
     * starting at column pos<PRE>
     * JavaLine j = new JavaLine("int a; // Declaration");
     * String res = j.returnLineWithCommentAt(10);
     * Would set res to be the following string:
     * int a;   // Declaration</PRE>
     * @param pos Start // comment at pos
     * @return A new version of the line with any // comment
     * starting at column pos.
     */
    public String returnLineWithCommentAt(int pos) {
        if (java.length() == 0 && comment.length() > 0) {
            return spaces(2*this.lenJava) + comment.trim();
        }
        if (comment.length() == 0) {
            return spaces(2*this.lenJava) + java;
        }
        return spaces(
                2*this.lenJava)
                + java
                + spaces(pos-(java.length() + 2*this.lenJava))
                + comment;
    }
    /**
     * Return a string of 'number' spaces.
     * @param number of spaces required
     * @return A string of 'number' spaces
     */
    public static String spaces( int number ){
        String spaces ="";
        for (int i=0; i<number; i++){
            spaces+= " ";
        }
        return spaces;
    }
}