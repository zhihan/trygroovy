package trygroovy;

import static org.junit.Assert.*;
import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.Range

import org.junit.Test;

class GuavaRangeMapTest {

  @Test
  void testGet() {
    def x = TreeRangeMap.<Integer,Integer>create()
    x.put(Range.openClosed(0,3), 1)
    x.put(Range.openClosed(3,6), 2)
    assertEquals(x.get(4), 2)
    assertEquals(x.get(3), 1)
    assertEquals(x.get(1), 1)
    
    def y = x.getEntry(1)
    assertEquals(y.getKey(), Range.openClosed(0,3))
    assertEquals(y.getValue(), 1)
  }

  @Test
  void testSpan() {
    def x = TreeRangeMap.<Integer,Integer>create()
    x.put(Range.openClosed(0,3), 1)
    x.put(Range.openClosed(4,6), 2)
    def y = x.span()
    assertEquals(y, Range.openClosed(0,6))
  }
}
