package nl.alfaone.wledsimulatorspring.effect;

import nl.alfaone.wledsimulatorspring.domain.Segment;

import java.util.List;
import java.util.Random;

/**
 * Abstract base class for all effect implementations.
 * Provides shared utility methods for color manipulation, normalization, and common patterns.
 */
public abstract class AbstractEffect implements EffectRenderer {

    protected final Random random = new Random();

    /**
     * Extract the primary color from segment configuration.
     * Returns white (255,255,255) if no color is configured.
     *
     * @param segment Segment containing color configuration
     * @return RGB array [R, G, B]
     */
    protected int[] getPrimaryColor(Segment segment) {
        if (segment.getColors() != null && !segment.getColors().isEmpty() &&
            segment.getColors().get(0) != null && segment.getColors().get(0).size() >= 3) {
            List<Integer> color = segment.getColors().get(0);
            return new int[]{color.get(0), color.get(1), color.get(2)};
        }
        return new int[]{255, 255, 255}; // Default white
    }

    /**
     * Extract the secondary color from segment configuration.
     * Returns black (0,0,0) if no secondary color is configured.
     *
     * @param segment Segment containing color configuration
     * @return RGB array [R, G, B]
     */
    protected int[] getSecondaryColor(Segment segment) {
        if (segment.getColors() != null && segment.getColors().size() > 1 &&
            segment.getColors().get(1) != null && segment.getColors().get(1).size() >= 3) {
            List<Integer> color = segment.getColors().get(1);
            return new int[]{color.get(0), color.get(1), color.get(2)};
        }
        return new int[]{0, 0, 0}; // Default black
    }

    /**
     * Extract the tertiary color from segment configuration.
     * Returns mid-gray (128,128,128) if no tertiary color is configured.
     *
     * @param segment Segment containing color configuration
     * @return RGB array [R, G, B]
     */
    protected int[] getTertiaryColor(Segment segment) {
        if (segment.getColors() != null && segment.getColors().size() > 2 &&
            segment.getColors().get(2) != null && segment.getColors().get(2).size() >= 3) {
            List<Integer> color = segment.getColors().get(2);
            return new int[]{color.get(0), color.get(1), color.get(2)};
        }
        return new int[]{128, 128, 128}; // Default gray
    }

    /**
     * Convert HSV (Hue, Saturation, Value) to RGB.
     * This is essential for rainbow, color cycling, and gradient effects.
     *
     * @param h Hue (0.0-1.0, wraps around)
     * @param s Saturation (0.0-1.0)
     * @param v Value/Brightness (0.0-1.0)
     * @return RGB array [R, G, B] with values 0-255
     */
    protected int[] hsvToRgb(double h, double s, double v) {
        h = h % 1.0;
        if (h < 0) h += 1.0; // Handle negative hue values

        int i = (int)(h * 6);
        double f = h * 6 - i;
        double p = v * (1 - s);
        double q = v * (1 - f * s);
        double t = v * (1 - (1 - f) * s);

        double r, g, b;
        switch (i % 6) {
            case 0: r = v; g = t; b = p; break;
            case 1: r = q; g = v; b = p; break;
            case 2: r = p; g = v; b = t; break;
            case 3: r = p; g = q; b = v; break;
            case 4: r = t; g = p; b = v; break;
            case 5: r = v; g = p; b = q; break;
            default: r = g = b = 0;
        }

        return new int[]{(int)(r * 255), (int)(g * 255), (int)(b * 255)};
    }

    /**
     * Blend two RGB colors with a linear interpolation factor.
     * factor=0 returns color1, factor=1 returns color2, factor=0.5 returns mid-point.
     *
     * @param color1 First RGB color [R, G, B]
     * @param color2 Second RGB color [R, G, B]
     * @param factor Blend factor (0.0-1.0)
     * @return Blended RGB array [R, G, B]
     */
    protected int[] blend(int[] color1, int[] color2, double factor) {
        factor = Math.max(0, Math.min(1, factor)); // Clamp to 0-1
        return new int[]{
            (int)(color1[0] * (1 - factor) + color2[0] * factor),
            (int)(color1[1] * (1 - factor) + color2[1] * factor),
            (int)(color1[2] * (1 - factor) + color2[2] * factor)
        };
    }

    /**
     * Scale a color by a brightness factor.
     *
     * @param color RGB color [R, G, B]
     * @param factor Scaling factor (0.0-1.0)
     * @return Scaled RGB array [R, G, B]
     */
    protected int[] scale(int[] color, double factor) {
        factor = Math.max(0, Math.min(1, factor)); // Clamp to 0-1
        return new int[]{
            (int)(color[0] * factor),
            (int)(color[1] * factor),
            (int)(color[2] * factor)
        };
    }

    /**
     * Normalize speed parameter from WLED range (0-255) to multiplier (0.0-2.0).
     * Speed 128 = 1.0 (normal), 0 = 0.0 (stopped), 255 = 2.0 (double speed).
     *
     * @param speed Speed value from segment (0-255)
     * @return Normalized speed factor
     */
    protected double normalizeSpeed(int speed) {
        return speed / 128.0;
    }

    /**
     * Normalize intensity parameter from WLED range (0-255) to 0.0-1.0.
     *
     * @param intensity Intensity value from segment (0-255)
     * @return Normalized intensity (0.0-1.0)
     */
    protected double normalizeIntensity(int intensity) {
        return intensity / 255.0;
    }

    /**
     * Calculate adjusted time based on speed parameter.
     * Higher speed = faster animation.
     *
     * @param time Original time in milliseconds
     * @param speed Speed value from segment (0-255)
     * @return Adjusted time in milliseconds
     */
    protected double getAdjustedTime(long time, int speed) {
        return time * normalizeSpeed(speed);
    }

    /**
     * Calculate a sine wave value (0.0-1.0) based on time.
     * Useful for breathing, pulsing, and oscillation effects.
     *
     * @param time Time in milliseconds
     * @param period Period of oscillation in milliseconds
     * @return Sine wave value (0.0-1.0)
     */
    protected double sineWave(double time, double period) {
        return (Math.sin(time / period * Math.PI * 2) + 1.0) / 2.0;
    }

    /**
     * Calculate a triangle wave value (0.0-1.0) based on time.
     * Linear up, linear down. Useful for scanning effects.
     *
     * @param time Time in milliseconds
     * @param period Period of oscillation in milliseconds
     * @return Triangle wave value (0.0-1.0)
     */
    protected double triangleWave(double time, double period) {
        double phase = (time / period) % 1.0;
        return phase < 0.5 ? phase * 2 : 2 - phase * 2;
    }

    /**
     * Calculate distance-based fade (0.0-1.0).
     * Returns 1.0 at distance=0, fades to 0.0 at distance=maxDistance.
     *
     * @param distance Distance from target point
     * @param maxDistance Maximum distance before full fade
     * @return Fade factor (0.0-1.0)
     */
    protected double distanceFade(double distance, double maxDistance) {
        if (distance >= maxDistance) return 0.0;
        return 1.0 - (distance / maxDistance);
    }

    /**
     * Clamp an RGB color component to valid range (0-255).
     *
     * @param value Color component value
     * @return Clamped value (0-255)
     */
    protected int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    /**
     * Create a black (off) color.
     *
     * @return Black RGB array [0, 0, 0]
     */
    protected int[] black() {
        return new int[]{0, 0, 0};
    }

    /**
     * Create a white color.
     *
     * @return White RGB array [255, 255, 255]
     */
    protected int[] white() {
        return new int[]{255, 255, 255};
    }

    /**
     * Default implementation of is2D() - override if effect requires 2D matrix.
     */
    @Override
    public boolean is2D() {
        return false;
    }

    /**
     * Default implementation of isAudioReactive() - override if effect requires audio input.
     */
    @Override
    public boolean isAudioReactive() {
        return false;
    }
}
