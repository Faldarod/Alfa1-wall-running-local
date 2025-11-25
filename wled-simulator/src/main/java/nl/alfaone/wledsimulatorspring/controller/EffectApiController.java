package nl.alfaone.wledsimulatorspring.controller;

import nl.alfaone.wledsimulatorspring.domain.Segment;
import nl.alfaone.wledsimulatorspring.service.EffectSimulator;
import nl.alfaone.wledsimulatorspring.service.WledService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST API controller for WLED effect information and simulation
 */
@RestController
@RequestMapping("/api/effects")
public class EffectApiController {

    private final WledService wledService;
    private final EffectSimulator effectSimulator;

    public EffectApiController(WledService wledService, EffectSimulator effectSimulator) {
        this.wledService = wledService;
        this.effectSimulator = effectSimulator;
    }

    /**
     * Get list of all available effects
     * GET /api/effects
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getEffects() {
        Map<String, Object> response = new HashMap<>();
        response.put("effects", wledService.getEffectsMap());
        response.put("count", wledService.getEffects().size());
        return ResponseEntity.ok(response);
    }

    /**
     * Get list of all available palettes
     * GET /api/effects/palettes
     */
    @GetMapping("/palettes")
    public ResponseEntity<Map<String, Object>> getPalettes() {
        Map<String, Object> response = new HashMap<>();
        response.put("palettes", wledService.getPalettes());
        response.put("count", wledService.getPalettes().size());
        return ResponseEntity.ok(response);
    }

    /**
     * Simulate effect rendering for a segment
     * GET /api/effects/simulate/{segmentId}?time={timestamp}
     */
    @GetMapping("/simulate/{segmentId}")
    public ResponseEntity<Map<String, Object>> simulateEffect(
            @PathVariable Integer segmentId,
            @RequestParam(defaultValue = "0") long time) {

        Segment segment = wledService.getCurrentState().getSegments().stream()
                .filter(s -> s.getId().equals(segmentId))
                .findFirst()
                .orElse(null);

        if (segment == null) {
            return ResponseEntity.notFound().build();
        }

        // Calculate colors for all LEDs in the segment
        List<int[]> ledColors = new ArrayList<>();
        for (int i = 0; i < segment.getLength(); i++) {
            int[] color = effectSimulator.calculateLedColor(segment, i, time);
            ledColors.add(color);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("segmentId", segmentId);
        response.put("effect", segment.getEffect());
        response.put("effectName", wledService.getEffects().get(segment.getEffect()));
        response.put("colors", ledColors);
        response.put("time", time);

        return ResponseEntity.ok(response);
    }

    /**
     * Simulate all segments
     * GET /api/effects/simulate-all?time={timestamp}
     */
    @GetMapping("/simulate-all")
    public ResponseEntity<Map<String, Object>> simulateAllEffects(
            @RequestParam(defaultValue = "0") long time) {

        List<Map<String, Object>> segmentData = new ArrayList<>();

        for (Segment segment : wledService.getCurrentState().getSegments()) {
            List<int[]> ledColors = new ArrayList<>();
            for (int i = 0; i < segment.getLength(); i++) {
                int[] color = effectSimulator.calculateLedColor(segment, i, time);
                ledColors.add(color);
            }

            Map<String, Object> segmentInfo = new HashMap<>();
            segmentInfo.put("segmentId", segment.getId());
            segmentInfo.put("effect", segment.getEffect());
            segmentInfo.put("effectName", wledService.getEffects().get(segment.getEffect()));
            segmentInfo.put("colors", ledColors);
            segmentInfo.put("on", segment.getOn());
            segmentInfo.put("brightness", segment.getBrightness());

            segmentData.add(segmentInfo);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("segments", segmentData);
        response.put("time", time);
        response.put("masterOn", wledService.getCurrentState().getOn());
        response.put("masterBrightness", wledService.getCurrentState().getBrightness());

        return ResponseEntity.ok(response);
    }
}
