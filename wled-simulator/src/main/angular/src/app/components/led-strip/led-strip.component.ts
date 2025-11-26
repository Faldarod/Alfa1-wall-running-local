import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';
import { EffectFrame, EffectSegmentFrame } from '../../models/wled-state.model';
import { EffectService } from '../../services/effect.service';

interface Led {
  index: number;
  color: string;
  segmentId: number;
}

@Component({
  selector: 'app-led-strip',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './led-strip.component.html',
  styleUrls: ['./led-strip.component.scss']
})
export class LedStripComponent implements OnInit, OnDestroy {
  @Input() totalLeds: number = 380;
  @Input() ledsPerRow: number = 20;

  leds: Led[] = [];
  private effectSubscription?: Subscription;

  constructor(private effectService: EffectService) {}

  ngOnInit(): void {
    // Initialize LEDs
    this.initializeLeds();

    // Subscribe to effect frames for real-time updates
    this.effectSubscription = this.effectService.effectFrame$.subscribe({
      next: (frame) => this.updateLedsFromFrame(frame),
      error: (error) => console.error('LED strip effect error:', error)
    });
  }

  ngOnDestroy(): void {
    this.effectSubscription?.unsubscribe();
  }

  private initializeLeds(): void {
    this.leds = Array.from({ length: this.totalLeds }, (_, index) => ({
      index,
      color: '#000000',
      segmentId: -1
    }));
  }

  private updateLedsFromFrame(frame: EffectFrame): void {
    if (!frame.masterOn) {
      // Turn off all LEDs when master is off
      this.leds.forEach(led => led.color = '#000000');
      return;
    }

    // Update each segment
    frame.segments.forEach((segment: EffectSegmentFrame) => {
      const segmentLeds = this.getSegmentLeds(segment.segmentId);

      if (!segment.on || !segment.colors || segment.colors.length === 0) {
        // Turn off this segment
        segmentLeds.forEach(ledIndex => {
          this.leds[ledIndex].color = '#000000';
        });
        return;
      }

      // Apply colors to segment LEDs
      segmentLeds.forEach((ledIndex, i) => {
        const colorIndex = i % segment.colors.length;
        const [r, g, b] = segment.colors[colorIndex];
        this.leds[ledIndex].color = this.rgbToHex(r, g, b);
      });
    });
  }

  private getSegmentLeds(segmentId: number): number[] {
    // Each segment has 76 LEDs (380 total / 5 segments)
    const ledsPerSegment = 76;
    const start = segmentId * ledsPerSegment;
    const end = Math.min(start + ledsPerSegment, this.totalLeds);

    return Array.from({ length: end - start }, (_, i) => start + i);
  }

  private rgbToHex(r: number, g: number, b: number): string {
    const toHex = (n: number) => {
      const hex = Math.max(0, Math.min(255, Math.round(n))).toString(16);
      return hex.length === 1 ? '0' + hex : hex;
    };
    return `#${toHex(r)}${toHex(g)}${toHex(b)}`;
  }

  getRows(): Led[][] {
    const rows: Led[][] = [];
    for (let i = 0; i < this.leds.length; i += this.ledsPerRow) {
      rows.push(this.leds.slice(i, i + this.ledsPerRow));
    }
    return rows;
  }
}
