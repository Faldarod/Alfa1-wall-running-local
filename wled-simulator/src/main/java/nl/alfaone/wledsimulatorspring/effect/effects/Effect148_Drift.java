package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 148: Drift
 * Slowly drifting color clouds.
 */
@Component
public class Effect148_Drift extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double drift1 = Math.sin((ledIndex * 0.05) + (adjustedTime / 300.0));
        double drift2 = Math.cos((ledIndex * 0.03) - (adjustedTime / 400.0));

        double brightness = ((drift1 + drift2) / 2.0 + 1.0) / 2.0;
        double hue = ((drift1 + drift2) / 4.0 + 0.5 + adjustedTime / 5000.0) % 1.0;

        return scale(hsvToRgb(hue, 0.8, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 148;
    }

    @Override
    public String getEffectName() {
        return "Drift";
    }
}
