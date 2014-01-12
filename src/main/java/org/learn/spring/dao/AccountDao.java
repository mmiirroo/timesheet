package org.learn.spring.dao;

import org.learn.spring.domain.Account;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDao extends NamedParameterJdbcDaoSupport {
    public void saveAccount(Account account) {
        String sql = "insert into account(id,name,age,sex)" + "values(:id, :name, :age, :sex)";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", account.getId());
        paramMap.put("name", account.getName());
        paramMap.put("age", account.getAge());
        paramMap.put("sex", account.getSex());

        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }

    public Account getAccountById(int id) {
        String sql = "select id, name, age, sex from account where id = :id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        List<Account> matchedAccounts = getNamedParameterJdbcTemplate().query(sql, paramMap, new ParameterizedRowMapper<Account>() {

            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                return account;
            }
        });
        return matchedAccounts.size() > 0 ? matchedAccounts.get(0) : null;
    }

    public boolean removeAccount(Account account) {
        String sql = "delete from account where  id = :id and name = :name and age = :age and sex = :sex ";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", account.getId());
        paramMap.put("name", account.getName());
        paramMap.put("age", account.getAge());
        paramMap.put("sex", account.getSex());

        getNamedParameterJdbcTemplate().update(sql, paramMap);
        return false;
    }
}
