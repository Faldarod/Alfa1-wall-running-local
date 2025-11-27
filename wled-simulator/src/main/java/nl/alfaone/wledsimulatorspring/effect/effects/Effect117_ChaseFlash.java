package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 117: Chase Flash
 * Chase with flash at each position.
 */
@Component
public class Effect117_ChaseFlash extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 40.0) % segmentLength);
        double flashPhase = ((adjustedTime % 200) / 200.0);

        if (ledIndex == position && flashPhase < 0.3) {
            return white();
        } else if (ledIndex == position) {
            return getPrimaryColor(segment);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 117;
    }

    @Override
    public String getEffectName() {
        return "Chase Flash";
    }
}
