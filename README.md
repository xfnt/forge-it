# 🛠️ ForgeIt — Because Real Data is for Cowards

**ForgeIt** - is a minimalist Java framework for generating objects with fake but realistic data.
No magic, no dependencies, no Lombok. Just **annotations, reflection**, and your inner need to avoid writing test data by hand.

> 💡 You annotate the fields — we forge the object.
> 🔥 No dependencies. No pain. Well, maybe a little pain — it's Java after all.
---

## 🚀 Example Usage

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

### 🧩 How It Works
You annotate your class with @Template — this means it can be "forged".
On each field, you specify a custom annotation (for example, @EmailField), which knows which data generator to use.
The @EmailField annotation contains the meta-annotation @ForgeTag, which points to the implementation of Generated.
Inside ForgeIt.forge(), the magic happens: all fields are collected, linked to generators, and generated values are injected into them.

### 🛠 Creating Your Own Annotation
Each custom annotation must be marked with @ForgeTag, which specifies which data generator to use:
```java
@ForgeTag(generatedClass = EmailGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmailField {}
```

Want a field with a phone number? Just write:
```java
@ForgeTag(generatedClass = PhoneNumberGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PhoneField {}
```

⚙️ Creating a Generator
All generators implement the Generated interface:
```java
public interface Generated<T, A extends Annotation> {
    T generate(A annotation);
}
```

Example Generator for @EmailField:
```java
public class EmailGenerator implements Generated<String, EmailField> {

    @Override
    public String generate(EmailField annotation) {
        return "user" + UUID.randomUUID().toString().substring(0, 5) + "@example.com";
    }
}
```

Example Generator for @DateField:
```java
public class DateGenerator implements Generated<LocalDate, DateField> {

    @Override
    public LocalDate generate(DateField annotation) {
        return LocalDate.now().minusYears(new Random().nextInt(40));
    }
}
```

### 🪜 Mini-Guide to Creating Annotations and Generators
- Create the Annotation:
```java
@ForgeTag(generatedClass = YourGenerator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface YourCustomField {}
```

- Write a generator:
```java
public class YourGenerator implements Generated<SomeType, YourCustomField> {
    public SomeType generate(YourCustomField annotation) {
        // логика генерации
    }
}
```

- Annotate the field in the class with @Template:
```java
@Template
public class Something {
    @YourCustomField
    private SomeType field;
}
```

- Call ForgeIt:
```java
Something something = ForgeIt.forge(Something.class);
```

### 🤝 License
MIT.  Use, adapt, sell, blame us for bugs — but with good manners.

---
