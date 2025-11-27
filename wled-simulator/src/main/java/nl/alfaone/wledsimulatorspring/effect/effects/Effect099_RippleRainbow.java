package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 99: Ripple Rainbow
 * Rainbow colored ripples from center.
 */
@Component
public class Effect099_RippleRainbow extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);
        double wave = Math.sin((distance - adjustedTime / 50.0) * 0.5);
        double brightness = (wave + 1.0) / 2.0;

        double hue = (distance / segmentLength + adjustedTime / 2000.0) % 1.0;
        return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
    }

    @Override
    public int getEffectId() {
        return 99;
    }

    @Override
    public String getEffectName() {
        return "Ripple Rainbow";
    }
}
