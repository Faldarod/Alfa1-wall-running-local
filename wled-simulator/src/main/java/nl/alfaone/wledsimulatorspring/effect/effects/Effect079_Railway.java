package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 79: Railway
 * Railway crossing signal effect.
 */
@Component
public class Effect079_Railway extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        boolean isFirstHalf = ledIndex < (segmentLength / 2);
        double flashPhase = (adjustedTime % 1000) / 1000.0;

        boolean firstFlash = flashPhase < 0.5;
        boolean showRed = (isFirstHalf && firstFlash) || (!isFirstHalf && !firstFlash);

        if (showRed) {
            return new int[]{255, 0, 0};
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 79;
    }

    @Override
    public String getEffectName() {
        return "Railway";
    }
}
