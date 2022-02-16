// Наглядное наследование методов родительского класса в класс-потомок

public class Advice extends Thought {

    @Override
    public void message() {
        System.out.println("Advice.message");
        super.message(); // вызов переопределённого метода родительского класса
    }
}
