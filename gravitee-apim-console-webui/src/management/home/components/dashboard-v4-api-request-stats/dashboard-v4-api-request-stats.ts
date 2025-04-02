/*
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import { Component, Input } from '@angular/core';
import { MatTooltip } from '@angular/material/tooltip';
import { CommonModule, DecimalPipe } from '@angular/common';
import { GioLoaderModule } from '@gravitee/ui-particles-angular';
import { MatCard } from '@angular/material/card';
import { MatIcon } from '@angular/material/icon';

import { GioShortNumberPipeModule } from '../../../../shared/utils/shortNumber.pipe.module';

export type v4ApisRequestStats = {
  requestsPerSecond: number;
  requestsTotal: number;
  responseMinTime: number;
  responseMaxTime: number;
  responseAvgTime: number;
};

@Component({
<<<<<<< HEAD:gravitee-apim-console-webui/src/management/home/components/dashboard-v4-api-request-stats/dashboard-v4-api-request-stats.ts
  selector: 'dashboard-v4-api-request-stats',
  standalone: true,
  imports: [CommonModule, MatTooltip, DecimalPipe, GioShortNumberPipeModule, GioLoaderModule, MatCard, MatIcon],
  templateUrl: './dashboard-v4-api-request-stats.html',
  styleUrls: ['./dashboard-v4-api-request-stats.scss'],
=======
  selector: 'dashboard-api-request-stats',
  imports: [CommonModule, MatTooltip, MatCard, MatIcon, DecimalPipe, GioShortNumberPipeModule, GioLoaderModule],
  templateUrl: './dashboard-api-request-stats.component.html',
  styleUrls: ['./dashboard-api-request-stats.component.scss'],
>>>>>>> 2efa73dab5 (chore(console): remove explicit `standalone: true`):gravitee-apim-console-webui/src/management/home/components/dashboard-api-request-stats/dashboard-api-request-stats.component.ts
})
export class DashboardV4ApiRequestStats {
  @Input()
  public data?: v4ApisRequestStats;
}
