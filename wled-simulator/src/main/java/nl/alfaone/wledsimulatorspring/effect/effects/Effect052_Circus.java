package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 52: Circus
 * Colorful circus-style alternating lights.
 */
@Component
public class Effect052_Circus extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        int pattern = (ledIndex + (int)(adjustedTime / 300.0)) % 3;

        switch (pattern) {
            case 0: return new int[]{255, 0, 0};    // Red
            case 1: return new int[]{255, 255, 0};  // Yellow
            case 2: return new int[]{0, 0, 255};    // Blue
            default: return black();
        }
    }

    @Override
    public int getEffectId() {
        return 52;
    }

    @Override
    public String getEffectName() {
        return "Circus";
    }
}
