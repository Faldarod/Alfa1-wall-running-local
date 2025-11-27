package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 115: Blink Rainbow
 * Blinking with rainbow colors.
 */
@Component
public class Effect115_BlinkRainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double blinkPhase = (adjustedTime / 500.0) % 2.0;
        boolean isOn = blinkPhase < 1.0;

        if (isOn) {
            double hue = (adjustedTime / 2000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 115;
    }

    @Override
    public String getEffectName() {
        return "Blink Rainbow";
    }
}
