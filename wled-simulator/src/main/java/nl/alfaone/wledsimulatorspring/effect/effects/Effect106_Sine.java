package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 106: Sine
 * Simple sine wave brightness modulation.
 */
@Component
public class Effect106_Sine extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave = Math.sin((adjustedTime + ledIndex * 10) / 100.0);
        double brightness = (wave + 1.0) / 2.0;

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 106;
    }

    @Override
    public String getEffectName() {
        return "Sine";
    }
}
