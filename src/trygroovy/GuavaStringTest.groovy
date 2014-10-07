package trygroovy;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.CharMatcher;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import static org.junit.Assert.*;

import org.junit.Test;

class GuavaStringTest {

  @Test
  public void testJoiner() {
    String x = Joiner.on(".").join("a", "b", "c");
    assertEquals(x, "a.b.c");
    
    String y = Joiner.on(".").skipNulls().join("a", null, "b");
    assertEquals(y, "a.b");

    String z = Joiner.on(".").useForNull("*").join("a", null, "b");
    assertEquals(z, "a.*.b");
    // appendTo method for StringBuilder
    StringBuilder sb = new StringBuilder();
    Joiner j = Joiner.on(".")
    j.appendTo(sb, ImmutableList.of("a","b","c"));
    String result = sb.toString();
    assertEquals(result, "a.b.c");
  }

  @Test
  public void testMapJoiner() {
    ImmutableMap<String,String> m = ImmutableMap.<String,String>of("a","1","b","2"); 
    Joiner.MapJoiner j = Joiner.on(";").withKeyValueSeparator(":");
    String x = j.join(m.entrySet());
    assertEquals(x, "a:1;b:2")    
  }
  
  @Test
  public void testSplitter() {
    String a = "a,b,c";
    Splitter s = Splitter.on(",");
    def result = s.splitToList(a);
    assertEquals(result, ImmutableList.of("a","b","c"));
  }
  
  @Test
  public void testSplitterOmitEmpty() {   
    String b = "a,b,,c";
    Splitter s = Splitter.on(",").omitEmptyStrings();
    def result = s.splitToList(b);
    assertEquals(result, ImmutableList.of("a","b","c"));
  }
  
  @Test
  public void testSplitterTrimResults() {
    String b = "a,b , ,c";
    Splitter s = Splitter.on(",").omitEmptyStrings().trimResults();
    def result = s.splitToList(b);
    assertEquals(result, ImmutableList.of("a","b","c"));
  }
  
  @Test
  public void testSplitterWithCharMatcher() {
    String a = "a,b;c";
    Splitter s = Splitter.on(CharMatcher.anyOf(";,"));
    def result = s.splitToList(a);
    assertEquals(result, ImmutableList.of("a","b","c"));
  }
  
  @Test
  public void testSplitterReturnIterable() {
    String a = "a,b;c";
    Splitter s = Splitter.on(CharMatcher.anyOf(";,"));
    def iter = s.split(a);
    assertEquals(ImmutableList.copyOf(iter), ImmutableList.of("a","b","c"));
  }
  
  @Test 
  public void testMapSplitterWithChar() {
    String a = "a=b&c=d";
    Splitter.MapSplitter s = Splitter.on("&").withKeyValueSeparator("=");
    def result = s.split(a);
    assertEquals(result, ImmutableMap.<String,String>of("a","b","c","d"));
  } 

  @Test
  public void testMapSplitterWithSplitter() {
    String a = "a=b&c=d";
    Splitter.MapSplitter s = Splitter.on("&")
      .withKeyValueSeparator(Splitter.on("="));
    def result = s.split(a);
    assertEquals(result, ImmutableMap.<String,String>of("a","b","c","d"));
  }
}
