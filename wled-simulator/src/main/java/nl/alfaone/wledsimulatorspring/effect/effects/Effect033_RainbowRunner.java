package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 33: Rainbow Runner
 * Moving rainbow segment across the strip.
 */
@Component
public class Effect033_RainbowRunner extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);
        int position = (int)((adjustedTime / 100.0) % segmentLength);
        int rainbowWidth = Math.max(3, intensity / 10);

        double distance = Math.abs(ledIndex - position);
        if (distance < rainbowWidth) {
            double hue = (distance / rainbowWidth + adjustedTime / 2000.0) % 1.0;
            return hsvToRgb(hue, 1.0, 1.0);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 33;
    }

    @Override
    public String getEffectName() {
        return "Rainbow Runner";
    }
}
