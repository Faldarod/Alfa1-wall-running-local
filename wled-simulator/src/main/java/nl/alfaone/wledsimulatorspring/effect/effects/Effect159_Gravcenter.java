package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 159: Gravcenter
 * Gravity center pull effect (audio reactive).
 */
@Component
public class Effect159_Gravcenter extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int center = segmentLength / 2;
        double distance = Math.abs(ledIndex - center);

        double gravity = Math.abs(Math.sin(adjustedTime / 180.0));
        double pull = Math.exp(-distance / (10.0 * gravity + 1.0));

        double hue = (distance / segmentLength * 0.3 + adjustedTime / 3000.0) % 1.0;
        return scale(hsvToRgb(hue, 1.0, 1.0), pull);
    }

    @Override
    public int getEffectId() {
        return 159;
    }

    @Override
    public String getEffectName() {
        return "Gravcenter";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
