package refactoring.performanceinvoice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import refactoring.performanceinvoice.domain.playtype.Play;

class PlayTest {

    @Test
    void testConstructorAndGetters() {
        // Given
        String id = "hamlet";
        String name = "Hamlet";
        String type = "tragedy";

        // When
        Play play = new Play(id, name, type);

        // Then
        assertEquals(id, play.getId());
        assertEquals(name, play.getName());
        assertEquals(type, play.getType().getName());
    }

}
