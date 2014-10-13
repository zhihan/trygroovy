package trygroovy;

import com.google.common.collect.ImmutableList
import com.google.common.collect.Iterables
import com.google.common.base.Predicate
import com.google.common.base.Function
import com.google.common.collect.Lists
import com.google.common.collect.Ordering

import static org.junit.Assert.*
import org.junit.Test;
/**
 * Go through the API of the Guava Iterables package and test
 * the functions in the package.
 */
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
  
  @Test 
  void testElementsEqual() {
    def x = ImmutableList.of(1,2)
    def y = Lists.newArrayList(1,2)
    assertTrue(Iterables.elementsEqual(x, y))
  }
  
  @Test
  void testFind() {
    def x = ImmutableList.of(1,2,3)
    Integer b = Iterables.find(x, new Predicate<Integer>() {
      @Override boolean apply(Integer i) {
        return (i == 2);
        }
      })
    assertEquals(b, 2)
    
    Integer c = Iterables.find(x, new Predicate<Integer>() {
          @Override
          boolean apply(Integer i) {
            return (i > 5);
          }
        }, 6)
    assertEquals(c, 6)
  }
  
  @Test
  void testGet() {
    def x = ImmutableList.of(1,2,3)
    Integer y = Iterables.get(x, 1)
    assertEquals(y, 2)
    Integer z = Iterables.get(x, 5, 0) // default value
    assertEquals(z, 0)
  }
  
  @Test
  void testMergeSort(){
    // If the input is already sorted, the output will be a merged
    // copy of the sorted items.
    def x = ImmutableList.of(ImmutableList.of(2,4), 
      ImmutableList.of(1,3))
    def y = Iterables.mergeSorted(x, Ordering.natural()).toList()
    assertTrue(Iterables.elementsEqual(y, 
      ImmutableList.of(1,2,3,4)))
  }
  
  @Test
  void testPartition() {
    def x = ImmutableList.of(1,2,3,4)
    def y = Iterables.partition(x, 2)
    assertTrue(Iterables.elementsEqual(y.get(0), ImmutableList.of(1,2)))
    assertTrue(Iterables.elementsEqual(y.get(1), ImmutableList.of(3,4)))  
  }
  
  @Test
  void testRemoveAll() {
    def x = Lists.newArrayList(1,2)
    def y = Iterables.removeAll(x, ImmutableList.of(1,2,3))
    assertTrue(x.isEmpty())
    assertTrue(y)
  }
  
  @Test
  void testRemoveIf() {
    def x = Lists.newArrayList(1,2,3,4)
    def y = Iterables.removeIf(x, new Predicate<Integer>(){
      @Override
      boolean apply(Integer i) {
        return i > 2;
      }
    })
    assertTrue(x.size() == 2)
    assertTrue(y)
  }
  
  @Test
  void testTransform() {
    def x = ImmutableList.of(1,2,3)
    def y = Iterables.transform(x, new Function<Integer, Integer>(){
      @Override
      Integer apply(Integer i) {
        return i*2;
      } 
    })
    
    assertTrue(Iterables.elementsEqual(y, ImmutableList.of(2,4,6)))
  }
}
