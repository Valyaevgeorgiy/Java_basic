public class MyGenericClass<T> {
    public T obj;
    public MyGenericClass(T obj){
        this.obj = obj;
        // если obj является экземпляром класса А, то возможно вызвать методы класса А
        if (obj instanceof ClassA){
            ClassA a = (ClassA) obj;
            a.methodA();
        }
    }

    public T getObj(){
        return obj;
    }
}
