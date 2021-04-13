# Filter fluent API

The Filter class allow developer to create dynamic filters in a fluent way like the Comparator interface.

## Usage

```java
Filter<String> filter = Filter.filtering((String v) -> v.contains("A"))
    .thenFiltering(v -> v.contains("B"));

List<String> filtered = stringList.stream()
    .filter(filter)
    .collect(Collectors.toList());
```
```java
Filter<TestObject> filter = Filter.filtering(TestObject.class)
    .thenFiltering(v -> v.getVarStr().contains("A"))
    .thenFiltering(v -> v.getVarInt() == 1);

List<TestObject> filtered = testObjectList.stream()
    .filter(filter)
    .collect(Collectors.toList());
```
```java
Filter<TestObject> filter = Filter.filtering(TestObject.class)
    .thenFiltering(Filters.objectEquals(TestObject::getVarStr, "A"))
    .thenFiltering(Filters.objectEquals(TestObject::getVarInt, 3));

List<TestObject> filtered = testObjectList.stream()
    .filter(filter)
    .collect(Collectors.toList());
```
```java
Filter<TestObject> filter = Filter.filtering(TestObject.class)
    .objectEquals(TestObject::getVarStr, "A")
    .objectEquals(TestObject::getVarInt, 3);

List<TestObject> filtered = testObjectList.stream()
    .filter(filter)
    .collect(Collectors.toList());
```
```java
Filter<TestObject> filter = Filter.filtering(TestObject.class)
    .objectEquals(TestObject::getVarStr, "A")
    .objectNotEquals(TestObject::getVarInt, 4);

List<TestObject> filtered = testObjectList.stream()
    .filter(filter)
    .collect(Collectors.toList());
```
