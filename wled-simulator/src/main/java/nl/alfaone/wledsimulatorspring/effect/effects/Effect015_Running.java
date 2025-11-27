package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 15: Running
 * Running lights effect with smooth wave motion.
 */
@Component
public class Effect015_Running extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave = Math.sin((ledIndex / (double)segmentLength * Math.PI * 2) - (adjustedTime / 300.0));
        double brightness = (wave + 1.0) / 2.0;

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 15;
    }

    @Override
    public String getEffectName() {
        return "Running";
    }
}
