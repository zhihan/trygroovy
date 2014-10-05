package trygroovy;

import com.google.common.collect.Ordering

import static org.junit.Assert.*;
import org.junit.Test;

class GuavaOrderingTest {
  class People {
    String firstName;
    String lastName;
    
    People(String f, String l) {
      firstName = f;
      lastName = l;
    }
  }
  
  
  @Test
  public void testEqualOrdering() {
    def a = new People("a", "1")
    def b = new People("b", "0")
    def ordering = Ordering.allEqual()
    assertEquals(ordering.compare(a,b), 0)
  }

}
