package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 133: 2D Colored Bursts
 * Enhanced colored bursts for 2D matrices.
 */
@Component
public class Effect133_ColoredBursts2D extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double burstRadius = (adjustedTime / 80.0) % (segmentLength / 2.0);
        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        if (Math.abs(distance - burstRadius) < 3) {
            double hue = (burstRadius / (segmentLength / 2.0) + adjustedTime / 2000.0) % 1.0;
            double brightness = 1.0 - (burstRadius / (segmentLength / 2.0));
            return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
        }

        return scale(getPrimaryColor(segment), 0.1);
    }

    @Override
    public int getEffectId() {
        return 133;
    }

    @Override
    public String getEffectName() {
        return "2D Colored Bursts";
    }

    @Override
    public boolean is2D() {
        return true;
    }
}
