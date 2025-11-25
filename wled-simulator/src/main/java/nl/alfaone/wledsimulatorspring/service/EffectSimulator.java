package nl.alfaone.wledsimulatorspring.service;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service to simulate WLED effect rendering
 * Provides color calculations for various effect types to enable visual simulation
 */
@Service
public class EffectSimulator {

    private final Random random = new Random();

    /**
     * Calculate LED colors for a segment based on its current effect
     * @param segment The segment configuration
     * @param ledIndex The LED index within the segment
     * @param time Current animation time (milliseconds)
     * @return RGB color array [R, G, B]
     */
    public int[] calculateLedColor(Segment segment, int ledIndex, long time) {
        if (!segment.getOn()) {
            return new int[]{0, 0, 0}; // Off
        }

        int effectId = segment.getEffect() != null ? segment.getEffect() : 0;
        int speed = segment.getSpeed() != null ? segment.getSpeed() : 128;
        int intensity = segment.getIntensity() != null ? segment.getIntensity() : 128;
        int brightness = segment.getBrightness() != null ? segment.getBrightness() : 255;

        // Get primary color
        int[] baseColor = getPrimaryColor(segment);

        // Apply effect-specific modifications
        int[] effectColor = applyEffect(effectId, baseColor, ledIndex, segment.getLength(), time, speed, intensity);

        // Apply brightness
        return applyBrightness(effectColor, brightness);
    }

    /**
     * Get the primary color from segment configuration
     */
    private int[] getPrimaryColor(Segment segment) {
        if (segment.getColors() != null && !segment.getColors().isEmpty() &&
            segment.getColors().get(0) != null && segment.getColors().get(0).size() >= 3) {
            List<Integer> color = segment.getColors().get(0);
            return new int[]{color.get(0), color.get(1), color.get(2)};
        }
        return new int[]{255, 255, 255}; // Default white
    }

    /**
     * Apply effect-specific color calculations
     */
    private int[] applyEffect(int effectId, int[] baseColor, int ledIndex, int segmentLength,
                              long time, int speed, int intensity) {
        // Normalize speed (0-255) to time multiplier
        double speedFactor = speed / 128.0;
        double adjustedTime = time * speedFactor;

        switch (effectId) {
            case 0: // Solid
                return baseColor;

            case 1: // Blink
                return applyBlink(baseColor, adjustedTime);

            case 2: // Breathe
                return applyBreathe(baseColor, adjustedTime);

            case 9: // Rainbow
                return applyRainbow(ledIndex, segmentLength, adjustedTime);

            case 10: // Scan
                return applyScan(baseColor, ledIndex, segmentLength, adjustedTime);

            case 20: // Sparkle
                return applySparkle(baseColor, ledIndex, intensity);

            case 28: // Chase
                return applyChase(baseColor, ledIndex, segmentLength, adjustedTime, intensity);

            case 40: // Scanner (Knight Rider)
                return applyScanner(baseColor, ledIndex, segmentLength, adjustedTime, intensity);

            case 42: // Fireworks
                return applyFireworks(baseColor, ledIndex, adjustedTime, intensity);

            case 66: // Fire 2012
                return applyFire(ledIndex, segmentLength, adjustedTime, intensity);

            case 74: // Colortwinkle
                return applyColorTwinkle(ledIndex, adjustedTime, intensity);

            case 88: // Candle
                return applyCandle(adjustedTime, intensity);

            case 92: // Sinelon
                return applySinelon(baseColor, ledIndex, segmentLength, adjustedTime);

            case 97: // Plasma
                return applyPlasma(ledIndex, segmentLength, adjustedTime);

            default:
                // For unimplemented effects, return base color with slight animation
                double pulse = Math.sin(adjustedTime / 1000.0) * 0.2 + 0.8;
                return new int[]{
                    (int)(baseColor[0] * pulse),
                    (int)(baseColor[1] * pulse),
                    (int)(baseColor[2] * pulse)
                };
        }
    }

    private int[] applyBlink(int[] baseColor, double time) {
        boolean on = (time / 1000.0) % 2 < 1;
        return on ? baseColor : new int[]{0, 0, 0};
    }

    private int[] applyBreathe(int[] baseColor, double time) {
        double brightness = (Math.sin(time / 1000.0) + 1.0) / 2.0; // 0-1
        return new int[]{
            (int)(baseColor[0] * brightness),
            (int)(baseColor[1] * brightness),
            (int)(baseColor[2] * brightness)
        };
    }

    private int[] applyRainbow(int ledIndex, int segmentLength, double time) {
        double hue = ((ledIndex / (double)segmentLength) + (time / 5000.0)) % 1.0;
        return hsvToRgb(hue, 1.0, 1.0);
    }

    private int[] applyScan(int[] baseColor, int ledIndex, int segmentLength, double time) {
        int position = (int)((time / 50.0) % segmentLength);
        double distance = Math.abs(ledIndex - position);
        double brightness = Math.max(0, 1.0 - distance / 5.0);
        return new int[]{
            (int)(baseColor[0] * brightness),
            (int)(baseColor[1] * brightness),
            (int)(baseColor[2] * brightness)
        };
    }

    private int[] applySparkle(int[] baseColor, int ledIndex, int intensity) {
        // Random sparkles based on intensity
        double sparkleChance = intensity / 2550.0; // 0-0.1
        if (random.nextDouble() < sparkleChance) {
            return new int[]{255, 255, 255}; // White sparkle
        }
        return baseColor;
    }

    private int[] applyChase(int[] baseColor, int ledIndex, int segmentLength, double time, int intensity) {
        int chaseSize = Math.max(1, intensity / 50); // Size of chase group
        int position = (int)((time / 100.0) % segmentLength);
        boolean inChase = Math.abs(ledIndex - position) < chaseSize;
        return inChase ? baseColor : new int[]{0, 0, 0};
    }

    private int[] applyScanner(int[] baseColor, int ledIndex, int segmentLength, double time, int intensity) {
        double position = (Math.sin(time / 1000.0) + 1.0) / 2.0 * segmentLength;
        double distance = Math.abs(ledIndex - position);
        double tailLength = intensity / 10.0;
        double brightness = Math.max(0, 1.0 - distance / tailLength);
        return new int[]{
            (int)(baseColor[0] * brightness),
            (int)(baseColor[1] * brightness),
            (int)(baseColor[2] * brightness)
        };
    }

    private int[] applyFireworks(int[] baseColor, int ledIndex, double time, int intensity) {
        double burst = Math.sin(time / 200.0);
        if (burst > 0.95) {
            return random.nextBoolean() ? new int[]{255, 255, 255} : baseColor;
        }
        return new int[]{0, 0, 0};
    }

    private int[] applyFire(int ledIndex, int segmentLength, double time, int intensity) {
        // Fire effect with red-orange-yellow colors
        double heat = random.nextDouble() * (intensity / 255.0);
        double flicker = Math.sin(time / 100.0 + ledIndex) * 0.3 + 0.7;
        heat *= flicker;

        if (heat < 0.3) {
            return new int[]{(int)(heat * 255 * 3), 0, 0}; // Dark red
        } else if (heat < 0.6) {
            return new int[]{255, (int)((heat - 0.3) * 255 * 3), 0}; // Orange
        } else {
            return new int[]{255, 255, (int)((heat - 0.6) * 255 * 2.5)}; // Yellow
        }
    }

    private int[] applyColorTwinkle(int ledIndex, double time, int intensity) {
        if (random.nextDouble() < intensity / 2550.0) {
            double hue = random.nextDouble();
            return hsvToRgb(hue, 1.0, 1.0);
        }
        return new int[]{0, 0, 0};
    }

    private int[] applyCandle(double time, int intensity) {
        double flicker = random.nextDouble() * 0.3 + 0.7;
        double flame = Math.sin(time / 100.0) * 0.2 + 0.8;
        double brightness = flicker * flame * (intensity / 255.0);
        return new int[]{
            (int)(255 * brightness),
            (int)(147 * brightness),
            (int)(41 * brightness)
        };
    }

    private int[] applySinelon(int[] baseColor, int ledIndex, int segmentLength, double time) {
        double position = (Math.sin(time / 500.0) + 1.0) / 2.0 * segmentLength;
        double distance = Math.abs(ledIndex - position);
        double brightness = Math.max(0, 1.0 - distance / 3.0);
        return new int[]{
            (int)(baseColor[0] * brightness),
            (int)(baseColor[1] * brightness),
            (int)(baseColor[2] * brightness)
        };
    }

    private int[] applyPlasma(int ledIndex, int segmentLength, double time) {
        double value1 = Math.sin(ledIndex / 10.0 + time / 1000.0);
        double value2 = Math.sin(ledIndex / 15.0 - time / 800.0);
        double hue = (value1 + value2 + 2.0) / 4.0;
        return hsvToRgb(hue, 1.0, 1.0);
    }

    /**
     * Apply brightness scaling to RGB color
     */
    private int[] applyBrightness(int[] color, int brightness) {
        double scale = brightness / 255.0;
        return new int[]{
            (int)(color[0] * scale),
            (int)(color[1] * scale),
            (int)(color[2] * scale)
        };
    }

    /**
     * Convert HSV to RGB
     * @param h Hue (0-1)
     * @param s Saturation (0-1)
     * @param v Value/Brightness (0-1)
     * @return RGB array [R, G, B] (0-255)
     */
    private int[] hsvToRgb(double h, double s, double v) {
        h = h % 1.0;
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
}
