package package1;

public class FirstChildClass extends ParentClass{
    public FirstChildClass() {
        System.out.println("This is child class 1");
    }

    public void methodP1() {
        publicMethod();
        //privateMethod();
        protectedMethod();
        defaultMethod();
    }

    @Override
    public void publicMethod() {
        super.publicMethod();
        System.out.println("This is publicMethod overriden in child class");
    }
}
