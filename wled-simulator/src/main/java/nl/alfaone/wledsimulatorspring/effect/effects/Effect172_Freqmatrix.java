package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 172: Freqmatrix
 * Frequency matrix visualization (audio reactive).
 */
@Component
public class Effect172_Freqmatrix extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int freqBand = ledIndex / (segmentLength / 10);
        double amplitude = Math.abs(Math.sin(adjustedTime / (80.0 + freqBand * 10)));
        double height = amplitude * (segmentLength / 10.0);

        if ((ledIndex % (segmentLength / 10)) < height) {
            double hue = freqBand / 10.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 172;
    }

    @Override
    public String getEffectName() {
        return "Freqmatrix";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
