package testing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   MovementTest.class,
   DisplayTest.class
})
public class AllTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	/*public static void main(String[] args){
		 Result result = JUnitCore.runClasses(MovementTest.class);
		    for (Failure failure : result.getFailures()) {
		      System.out.println(failure.toString());
		    }
	}*/

}
