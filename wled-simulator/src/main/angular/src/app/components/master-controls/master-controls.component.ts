import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatIconModule } from '@angular/material/icon';
import { WledState } from '../../models/wled-state.model';
import { StateService } from '../../services/state.service';

@Component({
  selector: 'app-master-controls',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatIconModule
  ],
  templateUrl: './master-controls.component.html',
  styleUrls: ['./master-controls.component.scss']
})
export class MasterControlsComponent {
  @Input() state!: WledState;

  constructor(private stateService: StateService) {}

  toggleMasterPower(): void {
    this.stateService.updateMasterPower(!this.state.on);
  }

  onBrightnessChange(value: number): void {
    this.stateService.updateMasterBrightness(value);
  }
}
