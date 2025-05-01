# 🛠️ ForgeIt — Because Real Data is for Cowards

**ForgeIt** — это минималистичный Java-фреймворк для генерации объектов с фейковыми, но правдоподобными данными.  
Никакой магии, зависимостей или ломбоков. Только **аннотации, рефлексия** и твоя внутренняя потребность не писать руками тестовые данные.

> 💡 Ты аннотируешь поля — мы куём объект.  
> 🔥 Без зависимостей. Без боли. Ну ладно, немного боли всё же есть — это Java.

---

## 🚀 Пример использования

```java
@Template
public class User {

    @EmailField
    private String email;

    @DateField
    private LocalDate birthday;
}

public class MyTest {
    @Test
    public void testUserGeneration() {
        User user = ForgeIt.forge(User.class);
        assertNotNull(user.getEmail());
        assertNotNull(user.getBirthday());
    }
}
```

### 🧩 Как это работает
Ты помечаешь свой класс аннотацией @Template — это значит, что его можно "ковать". \
На каждом поле указываешь кастомную аннотацию (например, @EmailField), которая знает, какой генератор данных использовать. \
Аннотация @EmailField содержит мета-аннотацию @ForgeTag, которая указывает на реализацию Generated. \
Внутри ForgeIt.forge() запускается магия: все поля собираются, связываются с генераторами, и в них втыкаются сгенерированные значения. \

### 🛠 Создание своей аннотации
Каждая пользовательская аннотация должна быть помечена @ForgeTag, указывающей, какой генератор данных использовать:
```java
@ForgeTag(generatedClass = EmailGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmailField {}
```

Хочешь поле с номером телефона? Пиши:
```java
@ForgeTag(generatedClass = PhoneNumberGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PhoneField {}
```

⚙️ Создание генератора
Все генераторы реализуют интерфейс:

```java
public interface Generated<T, A extends Annotation> {
    T generate(A annotation);
}
```

Пример генератора для @EmailField:
```java
public class EmailGenerator implements Generated<String, EmailField> {

    @Override
    public String generate(EmailField annotation) {
        return "user" + UUID.randomUUID().toString().substring(0, 5) + "@example.com";
    }
}
```

Пример генератора для @DateField:
```java
public class DateGenerator implements Generated<LocalDate, DateField> {

    @Override
    public LocalDate generate(DateField annotation) {
        return LocalDate.now().minusYears(new Random().nextInt(40));
    }
}
```

### 🪜 Мини-инструкция по созданию аннотаций и генераторов
Создай аннотацию:
```java
@ForgeTag(generatedClass = YourGenerator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface YourCustomField {}
```

Напиши генератор:
```java
public class YourGenerator implements Generated<SomeType, YourCustomField> {
    public SomeType generate(YourCustomField annotation) {
        // логика генерации
    }
}
```

Аннотируй поле в классе с @Template:
```java
@Template
public class Something {
    @YourCustomField
    private SomeType field;
}
```

Позови ForgeIt:
```java
Something something = ForgeIt.forge(Something.class);
```

### 🤝 Лицензия
MIT. Используй, адаптируй, продавай, вини нас при баге — но с хорошим тоном.

---






