package refactoring.performanceinvoice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(type, play.getType());
    }

    @Test
    void testConstructorWithNullValues() {
        // Given
        String id = null;
        String name = null;
        String type = null;

        // When
        Play play = new Play(id, name, type);

        // Then
        assertNull(play.getId());
        assertNull(play.getName());
        assertNull(play.getType());
    }

    @Test
    void testConstructorWithEmptyValues() {
        // Given
        String id = "";
        String name = "";
        String type = "";

        // When
        Play play = new Play(id, name, type);

        // Then
        assertEquals("", play.getId());
        assertEquals("", play.getName());
        assertEquals("", play.getType());
    }
}
