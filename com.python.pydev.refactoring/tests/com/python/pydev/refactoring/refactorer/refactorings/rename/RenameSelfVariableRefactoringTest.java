/*
 * Created on Apr 9, 2006
 */
package com.python.pydev.refactoring.refactorer.refactorings.rename;


public class RenameSelfVariableRefactoringTest extends RefactoringTestBase{
    public static void main(String[] args) {
        try {
            RenameSelfVariableRefactoringTest test = new RenameSelfVariableRefactoringTest();
            test.setUp();
            test.testRename();
            test.tearDown();

            junit.textui.TestRunner.run(RenameSelfVariableRefactoringTest.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    
    public void testHierarchyRename1() throws Exception {
        String str ="" +
        "class Foo(Bar):\n" +
        "    def m1(self):\n" +
        "        self.%s = 1\n" +
        "        print self.%s\n" +
        "    def m2(self):\n" +
        "        print self.%s\n" +
        "class Bar:\n" +
        "    def m3(self):\n" +
        "        self.%s = 1\n" +
        "        print self.%s\n" +
        "\n" +
        "";
        int line = 3;
        int col = 20;
        checkRename(str, line, col, "aa", false, true);
        
    }
    
    public void testSimpleRename() throws Exception {
        String str ="" +
        "class Foo:\n" +
        "    def m1(self):\n" +
        "        self.%s = 1\n" +
        "        print self.%s\n" +
        "    def m2(self):\n" +
        "        print self.%s\n" +
        "";
        int line = 3;
        int col = 20;
        checkRename(str, line, col, "aa", false, true);
    }
    
    public void testClassMethodRename() throws Exception {
        String str ="" +
        "class Foo:\n" +
        "    def %s(self):\n" +
        "        print self.%s()\n" +
        "";
        int line = 2;
        int col = 19;
        checkRename(str, line, col, "m1", false, true);
    }
    
    public void testClassMethodRename2() throws Exception {
        String str ="" +
        "class Foo:\n" +
        "    def %s(self):\n" +
        "        print self.%s()\n" +
        "";
        int line = 1;
        int col = 9;
        checkRename(str, line, col, "m1", false, true);
    }
    
    public void testClassMethodRename3() throws Exception {
        String str ="" +
        "class Foo:\n" +
        "    def %s(self):\n" +
        "        print self.%s()\n" +
        "    %s = staticmethod(%s)" +
        "";
        int line = 1;
        int col = 9;
        checkRename(str, line, col, "m1", false, true);
    }

    public void testClassMethodRename4() throws Exception {
    	String str ="" +
    	"class Foo( object ):\n" +
    	"    def __init__(self):\n" +
    	"        self.%s = None\n" + //selected
    	"    def SetData(self):\n" +
    	"        self.%s.met1()\n" +
    	"\n" +
    	"";
    	int line = 2;
    	int col = 14;
    	checkRename(str, line, col, "blaa", false, true);
    }
    
    public void testClassMethodRename5() throws Exception {
    	String str ="" +
    	"class Foo( object ):\n" +
    	"    def __init__(self):\n" +
    	"        self.%s = None\n" + //selected
    	"    def SetData(self):\n" +
    	"        self.%s.met.ff\n" +
    	"        self.%s.met.ftt()\n" +
    	"\n" +
    	"";
    	int line = 2;
    	int col = 14;
    	checkRename(str, line, col, "blaa", false, true);
    }
    
    public void testDontRename() throws Exception {
        String str ="" +
        "class Foo:\n" +
        "    def %s(self):\n" +
        "        m1 = 10\n" +
        "        print m1\n" +
        "        print self.%s\n" +
        "";
        int line = 1;
        int col = 9;
        checkRename(str, line, col, "m1", false, true);
    }
    
    public void testDontRename2() throws Exception {
        String str ="" +
        "class Foo:\n" +
        "    def m1(self):\n" +
        "        %s = 10\n" +
        "        print %s\n" +
        "        print self.m1\n" +
        "";
        int line = 2;
        int col = 9;
        checkRename(str, line, col, "m1", false, true);
    }
    
    public void testRename() throws Exception {
    	String str ="" +
    	"class Foo:\n"+
		"    def _riskMaps(self):\n"+
		"        for a in self.%s().items():\n"+
		"            pass\n"+
		"    def %s(self):\n"+
		"        pass\n";
    	int line = 2;
    	int col = 23;
    	checkRename(str, line, col, "riskMapNames", false, true);
    }
    


}
