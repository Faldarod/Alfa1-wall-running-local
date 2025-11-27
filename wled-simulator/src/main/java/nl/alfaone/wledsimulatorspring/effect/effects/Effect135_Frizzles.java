package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 135: 2D Frizzles
 * Frizzled electric patterns.
 */
@Component
public class Effect135_Frizzles extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double frizzle = Math.sin(ledIndex * 0.7 + adjustedTime / 50.0) *
                        Math.cos(ledIndex * 0.3 - adjustedTime / 70.0) *
                        Math.sin(adjustedTime / 90.0);

        if (Math.abs(frizzle) > 0.7) {
            double hue = (adjustedTime / 1000.0) % 1.0;
            return hsvToRgb(hue, 1.0, Math.abs(frizzle));
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 135;
    }

    @Override
    public String getEffectName() {
        return "2D Frizzles";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
