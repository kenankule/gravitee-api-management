/*
 * Copyright © 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.rest.api.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import io.gravitee.rest.api.model.context.OriginContext;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eric LELEU (eric.leleu at graviteesource.com)
 * @author Nicolas GERAUD (nicolas.geraud at graviteesource.com)
 * @author GraviteeSource Team
 */
@NoArgsConstructor
@Getter
@Setter
public class NewSubscriptionEntity {

    private String application;
    private String plan;
    private String request;
    private PageEntity.PageRevisionId generalConditionsContentRevision;
    private Boolean generalConditionsAccepted;
    private ApiKeyMode apiKeyMode;

    private SubscriptionConfigurationEntity configuration;

    private OriginContext.Origin origin = OriginContext.Origin.MANAGEMENT;

    private Map<String, String> metadata;

    public NewSubscriptionEntity(String plan, String application) {
        this.application = application;
        this.plan = plan;
    }
}
