package com.tia102g1.socialmember.dao.impl;


import com.tia102g1.socialmember.dao.OAuth2MemberDao;
import com.tia102g1.socialmember.model.OAuth2Member;
import com.tia102g1.socialmember.rowmapper.OAuth2MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuth2MemberDaoImpl implements OAuth2MemberDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private OAuth2MemberRowMapper oAuth2MemberRowMapper;

    @Override
    public OAuth2Member getOAuth2Member(String provider, String providerId) {
        String sql = """
                SELECT oauth2memberId, provider, providerId, name, email, accessToken, expiresAt
                FROM socialmember
                WHERE provider = :provider AND providerId = :providerId
                """;

        Map<String, Object> map = new HashMap<>();
        map.put("provider", provider);
        map.put("providerId", providerId);

        List<OAuth2Member> oAuth2MemberList = namedParameterJdbcTemplate.query(sql, map, oAuth2MemberRowMapper);

        if (oAuth2MemberList.size() > 0) {
            return oAuth2MemberList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createOAuth2Member(OAuth2Member oAuth2Member) {
        String sql = """
                INSERT INTO socialmember(provider, providerId, name, email, accessToken, expiresAt)
                VALUES (:provider, :providerId, :name, :email, :accessToken, :expiresAt)
                """;

        Map<String, Object> map = new HashMap<>();
        map.put("provider", oAuth2Member.getProvider());
        map.put("providerId", oAuth2Member.getProviderId());
        map.put("name", oAuth2Member.getName());
        map.put("email", oAuth2Member.getEmail());
        map.put("accessToken", oAuth2Member.getAccessToken());
        map.put("expiresAt", oAuth2Member.getExpiresAt());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int oauth2MemberId = keyHolder.getKey().intValue();

        return oauth2MemberId;
    }
}