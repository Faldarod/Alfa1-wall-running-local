package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 30: Chase Rainbow
 * Chase effect with rainbow colors.
 */
@Component
public class Effect030_ChaseRainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int chaseSize = Math.max(1, intensity / 50);
        int position = (int)((adjustedTime / 100.0) % segmentLength);

        if (Math.abs(ledIndex - position) < chaseSize) {
            double hue = (adjustedTime / 2000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 30;
    }

    @Override
    public String getEffectName() {
        return "Chase Rainbow";
    }
}
