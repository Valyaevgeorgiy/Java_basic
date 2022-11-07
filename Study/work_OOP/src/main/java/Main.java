import package1.*;
import package1.SecondChildClass;
import package2.*;

public class Main {

    public static void main(String[] args) {

        FirstChildClass child = new FirstChildClass();
        child.publicMethod();

        package1.SecondChildClass secondChildClass1 = new package1.SecondChildClass();
        package2.SecondChildClass secondChildClass2 = new package2.SecondChildClass();

    }
}
