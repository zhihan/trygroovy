package trygroovy;

import com.google.common.collect.Ordering
import com.google.common.base.Function
import com.google.common.collect.ImmutableList

import java.util.Comparator
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
    assertTrue(byFirstName.compare(a,b) < 0)
    assertTrue(byLastName.compare(a,b) > 0)
    
    def firstThenLast = byFirstName.compound(byLastName) 
    assertTrue(firstThenLast.compare(a,b) < 0)
    assertTrue(firstThenLast.compare(b,c) < 0) 
  }
  
  @Test
  public void testExplicitOrdering(){
    def l = ImmutableList.of("a", "c", "b")
    def ordering = Ordering.explicit(l)
    assertTrue(ordering.compare("a", "b")<0)
    assertTrue(ordering.compare("b", "c")>0)
  }
  
  @Test
  public void testUsingComparator() {
    // Test ordering using existing java style comparators
    def comp = new Comparator<People> () {
      int compare(People a, People b) {
        return a.firstName.compareTo(b.firstName)
      }
    }
    def a = new People("a", "1")
    def b = new People("b", "0")
    def ordering = Ordering.from(comp)
    assertTrue(ordering.compare(a,b) < 0)
  }
  
  @Test
  public void testMax() {
    def byFirstName = Ordering.natural().onResultOf(firstNameFn)
    def byLastName = Ordering.natural().onResultOf(lastNameFn)
    def firstThenLast = byLastName.compound(byFirstName)
    
    def people = ImmutableList.of(new People("a", "a"),
      new People("xx", "b"), new People("yy", "c"))
    
    assertEquals(firstThenLast.max(people).getFirstName(), "yy")
    assertEquals(firstThenLast.min(people).getFirstName(), "a")
  }
}
