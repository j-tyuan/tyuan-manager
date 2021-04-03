/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.system.model.cache;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class Cache {

    private long updateTime;

    private long total;

    public void updateByMap(Map map) {

        Long total = (Long) map.get("total");
        if (null == total || total == 0) {

            return;
        }
        this.total = total;

        Long time = ((Date) map.get("updateDate")).getTime();
        this.updateTime = time;

    }

    public boolean complete(Map map) {

        if (null == map) {

            return false;
        }

        Long total = (Long) map.get("total");
        if (null == total || total == 0) {
            return false;
        }

        Long time = ((Date) map.get("updateDate")).getTime();
        return this.complete(time, total);
    }

    public boolean complete(long updateTime, long total) {
        return this.updateTime == updateTime &&
                this.total == total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cache cache = (Cache) o;
        return updateTime == cache.updateTime &&
                total == cache.total;
    }

    @Override
    public int hashCode() {
        return Objects.hash(updateTime, total);
    }
}
