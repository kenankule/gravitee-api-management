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
package io.gravitee.gateway.services.sync.process.common.synchronizer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Guillaume LAMIRAND (guillaume.lamirand at graviteesource.com)
 * @author GraviteeSource Team
 */
@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum Order {
    LICENSE(1),
    ACCESS_POINT(2),
    ORGANIZATION(3),
    DICTIONARY(4),
    API(5),
    SUBSCRIPTION(6),
    API_KEY(7),
    DEBUG(8);

    static {
        Set<Integer> elements = new HashSet<>();
        List<Order> orders = Arrays.stream(Order.values()).filter(order -> !elements.add(order.index)).toList();
        if (!orders.isEmpty()) {
            throw new IllegalStateException(String.format("Synchronizer order contain duplicated indexes [%s].", orders));
        }
    }

    private final int index;
}
