package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 144: 2D Tartan
 * Scottish tartan plaid pattern.
 */
@Component
public class Effect144_Tartan extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((ledIndex + adjustedTime / 50.0) % 20);
        boolean pattern1 = (position / 5) % 2 == 0;
        boolean pattern2 = (position % 5) < 2;

        if (pattern1 && pattern2) {
            return getPrimaryColor(segment);
        } else if (pattern1) {
            return getSecondaryColor(segment);
        } else if (pattern2) {
            return getTertiaryColor(segment);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 144;
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
