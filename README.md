# 🛠️ ForgeIt — Because Real Data is for Cowards.

ForgeIt — это минималистичный фреймворк для генерации объектов с фейковыми, но реалистичными данными.

💡 Ты аннотируешь поля — мы куём объект.  
🔥 Без магии. Без зависимостей. Только Java и боль.

## Пример

```java
@Forgeable
public class User {

    @CustomAnnotation(generator = CustomGenerator.class)
    private String login;

    @CustomAnnotation(generator = CustomGenerator.class)
    private LocalDate birthday;
}
```
```java
public class MyTest {
    @Test
    public void test() {
        User user = ForgeIt.forge(User.class);
    }
}
```