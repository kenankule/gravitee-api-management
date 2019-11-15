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
import {Component, Input, OnInit} from '@angular/core';
import {User} from 'ng-portal-webclient/dist';
import jdenticon from 'jdenticon';

@Component({
  selector: 'app-user-avatar',
  templateUrl: './user-avatar.component.html'
})
export class UserAvatarComponent implements OnInit {

  private hasError: boolean;
  @Input() user: User;
  @Input() size: number = Number(40);

  constructor() {
    this.hasError = false;
  }

  ngOnInit() {
  }

  getDefaultAvatar() {
    return jdenticon.toSvg(this.user.display_name, this.size);
  }

}
