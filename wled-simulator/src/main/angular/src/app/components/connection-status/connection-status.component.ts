import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { ConnectionStatus } from '../../models/wled-state.model';

@Component({
  selector: 'app-connection-status',
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule
  ],
  templateUrl: './connection-status.component.html',
  styleUrls: ['./connection-status.component.scss']
})
export class ConnectionStatusComponent {
  @Input() status: ConnectionStatus = ConnectionStatus.DISCONNECTED;

  ConnectionStatus = ConnectionStatus;

  getStatusIcon(): string {
    switch (this.status) {
      case ConnectionStatus.CONNECTED:
        return 'wifi';
      case ConnectionStatus.CONNECTING:
        return 'wifi_find';
      case ConnectionStatus.DISCONNECTED:
        return 'wifi_off';
      case ConnectionStatus.ERROR:
        return 'error';
      default:
        return 'help';
    }
  }

  getStatusText(): string {
    switch (this.status) {
      case ConnectionStatus.CONNECTED:
        return 'Connected';
      case ConnectionStatus.CONNECTING:
        return 'Connecting...';
      case ConnectionStatus.DISCONNECTED:
        return 'Disconnected';
      case ConnectionStatus.ERROR:
        return 'Connection Error';
      default:
        return 'Unknown';
    }
  }

  getStatusClass(): string {
    switch (this.status) {
      case ConnectionStatus.CONNECTED:
        return 'status-connected';
      case ConnectionStatus.CONNECTING:
        return 'status-connecting';
      case ConnectionStatus.DISCONNECTED:
        return 'status-disconnected';
      case ConnectionStatus.ERROR:
        return 'status-error';
      default:
        return '';
    }
  }
}
