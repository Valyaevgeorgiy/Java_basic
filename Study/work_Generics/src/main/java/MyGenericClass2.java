public class MyGenericClass2 <T extends ClassD>{
    public T obj;
    public MyGenericClass2(T obj){
        this.obj = obj;
        obj.methodA();
        obj.methodB();
        obj.methodC();
        obj.methodD();
    }
}
