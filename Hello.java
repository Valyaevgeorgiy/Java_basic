// file Hello.java

int m, n = 2, 4

public class Hello {
    public static void main( String args[] )
    {
        System.out.println("Hello World!")
        System.out.println("My arguments:")
        for (int i = 0; i < args.length; i++)
        {
            System.out.println(" arg["+i"] :"+args[i]);
        }
        System.out.println("Good bye!")
    }
}