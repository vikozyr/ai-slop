# AI_Slop
Система керування студентським розкладом (Лабораторна робота №1).

## Безперервна інтеграція (CI)

Збірка виконується на сервері Jenkins за файлом `Jenkinsfile`:

1. **Prepare** — підготовка Gradle Wrapper;
2. **Lint** — перевірка стилю коду Checkstyle (`config/checkstyle/checkstyle.xml`);
3. **Unit tests** — модульні тести JUnit 5;
4. **Build** — збірка `jar` та архівування артефактів.

Збірка запускається вручну (Build Now) або автоматично через GitHub webhook після кожного push.

Локальний запуск:

```bash
./gradlew checkstyleMain checkstyleTest test jar
```
