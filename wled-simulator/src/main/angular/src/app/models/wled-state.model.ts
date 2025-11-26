export interface WledState {
  on: boolean;
  bri: number;
  seg: Segment[];
  transition?: number;
  ps?: number;
  pl?: number;
}

export interface Segment {
  id: number;
  start: number;
  stop: number;
  length?: number;
  on: boolean;
  bri: number;
  col: [[number, number, number], [number, number, number], [number, number, number]];
  fx: number;
  sx: number;
  ix: number;
  pal: number;
  grp?: number;
  spc?: number;
  of?: number;
  frz?: boolean;
  rev?: boolean;
  mi?: boolean;
}

export interface DeviceInfo {
  name: string;
  version: string;
  leds: {
    count: number;
  };
  mac?: string;
  brand?: string;
  product?: string;
}

export interface EffectMap {
  [id: number]: string;
}

export interface PaletteMap {
  [id: number]: string;
}

export interface EffectFrame {
  masterOn: boolean;
  segments: EffectSegmentFrame[];
}

export interface EffectSegmentFrame {
  segmentId: number;
  on: boolean;
  effect: number;
  colors: [number, number, number][];
}

export interface WledMessage {
  state: WledState;
  info?: DeviceInfo;
}

export enum ConnectionStatus {
  CONNECTED = 'CONNECTED',
  CONNECTING = 'CONNECTING',
  DISCONNECTED = 'DISCONNECTED',
  ERROR = 'ERROR'
}
