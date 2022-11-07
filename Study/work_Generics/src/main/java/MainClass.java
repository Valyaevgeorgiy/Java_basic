import java.util.ArrayList;

public class MainClass {

    // extends - верхняя формальная граница (наследники класса А)
    // super - нижняя формальная граница (родители класса С)
    public static void test(ArrayList<? extends ClassA> list){
        System.out.println("Good extend!");
    }

    public static void test2(ArrayList<? super ClassC> list){
        System.out.println("Good super!");
    }

    public static <T> void printClass(T obj) {
        System.out.println(obj.getClass().toGenericString());
    }

    public static void main(String[] args){
        MyGenericClass<ClassA> myGenericClass = new MyGenericClass<>(new ClassA());

        /*
        ArrayList<ClassB> list = new ArrayList<>();
        list.add(new ClassB());
        list.add(new ClassC());
        list.add(new ClassD());
        test(list);

        ArrayList<ClassD> list2 = new ArrayList<>();
        list.add(new ClassD());
        test(list2);
        test2(list);
        //test2(list2);
        */

        ArrayList list3 = new ArrayList();
        list3.add(new ClassA());
        list3.add(new ClassC());
        list3.add("sofgjoidgdo");
        list3.add(5);
        System.out.println(list3);

        printClass(new ClassC());
        printClass(5);
    }
}
