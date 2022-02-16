
public class Hello {
    public static void main( String args[] ) {
        
        System.out.println("Hello World!") // вывод на экран
            
        System.out.println("My arguments:")
        for (int i = 0; i < args.length; i++) { // структура примитивного цикла for 
            System.out.println(" arg["+i"] :"+args[i]);
        }
        
        System.out.println("Good bye!")
    }
}
