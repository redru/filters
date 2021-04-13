package io.redru.filters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FiltersTest {

  private List<String> stringList;
  private List<TestObject> testObjectList;

  @BeforeEach
  void setUp() {
    stringList = Arrays.asList("A BC", "A", "B", "ABC", "CA");
    testObjectList = Arrays.asList(
        new TestObject("ABC", 1),
        new TestObject("AC", 1),
        new TestObject("BC", 2),
        new TestObject("C", 2),
        new TestObject("A", 3),
        new TestObject("ABC", 3)
    );
  }

  @Test
  @DisplayName("Filter using Predicate constructor")
  void testFilter_1() {
    Filter<String> filter = Filter.filtering((String v) -> v.contains("A"))
        .thenFiltering(v -> v.contains("B"));

    List<String> filtered = stringList.stream()
        .filter(filter)
        .collect(Collectors.toList());

    assert filtered.size() == 2;
  }

  @Test
  @DisplayName("Filter using Class constructor")
  void testFilter_2() {
    Filter<TestObject> filter = Filter.filtering(TestObject.class)
        .thenFiltering(v -> v.getVarStr().contains("A"))
        .thenFiltering(v -> v.getVarInt() == 1);

    List<TestObject> filtered = testObjectList.stream()
        .filter(filter)
        .collect(Collectors.toList());

    assert filtered.size() == 2;
  }

  @Test
  @DisplayName("Filter using 'equals' static method")
  void testFilter_3() {
    Filter<TestObject> filter = Filter.filtering(TestObject.class)
        .thenFiltering(Filter.equals(TestObject::getVarStr, "A"))
        .thenFiltering(Filter.equals(TestObject::getVarInt, 3));

    List<TestObject> filtered = testObjectList.stream()
        .filter(filter)
        .collect(Collectors.toList());

    assert filtered.size() == 1;
  }

}
