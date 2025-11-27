package nl.alfaone.wledsimulatorspring.effect.effects;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.AbstractEffect;
import org.springframework.stereotype.Component;

/**
 * Effect 89: Candle Multi
 * Multiple candles with independent flickers.
 */
@Component
public class Effect089_CandleMulti extends AbstractEffect {

    @Override
    public int[] render(Segment segment, int ledIndex, long time) {
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int segmentLength = segment.getLength() != null ? segment.getLength() : 76;

        // Each candle zone has independent flicker
        int candleZone = ledIndex / (segmentLength / 5);
        double flicker1 = Math.sin(time * 0.01 + candleZone * 2.1);
        double flicker2 = Math.sin(time * 0.015 + candleZone * 3.7);
        double flicker = (flicker1 + flicker2) / 2.0;
        double brightness = 0.6 + 0.4 * flicker * normalizeIntensity(intensity);

        int red = 255;
        int green = (int)(140 * brightness);
        int blue = 0;

        return new int[]{red, clamp(green), blue};
    }

    @Override
    public int getEffectId() {
        return 89;
    }

    @Override
    public String getEffectName() {
        return "Candle Multi";
    }
}
