# 🛠️ ForgeIt — Because Real Data is for Cowards.

ForgeIt — это минималистичный фреймворк для генерации объектов с фейковыми, но реалистичными данными.

💡 Ты аннотируешь поля — мы куём объект.  
🔥 Без магии. Без зависимостей. Только Java и боль.

## Пример

```java
<@PlaceForAnnotation>
public class User {

    <@PlaceForAnnotation>
    private String login;

    <@PlaceForAnnotation>
    private LocalDate birthday;
}
