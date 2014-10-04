package trygroovy;

import com.google.common.base.Functions;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import static org.junit.Assert.*;
import org.junit.Test;

/** A simple class that illustrates the use of Guava Function 
 * library. */
class GuavaFunctionTest {

	@Test
	public void testConstant() {
		def f = Functions.constant(new Integer(1))
		// A constant function always returns the value
		assertEquals(f.apply("anything"), new Integer(1))
	}

	@Test
	public void testMyConstant() {
		// Create my own constant functions
		def f = new Function<Integer, Integer> () {
					@Override
					Integer apply(Integer x) {
						return 1;
					}
				}
		assertEquals(f.apply(4), new Integer(1))
  }
	
  @Test
  public void testIdentity(){
    def f = Functions.<String>identity()
    assertEquals(f.apply("a"), "a")
    assertEquals(f.apply("b"), "b")
  }

  @Test
  public void testToString(){
    def f = Functions.<String>toStringFunction()
    assertEquals("a".toString(), f.apply("a"))
  }
  
  @Test
	public void testOfMap(){
    def m = ImmutableMap.of(1,2,2,4)
    def f = Functions.forMap(m)
    assertEquals(f.apply(1), 2)
    assertEquals(f.apply(2), 4)
	 // Trying other values will cause exception
    
    def g = Functions.forMap(m, 0)
    assertEquals(g.apply(1),2)
    assertEquals(g.apply(0),0) // default value
  }
  
  @Test
  public void testCompose(){
    def m = ImmutableMap.of(1,2,2,4)
    def f = Functions.forMap(m, 0)
    def g = Functions.forMap(m, 0)
    def x = Functions.compose(g, f)
    assertEquals(x.apply(1), 4)
    assertEquals(x.apply(2), 0)
  }
}
