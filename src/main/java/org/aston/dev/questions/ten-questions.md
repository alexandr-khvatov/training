# 10 вопросов по java.util.Collection

1. Назовите основные интерфейсы фреймфорка Collection ?

<details>
  <summary>Ответ</summary>

![img.png](collections-chart-colored.png)

</details>

2. Почему Map не расширяет интерфейс Collection ?

<details>
  <summary>Ответ</summary>

---

- Map представляет собой key-value storage

---
</details>

3. Какой тип данных можно использовать в качестве ключа Map,
   что является лучшим кандидатом для использования в качестве ключа, ответ аргументируйте ?

<details>
  <summary>Ответ</summary>

---

- Тип данных Key, должен выполнять контракт hashCode() и equals().

- Лучшим кандидатом является Immutable Class

---
</details>

4. Какие реализации интерфейса Set Вы знаете, какие их основные особенности ?

<details>
  <summary>Ответ</summary>

---

- HashSet
    - no order
    - NULL Value OK
    - uses hashCode()
- LinkedHashSet
    - order OK
    - NULL Value OK
- TreeSet
    - no NULL Value
    - preserves natural order
    - navigable

---
</details>

5. Какие представления коллекций можно получить из Map ?

<details>
  <summary>Ответ</summary>

---

- Entry Set

```java
        Map<String, LocalDate> map=new HashMap<>();

        Set<Map.Entry<String, LocalDate>>entries=map.entrySet();

        for(Map.Entry<String, LocalDate> e:entries){
        String key=e.getKey();
        LocalDate value=e.getValue();
        }
```

- Key Set

```java
        Map<String, LocalDate> map=new HashMap<>();

        Set<String> keys=map.keySet();

        boolean isRemoved=keys.remove("key");
        boolean isContains=keys.contains("key");
```

- Values collection

```java
        Map<String, LocalDate> map=new HashMap<>();

        Collection<LocalDate> values=map.values();

        boolean isRemoved=values.remove("key");
        boolean isContains=values.contains("key");
```

Все изменения на view отражаются на исходной Map

Для Iterator соблюдается принцип fail-fast. (Модификация(только удаление) доступна только в самом Iterator)

---
</details>

6. Как устроена работа Priority Queue ?

<details>

  <summary>Ответ</summary>

---

Основана на массиве   
Приоретет определяется на основе Comparator или Comparable (возможен ClassCustException)  
f





---

</details>

7. Когда необходимо использовать TreeMap ?

<details>
  <summary>Ответ</summary>

---
Когда необходимо <K,V> хранилище с отсортированными элементами;
(default ASC)

Порядок определяется в соответствии с Comparator  
или natural order по Comparable (возможен ClassCustException)

Структура данных: КРАСНО-ЧЁРНОЕ ДЕРЕВО, complexity:  O(log(n))  
NULL key OK - если Comparator допускает  

Вставка по KEY сохраняет последний вставленный элемент

---
</details>
8. Чем отличается LinkedList от ArrayDeque ?  
9. Чем отличаются synchronized коллекции от concurrent ?  
10. Какие concurrent коллекции Вы знаете, и при каких условиях их применение будет оптимальным ?  