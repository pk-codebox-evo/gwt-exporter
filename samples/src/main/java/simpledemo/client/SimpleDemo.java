package simpledemo.client;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportClosure;
import org.timepedia.exporter.client.ExportPackage;
import org.timepedia.exporter.client.Exportable;
import org.timepedia.exporter.client.ExporterUtil;
import org.timepedia.exporter.client.NoExport;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class SimpleDemo implements EntryPoint {

  
  public void onModuleLoad() {
    GWT.create(C.class);
    runJsTests1();
    
    ExporterUtil.exportAll();
    runJsTests();
  }
  
  public static <T> void print(T s) {
    RootPanel.get().add(new Label(s.toString()));
  }
  
  public static <T> void mAssertEqual(T a, T b) {
    if (a.toString().equals(b.toString())) {
      print("OK -> " + b);
    } else {
      print("ERROR -> " + a.toString() + " <=> " + b.toString() + " ["
          + a.getClass().getName() + ", " + b.getClass().getName() + "]");
    }
  }

  @ExportPackage("gwt")
  @Export
  public static class HelloAbstract implements Exportable {
    public String helloAbstract(){
      return this.getClass().getName();
    }
    
    @NoExport
    public String noHelloAbstract(){
      return this.getClass().getName();
    }
  }
  
  @ExportPackage("gwt")
  @Export
  public static class HelloClass extends HelloAbstract implements Exportable {
    public String hello(){
      return this.getClass().getName();
    }

    public static String[] test0(char c, byte b, int i, double d, float f, String s, Object o, Exportable e) {
      String[] ret = new String[8];
      ret[0] = "" + (int)c;
      ret[1] = "" + b;
      ret[2] = "" + i;
      ret[3] = "" + d;
      ret[4] = "" + f;
      ret[5] = "" + s;
      ret[6] = "" + o.getClass().getName();
      ret[7] = "" + e.getClass().getName();
      return ret;
    }

    public static int[] test1(char[]c, byte[] b, int[] i, double[]d, float[] f, long[] l, String[] s, Object[] o, Exportable[] e) {
      int[] ret = new int[9];
      ret[0] = c.length;
      ret[1] = b.length;
      ret[2] = i.length;
      ret[3] = d.length;
      ret[4] = f.length;
      ret[5] = l.length;
      ret[6] = s.length;
      ret[7] = o.length;
      ret[8] = e.length;
      return ret;
    }

    public static long[] test2() {
      return new long[]{1l,2l};
    }
    
    public static Exportable[] test3() {
      return new HelloClass[]{new HelloClass()};
    }
    
    public static char test4() {
      return 1;
    }
    
    public static byte test5() {
      return 2;
    }
    
    public static int test6() {
      return 3;
    }
    
    public static double test7() {
      return 4;
    }
    
    public static float test8() {
      return 5;
    }
    
    public static String test9() {
      return "A";
    }
    
    public static JavaScriptObject test10() {
      return new Label("").getElement();
    }
    
    public static long test11() {
      return 6;
    }
    
    public static String test12(long l){
      return "" + l;
    }
    
    public static long test13(long l, double d) {
      return l + (long)d;
    }
    
    public long test14(long l, double d, long[] a) {
      return l + (long)d + a[0];
    }
    
    public long[] test15(long[] a) {
      return a;
    }

    public static String test16(long l) {
      return "" + l;
    }

    public static long test16(long a, long b) {
      return (a + b);
    }

    public String test17(long l) {
      return "" + l;
    }

    public long test17(long a, long b) {
      return (a + b);
    }
    
    public static String test18(String a, String[] b) {
      return a + "_" + b.length;
    }

    public static String test18(String a, String b, String[] c) {
      return a + "_" + b + "_" + c.length;
    }
    
    public String test19(String a, String[] b) {
      return test18(a, b);
    }

    public String test19(String a, String b, String[] c) {
      return test18(a, b, c);
    }
    
    public static String test20(String a, long b, String...c) {
      return a + "_" + b + "_" + c.length;
    }

    public String test21(String a, long b, String...c) {
      return test20(a, b, c);
    }
    
  }
  
  @ExportPackage("gwt")
  @Export
  public static class Foo implements Exportable {
    
    String n = "foo";
    public Foo() {
    }
    public Foo(String id) {
      n= id;
    }
    public Foo(String id, String a) {
      n= id + a;
    }
    public String toString(){
      return n;
    }
    public String toString(String a){
      return n + ">" + a;
    }
    
    @ExportClosure
    public interface Closure extends Exportable {
      public String execute(String par1, String par2);
    }
    
    public String executeJsClosure(Closure closure){
       return closure.execute("Hello", "Friend");
    }
  }
  
  public static interface MInterface extends Exportable {
    @Export
    String m1();
    String m1(int a);
    @Export
    String m1(int a, int b);
  }
  
  public static abstract class MBase implements MInterface {
    @Export
    public String m0() {
      return "m0";
    }
    public String m1() {
      return "m1";
    }
    public String m1(int a) {
      return "m1-" + a;
    }
    public String m1(int a, int b) {
      return "m1-" + a + b;
    }
    @Export
    public String m2() {
      return "m2";
    }
    @Export
    public String m5() {
      return "m5";
    }
  }
  
  @ExportPackage("gwt")
  public static class MClass extends MBase {
    @Export
    public String m0() {
      return "om0";
    }
    @Export
    public String m3() {
      return "m3";
    }
    public String m4() {
      return "m4";
    }
    @NoExport
    public String m5() {
      return super.m5();
    }
  }
  

  public static class A implements Exportable {
    public B convertToB() {
      return new B();
    }
    @Export
    public String foo() {
      return "foo";
    }
    @Export
    public String toString() {
      return this.getClass().getName().replaceAll("^.+[\\.\\$]", "");
    }
  }

  public static class B extends A {
    public C convertToC() {
      return new C();
    }
    public String toString() {
      return "A";
    }
  }

  @ExportPackage("gwt")
  public static class C extends A {
    @Export
    public A convertToA() {
      return new A();
    }

    @Export
    public String ma(String a, String[] b) {
      return "kk2";
    }
    @Export
    public String ma(String[] a) {
      return "kk1";
    }
    @Export
    public String m2(String[] a, long[] b) {
      return "kk1";
    }
    
    @Export
    public String m2(long a) {
      return "kk1";
    }
    @Export
    public String mlong(int a, long b) {
      return "";
    }

    @Export
    public String mlong(long b) {
      return "";
    }
    
    @Export
    public String marray(String a, String ... s) {
      String ret = a + " - " + (s == null ? "null" : s.length) ;
      System.out.println("Calling marray " + ret);
      return ret ;
    }
  }
  
  public native JavaScriptObject runJsTests1() /*-{
    p = function(a, b) {@simpledemo.client.SimpleDemo::mAssertEqual(Ljava/lang/Object;Ljava/lang/Object;)(a, b);}
    
    var c = new $wnd.gwt.SimpleDemo.C();
    p("C", c); 
    p("C", c.toString()); 
    var a = c.convertToA();
    p("A", a);
    a = new $wnd.simpledemo.SimpleDemo.A();
    p("A", a);
    
    // GWT.create(C) should not export B
    var c = $wnd.simpledemo.SimpleDemo.B ? "defined" : "undefined";
    p("undefined", c); 
  }-*/;

  public native JavaScriptObject runJsTests() /*-{
    p = function(a, b) {@simpledemo.client.SimpleDemo::mAssertEqual(Ljava/lang/Object;Ljava/lang/Object;)(a, b);}
    
    var h = new $wnd.gwt.SimpleDemo.HelloClass();
    p("1,2,3,4.0,5.0,S,com.google.gwt.core.client.JavaScriptObject$,simpledemo.client.SimpleDemo$HelloClass", $wnd.gwt.SimpleDemo.HelloClass.test0(1, 2, 3, 4, 5, "S", window.document, h));
    p("1,1,1,1,1,2,2,2,1", $wnd.gwt.SimpleDemo.HelloClass.test1([0], [0], [0], [0], [0], [1,2], ["a","b"], [window,document], [h]));
    p("1,2", $wnd.gwt.SimpleDemo.HelloClass.test2());
    p("simpledemo.client.SimpleDemo$HelloClass", $wnd.gwt.SimpleDemo.HelloClass.test3()[0].hello());
    p("simpledemo.client.SimpleDemo$HelloClass", $wnd.gwt.SimpleDemo.HelloClass.test3()[0].helloAbstract());
    p("undefined", "" + $wnd.gwt.SimpleDemo.HelloClass.test3()[0].noHelloAbstract);
    
    p("1", "" + $wnd.gwt.SimpleDemo.HelloClass.test4(1, "A"));
    p("2", "" + $wnd.gwt.SimpleDemo.HelloClass.test5());
    p("3", "" + $wnd.gwt.SimpleDemo.HelloClass.test6());
    p("4", "" + $wnd.gwt.SimpleDemo.HelloClass.test7());
    p("5", "" + $wnd.gwt.SimpleDemo.HelloClass.test8());
    p("A", "" + $wnd.gwt.SimpleDemo.HelloClass.test9());
    p("div", "" + $wnd.gwt.SimpleDemo.HelloClass.test10().tagName.toLowerCase());
    p("6", "" + $wnd.gwt.SimpleDemo.HelloClass.test11());
    p("1", "" + $wnd.gwt.SimpleDemo.HelloClass.test12(1));
    p("5", "" + $wnd.gwt.SimpleDemo.HelloClass.test13(2, 3));
    p("4", "" + $wnd.gwt.SimpleDemo.HelloClass.test16(4));
    p("14", "" + $wnd.gwt.SimpleDemo.HelloClass.test16(4, 10));
    p("a_2", "" + $wnd.gwt.SimpleDemo.HelloClass.test18("a", ["b", "c"]));
    p("a_b_1", "" + $wnd.gwt.SimpleDemo.HelloClass.test18("a", "b", ["c"]));
    p("a_1_0", "" + $wnd.gwt.SimpleDemo.HelloClass.test20("a", 1));
    p("a_1_3", "" + $wnd.gwt.SimpleDemo.HelloClass.test20("a", 1, "a", "e", "i"));
    
    var h = new $wnd.gwt.SimpleDemo.HelloClass();
    p("102", "" + h.test14(1, 1, [100]));
    p("100,200", "" + h.test15([100, 200]));
    p("5", "" + h.test17(5));
    p("15", "" + h.test17(5,10));
    p("a_2", "" + h.test19("a", ["b", "c"]));
    p("a_b_1", "" + h.test19("a", "b", ["c"]));
    p("a_1_0", "" + h.test21("a", 1));
    p("a_1_3", "" + h.test21("a", 1, "a", "e", "i"));
    
    var v1 = new $wnd.gwt.SimpleDemo.Foo();
    p("foo", v1);
    var v2 = new $wnd.gwt.SimpleDemo.Foo("foo2");
    p("foo2", v2);
    var v3 = new $wnd.gwt.SimpleDemo.Foo("foo3", "bbb");
    p("foo3bbb", v3);
    p("foo3bbb>ccc", v3.toString("ccc"));
    p("Hello,Friend", v3.executeJsClosure(function(arg1, arg2) {
        return arg1 + "," + arg2;
    }));    
    
    var m = new $wnd.gwt.SimpleDemo.MClass();
    p("om0", m.m0());
    p("m1", m.m1());
    p("m1-23", m.m1(2, 3));
    p("m2", m.m2());
    p("m2", m.m2());
    p("m3", m.m3());
    var m5 = $wnd.gwt.SimpleDemo.MClass.m5 ? "defined" : "undefined";
    p("undefined", m5);
    
    // exportAll must export B
    var c = $wnd.simpledemo.SimpleDemo.B ? "defined" : "undefined";
    p("defined", c); 
  }-*/;
  
}
