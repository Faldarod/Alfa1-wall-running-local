package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 170: Freqpixels
 * Frequency-based pixels (audio reactive).
 */
@Component
public class Effect170_Freqpixels extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double freq = (double)ledIndex / segmentLength;
        double amplitude = Math.abs(Math.sin(adjustedTime / (100.0 * (1.0 + freq))));

        if (amplitude > 0.7) {
            double hue = freq;
            return hsvToRgb(hue, 1.0, amplitude);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 170;
    }

    @Override
    public String getEffectName() {
        return "Freqpixels";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
