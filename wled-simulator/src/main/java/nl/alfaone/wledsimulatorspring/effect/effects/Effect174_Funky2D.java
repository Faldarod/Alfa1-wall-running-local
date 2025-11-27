package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 174: 2D Funky Plank
 * Enhanced 2D funky plank animation.
 */
@Component
public class Effect174_Funky2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double plankPos = (segmentLength / 2.0) +
                         (segmentLength / 3.0) * Math.sin(adjustedTime / 110.0) *
                         Math.cos(adjustedTime / 150.0);
        int plankSize = 10;

        if (Math.abs(ledIndex - plankPos) < plankSize / 2.0) {
            double hue = (adjustedTime / 1200.0) % 1.0;
            double brightness = 1.0 - (Math.abs(ledIndex - plankPos) / (plankSize / 2.0)) * 0.5;
            return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 174;
    }

    @Override
    public String getEffectName() {
        return "2D Funky Plank";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
