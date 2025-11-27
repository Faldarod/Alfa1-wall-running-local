package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 26: Blink Rainbow
 * Blink effect with rainbow color changes.
 */
@Component
public class Effect026_BlinkRainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        boolean isOn = ((int)(adjustedTime / 1000.0) % 2) == 0;

        if (isOn) {
            double hue = (adjustedTime / 3000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 26;
    }

    @Override
    public String getEffectName() {
        return "Blink Rainbow";
    }
}
