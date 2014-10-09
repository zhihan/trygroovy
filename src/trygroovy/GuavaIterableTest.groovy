package trygroovy;

import com.google.common.collect.ImmutableList
import com.google.common.collect.Iterables
import com.google.common.base.Predicate
import com.google.common.collect.Lists

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
  void testAny() {
    def a = ImmutableList.<Integer>of(1,2,3)
    boolean x = Iterables.any(a, new Predicate<Integer>(){
          @Override
          boolean apply(Integer i) {
            return i>2
          }
        })
    assertTrue(x)

    boolean y = Iterables.any(a, new Predicate<Integer>(){
          @Override
          boolean apply(Integer i) {
            return i>3
          }
        })
    assertFalse(y)
  }
  
  @Test
  void testConcat() {
    def l = ImmutableList.of(ImmutableList.of(1,2),
      ImmutableList.of(3,4))
    def x = Iterables.concat(l)
    assertEquals(x.size(), 4)
  }
  
  @Test
  void testConsumingIterator() {
    def x = Lists.newArrayList(1,2,3,4)
    def y = Iterables.consumingIterable(x)
    for (c in y) {
      // consumes c
    }
    assertEquals(x.size(), 0) 
  }
  
  @Test
  void testContains(){
    def x = ImmutableList.of(1,2)
    assertTrue(Iterables.contains(x, 1))
  }
  
  @Test
  void testFilterForClass() {
    def x = ImmutableList.of(1, "1")
    def b = Iterables.filter(x, String.class)
    assertEquals(b.size(), 1)
  }
  
  @Test
  void testFilterForPredicate() {
    def x = ImmutableList.of(1, 2)
    def b = Iterables.filter(x, new Predicate<Integer>() {
      @Override
      boolean apply(Integer i) {
        return i > 1
      }
    })
    assertEquals(b.size(), 1)
  }
  
}
