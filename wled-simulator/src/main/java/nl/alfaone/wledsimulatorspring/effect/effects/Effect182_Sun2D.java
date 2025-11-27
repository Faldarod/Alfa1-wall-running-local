package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 182: 2D Akemi
 * Advanced audio-reactive visualization (simulated).
 */
@Component
public class Effect182_Sun2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double rays = Math.sin((adjustedTime / 40.0) + distance * 0.4) *
                     Math.cos((adjustedTime / 60.0) - distance * 0.3);
        double core = Math.exp(-distance / 12.0);

        double brightness = core * ((rays + 1.5) / 2.5);

        // Warm sun colors
        return scale(hsvToRgb(0.1, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 182;
    }

    @Override
    public String getEffectName() {
        return "2D Akemi";
    }

    @Override
    public boolean is2D() {
        return true;
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
