/**
 * Copyright © 2016-2021 The Thingsboard Authors
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
package org.tyuan.service.application.service.security.permission;


import org.tyuan.service.application.service.security.model.SecurityUser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface PermissionChecker<I> {

    default boolean hasPermission(SecurityUser user, Operation operation) {
        return false;
    }

    default boolean hasPermission(SecurityUser user, Operation operation, I id) {
        return false;
    }

    public class GenericPermissionChecker<I> implements PermissionChecker<I> {

        private final Set<Operation> allowedOperations;

        public GenericPermissionChecker(Operation... operations) {
            allowedOperations = new HashSet<Operation>(Arrays.asList(operations));
        }

        @Override
        public boolean hasPermission(SecurityUser user, Operation operation) {
            return allowedOperations.contains(Operation.ALL) || allowedOperations.contains(operation);
        }

        @Override
        public boolean hasPermission(SecurityUser user, Operation operation, I id) {
            return allowedOperations.contains(Operation.ALL) || allowedOperations.contains(operation);
        }
    }

    public static PermissionChecker denyAllPermissionChecker = new PermissionChecker() {};

    public PermissionChecker allowAllPermissionChecker = new PermissionChecker<Long>() {

        @Override
        public boolean hasPermission(SecurityUser user, Operation operation) {
            return true;
        }

        @Override
        public boolean hasPermission(SecurityUser user, Operation operation, Long id) {
            return true;
        }
    };


}
