package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 31: Chase Flash
 * Chase with brief flash at each position.
 */
@Component
public class Effect031_ChaseFlash extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int position = (int)((adjustedTime / 100.0) % segmentLength);

        if (ledIndex == position) {
            double flashPhase = (adjustedTime % 100) / 100.0;
            return flashPhase < 0.3 ? white() : getPrimaryColor(segment);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 31;
    }

    @Override
    public String getEffectName() {
        return "Chase Flash";
    }
}
