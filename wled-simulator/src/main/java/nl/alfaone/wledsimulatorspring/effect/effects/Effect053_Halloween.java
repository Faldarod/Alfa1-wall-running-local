package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 53: Halloween
 * Orange and purple Halloween colors.
 */
@Component
public class Effect053_Halloween extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int pattern = (ledIndex + (int)(adjustedTime / 400.0)) % 2;

        return pattern == 0 ? new int[]{255, 100, 0} : new int[]{128, 0, 255};
    }

    @Override
    public int getEffectId() {
        return 53;
    }

    @Override
    public String getEffectName() {
        return "Halloween";
    }
}
