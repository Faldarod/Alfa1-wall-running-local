import { Injectable, OnDestroy } from '@angular/core';
import { interval, Subject, Observable } from 'rxjs';
import { switchMap, takeUntil, share } from 'rxjs/operators';
import { EffectFrame } from '../models/wled-state.model';
import { WledApiService } from './wled-api.service';

@Injectable({
  providedIn: 'root'
})
export class EffectService implements OnDestroy {
  private destroy$ = new Subject<void>();
  private effectFrameSubject = new Subject<EffectFrame>();
  public effectFrame$: Observable<EffectFrame>;

  constructor(private apiService: WledApiService) {
    // Create shared observable for effect animation loop (20 FPS = 50ms interval)
    this.effectFrame$ = interval(50).pipe(
      switchMap(() => this.apiService.simulateEffects(Date.now())),
      takeUntil(this.destroy$),
      share()
    );

    // Start the animation loop
    this.effectFrame$.subscribe({
      next: (frame) => this.effectFrameSubject.next(frame),
      error: (error) => console.error('Effect simulation error:', error)
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
