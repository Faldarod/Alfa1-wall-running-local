package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 176: 2D Tartan
 * Enhanced 2D tartan pattern.
 */
@Component
public class Effect176_Tartan2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((ledIndex + adjustedTime / 40.0) % 24);
        boolean pattern1 = (position / 6) % 2 == 0;
        boolean pattern2 = (position % 6) < 3;
        boolean pattern3 = (position % 3) < 1;

        if (pattern1 && pattern2 && pattern3) {
            return blend(getPrimaryColor(segment), getSecondaryColor(segment), 0.5);
        } else if (pattern1 && pattern2) {
            return getPrimaryColor(segment);
        } else if (pattern1 && pattern3) {
            return getSecondaryColor(segment);
        } else if (pattern2) {
            return getTertiaryColor(segment);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 176;
    }

    @Override
    public String getEffectName() {
        return "2D Tartan";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
