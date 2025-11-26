import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WledState, DeviceInfo, EffectMap, PaletteMap, EffectFrame } from '../models/wled-state.model';

@Injectable({
  providedIn: 'root'
})
export class WledApiService {
  private readonly baseUrl = '';  // Empty because we're served from same origin

  constructor(private http: HttpClient) {}

  /**
   * Get current device state
   */
  getState(): Observable<WledState> {
    return this.http.get<WledState>(`${this.baseUrl}/json/state`);
  }

  /**
   * Update device state
   */
  updateState(payload: Partial<WledState>): Observable<WledState> {
    return this.http.post<WledState>(`${this.baseUrl}/json/state`, payload);
  }

  /**
   * Get device information
   */
  getInfo(): Observable<DeviceInfo> {
    return this.http.get<DeviceInfo>(`${this.baseUrl}/json/info`);
  }

  /**
   * Get available effects
   */
  getEffects(): Observable<{ effects: EffectMap; count: number }> {
    return this.http.get<{ effects: EffectMap; count: number }>(`${this.baseUrl}/api/effects`);
  }

  /**
   * Get available palettes
   */
  getPalettes(): Observable<{ palettes: PaletteMap; count: number }> {
    return this.http.get<{ palettes: PaletteMap; count: number }>(`${this.baseUrl}/api/effects/palettes`);
  }

  /**
   * Simulate effects for all segments at given time
   */
  simulateEffects(time: number): Observable<EffectFrame> {
    return this.http.get<EffectFrame>(`${this.baseUrl}/api/effects/simulate-all?time=${time}`);
  }

  /**
   * Get combined state and info
   */
  getAll(): Observable<{ state: WledState; info: DeviceInfo; effects: EffectMap; palettes: PaletteMap }> {
    return this.http.get<any>(`${this.baseUrl}/json`);
  }
}
