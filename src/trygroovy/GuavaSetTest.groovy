package trygroovy;

import com.google.common.base.Predicate
import com.google.common.collect.ImmutableSet
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMultiset
import com.google.common.collect.ImmutableSortedMultiset
import com.google.common.collect.Sets;

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
  
  @Test
  void testSetCartesian() {
    def x = Sets.newHashSet(1,2)
    def y = Sets.cartesianProduct(x, x)
    assertEquals(y.size(), 4)
  }

  @Test
  void testSetFilter() {
    def x = Sets.newHashSet(1,2,3,4)
    def y = Sets.filter(x, new Predicate<Integer>(){
      @Override
      boolean apply(Integer i){
        return i > 2;
      }
    })
    assertEquals(y.size(), 2)
  }
  
  @Test
  void testPowerSet() {
    def x = Sets.newHashSet(1,2,3)
    def y = Sets.powerSet(x)
    assertEquals(y.size(), 8)
    assertTrue(y.contains(x))
    assertTrue(y.contains(Sets.newHashSet()))
  }
  
  @Test
  void testIntersect() {
    def x = Sets.newHashSet(1,2,3)
    def y = Sets.newHashSet(3,4,5)
    
    assertEquals(Sets.intersection(x,y), Sets.newHashSet(3))
    assertEquals(Sets.union(x,y), Sets.newHashSet(1,2,3,4,5))
  }

  @Test
  void testDifference() {
    def x = Sets.newHashSet(1,2,3)
    def y = Sets.newHashSet(3,4,5)
    
    assertEquals(Sets.difference(x,y), Sets.newHashSet(1,2))
  }
  
  @Test
  void testDifference3() {
    def x = Sets.newHashSet(1,2,3)
    def y = Sets.newHashSet(3,4,5)
    
    assertEquals(Sets.symmetricDifference(x,y), Sets.newHashSet(1,2,4,5))
  }
  
}
