package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 129: Akemi
 * Audio-reactive visualization (simulated).
 */
@Component
public class Effect129_Akemi extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double beat = Math.abs(Math.sin(adjustedTime / 100.0));
        double position = (double)ledIndex / segmentLength;

        if (position < beat) {
            double hue = (adjustedTime / 1000.0 + position) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 129;
    }

    @Override
    public String getEffectName() {
        return "Akemi";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
