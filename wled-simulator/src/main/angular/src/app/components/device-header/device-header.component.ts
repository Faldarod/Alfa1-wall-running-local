import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { DeviceInfo } from '../../models/wled-state.model';

@Component({
  selector: 'app-device-header',
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule
  ],
  templateUrl: './device-header.component.html',
  styleUrls: ['./device-header.component.scss']
})
export class DeviceHeaderComponent {
  @Input() deviceInfo!: DeviceInfo;
}
