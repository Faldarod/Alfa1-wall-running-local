package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 88: Candle
 * Realistic candle flame flicker effect.
 */
@Component
public class Effect088_Candle extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;

        // Candle flicker with varying intensity
        double flicker1 = Math.sin(time * 0.01 + ledIndex * 0.3);
        double flicker2 = Math.sin(time * 0.017 + ledIndex * 0.7);
        double flicker = (flicker1 + flicker2) / 2.0;
        double brightness = 0.6 + 0.4 * flicker * normalizeIntensity(intensity);

        // Warm candle colors
        int red = 255;
        int green = (int)(140 * brightness);
        int blue = 0;

        return new int[]{red, clamp(green), blue};
    }

    @Override
    public int getEffectId() {
        return 88;
    }

    @Override
    public String getEffectName() {
        return "Candle";
    }
}
