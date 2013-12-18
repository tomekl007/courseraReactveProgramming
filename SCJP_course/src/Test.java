/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 11/19/13
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Date;
public class Test {
    public static void main(String[] args) {

    }
    public Object s(){
        return Void.TYPE;
    }
    String $s;
    String _s;
    String s2;

}

strictfp class s{

}

interface sup {
    String methhod() throws Exc;
}
class MemberClass{
    String s = "s";
}
class sub extends MemberClass implements sup {
    int i2;

    @Override
    public String methhod() throws RuntimeExceptionSUb {
        int i = 0;

        System.out.println(i);
        System.out.println(s);
        return "s";
    }
}
class sub2 implements sup {


    @Override
    public String methhod() {
        return "s";
    }
}

class sub3 implements sup {


    @Override
    public String methhod() throws SUbExc{
        return "s";
    }
}
class synch{

    native public String ss();




}


class RuntimeExceptionSUb extends RuntimeException{}
class Exc extends Exception{}
class SUbExc extends Exc{}

abstract class AbstarctClass implements sup{

}


enum S{
    ONE, TWO, THREE;
    private String sp;
     S(){}
     S(String s, Integer i){
         if(s.equals("two") && i.equals(2)) {
             sp = s;
         }
    }

}

enum Animals {
    DOG("woof"), CAT("meow"), FISH("burble");
    String sound;
    Animals(String s) { sound = s; }
    }
 class TestEnum { static Animals a;
     public static void main(String [] args) {
         System.out.println(a.DOG.sound + " " + a.FISH.sound);
        }
    }


class Top {
    public Top(String s) { System.out.print("B"); }
    //should be no arg constructor :
    public Top(){
        this("nothing");

    }
}
 class Bottom2 extends Top {
    public Bottom2(String s) { System.out.print("D"); }
    public static void main(String [] args) {
        new Bottom2("C");
        System.out.println(" ");
    } }

class Clidder {
    private final void flipper() { System.out.println("Clidder"); }
}
 class Clidlet extends Clidder {
    public final void flipper() { System.out.println("Clidlet"); }
     /*Although a final method cannot be overridden, in this case, the method
is private, and therefore hidden. The effect is that a new, accessible, method flipper is
created. Therefore, no polymorphism occurs in this example, the method invoked is simply
that of the child class, and no error occurs.*/
    public static void main(String [] args) {
        new Clidlet().flipper();
    } }

class AgedP {
    AgedP() {}
    public AgedP(int x) {
    }
}
 class Kinder extends AgedP {
    public Kinder(int x) {
        super();
    }
}

class X { void do1() { } }
 class Y extends X { void do2() { } }

   class Chrome {
 public static void main(String [] args) {
         X x1 = new X();
         X x2 = new Y();
         Y y1 = new Y();
        ((Y)x2).do2();
       }
     }


 class Redwood extends Tree {
     public static void main(String[] args) {
         new Redwood().go();
      }
    void go() {
     go2(new Tree(), new Redwood());
     go2((Redwood) new Tree(), new Redwood());
      }
     void go2(Tree t1, Redwood r1) {
        Redwood r2 = (Redwood)t1;   //runtime exception
        Tree t2 = (Tree)r1;
        }
     }
 class Tree { }


class Mammal {
    String name = "furry ";
    String makeNoise() { return "generic noise"; }
    }
 class Zebra extends Mammal {
   String name = "stripes ";
   String makeNoise() { return "bray"; }
    }
  class ZooKeeper {
     public static void main(String[] args) { new ZooKeeper().go(); }
     void go() {
     Mammal m = new Zebra();
     System.out.println(m.name + m.makeNoise());
        }
     }

class A { }
class B extends A { }
 class ComingThru {
 static String s = "-";
    public static void main(String[] args) {
       A[] aa = new A[2];
       B[] ba = new B[2];
        sifter(aa);
        sifter(ba);
        sifter(7);
        System.out.println(s);
        }
     static void sifter(A[]... a2) { s += "1"; }
     static void sifter(B[]... b1) { s += "2"; }
     static void sifter(B[] b1) { s += "3"; }
     static void sifter(Object o) { s += "4"; }
     /*In general, overloaded var-args
     methods are chosen last. Remember that arrays
    are objects. Finally, an int can be boxed to an Integer and then "widened" to an Object*/
     }


class Supercalss{}
class Subclass extends Supercalss{}

class ChapterThree{
    public static void main(String[] args) {
        //Reference variables can refer to subclasses of the declared type but not to
        //superclasses.
        Supercalss supercalss = new Subclass();//superclass always has methods which are in subclass aslo

        //Subclass subclass = new Supercalss();//because subclass will have methods which are not in superclass

        int[] array = new int[3];
        int array2 [] = new int[4];
        array2 = array;
        //System.out.println(array2[3]);
        ChapterThree chapterThree = new ChapterThree();
        System.out.println(chapterThree.s);


    }
    String s ="";
    static {
        System.out.println("Static");
        int s = 2;
    }

    ChapterThree(){
        System.out.println("constructor");
        s+= " constructor";
    }
    {
        System.out.println("instance");
        int w = 0;
        s+=" instance";
    }
}

class GarbageCollectorEx{
    public static void main(String[] args) {

                Runtime rt = Runtime.getRuntime();
                System.out.println("Total JVM memory: "
                        + rt.totalMemory());
                System.out.println("Before Memory = "
                        + rt.freeMemory());
                Date d = null;
                for(int i = 0;i<10000;i++) {
                 d = new Date();
                  d = null;
                  }
                 System.out.println("After Memory = "
                     + rt.freeMemory());
                 rt.gc(); // an alternate to System.gc()
                 System.out.println("After GC Memory = "
                     + rt.freeMemory());
                 }
}

class ClassWithFinalizeMethod{
    public static void main(String[] args) {
        ClassWithFinalizeMethod c = new ClassWithFinalizeMethod();
        while(true){
            c= null;
            System.gc();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize ");
    }
}

class Alien {
    String invade(short ships) { return "a few"; }
    String invade(short... ships) { return "many"; }
}
class Defender {
    public static void main(String [] args) {
        System.out.println(new Alien().invade((short)7));
    } }

class Dims {
     public static void main(String[] args) {
        int[][] a = {{1,2,}, {3,4}};
        int[] b = (int[]) a[1];
        Object o1 = a;
        int[][] a2 = (int[][]) o1;
        int[] b2 = (int[]) o1;
        System.out.println(b[1]);
        } }

class Mixer {
    Mixer() { }
    Mixer(Mixer m) { m1 = m; }
    Mixer m1;
    public static void main(String[] args) {
        Mixer m2 = new Mixer();
        Mixer m3 = new Mixer(m2); m3.go();
        Mixer m4 = m3.m1; m4.go();
        Mixer m5 = m2.m1; m5.go();
    }
    void go() { System.out.print("hi "); }
}

/**
 * correct. Static init blocks are executed at class loading time, instance init blocks run
 right after the call to super() in a constructor. When multiple init blocks of a single type
 occur in a class, they run in order, from the top down.
 */
class Bird {
    { System.out.print("b1 "); }
    public Bird() { System.out.print("b2 "); }
}
class Raptor extends Bird {
    static { System.out.print("r1 "); }
    public Raptor() { System.out.print("r2 "); }
    { System.out.print("r3 "); }
    static { System.out.print("r4 "); }
}
class Hawk extends Raptor {
    public static void main(String[] args) {
        System.out.print("pre ");
        new Hawk();
        System.out.println("hawk ");
    }
}

class Bridge {
     public enum Suits {
         CLUBS(20), DIAMONDS(20), HEARTS(30), SPADES(30),
                 NOTRUMP(40) { public int getValue(int bid) {
            return ((bid-1)*30)+40; } };
      Suits(int points) { this.points = points; }
      private int points;
      public int getValue(int bid) { return points * bid; }
       }
     public static void main(String[] args) {
         System.out.println(Suits.NOTRUMP.getValue(3));
         System.out.println(Suits.SPADES + " " + Suits.SPADES.points);
         System.out.println(Suits.values());
         }
     }

