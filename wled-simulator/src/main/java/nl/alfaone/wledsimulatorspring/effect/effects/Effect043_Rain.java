package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 43: Rain
 * Falling rain droplet effect.
 */
@Component
public class Effect043_Rain extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        double adjustedTime = getAdjustedTime(time, speed);

        // Create falling drops
        double dropPosition = ((adjustedTime / 50.0) + ledIndex * 0.3) % segmentLength;
        double distance = Math.abs(ledIndex - dropPosition);

        double dropChance = normalizeIntensity(intensity) * 0.1;
        if (distance < 2 && random.nextDouble() < dropChance) {
            return getPrimaryColor(segment);
        }
        return black();
    }

    @Override
    public int getEffectId() {
        return 43;
    }

    @Override
    public String getEffectName() {
        return "Rain";
    }
}
