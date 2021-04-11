package io.redru.filters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class FiltersTest {

  @Test
  void testFilter_1() {
    Filter<String> filter = Filter.filtering((String v) -> v.contains("Hello"))
        .thenFiltering(v -> v.contains("World"))
        .thenFiltering(v -> v.contains("Hi"))
        .thenFiltering(v -> v.contains("Ciao"))
        .thenFiltering(v -> v.contains("Hola"));

    List<String> filtered = Stream.of("Hello World Hi Ciao Hola sadasdfasf", "Hello", "World", "Hi", "Ciao")
        .filter(filter)
        .collect(Collectors.toList());

    assert filtered.size() == 1;
  }

  @Test
  void testFilter_2() {
    class Struct {
      private final String name;
      private final Integer age;

      Struct(String name, Integer age) {
        this.name = name;
        this.age = age;
      }

      public String getName() {
        return name;
      }

      public Integer getAge() {
        return age;
      }
    }

    Filter<Struct> filter = Filter.filtering(Struct.class)
        .thenFiltering(v -> v.getName().contains("Hi"))
        .thenFiltering(v -> v.getAge() == 10);

    List<Struct> filtered = Stream.of(
        new Struct("Hi", 10),
        new Struct("Hi", 50),
        new Struct("World", 11),
        new Struct("Hello World", 12))
        .filter(filter)
        .collect(Collectors.toList());

    assert filtered.size() == 1;
  }

}
