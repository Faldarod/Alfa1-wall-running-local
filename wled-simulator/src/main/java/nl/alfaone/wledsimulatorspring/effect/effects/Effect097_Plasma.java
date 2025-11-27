package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 97: Plasma
 * Smooth plasma wave effect.
 */
@Component
public class Effect097_Plasma extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double plasma1 = Math.sin(ledIndex * 0.3 + adjustedTime / 100.0);
        double plasma2 = Math.sin(ledIndex * 0.17 + adjustedTime / 150.0);
        double plasma3 = Math.sin((ledIndex + adjustedTime / 200.0) * 0.1);
        double plasma4 = Math.sin(Math.sqrt(Math.pow(ledIndex - segmentLength / 2.0, 2)) * 0.2 + adjustedTime / 80.0);

        double combined = (plasma1 + plasma2 + plasma3 + plasma4) / 4.0;
        double hue = (combined * 0.5 + 0.5 + adjustedTime / 2000.0) % 1.0;

        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 97;
    }

    @Override
    public String getEffectName() {
        return "Plasma";
    }
}
