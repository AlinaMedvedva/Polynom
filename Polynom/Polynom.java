package Polynom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

public class Polynom {
    private ArrayList<Double> koeff; //от a_0 до a_n

    public Polynom(){
        koeff = new ArrayList<>();
        koeff.add(0.0);
    }

    public Polynom(ArrayList<Double> k){
        koeff = new ArrayList<>();
        if(k.size() == 0){
            koeff.add(0.0);
            return;
        }
        Iterator<Double> it = k.iterator();
        while(it.hasNext()){
            koeff.add(it.next());
        }
    }

    public Polynom(int n){
        koeff = new ArrayList<>();
        if(n == 0) {
            koeff.add(0.0);
            return;
        }
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            koeff.add((double)rnd.nextInt(0,20));
        }
    }

    public double get_koeff(int index) throws MyException{
        if(index >= koeff.size()){
            throw new MyException("Полином меньше степени вводимого коэффициента");
        }
        else return koeff.get(index);
    }

    public void set_koeff(int index, double num) throws MyException{
        if(index >= koeff.size()){
            throw new MyException("Полином меньше степени вводимого коэффициента");
        }
        else koeff.set(index, num);
    }

    //вычисление значения в заданной точке
    public double calculation(double x){
        double result = 0.0;
        for (int i = 0; i < koeff.size(); i++) {
            result += koeff.get(i)*Math.pow(x, i);
        }
        return result;
    }


    //умножение на число
    public Polynom multi_by_a_num(double num){
        ArrayList<Double> new_koeff = new ArrayList<>();
        Iterator<Double> it = koeff.iterator();
        while(it.hasNext()){
            new_koeff.add(it.next()*num);
        }
        return new Polynom(new_koeff);
    }

    //умножение на многочлен
    public Polynom multi_by_a_polynom(Polynom b){
        ArrayList<Double> koeff_b = b.koeff;
        int mx = koeff_b.size()+koeff.size()-1;
        ArrayList<Double> new_koeff = new ArrayList<>();
        for (int i = 0; i < mx; i++) {
            new_koeff.add(0.0);
        }
        for (int i = 0; i < koeff.size(); i++) {
            for (int j = 0; j < koeff_b.size(); j++) {
                new_koeff.set(i+j, new_koeff.get(i+j) + koeff_b.get(j)*koeff.get(i));
            }
        }
        int rem = mx - 1;
        while(rem > 1){ //на случай, если у нас new_koeff полностью нулевой, то оставим один ноль
            if(new_koeff.get(rem) != 0.0) break;
            new_koeff.remove(rem);
            rem--;
        }
        return new Polynom((new_koeff));
    }

    //сложение полиномов
    public Polynom plus(Polynom b){
        ArrayList<Double> nw = new ArrayList<>();
        ArrayList<Double> mn = koeff.size() <= b.koeff.size()? koeff : b.koeff;
        ArrayList<Double> mx = koeff.size() > b.koeff.size()? koeff : b.koeff;
        for (int i = 0; i < mn.size(); i++) {
            nw.add(mn.get(i) + mx.get(i));
        }
        for (int i = mn.size(); i < mx.size(); i++) {
            nw.add(mx.get(i));
        }
        return new Polynom(nw);
    }

    //производная
    public Polynom derivative(){
        int n = koeff.size() - 1;
        ArrayList<Double> der = new ArrayList<>();
        if (n>0){
            for (int i = 1; i < koeff.size(); i++) {
                der.add(i - 1, koeff.get(i) * i);
            }
        }
        else der.add(0.0);
        return new Polynom(der);
    }

    //Интеграл
    public Polynom integral(double c){
        int n = koeff.size() + 1;
        ArrayList<Double> in = new ArrayList<>();
        in.add(0, c);
        for (int i = 1; i < n; i++) {
            in.add(i, koeff.get(i-1)/i);
        }
        return new Polynom(in);
    }

    //формула Ньютона-Лейбница
    public double newton_leibniz(double a, double b, double c){
        if(b < a){
            double temp = b;
            b = a;
            a = temp;
        }
        Polynom in = integral(c);
        return in.calculation(b) - in.calculation(a);
    }

    //сумма Римана
    public double summ_rim(double a, double b, double dist){ //отрезок [a, b] с разбиением dist
        if(b < a){
            double temp = b;
            b = a;
            a = temp;
        }
        double st = a;
        double sum = 0;
        while(st < b){
            double x = st + dist/2;
            sum += calculation(x);
            st+=dist;
        }
        return sum*=dist;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < koeff.size(); i++) {
            s += koeff.get(i).toString() + " ";
        }
        return s;
    }
}