package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 120: Colored Bursts
 * Multiple colored burst explosions.
 */
@Component
public class Effect120_ColoredBursts extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        double burstPhase = (adjustedTime / 100.0) % (segmentLength * 2);
        double burstCenter = segmentLength / 2.0;
        double distance = Math.abs(ledIndex - burstCenter);

        if (distance < burstPhase && distance > burstPhase - 5) {
            double hue = (burstPhase / segmentLength + adjustedTime / 2000.0) % 1.0;
            double brightness = 1.0 - (burstPhase / segmentLength);
            return scale(hsvToRgb(hue, 1.0, 1.0), brightness);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 120;
    }

    @Override
    public String getEffectName() {
        return "Colored Bursts";
    }
}
