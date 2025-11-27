package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 165: Pixels
 * Random colored pixels (audio reactive).
 */
@Component
public class Effect165_Pixels extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        double pixelChance = normalizeSpeed(speed) * normalizeIntensity(intensity) * 0.04;
        if (random.nextDouble() < pixelChance) {
            double hue = random.nextDouble();
            return hsvToRgb(hue, 1.0, 1.0);
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 165;
    }

    @Override
    public String getEffectName() {
        return "Pixels";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
