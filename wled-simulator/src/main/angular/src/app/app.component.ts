import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

import { DeviceHeaderComponent } from './components/device-header/device-header.component';
import { MasterControlsComponent } from './components/master-controls/master-controls.component';
import { LedStripComponent } from './components/led-strip/led-strip.component';
import { SegmentCardComponent } from './components/segment-card/segment-card.component';
import { ConnectionStatusComponent } from './components/connection-status/connection-status.component';

import { WledState, DeviceInfo, EffectMap, PaletteMap, ConnectionStatus } from './models/wled-state.model';
import { StateService } from './services/state.service';
import { WebsocketService } from './services/websocket.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    DeviceHeaderComponent,
    MasterControlsComponent,
    LedStripComponent,
    SegmentCardComponent,
    ConnectionStatusComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  deviceState$!: Observable<WledState>;
  deviceInfo$!: Observable<DeviceInfo>;
  effects$!: Observable<EffectMap>;
  palettes$!: Observable<PaletteMap>;
  connectionStatus$!: Observable<ConnectionStatus>;

  constructor(
    private stateService: StateService,
    private websocketService: WebsocketService
  ) {}

  ngOnInit(): void {
    this.deviceState$ = this.stateService.deviceState$;
    this.deviceInfo$ = this.stateService.deviceInfo$;
    this.effects$ = this.stateService.effects$;
    this.palettes$ = this.stateService.palettes$;
    this.connectionStatus$ = this.websocketService.connectionStatus$;
  }
}
