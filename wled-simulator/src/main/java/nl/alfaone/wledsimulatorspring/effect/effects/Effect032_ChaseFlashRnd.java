package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 32: Chase Flash Rnd
 * Chase flash with random colors.
 */
@Component
public class Effect032_ChaseFlashRnd extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int position = (int)((adjustedTime / 100.0) % segmentLength);

        if (ledIndex == position) {
            double flashPhase = (adjustedTime % 100) / 100.0;
            if (flashPhase < 0.3) {
                return white();
            } else {
                random.setSeed((long)(adjustedTime / 500.0));
                double hue = random.nextDouble();
                return hsvToRgb(hue, 1.0, 1.0);
            }
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 32;
    }

    @Override
    public String getEffectName() {
        return "Chase Flash Rnd";
    }
}
