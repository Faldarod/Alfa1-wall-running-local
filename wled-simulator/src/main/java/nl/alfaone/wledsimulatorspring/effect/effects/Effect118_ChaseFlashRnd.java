package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 118: Chase Flash Random
 * Chase with random colored flashes.
 */
@Component
public class Effect118_ChaseFlashRnd extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int position = (int)((adjustedTime / 40.0) % segmentLength);
        double flashPhase = ((adjustedTime % 200) / 200.0);

        if (ledIndex == position && flashPhase < 0.3) {
            double hue = random.nextDouble();
            return hsvToRgb(hue, 1.0, 1.0);
        } else if (ledIndex == position) {
            return getPrimaryColor(segment);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 118;
    }

    @Override
    public String getEffectName() {
        return "Chase Flash Random";
    }
}
