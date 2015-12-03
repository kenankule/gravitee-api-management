/**
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
package io.gravitee.gateway.services.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gravitee.gateway.core.definition.Api;
import io.gravitee.gateway.core.manager.ApiManager;
import io.gravitee.repository.exceptions.TechnicalException;
import io.gravitee.repository.management.api.ApiRepository;
import io.gravitee.repository.management.model.LifecycleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author David BRASSELY (brasseld at gmail.com)
 */
public class SyncManager {

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(SyncManager.class);

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ApiManager apiManager;

    @Autowired
    private ObjectMapper objectMapper;

    public void refresh() {
        logger.debug("Refreshing gateway state...");

        try {
            Set<io.gravitee.repository.management.model.Api> apis = apiRepository.findAll();

            Map<String, Api> apisMap = apis.stream()
                    .map(this::convert)
                    .filter(api -> api != null)
                    .collect(Collectors.toMap(Api::getId, api -> api));

            // Determine APIs to undeploy
            Set<String> apiToRemove = apiManager.apis().stream()
                    .filter(apiId -> !apisMap.containsKey(apiId.getId()))
                    .map(Api::getId)
                    .collect(Collectors.toSet());

            apiToRemove.stream().forEach(apiManager::undeploy);

            // Determine APIs to update
            apisMap.keySet().stream()
                    .filter(apiId ->  apiManager.get(apiId) != null)
                    .forEach(apiId -> {
                        // Get local cached API
                        Api deployedApi = apiManager.get(apiId);

                        // Get API from store
                        Api remoteApi = apisMap.get(apiId);

                        if (deployedApi.getDeployedAt().before(remoteApi.getDeployedAt())) {
                            apiManager.update(remoteApi);
                        }
                    });

            // Determine APIs to create
            apisMap.keySet().stream()
                    .filter(apiId ->  apiManager.get(apiId) == null)
                    .forEach(apiId -> {
                        Api newApi = apisMap.get(apiId);
                        apiManager.deploy(newApi);
                    });

        } catch (TechnicalException te) {
            logger.error("Unable to sync instance", te);
        }
    }

    private Api convert(io.gravitee.repository.management.model.Api remoteApi) {
        try {
            String definition = remoteApi.getDefinition();
            if (definition != null && !definition.isEmpty()) {
                Api api = objectMapper.readValue(definition, Api.class);

                api.setId(remoteApi.getId());
                api.setName(remoteApi.getName());
                api.setVersion(remoteApi.getVersion());
                api.setEnabled(remoteApi.getLifecycleState() == LifecycleState.STARTED);
                api.setDeployedAt(remoteApi.getUpdatedAt());

                return api;
            }
        } catch (IOException ioe) {
            logger.error("Unable to prepare API definition from repository", ioe);
        }

        return null;
    }

    public void setApiRepository(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public void setApiManager(ApiManager apiManager) {
        this.apiManager = apiManager;
    }
}
