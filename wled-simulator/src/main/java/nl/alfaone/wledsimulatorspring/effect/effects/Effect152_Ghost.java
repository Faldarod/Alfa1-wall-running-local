package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 152: Ghost Rider
 * Ghostly trailing light effect.
 */
@Component
public class Effect152_Ghost extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (adjustedTime / 25.0) % segmentLength;
        double distance = Math.abs(ledIndex - position);

        if (distance < 15) {
            double fade = 1.0 - (distance / 15.0);
            // Ghostly white-blue color
            int red = (int)(200 * fade);
            int green = (int)(220 * fade);
            int blue = (int)(255 * fade);
            return new int[]{clamp(red), clamp(green), clamp(blue)};
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 152;
    }

    @Override
    public String getEffectName() {
        return "Ghost Rider";
    }
}
