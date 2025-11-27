package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 157: Phun
 * Fun phase patterns.
 */
@Component
public class Effect157_Phun extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double phase1 = Math.sin((ledIndex * 0.4) + (adjustedTime / 80.0));
        double phase2 = Math.sin((ledIndex * 0.3) - (adjustedTime / 100.0));
        double phase3 = Math.sin((ledIndex * 0.2) + (adjustedTime / 120.0));

        double brightness = (phase1 + phase2 + phase3 + 3.0) / 6.0;
        double hue = ((phase1 + phase2) / 4.0 + 0.5 + adjustedTime / 2000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 157;
    }

    @Override
    public String getEffectName() {
        return "Phun";
    }
}
