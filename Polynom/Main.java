package Polynom;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        int n = 5, m = 6, num = 8;
        Polynom first = new Polynom(n);
        Polynom second = new Polynom(m);

        Polynom in = first.integral(2);
        Polynom der = first.derivative();
        System.out.println("First polynom:");
        System.out.println(first);
        System.out.println("Integral (first)");
        System.out.println(in);
        System.out.println("Derivate (first)");
        System.out.println(der);
        double p;
        System.out.println("Ошибка получения коэфициента индекса 6 из полинома степени 4");
        try {
            p = first.get_koeff(6);
        }catch (MyException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println("New-leb formula = " + first.newton_leibniz(1.0, 2.0, 2.0));
        System.out.println("Sum of Riman = " + first.summ_rim(1.0, 2.0, 0.1));
        System.out.println();
        Polynom third = first.plus(second);
        Polynom fourth = first.multi_by_a_polynom(second);
        Polynom fifth = first.multi_by_a_num(num);
        System.out.println("First:");
        System.out.println(first);
        System.out.println("Second:");
        System.out.println(second);
        System.out.println("first+second = third:");
        System.out.println(third);
        System.out.println("first * second = fourth:");
        System.out.println(fourth);
        System.out.println("first * 8 = fifth:");
        System.out.println(fifth);
    }
}
