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
package io.gravitee.repository.management.api;

import io.gravitee.repository.exceptions.TechnicalException;
import io.gravitee.repository.management.model.ApiCategoryOrder;
import java.util.Optional;
import java.util.Set;

public interface ApiCategoryOrderRepository extends FindAllRepository<ApiCategoryOrder> {
    ApiCategoryOrder create(ApiCategoryOrder apiCategoryOrder) throws TechnicalException;
    ApiCategoryOrder update(ApiCategoryOrder apiCategoryOrder) throws TechnicalException;
    void delete(String apiId, String categoryId) throws TechnicalException;
    Optional<ApiCategoryOrder> findById(String apiId, String categoryId);
    Set<ApiCategoryOrder> findAllByCategoryId(String categoryId);
    Set<ApiCategoryOrder> findAllByApiId(String apiId);
}
