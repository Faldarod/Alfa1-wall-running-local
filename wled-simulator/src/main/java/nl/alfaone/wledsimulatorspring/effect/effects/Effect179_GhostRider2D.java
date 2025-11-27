package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 179: 2D Ghost Rider
 * Enhanced 2D ghostly effect.
 */
@Component
public class Effect179_GhostRider2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double position = (adjustedTime / 20.0) % segmentLength;
        double distance = Math.abs(ledIndex - position);

        if (distance < 20) {
            double fade = 1.0 - (distance / 20.0);
            fade = Math.pow(fade, 2); // Exponential fade

            // Ghostly white-blue-green color
            double hue = 0.5 + 0.1 * Math.sin(adjustedTime / 100.0);
            return scale(hsvToRgb(hue, 0.5, 1.0), fade);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 179;
    }

    @Override
    public String getEffectName() {
        return "2D Ghost Rider";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
