package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 171: Noisemeter
 * Noise level meter (audio reactive).
 */
@Component
public class Effect171_Noisemeter extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double noiseLevel = Math.abs(Math.sin(adjustedTime / 120.0)) * segmentLength;

        if (ledIndex < noiseLevel) {
            double hue = (double)ledIndex / segmentLength * 0.33; // Green to red
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 171;
    }

    @Override
    public String getEffectName() {
        return "Noisemeter";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
