package nl.alfaone.wledsimulatorspring.service;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.effect.EffectFactory;
import nl.alfaone.wledsimulatorspring.effect.EffectRenderer;
import org.springframework.stereotype.Service;

/**
 * Service to simulate WLED effect rendering.
 * Delegates to EffectFactory for effect-specific rendering,
 * applies global brightness adjustment.
 */
@Service
public class EffectSimulator {

    private final EffectFactory effectFactory;

    /**
     * Constructor with dependency injection.
     *
     * @param effectFactory Factory for effect lookup
     */
    public EffectSimulator(EffectFactory effectFactory) {
        this.effectFactory = effectFactory;
    }

    /**
     * Calculate LED colors for a segment based on its current effect.
     * Delegates to EffectFactory for effect-specific rendering, then applies brightness.
     *
     * @param segment The segment configuration
     * @param ledIndex The LED index within the segment
     * @param time Current animation time (milliseconds)
     * @return RGB color array [R, G, B]
     */
    public int[] calculateLedColor(Segment segment, int ledIndex, long time) {
        if (!segment.getOn()) {
            return new int[]{0, 0, 0}; // Off
        }

        // Get effect ID with default to 0 (Solid)
        int effectId = segment.getEffect() != null ? segment.getEffect() : 0;

        // Get effect renderer from factory
        EffectRenderer renderer = effectFactory.getEffect(effectId);

        // Render effect
        int[] effectColor = renderer.render(segment, ledIndex, time);

        // Apply global brightness
        int brightness = segment.getBrightness() != null ? segment.getBrightness() : 255;
        return applyBrightness(effectColor, brightness);
    }

    /**
     * Apply brightness scaling to RGB color.
     *
     * @param color RGB color array [R, G, B]
     * @param brightness Brightness value (0-255)
     * @return Brightness-adjusted RGB array [R, G, B]
     */
    private int[] applyBrightness(int[] color, int brightness) {
        double scale = brightness / 255.0;
        return new int[]{
            (int)(color[0] * scale),
            (int)(color[1] * scale),
            (int)(color[2] * scale)
        };
    }
}
