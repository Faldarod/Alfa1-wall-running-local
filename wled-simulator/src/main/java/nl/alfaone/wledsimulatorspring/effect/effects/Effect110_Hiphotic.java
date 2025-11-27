package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 110: Hiphotic
 * Hypnotic spiral-like pattern.
 */
@Component
public class Effect110_Hiphotic extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double spiral = Math.sin(ledIndex * 0.3 + adjustedTime / 80.0) *
                       Math.cos(ledIndex * 0.2 - adjustedTime / 120.0);
        double brightness = (spiral + 1.0) / 2.0;

        double hue = (ledIndex * 0.02 + adjustedTime / 1000.0) % 1.0;
        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 110;
    }

    @Override
    public String getEffectName() {
        return "Hiphotic";
    }
}
