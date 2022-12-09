package com.siukatech.pocsimple.lkmtest;

import java.util.stream.Stream;

public class IkmTest {

    private int data = 10;
    static public void main (String [] args) {
        int a = 9, b = 2;
        float f;
        f = a / b;
        System.out.println("1 f = " + f);
        f = f / 2;
        System.out.println("2 f = " + f);
        f = a + b / f;
        System.out.println("3 f = " + f);
        f = 10 + 4 / 2 * 9 + 17;
        System.out.println("4 f = " + f);
//
//        int x = 5;
//        x = x >> 1;
//        x = ~ x;
//        x = !x;
//
//        List<String> letters = Arrays.asList("D","B","A","C","F","G");
//        Predicate<String>   p1 = s -> s.compareTo("C") > 0;
//        Predicate<String>   p2 = s -> s.equals("B");
//        letters.removeIf(p1.negate().or(p2));
//        letters.sort((s1,s2) -> s1.compareTo(s2));
//        System.out.print(letters);
//
//        String stringA= "A";
//        String stringB = "B";
//        String stringnull = null;
//        Formatter fmt = new Formatter(new StringBuffer("C"));
//        fmt.format("%s%s", stringA, stringB);
//        System.out.println("Line 1" + fmt);
//        fmt.format("%-2s", stringB);
//        System.out.println("Line 2" + fmt);
//        fmt.format("%b", stringnull);
//        System.out.println("Line 3" + fmt);
//
//        int x;
//        //System.out.print(x);
//
        System.out.println(Stream.of("green","yellow","blue")
                .max((s1,s2) -> s1.compareTo(s2))
                .filter(s -> s.endsWith("n"))
                .orElse("yellow")
        );
//        Runnable r = () -> System.out.println("Hi");
//        new Thread(r).start();
//
//        Integer x = 3;
//        Integer y = null;
//        try {
//            System.out.println(Integer.compareUnsigned(x, 3) == 0 || Integer.compareUnsigned(y, 0) == 0);
//        }
//        catch (Exception ex)
//        {
//            System.out.println(ex.getClass().toString());
//        }
//        try {
//            System.out.println(y.compareTo(null) == 0 || true);
//        }
//        catch (Exception ex)
//        {
//            System.out.println(ex.getClass().toString());
//        }
//
//        StringBuilder sb = new StringBuilder();
    }


}

//
//class ClassA {
//    protected String getStr() { return ""; }
//}
//
//class ClassB extends ClassA {
////    @Override
////    protected String getStr() { return ""; }
//@Override
//public String getStr() { return ""; }
//}
