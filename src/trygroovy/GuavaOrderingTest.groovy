package trygroovy;

import com.google.common.collect.Ordering
import com.google.common.base.Function

import static org.junit.Assert.*
import org.junit.Test

class GuavaOrderingTest {
  class People {
    String firstName;
    String lastName;
    
    People(String f, String l) {
      firstName = f;
      lastName = l;
    }
  }
  
  private def firstNameFn = new Function<People, String>(){
    String apply(People p) {
      return p.firstName;
    }
  }
  
  private def lastNameFn = new Function<People, String>(){
    String apply(People p) {
      return p.lastName;
    }
  }
  
  @Test
  public void testEqualOrdering() {
    def a = new People("a", "1")
    def b = new People("b", "0")
    def ordering = Ordering.allEqual()
    assertEquals(ordering.compare(a,b), 0)
  }
  
  @Test
  public void testNaturalOrdering() {
    def a = new People("a", "1")
    def b = new People("b", "0")
    def ordering = Ordering.natural()
    assertTrue(ordering.compare(a.firstName, b.firstName) < 0)
    assertTrue(ordering.compare(a.lastName, b.lastName) > 0)
  }
  
  
  @Test
  public void testCompound(){
    def a = new People("a", "1")
    def b = new People("b", "0")
    def c = new People("b", "1")
    def byFirstName = Ordering.natural().onResultOf(firstNameFn)
    def byLastName = Ordering.natural().onResultOf(lastNameFn)
    assertEquals(byFirstName.compare(a,b), -1)
    assertEquals(byLastName.compare(a,b), 1)
    
    def firstThenLast = byFirstName.compound(byLastName) 
    assertEquals(firstThenLast.compare(a,b), -1)
    assertEquals(firstThenLast.compare(b,c), -1)
    
  }

}
