package spring.springportfolio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import spring.springportfolio.domain.Member;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert =  new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("name",member.getName());
        //4줄로 insert query를 짜지않아도 만들어줌
        Number key=jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result=jdbcTemplate.query(" select * from member where id=? ",memberRowMapper(), id);
//객체를 memberRowMapper에서 만들어서 리스트로 반환후
        return result.stream().findAny();
        //받은 리스트result를 Optiona로 바꿔서 반환--findByName도 같음
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result=jdbcTemplate.query(" select * from member where name=? ",memberRowMapper(),name);

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        return jdbcTemplate.query(" select * from member ",memberRowMapper());
        //객체는 memberRowMapper에서 콜백함수로 만들어줌
    }

    private RowMapper<Member> memberRowMapper(){
        return new RowMapper<Member>() {//콜백함수
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member=new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        };
    }
}
