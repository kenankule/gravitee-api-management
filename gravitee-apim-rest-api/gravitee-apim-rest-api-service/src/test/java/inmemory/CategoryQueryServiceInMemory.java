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
package inmemory;

import io.gravitee.apim.core.category.model.Category;
import io.gravitee.apim.core.category.query_service.CategoryQueryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryQueryServiceInMemory implements CategoryQueryService, InMemoryAlternative<Category> {

    private List<Category> storage = new ArrayList<>();

    @Override
    public void initWith(List<Category> items) {
        storage.clear();
        storage.addAll(items);
    }

    @Override
    public void reset() {
        storage.clear();
    }

    @Override
    public List<Category> storage() {
        return storage;
    }

    @Override
    public Optional<Category> findById(String idOrKey, String environmentId) {
        return storage
            .stream()
            .filter(category -> Objects.equals(idOrKey, category.getId()) || (Objects.equals(idOrKey, category.getKey())))
            .findFirst();
    }
}
