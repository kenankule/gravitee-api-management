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
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ApiScoreDashboardComponent } from './dashboard/api-score-dashboard.component';
import { ApiScoreNavigationComponent } from './navigation/api-score-navigation.component';
import { ApiScoreRulesetsComponent } from './rulesets/api-score-rulesets.component';
import { ImportApiScoreRulesetComponent } from './rulesets/import/import-api-score-ruleset.component';
import { EditApiScoreRulesetComponent } from './rulesets/edit/edit-api-score-ruleset.component';
import { ImportScoringFunctionComponent } from './rulesets/import-function/import-scoring-function.component';

const routes: Routes = [
  {
    path: '',
    component: ApiScoreNavigationComponent,

    children: [
      {
        path: '',
        component: ApiScoreDashboardComponent,
      },
      {
        path: 'rulesets/import',
        component: ImportApiScoreRulesetComponent,
      },
      {
        path: 'rulesets/:id/edit',
        component: EditApiScoreRulesetComponent,
      },
      {
        path: 'rulesets',
        component: ApiScoreRulesetsComponent,
      },
      {
        path: 'rulesets/import-function',
        component: ImportScoringFunctionComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ApiScoreRoutingModule {}
