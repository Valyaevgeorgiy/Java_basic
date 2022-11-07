public class MainClass {

    public static void main(String args[]) {
        OuterClass outerclass = new OuterClass();
        OuterClass.InnerClass innerclass = outerclass.new InnerClass();
        OuterClass.StaticInnerClass staticinnerclass = new OuterClass.StaticInnerClass();
    }

}
