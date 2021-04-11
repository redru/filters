package io.redru.filters;

import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface Filter<T> extends Predicate<T> {

  static <T> Filter<T> filtering(Class<T> clazz) {
    Objects.requireNonNull(clazz);

    return (T t) -> true;
  }

  static <T> Filter<T> filtering(Predicate<? super T> predicate) {
    Objects.requireNonNull(predicate);

    return predicate::test;
  }

  default Filter<T> thenFiltering(Predicate<? super T> predicate) {
    Objects.requireNonNull(predicate);

    return (T t) -> this.test(t) && predicate.test(t);
  }

}
