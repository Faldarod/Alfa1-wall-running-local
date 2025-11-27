package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 168: Rocktaves
 * Octave-based rock lighting (audio reactive).
 */
@Component
public class Effect168_Rocktaves extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int octave = ledIndex / (segmentLength / 8);
        double beat = Math.abs(Math.sin(adjustedTime / (100.0 - octave * 10)));

        if (beat > 0.7) {
            double hue = octave / 8.0;
            return hsvToRgb(hue, 1.0, beat);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 168;
    }

    @Override
    public String getEffectName() {
        return "Rocktaves";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
