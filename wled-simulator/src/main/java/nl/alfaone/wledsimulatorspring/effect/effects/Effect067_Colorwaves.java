package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 67: Colorwaves
 * Waves of color moving across the strip.
 */
@Component
public class Effect067_Colorwaves extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double wave1 = sineWave(ledIndex + adjustedTime / 30.0, segmentLength / 2.0);
        double wave2 = sineWave(ledIndex - adjustedTime / 40.0, segmentLength / 3.0);

        double hue = ((wave1 + wave2) / 2.0 + adjustedTime / 1000.0) % 1.0;

        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 67;
    }

    @Override
    public String getEffectName() {
        return "Colorwaves";
    }
}
