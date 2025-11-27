package nl.alfaone.wledsimulatorspring.effect;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory service for effect lookup and management.
 * Uses Spring's dependency injection to automatically discover all @Component effect implementations.
 */
@Service
public class EffectFactory {

    private final Map<Integer, EffectRenderer> effectsById;
    private final EffectRenderer fallbackEffect;

    /**
     * Constructor with Spring auto-injection.
     * Spring automatically collects all beans implementing EffectRenderer.
     *
     * @param effectList List of all EffectRenderer implementations (auto-injected by Spring)
     */
    public EffectFactory(List<EffectRenderer> effectList) {
        this.effectsById = new HashMap<>();

        // Register all effects by their ID
        for (EffectRenderer effect : effectList) {
            int id = effect.getEffectId();
            if (effectsById.containsKey(id)) {
                throw new IllegalStateException(
                    String.format("Duplicate effect ID %d: %s and %s",
                        id, effectsById.get(id).getEffectName(), effect.getEffectName())
                );
            }
            effectsById.put(id, effect);
        }

        // Set fallback to Effect 0 (Solid) or first available effect
        this.fallbackEffect = effectsById.getOrDefault(0, effectList.isEmpty() ? null : effectList.get(0));

        System.out.println("EffectFactory initialized with " + effectsById.size() + " effects");
    }

    /**
     * Get an effect renderer by its ID.
     * Returns a fallback effect if the requested ID is not found.
     *
     * @param effectId Effect ID (0-186)
     * @return EffectRenderer implementation
     */
    public EffectRenderer getEffect(int effectId) {
        return effectsById.getOrDefault(effectId, fallbackEffect);
    }

    /**
     * Check if an effect ID is registered.
     *
     * @param effectId Effect ID to check
     * @return true if the effect is registered
     */
    public boolean hasEffect(int effectId) {
        return effectsById.containsKey(effectId);
    }

    /**
     * Get the total number of registered effects.
     *
     * @return Number of registered effects
     */
    public int getEffectCount() {
        return effectsById.size();
    }

    /**
     * Get all registered effect IDs.
     *
     * @return Array of effect IDs
     */
    public int[] getRegisteredEffectIds() {
        return effectsById.keySet().stream()
            .mapToInt(Integer::intValue)
            .sorted()
            .toArray();
    }

    /**
     * Get all registered effects (for debugging/admin purposes).
     *
     * @return Map of effect ID to EffectRenderer
     */
    public Map<Integer, EffectRenderer> getAllEffects() {
        return new HashMap<>(effectsById); // Return copy to prevent external modification
    }
}
