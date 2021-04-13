package io.redru.filters;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Filters {

  static <T, U> Predicate<T> objectEquals(Function<? super T, ? extends U> keyExtractor, Object other) {
    Objects.requireNonNull(keyExtractor);
    Objects.requireNonNull(other);

    return (T t) -> Objects.equals(keyExtractor.apply(t), other);
  }

}
