package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 136: 2D Lissajous
 * Lissajous curve patterns (2D adapted for 1D).
 */
@Component
public class Effect136_Lissajous extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double x = Math.sin(adjustedTime / 100.0) * (segmentLength / 2.0) + (segmentLength / 2.0);
        double distance = Math.abs(ledIndex - x);

        if (distance < 3) {
            double hue = (adjustedTime / 2000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0 - distance / 3.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 136;
    }

    @Override
    public String getEffectName() {
        return "2D Lissajous";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
