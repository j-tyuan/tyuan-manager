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
package org.tyuan.service.application.service.security.auth;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tyuan.service.application.config.JwtSettings;
import org.tyuan.service.application.service.security.model.token.JwtTokenFactory;
import org.tyuan.service.data.security.JwtToken;
import org.tyuan.service.data.security.event.UserAuthDataChangedEvent;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.tyuan.service.data.cache.CacheConstant.TOKEN_OUTDATAGE_TIME_CACHE;

@Service
@RequiredArgsConstructor
public class TokenOutdatingService {
    private final CacheManager cacheManager;
    private final JwtTokenFactory tokenFactory;
    private final JwtSettings jwtSettings;
    private Cache tokenOutdatageTimeCache;

    @PostConstruct
    protected void initCache() {
        tokenOutdatageTimeCache = cacheManager.getCache(TOKEN_OUTDATAGE_TIME_CACHE);
    }

    @EventListener(classes = UserAuthDataChangedEvent.class)
    public void onUserAuthDataChanged(UserAuthDataChangedEvent userAuthDataChangedEvent) {
        outdateOldUserTokens(userAuthDataChangedEvent.getUserId());
    }

    public boolean isOutdated(JwtToken token, String userId) {
        Claims claims = tokenFactory.parseTokenClaims(token).getBody();
        long issueTime = claims.getIssuedAt().getTime();

        return Optional.ofNullable(tokenOutdatageTimeCache.get(userId, Long.class))
                .map(outdatageTime -> {
                    if (System.currentTimeMillis() - outdatageTime <= SECONDS.toMillis(jwtSettings.getRefreshTokenExpTime())) {
                        return MILLISECONDS.toSeconds(issueTime) < MILLISECONDS.toSeconds(outdatageTime);
                    } else {
                        /*
                         * Means that since the outdating has passed more than
                         * the lifetime of refresh token (the longest lived)
                         * and there is no need to store outdatage time anymore
                         * as all the tokens issued before the outdatage time
                         * are now expired by themselves
                         * */
                        tokenOutdatageTimeCache.evict(userId);
                        return false;
                    }
                })
                .orElse(false);
    }

    public void outdateOldUserTokens(String userId) {
        tokenOutdatageTimeCache.put(userId, System.currentTimeMillis());
    }

}
