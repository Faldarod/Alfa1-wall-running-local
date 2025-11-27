package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 5: Random Colors
 * Each LED displays a random color that changes frequently.
 * Speed controls how fast colors change.
 */
@Component
public class Effect005_RandomColors extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        // Faster color changes - divide by smaller number for more frequent updates
        // Speed affects the time division: higher speed = faster changes
        double timeScale = 1000.0 / (1.0 + normalizeSpeed(speed) * 4.0); // 1000ms to 200ms
        long seed = ledIndex * 1000 + (long)(adjustedTime / timeScale);
        random.setSeed(seed);

        double hue = random.nextDouble();
        return hsvToRgb(hue, 1.0, 1.0);
    }

    @Override
    public int getEffectId() {
        return 5;
    }

    @Override
    public String getEffectName() {
        return "Random Colors";
    }
}
