package refactoring.performanceinvoice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import refactoring.performanceinvoice.domain.play.Play;
import refactoring.performanceinvoice.domain.play.playtype.PlayTypes;

class PlayTest {

    @Test
    void should_create_play() {
        // Given
        String id = "hamlet";
        String name = "Hamlet";
        String type = "tragedy";

        // When
        Play play = new Play(id, name, type);

        // Then
        assertEquals(id, play.getId());
        assertEquals(name, play.getName());
        assertEquals(PlayTypes.get(type), play.getType());
    }

}
