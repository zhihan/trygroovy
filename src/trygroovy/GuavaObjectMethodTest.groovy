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
  }
  
  @Test
  public void testEquals() {
    assertTrue(Objects.equal("a", "a"))
    assertFalse(Objects.equal(null, "a"))
    assertFalse(Objects.equal("a", null))
    assertTrue(Objects.equal(null, null))
  }
  
  @Test
  public void testComparisonChain() {
    People a = new People(firstName: "a", lastName: "b")
    People b = new People(firstName: "b", lastName: "c")
    assertTrue(a.compareTo(b) != 0)
  }
}
