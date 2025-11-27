package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 164: Matripix
 * Matrix-style pixels (audio reactive).
 */
@Component
public class Effect164_Matripix extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        double adjustedTime = getAdjustedTime(time, speed);

        double pixelChance = 0.03;
        if (random.nextDouble() < pixelChance) {
            double brightness = 0.5 + 0.5 * random.nextDouble();
            return scale(new int[]{0, 255, 0}, brightness); // Matrix green
        }

        return black();
    }

    @Override
    public int getEffectId() {
        return 164;
    }

    @Override
    public String getEffectName() {
        return "Matripix";
    }

    @Override
    public boolean isAudioReactive() {
        return true;
    }
}
