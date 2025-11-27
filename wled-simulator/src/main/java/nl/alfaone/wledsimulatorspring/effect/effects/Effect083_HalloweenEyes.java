package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 83: Halloween Eyes
 * Spooky blinking eyes appearing randomly.
 */
@Component
public class Effect083_HalloweenEyes extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;
        double adjustedTime = getAdjustedTime(time, speed);

        int eyePosition = (int)((adjustedTime / 200.0) % (segmentLength - 3));
        double blinkPhase = ((adjustedTime % 500) / 500.0);
        boolean blinking = blinkPhase > 0.8;

        if (!blinking && (ledIndex == eyePosition || ledIndex == eyePosition + 2)) {
            return new int[]{255, 50, 0}; // Orange eyes
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 83;
    }

    @Override
    public String getEffectName() {
        return "Halloween Eyes";
    }
}
