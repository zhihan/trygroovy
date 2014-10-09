package trygroovy;

import com.google.common.collect.ImmutableList
import com.google.common.collect.Iterables
import com.google.common.base.Predicate

import static org.junit.Assert.*
import org.junit.Test;

class GuavaIterableTest {

  @Test
  public void testAddAll() {
    def b = new ArrayList<Integer>()
    def a = ImmutableList.<Integer>of(1,2,3)
    Boolean x = Iterables.addAll(b, a)
    assertTrue(x)
    assertEquals(b.size(), 3)
    assertTrue(b.contains(1))
  }

  @Test
  public void testAll() {
    def a = ImmutableList.<Integer>of(1,2,3)
    boolean x = Iterables.all(a, new Predicate<Integer>(){
          @Override
          boolean apply(Integer i) {
            return i>0;
          }
        })
    assertTrue(x)

    boolean y = Iterables.all(a, new Predicate<Integer>(){
          @Override
          boolean apply(Integer i) {
            return i>1;
          }
        })
    assertFalse(y)
  }
  
  @Test
}
