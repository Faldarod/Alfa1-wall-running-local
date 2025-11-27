package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 154: Soapy
 * Soap bubble iridescent effect.
 */
@Component
public class Effect154_Soapy extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double bubble1 = Math.sin((ledIndex * 0.2) + (adjustedTime / 100.0));
        double bubble2 = Math.cos((ledIndex * 0.15) - (adjustedTime / 130.0));

        double hue = ((bubble1 + bubble2) / 4.0 + 0.5 +
                     (double)ledIndex / segmentLength * 0.5 +
                     adjustedTime / 1000.0) % 1.0;
        double saturation = 0.5 + 0.3 * bubble1;
        double brightness = ((bubble1 + bubble2) / 2.0 + 1.5) / 2.5;

        return scale(hsvToRgb(hue, saturation, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 154;
    }

    @Override
    public String getEffectName() {
        return "Soapy";
    }
}
