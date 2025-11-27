package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 116: Chase Rainbow
 * Chase effect with rainbow colors.
 */
@Component
public class Effect116_ChaseRainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 30.0) % segmentLength);
        double hue = (adjustedTime / 2000.0) % 1.0;

        if (ledIndex == position) {
            return hsvToRgb(hue, 1.0, 1.0);
        } else if (ledIndex == (position - 1 + segmentLength) % segmentLength) {
            return scale(hsvToRgb(hue, 1.0, 1.0), 0.5);
        } else if (ledIndex == (position - 2 + segmentLength) % segmentLength) {
            return scale(hsvToRgb(hue, 1.0, 1.0), 0.2);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 116;
    }

    @Override
    public String getEffectName() {
        return "Chase Rainbow";
    }
}
