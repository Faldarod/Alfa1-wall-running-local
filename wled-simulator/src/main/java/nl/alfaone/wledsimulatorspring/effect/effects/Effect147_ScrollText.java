package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 147: Scrolling Text
 * Simulated scrolling text effect.
 */
@Component
public class Effect147_ScrollText extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 30.0) % segmentLength);

        // Simulate text pixels (alternating pattern)
        if ((ledIndex >= position && ledIndex < position + 3) ||
            (ledIndex >= position + 5 && ledIndex < position + 7)) {
            return getPrimaryColor(segment);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 147;
    }

    @Override
    public String getEffectName() {
        return "Scrolling Text";
    }
}
