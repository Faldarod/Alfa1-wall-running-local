package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 14: Theater Rainbow
 * Theater chase with rainbow colors.
 */
@Component
public class Effect014_TheaterRainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int step = (int)(adjustedTime / 100.0) % 3;
        boolean isOn = (ledIndex % 3) == step;

        if (isOn) {
            double hue = ((double)ledIndex / segmentLength + adjustedTime / 3000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 14;
    }

    @Override
    public String getEffectName() {
        return "Theater Rainbow";
    }
}
