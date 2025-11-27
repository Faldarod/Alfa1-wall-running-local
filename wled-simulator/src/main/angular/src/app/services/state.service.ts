import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { WledState, DeviceInfo, EffectMap, PaletteMap, Segment } from '../models/wled-state.model';
import { WledApiService } from './wled-api.service';
import { WebsocketService } from './websocket.service';

@Injectable({
  providedIn: 'root'
})
export class StateService {
  // Track last update ID to prevent duplicate processing
  private lastUpdateId = 0;

  // State subjects
  private deviceStateSubject = new BehaviorSubject<WledState>({
    on: false,
    bri: 128,
    seg: [],
    v: 0
  });
  public deviceState$: Observable<WledState> = this.deviceStateSubject.asObservable();

  private deviceInfoSubject = new BehaviorSubject<DeviceInfo>({
    name: 'Loading...',
    version: '0.0.0',
    leds: { count: 0 }
  });
  public deviceInfo$: Observable<DeviceInfo> = this.deviceInfoSubject.asObservable();

  private effectsSubject = new BehaviorSubject<EffectMap>({});
  public effects$: Observable<EffectMap> = this.effectsSubject.asObservable();

  private palettesSubject = new BehaviorSubject<PaletteMap>({});
  public palettes$: Observable<PaletteMap> = this.palettesSubject.asObservable();

  constructor(
    private apiService: WledApiService,
    private websocketService: WebsocketService
  ) {
    this.initialize();
  }

  /**
   * Initialize state service
   */
  private initialize(): void {
    // Load initial data
    this.loadInitialData();

    // Subscribe to WebSocket updates with deduplication
    this.websocketService.messages$.subscribe(message => {
      if (message.state) {
        this.updateStateIfNewer(message.state);
      }
      if (message.info) {
        this.deviceInfoSubject.next(message.info);
      }
    });
  }

  /**
   * Load initial data from API
   */
  private loadInitialData(): void {
    // Load state
    this.apiService.getState().subscribe({
      next: (state) => this.updateStateIfNewer(state),
      error: (error) => console.error('Failed to load state:', error)
    });

    // Load device info
    this.apiService.getInfo().subscribe({
      next: (info) => this.deviceInfoSubject.next(info),
      error: (error) => console.error('Failed to load device info:', error)
    });

    // Load effects
    this.apiService.getEffects().subscribe({
      next: (data) => this.effectsSubject.next(data.effects),
      error: (error) => console.error('Failed to load effects:', error)
    });

    // Load palettes
    this.apiService.getPalettes().subscribe({
      next: (data) => this.palettesSubject.next(data.palettes),
      error: (error) => console.error('Failed to load palettes:', error)
    });
  }

  /**
   * Update state only if it has a newer updateId
   */
  private updateStateIfNewer(state: WledState): void {
    const updateId = state.v || 0;
    console.log(`[StateService] Received state update: updateId=${updateId}, lastUpdateId=${this.lastUpdateId}`);
    if (updateId > this.lastUpdateId) {
      console.log(`[StateService] ✓ Applying update (updateId ${updateId} > ${this.lastUpdateId})`);
      this.lastUpdateId = updateId;
      this.deviceStateSubject.next(state);
    } else {
      console.log(`[StateService] ✗ Skipping duplicate update (updateId ${updateId} <= ${this.lastUpdateId})`);
    }
  }

  /**
   * Update master power
   */
  updateMasterPower(on: boolean): void {
    this.apiService.updateState({ on }).subscribe({
      next: (state) => this.updateStateIfNewer(state),
      error: (error) => console.error('Failed to update master power:', error)
    });
  }

  /**
   * Update master brightness
   */
  updateMasterBrightness(bri: number): void {
    this.apiService.updateState({ bri }).subscribe({
      next: (state) => this.updateStateIfNewer(state),
      error: (error) => console.error('Failed to update master brightness:', error)
    });
  }

  /**
   * Update segment
   */
  updateSegment(id: number, changes: Partial<Segment>): void {
    const payload: any = {
      seg: [{ id, ...changes }]
    };

    this.apiService.updateState(payload).subscribe({
      next: (state) => this.updateStateIfNewer(state),
      error: (error) => console.error('Failed to update segment:', error)
    });
  }

  /**
   * Get current device state
   */
  getCurrentState(): WledState {
    return this.deviceStateSubject.value;
  }

  /**
   * Get current device info
   */
  getCurrentInfo(): DeviceInfo {
    return this.deviceInfoSubject.value;
  }

  /**
   * Get effects map
   */
  getEffects(): EffectMap {
    return this.effectsSubject.value;
  }

  /**
   * Get palettes map
   */
  getPalettes(): PaletteMap {
    return this.palettesSubject.value;
  }
}
