package org.example;

import java.lang.annotation.Annotation;
/*
Аннотация- это форма метаданных, предоставляющие информацию о программе.
Могут использоваться:
  -> компилятором для обнаружения ошибок и подавления предупреждений.
  -> для обработки во время компиляции и развертывания: Программа может создавать код, XML-файлы и т.п. на основе аннотаций.
  -> для обработки во время выполнения: Некоторые аннотации могут использоваться во время выполнения программы.
TODO: Что такое повторяющаяся аннотация? Как сделать ProgrammingЫDetails повторяющейся аннотацией?
 */
public class Main {

    public static void main(String[] args) {

        MyClass myClass=new MyClass();
        myClass.depricatedMethod();
        //myClass.annotatedParamsMethod(null);
        Class<?> myClassClass=myClass.getClass();
        Annotation[] annotations=myClassClass.getAnnotations();
        for (Annotation a:annotations)
        {
            System.out.println(a);
        }
        if(myClassClass.isAnnotationPresent(ProgrammingDetails.class))
        {
            ProgrammingDetails annotation=myClassClass.getAnnotation(ProgrammingDetails.class);
            String str="This class is written by a "+annotation.programmerType()
                    +" progammer, whose name is "+annotation.programmerName()
                    +". It took him/her "+annotation.durationOfWriting()+" to finish the code starting from"
                    +annotation.dateOfWriting()+". Code was reviewed by (";
            for(String s:annotation.reviewers())
            {
                str+=s+", ";
            }
            str+=").";
            System.out.println(str);
        }

    }
}