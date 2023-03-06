public class OuterClass {

    public OuterClass(){
        System.out.println("This is OuterClass.");
    }

    public int publicVar = 1;
    private int privateVar = 2;
    protected int protectedVar = 3;
    int var = 4;

    public static int staticVar = 5;

    public void publicMethod() {
        System.out.println("This is publicMethod.");
    }

    public static void staticMethod() {
        System.out.println("This is staticMethod.");
    }

    private void privateMethod() {
        System.out.println("This is privateMethod.");
    }

    protected void protectedMethod() {
        System.out.println("This is protectedMethod.");
    }

    void defaultMethod() {
        System.out.println("This is defaultMethod.");
    }

    public class InnerClass {

        public InnerClass() {
            System.out.println(publicVar);
            System.out.println(privateVar);
            System.out.println(protectedVar);
            System.out.println(var);

            publicMethod();
            privateMethod();
            protectedMethod();
            defaultMethod();
        }

    }

    public static class StaticInnerClass {

        public StaticInnerClass() {
            System.out.println(staticVar);
            staticMethod();
        }
    }
}
