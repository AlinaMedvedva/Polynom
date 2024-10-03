package Polynom;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите степень первого полинома:");
        int n = in.nextInt();
        Polynom first = new Polynom(n);
        System.out.println("Polynom:");
        System.out.println(first.toString());
        Polynom integral = first.integral(2); //c = 2
        System.out.println("Integral:");
        System.out.println(integral.toString()); //+
        System.out.println("Derivate:");
        Polynom derivate = first.derivative();
        System.out.println(derivate.toString()); //+
        System.out.println();
        System.out.println("Введите отрезок для определённого интеграла и равномерное разбиние:");
        //вводим отрезок
        System.out.print("a = ");
        double a = in.nextDouble();
        System.out.print("b = ");
        double b = in.nextDouble();
        double new_leb = first.newton_leibniz(a, b, integral);
        //вводим равномерное разбиение для суммы Римана
        System.out.print("dist = ");
        double dist = in.nextDouble();
        double sum_rim = first.summ_rim(a, b, dist);
        System.out.printf("The Newton-Leibniz formula = %s%n", new_leb);
        System.out.printf("Sum of Riman = %s%n", sum_rim);


        System.out.println();
        //сложение и умножение полиномов
        System.out.print("Введите степень второго полинома:");
        int m = in.nextInt();//степень второго полинома
        Polynom second = new Polynom(m);
        System.out.println("Second Polynom:");
        System.out.println(second.toString());
        int p = 6;
        first.multi_by_a_num(p);
        System.out.println();
        System.out.println("Multi by a num first polynom");
        System.out.println(first);
        first.multi_by_a_polynom(second);
        System.out.println("Multi by second polynom, first polynom:");
        System.out.println(first);
        Polynom third = first.plus(second, first);
        System.out.println("first + second polynoms = third:");
        System.out.println(third);
    }
}
