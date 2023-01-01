package org.example;

import org.jetbrains.annotations.NotNull;

@ProgrammingDetails(programmerType = "junior", programmerName = "yuri", dateOfWriting = "23.9.1999",reviewers ={"Ahmad","Maria"})
public class MyClass extends MyClassParent{
    @Deprecated(since = "4.5", forRemoval = true)
    public void depricatedMethod()
    {
        System.out.println("Depricated methods");
    }

    @Override
    public void overridenMethods()
    {
        System.out.println("This is an overriden methods in parent class");
    }

    //Обратите внимание где здесь находится аннотация
    public void  annotatedParamsMethod(@NotNull Object  object)
    {
        if(object==null)
        {
            System.out.println("This is not supposed to be null!");
        }
    }
}
