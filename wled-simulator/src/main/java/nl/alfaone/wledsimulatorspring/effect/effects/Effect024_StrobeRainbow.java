package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 24: Strobe Rainbow
 * Strobe effect with rainbow color cycling.
 */
@Component
public class Effect024_StrobeRainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double period = 500.0;
        double phase = (adjustedTime % period) / period;

        if (phase < 0.1) {
            double hue = (adjustedTime / 2000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 24;
    }

    @Override
    public String getEffectName() {
        return "Strobe Rainbow";
    }
}
