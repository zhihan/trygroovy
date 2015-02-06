package trygroovy;

import com.google.common.base.Function
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableClassToInstanceMap
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSortedMap
import com.google.common.collect.Maps

import static org.junit.Assert.*;

import org.junit.Test;

class GuavaMapTest {

  @Test
  void testClassToInstanceMap() {
    def m = ImmutableClassToInstanceMap.builder()
      .put(String.class, "a")
      .put(Integer.class, 1).build()
    assertEquals(m.getInstance(String.class), "a")
    assertEquals(m.getInstance(Integer.class), 1)
    assertEquals(m.getInstance(Double.class), null)
  }
  
  @Test
  void testMap() {
    def m = ImmutableMap.<String,Integer>builder()
      .put("1", 1)
      .put("2", 2)
      .build()
    assertEquals(m.get("1"), 1)
    assertTrue(m.containsKey("1"))
    assertTrue(m.containsValue(1))
    
    def ks = m.keySet()
    assertEquals(ks.size(), 2)
    assertTrue(ks.contains("1"))
  } 

  @Test
  void testSortedMap() {
    def m = ImmutableSortedMap.<String,Integer>naturalOrder()
      .put("1", 1)
      .put("2", 2)
      .build()
    assertEquals(m.get("1"), 1)
    assertTrue(m.containsKey("1"))
    assertTrue(m.containsValue(1))
    
    def f = m.firstEntry()
    assertEquals(f.getKey(), "1")
    assertEquals(f.getValue(), 1)
    
    def j = m.lowerEntry("2")
    assertEquals(j.getKey(), "1")
    assertEquals(j.getValue(), 1)
  }
  
  @Test
  void testUniqueIndex(){
    List<String> x = ImmutableList.of("a","ab","abc")
    def m = Maps.uniqueIndex(x, new Function<String, Integer>(){
      @Override
      Integer apply(String s) {
        return s.length();
      }
    })
    assertEquals(m.get(1), "a")
    assertEquals(m.get(3), "abc")
    
  }
}
