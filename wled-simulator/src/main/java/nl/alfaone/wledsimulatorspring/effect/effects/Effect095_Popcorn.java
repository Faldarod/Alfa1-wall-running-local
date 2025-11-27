package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 95: Popcorn
 * Random popping lights like popcorn.
 */
@Component
public class Effect095_Popcorn extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double popChance = normalizeSpeed(speed) * 0.05;
        if (random.nextDouble() < popChance) {
            double brightness = 0.5 + 0.5 * normalizeIntensity(intensity);
            return scale(getPrimaryColor(segment), brightness);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 95;
    }

    @Override
    public String getEffectName() {
        return "Popcorn";
    }
}
