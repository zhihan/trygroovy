package trygroovy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class GuavaObjectMethodTest {
  public class People {
    String firstName;
    String lastName;
    
    int compareTo(People that) {
      return ComparisonChain.start()
      .compare(this.firstName, that.firstName)
      .compare(this.lastName, that.lastName)
      .result();
    }
    
    @Override
    String toString() {
      return Objects.toStringHelper(this)
      .add("firstName", firstName)
      .add("lastName", lastName)
      .toString();
    }
  }
  
  @Test
  public void testEquals() {
    // Test the robust equal method
    assertTrue(Objects.equal("a", "a"))
    assertFalse(Objects.equal(null, "a"))
    assertFalse(Objects.equal("a", null))
    assertTrue(Objects.equal(null, null))
  }
  
  @Test
  public void testComparisonChain() {
    // Test use the comparison chain.
    People a = new People(firstName: "a", lastName: "b")
    People b = new People(firstName: "b", lastName: "c")
    assertTrue(a.compareTo(b) != 0)
    assertTrue(a.compareTo(a) == 0)
  }
  
  @Test
  public void testToString(){
    // Try to use the toStringHelper
    People a = new People(firstName: "Natalie", lastName: "Han")
    String s = a.toString()
    System.out.println(s) // Print the toString to console
    assertTrue(s.length() > 10)
  }
}
