package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 104: Phased
 * Phase-shifted wave patterns.
 */
@Component
public class Effect104_Phased extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double phase1 = Math.sin((adjustedTime + ledIndex * 20) / 100.0);
        double phase2 = Math.sin((adjustedTime + ledIndex * 20 + 120) / 100.0);
        double phase3 = Math.sin((adjustedTime + ledIndex * 20 + 240) / 100.0);

        double brightness = (phase1 + phase2 + phase3 + 3.0) / 6.0;

        return scale(getPrimaryColor(segment), brightness);
    }

    @Override
    public int getEffectId() {
        return 104;
    }

    @Override
    public String getEffectName() {
        return "Phased";
    }
}
