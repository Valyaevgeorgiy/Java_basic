package package1;

public class ParentClass {

    public ParentClass() {
        System.out.println("This is parent class!");
    }

    //
    public void publicMethod() {
        System.out.println("This is public method in parent class");
    }

    //
    private void privateMethod() {
        System.out.println("This is private method in parent class");
    }

    //
    protected void protectedMethod() {
        System.out.println("This is protected method in parent class");
    }

    void defaultMethod() {
        System.out.println("This is default method in parent class");
    }
}
