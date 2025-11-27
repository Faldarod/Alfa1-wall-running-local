package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 184: 2D Soap
 * Enhanced 2D soap bubble iridescence.
 */
@Component
public class Effect184_Soap2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double bubble1 = Math.sin((ledIndex * 0.15) + (adjustedTime / 90.0));
        double bubble2 = Math.cos((ledIndex * 0.11) - (adjustedTime / 110.0));
        double bubble3 = Math.sin((adjustedTime / 70.0));

        double hue = ((bubble1 + bubble2) / 4.0 + 0.5 +
                     (double)ledIndex / segmentLength * 0.7 +
                     adjustedTime / 900.0) % 1.0;
        double saturation = 0.4 + 0.4 * bubble1;
        double brightness = ((bubble1 + bubble2 + bubble3) / 3.0 + 1.5) / 2.5;

        return scale(hsvToRgb(hue, saturation, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 184;
    }

    @Override
    public String getEffectName() {
        return "2D Soap";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
