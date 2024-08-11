package com.tia102g1.socialmember.rowmapper;


import com.tia102g1.socialmember.model.OAuth2Member;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OAuth2MemberRowMapper implements RowMapper<OAuth2Member> {

    @Override
    public OAuth2Member mapRow(ResultSet resultSet, int i) throws SQLException {
        OAuth2Member OAuth2Member = new OAuth2Member();
        OAuth2Member.setOauth2memberId(resultSet.getInt("oauth2memberId"));
        OAuth2Member.setProvider(resultSet.getString("provider"));
        OAuth2Member.setProviderId(resultSet.getString("providerId"));
        OAuth2Member.setName(resultSet.getString("NAME"));
        OAuth2Member.setEmail(resultSet.getString("email"));
        OAuth2Member.setAccessToken(resultSet.getString("accessToken"));
        OAuth2Member.setExpiresAt(resultSet.getTimestamp("expiresAt"));

        return OAuth2Member;
    }
}
