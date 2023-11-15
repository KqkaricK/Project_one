package com.learning.post;

import com.learning.entity.Post;
import com.learning.post.mapper.PostMapper;
import com.learning.util.paginated.PaginatedListHelper;
import com.learning.util.paginated.SimplePaginatedList;
import com.learning.web.post.PostForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("PostRepository")
public class PostRepositoryImpl extends BasePostImpl implements PostRepository {

    public SimplePaginatedList getPost (PostForm form){
        String sql = "select count(distinct u.id) from post u";
        String filt = "";
        if (StringUtils.isNotBlank(form.getPost()))
            filt += " where u.title LIKE '%" + form.getPost() + "'%";
        sql = sql + filt;
        int total = -1;
        if (form.getPageSize() != -1) {
            total = getJdbcTemplate().queryForObject(sql, Integer.class);
            form.fixPageNumber(total);
        }

        StringBuilder sql2 = new StringBuilder();

        sql2.append("select * from post u " + filt);

        if (form.getPageSize() > 0) {
            sql2.append(" \nlimit ");
            if (form.getFirstResult() > 0)
                sql2.append(form.getFirstResult())
                        .append(", ");
            sql2.append(form.getPageSize());
        }

        List list = getJdbcTemplate().query(sql2.toString(), new PostMapper());
        if (total == -1)
            total = list.size();
        return PaginatedListHelper.getPaginatedList(list, total, form);
    }

    public Post getPostById(Integer Id) {
        PostMapper postmapper = new PostMapper();
        String sql = "select * from post where id = ?";
        List<Post> list = getJdbcTemplate().query(sql, postmapper, Id);
        if (list.isEmpty()){
            return null;
        }else {
            return (Post)list.get(0);
        }
    }

    public void saveOrUpdate(final Post post) {
        if(post!=null && post.getTitle()!=null && post.getFul_text()!=null && post.getAnons()!=null) {
            if(post.getId() > 0) {
                String update = "update post set" +
                        " title = ?, anons = ?, ful_text = ?" +
                        " where id = ?";
                getJdbcTemplate().update(update,
                        post.getTitle(),
                        post.getAnons(),
                        post.getFul_text(),
                        post.getId());
            } else {
                String insert = "insert into post (" +
                        " title, anons , ful_text) " +
                        " VALUES('"+post.getTitle()+"', '"
                        +post.getAnons()+"','"
                        +post.getFul_text()+"')";

                KeyHolder keyHolder = new GeneratedKeyHolder();
                getJdbcTemplate().update(
                        con -> {
                            return con.prepareStatement(insert,new String[]{"id"});
                        },
                        keyHolder
                );
                post.setId(keyHolder.getKey().intValue());
            }
        }
    }
}



