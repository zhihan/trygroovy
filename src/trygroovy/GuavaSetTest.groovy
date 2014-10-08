package trygroovy;

import com.google.common.collect.ImmutableSet
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMultiset
import com.google.common.collect.ImmutableSortedMultiset
import java.util.NavigableSet
import static org.junit.Assert.*;

import org.junit.Test;

class GuavaSetTest {

  @Test
  public void testSetContains() {
    def x = ImmutableSet.<String>of("a", "b", "c")
    assertTrue(x.contains("a"))
    def y = ImmutableList.<String>of("a", "b")
    assertTrue(x.containsAll(y))
  }

  @Test
  public void testMultisetEntry() {
    def x = ImmutableMultiset.<String>of("a", "a", "b")
    assertTrue(x.contains("a"))
    assertTrue(x.contains("b"))
    def y = x.entrySet()
    for (i in y) {
      if (i.getElement() == "a") {
        assertEquals(i.getCount(), 2)
      } else {
        assertEquals(i.getCount(), 1)
      }
    }
  }
  
  @Test
  public void testSortedMultiset(){
    def x = ImmutableSortedMultiset.<String>of("a","a","b")
    NavigableSet n = x.elementSet()
    assertEquals(n.size(), 2)
    assertTrue(n.ceiling("c") == null)
    assertTrue(n.floor("a") == "a")
    def a = x.firstEntry()
    assertTrue(a.getElement() == "a")
    assertTrue(a.getCount() == 2)
    assertEquals(x.count("a"), 2)
  }
  
}
