package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 128: Gravimeter
 * Gravity-like pulling effect.
 */
@Component
public class Effect128_Gravimeter extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double gravity = Math.abs(Math.sin(adjustedTime / 200.0));
        int filledLeds = (int)(segmentLength * gravity);

        if (ledIndex < filledLeds) {
            double hue = (double)ledIndex / filledLeds * 0.3;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 128;
    }

    @Override
    public String getEffectName() {
        return "Gravimeter";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
