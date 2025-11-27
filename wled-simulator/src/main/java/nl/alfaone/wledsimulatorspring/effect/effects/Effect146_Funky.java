package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 146: Funky Plank
 * Fun bouncing plank animation.
 */
@Component
public class Effect146_Funky extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double plankPos = (segmentLength / 2.0) +
                         (segmentLength / 3.0) * Math.sin(adjustedTime / 120.0);
        int plankSize = 8;

        if (Math.abs(ledIndex - plankPos) < plankSize / 2.0) {
            double hue = (adjustedTime / 1500.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 146;
    }

    @Override
    public String getEffectName() {
        return "Funky Plank";
    }
}
