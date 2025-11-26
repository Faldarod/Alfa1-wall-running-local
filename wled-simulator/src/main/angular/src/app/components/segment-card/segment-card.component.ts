import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Segment, EffectMap, PaletteMap } from '../../models/wled-state.model';
import { StateService } from '../../services/state.service';

@Component({
  selector: 'app-segment-card',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatExpansionModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './segment-card.component.html',
  styleUrls: ['./segment-card.component.scss']
})
export class SegmentCardComponent implements OnInit {
  @Input() segment!: Segment;
  @Input() effects: EffectMap = {};
  @Input() palettes: PaletteMap = {};

  effectEntries: { id: number; name: string }[] = [];
  paletteEntries: { id: number; name: string }[] = [];

  constructor(private stateService: StateService) {}

  ngOnInit(): void {
    this.effectEntries = Object.entries(this.effects).map(([id, name]) => ({
      id: Number(id),
      name
    }));

    this.paletteEntries = Object.entries(this.palettes).map(([id, name]) => ({
      id: Number(id),
      name
    }));
  }

  toggleSegmentPower(): void {
    this.stateService.updateSegment(this.segment.id, {
      on: !this.segment.on
    });
  }

  onBrightnessChange(value: number): void {
    this.stateService.updateSegment(this.segment.id, {
      bri: value
    });
  }

  onEffectChange(effectId: number): void {
    this.stateService.updateSegment(this.segment.id, {
      fx: effectId
    });
  }

  onSpeedChange(value: number): void {
    this.stateService.updateSegment(this.segment.id, {
      sx: value
    });
  }

  onIntensityChange(value: number): void {
    this.stateService.updateSegment(this.segment.id, {
      ix: value
    });
  }

  onPaletteChange(paletteId: number): void {
    this.stateService.updateSegment(this.segment.id, {
      pal: paletteId
    });
  }

  onColorChange(colorIndex: number, colorValue: string): void {
    // Convert hex color to RGB array
    const hex = colorValue.replace('#', '');
    const r = parseInt(hex.substring(0, 2), 16);
    const g = parseInt(hex.substring(2, 4), 16);
    const b = parseInt(hex.substring(4, 6), 16);

    // Clone current colors array
    const newColors: [[number, number, number], [number, number, number], [number, number, number]] = [
      [...this.segment.col[0]],
      [...this.segment.col[1]],
      [...this.segment.col[2]]
    ] as [[number, number, number], [number, number, number], [number, number, number]];

    // Update the specific color
    newColors[colorIndex] = [r, g, b];

    this.stateService.updateSegment(this.segment.id, {
      col: newColors
    });
  }

  rgbToHex(rgb: [number, number, number]): string {
    const toHex = (n: number) => {
      const hex = Math.max(0, Math.min(255, Math.round(n))).toString(16);
      return hex.length === 1 ? '0' + hex : hex;
    };
    return `#${toHex(rgb[0])}${toHex(rgb[1])}${toHex(rgb[2])}`;
  }
}
