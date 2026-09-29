package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeCalculatorTest {

    // 1. Normal value
    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void ninetyFiveIsA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(95.0);

        // Assert
        assertEquals("A", grade);
    }

    // 2. Boundary: 90
    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой")
    void ninetyIsExactlyA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(90.0);

        // Assert
        assertEquals("A", grade);
    }

    // 3. Boundary: 89.99
    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой")
    void eightyNinePointNineNineIsB() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(89.99);

        // Assert
        assertEquals("B", grade);
    }

    // 4. Boundary: 60
    @Test
    @DisplayName("60 оноо яг D дүн байх ёстой")
    void sixtyIsExactlyD() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(60.0);

        // Assert
        assertEquals("D", grade);
    }

    // 5. Boundary: 59.99
    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой")
    void fiftyNinePointNineNineIsF() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(59.99);

        // Assert
        assertEquals("F", grade);
    }

    // 6. Boundary: 0
    @Test
    @DisplayName("0 оноо F дүн байх ёстой")
    void zeroIsF() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(0.0);

        // Assert
        assertEquals("F", grade);
    }

    // 7. Boundary: 100
    @Test
    @DisplayName("100 оноо A дүн байх ёстой")
    void oneHundredIsA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(100.0);

        // Assert
        assertEquals("A", grade);
    }

    // 8. Invalid: negative
    @Test
    @DisplayName("-1 оноо өгөхөд IllegalArgumentException үүсэх ёстой")
    void negativeScoreThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(-1.0)
        );
    }

    // 9. Invalid: over 100
    @Test
    @DisplayName("101 оноо өгөхөд IllegalArgumentException үүсэх ёстой")
    void scoreOverOneHundredThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(101.0)
        );
    }

    // 10. totalScore normal
    @Test
    @DisplayName("Бүх дээд оноог өгвөл нийт 100 оноо гарах ёстой")
    void totalScoreShouldBeOneHundred() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double total = calc.totalScore(
                10.0,
                40.0,
                10.0,
                10.0,
                30.0
        );

        // Assert
        assertEquals(100.0, total);
    }

    // 11. totalScore negative
    @Test
    @DisplayName("Ирц сөрөг байвал IllegalArgumentException үүсэх ёстой")
    void negativeAttendanceThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(
                        -5.0,
                        40.0,
                        10.0,
                        10.0,
                        30.0
                )
        );
    }

    // 12. totalScore over maximum
    @Test
    @DisplayName("Лабораторийн оноо 40-өөс их байвал exception үүсэх ёстой")
    void labOverMaximumThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(
                        10.0,
                        41.0,
                        10.0,
                        10.0,
                        30.0
                )
        );
    }
}