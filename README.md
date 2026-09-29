# Лаборатори №4 – JUnit 5 Unit Testing

## Оюутны мэдээлэл

- **Оюутны нэр:** E. Munkh-Ochir
- **Оюутны код:** B232270022
- **Хичээл:** Software Quality Assurance and Testing
- **Лаборатори:** Лаборатори №4 – JUnit 5 Unit Testing

---

## 1. Төслийн тухай

Энэхүү лабораторийн ажлаар Maven ашигласан Java төсөл дээр JUnit 5 framework ашиглан unit test боловсруулсан.

Төсөлд `GradeCalculator` класс үүсгэж, дараах үндсэн хоёр үйлдлийг хэрэгжүүлсэн:

- `letterGrade(double score)` – 0–100 оноог үсгэн үнэлгээ болгон хөрвүүлнэ.
- `totalScore(double att, double lab, double quiz1, double quiz2, double exam)` – хичээлийн нийт оноог тооцоолно.

Төслийг build хийх болон unit test-үүдийг ажиллуулахад Maven ашигласан.

---

## 2. Ашигласан орчин

### Java

```text
openjdk version "25.0.2" 2026-01-20
OpenJDK Runtime Environment Homebrew (build 25.0.2)
OpenJDK 64-Bit Server VM Homebrew (build 25.0.2, mixed mode, sharing)
```

### Maven

```text
Apache Maven 3.9.12
Java version: 25.0.2
OS name: "mac os x"
arch: "aarch64"
```

### Ашигласан технологи

- Java 25.0.2
- Maven 3.9.12
- JUnit Jupiter 5.10.2
- Maven Surefire Plugin 3.2.5
- JUnit 5 Parameterized Tests

---

## 3. Төслийн бүтэц

```text
lab04-junit/
├── pom.xml
├── README.md
├── .gitignore
├── src/
│   ├── main/
│   │   └── java/
│   │       └── mn/
│   │           └── edu/
│   │               └── must/
│   │                   └── sqat/
│   │                       └── GradeCalculator.java
│   └── test/
│       └── java/
│           └── mn/
│               └── edu/
│                   └── must/
│                       └── sqat/
│                           └── GradeCalculatorTest.java
└── results/
    ├── mvn-test.txt
    └── mvn-test-mutant.txt
```

---

## 4. GradeCalculator-ийн шаардлага

### 4.1 Үсгэн үнэлгээ

`letterGrade()` method нь дараах дүрмээр ажиллана.

| Оноо | Үнэлгээ |
|---|---|
| 90–100 | A |
| 80–89.99 | B |
| 70–79.99 | C |
| 60–69.99 | D |
| 0–59.99 | F |

0-ээс бага болон 100-аас их оноо өгсөн тохиолдолд `IllegalArgumentException` үүсгэнэ.

### 4.2 Нийт оноо

`totalScore()` method нь дараах онооны бүтцээр нийт оноог тооцно.

| Үзүүлэлт | Дээд оноо |
|---|---:|
| Attendance | 10 |
| Lab | 40 |
| Quiz 1 | 10 |
| Quiz 2 | 10 |
| Exam | 30 |
| **Нийт** | **100** |

Тухайн үзүүлэлт зөвшөөрөгдсөн хэмжээнээс бага эсвэл их байвал `IllegalArgumentException` үүсгэнэ.

---

## 5. Unit Test

`GradeCalculatorTest` класс нийт **14 test method** агуулна.

Тестүүдээр дараах тохиолдлуудыг шалгасан:

- Энгийн үсгэн үнэлгээний тооцоолол
- Хязгаарын утгууд
- 90 оноо → A
- 89.99 оноо → B
- 60 оноо → D
- 59.99 оноо → F
- 0 оноо → F
- 100 оноо → A
- Сөрөг оноо өгөх үед exception үүсэх
- 100-аас их оноо өгөх үед exception үүсэх
- Зөв нийт онооны тооцоолол
- Сөрөг attendance утга
- Lab-ийн дээд хэмжээнээс их утга
- Parameterized letter grade tests
- Parameterized total score tests

Parameterized test-үүдийг `@ParameterizedTest` болон `@CsvSource` ашиглан хэрэгжүүлсэн.

---

## 6. Эцсийн тестийн үр дүн

Эцсийн тестийг дараах командаар ажиллуулсан:

```bash
mvn test 2>&1 | tee results/mvn-test.txt
```

Эцсийн үр дүн:

```text
Tests run: 24, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Нийт **24 test case** ажилласан бөгөөд бүгд амжилттай болсон.

Эцсийн тестийн бүрэн үр дүн:

```text
results/mvn-test.txt
```

файлд хадгалагдсан.

---

## 7. Mutation Testing

Mutation testing хийхийн тулд `letterGrade()` method-ийн 90 онооны нөхцөлийг түр хугацаанд өөрчилсөн.

Анхны нөхцөл:

```java
if (score >= 90)
```

Mutation хийсний дараа:

```java
if (score > 90)
```

болгосон.

Дараа нь дараах командаар тест ажиллуулсан:

```bash
mvn test 2>&1 | tee results/mvn-test-mutant.txt
```

Mutation хийсэн үед тестийн үр дүн:

```text
Tests run: 24, Failures: 2, Errors: 0, Skipped: 0
BUILD FAILURE
```

Унасан тестүүд:

```text
GradeCalculatorTest.ninetyIsExactlyA
GradeCalculatorTest.letterGradeBoundaries(double, String)[2]
```

Эдгээр тестүүд 90 оноог `A` гэж хүлээж байсан боловч mutation хийсний дараа `B` болсон тул тестүүд амжилтгүй болсон.

Энэ нь 90 онооны boundary condition-ийг тестүүд зөв илрүүлж байгааг харуулсан.

Mutation test дууссаны дараа кодыг анхны зөв хэлбэрт нь буцаасан:

```java
if (score >= 90)
```

Үүний дараа final test дахин ажиллуулж, бүх 24 test case амжилттай болсон.

Mutation testing-ийн үр дүн:

```text
results/mvn-test-mutant.txt
```

файлд хадгалагдсан.

---

## 8. Хамгийн сонирхолтой тест болон алдаа

Энэ лабораторийн хамгийн сонирхолтой хэсэг нь 90 онооны boundary test байсан.

90 оноо нь `A` үнэлгээний хамгийн доод хязгаар учраас тусгайлан тест хийсэн.

Mutation testing хийх үед `score >= 90` нөхцөлийг `score > 90` болгон өөрчилсөн.

Ингэснээр яг 90 оноо `A` биш `B` гэж тооцогдсон.

`ninetyIsExactlyA` тест энэ өөрчлөлтийг шууд илрүүлж, тест амжилтгүй болсон.

Мөн parameterized test-ийн 90 онооны case мөн адил амжилтгүй болсон.

Энэ нь boundary value testing нь нөхцөлт логикийн алдааг илрүүлэхэд чухал болохыг харуулсан.

Кодыг буцааж зөв болгосны дараа бүх 24 тест амжилттай ажиллаж `BUILD SUCCESS` болсон.

---

## 9. Тест ажиллуулах

Бүх тестийг ажиллуулах:

```bash
mvn test
```

Тестийн үр дүнг файлд хадгалах:

```bash
mvn test 2>&1 | tee results/mvn-test.txt
```

---

## 10. Git

Төслийг хөгжүүлэх явцад Git ашиглан өөрчлөлтүүдийг үе шаттайгаар commit хийсэн.

Repository-д дараах зүйлс багтсан:

- Java source code
- JUnit 5 unit tests
- Maven configuration
- README documentation
- Final passing test results
- Mutation testing results

`.gitignore` файлд дараах файлуудыг Git-д оруулахгүй байхаар тохируулсан:

```text
target/
.idea/
.DS_Store
```

---

## 11. Дүгнэлт

Энэхүү лабораторийн ажлаар Maven болон JUnit 5 ашиглан Java application-ийн unit testing хийсэн.

Нийт 14 test method боловсруулж, parameterized tests ашиглан олон төрлийн input-ийг шалгасан.

Мөн boundary value болон invalid input-үүдийг тусгайлан тестэлсэн.

Mutation testing ашигласнаар тестүүд кодын жижиг логик өөрчлөлтийг илрүүлж чаддаг болохыг шалгасан.

Эцсийн байдлаар нийт 24 test case ажиллаж, 24 тест бүгд амжилттай болсон.

Ингэснээр `GradeCalculator` классын үндсэн үйлдлүүд болон validation logic-уудыг unit test-ээр шалгасан.