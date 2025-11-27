package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 158: Ripplepeak
 * Ripple with peak intensities (audio reactive).
 */
@Component
public class Effect158_Ripplepeak extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double peak = Math.abs(Math.sin(adjustedTime / 150.0));
        double ripple = Math.sin((distance - adjustedTime / 50.0) * 0.5);

        double brightness = ((ripple + 1.0) / 2.0) * peak;
        double hue = (distance / segmentLength + adjustedTime / 2000.0) % 1.0;

        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 158;
    }

    @Override
    public String getEffectName() {
        return "Ripplepeak";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
