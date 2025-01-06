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
package io.gravitee.rest.api.service.impl;

import io.gravitee.rest.api.service.exceptions.AbstractManagementException;
import io.gravitee.rest.api.service.exceptions.TransactionRetryableService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Azize ELAMRANI (azize.elamrani at graviteesource.com)
 * @author GraviteeSource Team
 */
@Transactional(value = "graviteeTransactionManager", noRollbackFor = AbstractManagementException.class)
@Retryable(
    retryFor = DataAccessException.class,
    exceptionExpression = TransactionRetryableService.EXPRESSION,
    maxAttemptsExpression = "${management.transaction.retry.maxAttempts:5}",
    backoff = @Backoff(
        delayExpression = "${management.transaction.retry.delayMs:100}",
        maxDelayExpression = "${management.transaction.retry.maxDelayMs:500}"
    )
)
public class TransactionalService {}
