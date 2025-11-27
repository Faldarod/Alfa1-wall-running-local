package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 44: Merry Christmas
 * Alternating red and green Christmas colors.
 */
@Component
public class Effect044_MerryChristmas extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int pattern = (ledIndex + (int)(adjustedTime / 500.0)) % 2;

        return pattern == 0 ? new int[]{255, 0, 0} : new int[]{0, 255, 0};
    }

    @Override
    public int getEffectId() {
        return 44;
    }

    @Override
    public String getEffectName() {
        return "Merry Christmas";
    }
}
