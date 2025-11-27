package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 156: Warp
 * Space warp speed effect.
 */
@Component
public class Effect156_Warp extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double warp = Math.exp(-distance / 5.0);
        double streaks = Math.sin(adjustedTime / 20.0 - distance * 0.5);

        if (streaks > 0.7) {
            return scale(white(), warp);
        }

        return scale(new int[]{0, 0, 50}, warp * 0.3); // Dark blue background
    }

    @Override
    public int getEffectId() {
        return 156;
    }

    @Override
    public String getEffectName() {
        return "Warp";
    }
}
