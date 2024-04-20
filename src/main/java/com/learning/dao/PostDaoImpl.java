package com.learning.dao;

import com.learning.entity.Post;
import com.learning.dao.mapper.PostMapper;
import com.learning.util.paginated.PaginatedListHelper;
import com.learning.util.paginated.SimplePaginatedList;
import com.learning.web.blog.BlogForm;
import com.learning.web.post.PostsForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("PostRepository")
public class PostDaoImpl extends BaseDaoImpl implements PostDao {

    public SimplePaginatedList getPosts (PostsForm form){
        String sql = "select count(distinct u.id) from post u";
        String filt = "";
        if (StringUtils.isNotBlank(form.getPost()))
            filt += " where u.title LIKE '%" + form.getPost() + "%'";
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

    @Override
    public SimplePaginatedList getTitle(BlogForm form) {
        String sql = "select count(distinct u.id) from post u";
        String filt = "";
        if (StringUtils.isNotBlank(form.getTitle()))
            filt += " where u.title LIKE '%" + form.getTitle() + "%'";
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
        List list = getJdbcTemplate().query(sql, postmapper, new Object[]{Id});
        if (list.isEmpty()){
            return null;
        }else {
            return (Post)list.get(0);
        }
    }

    public void SaveOrUpdate(final Post post) {
        if(post!=null && post.getTitle()!=null && post.getAnons()!=null && post.getFul_text()!=null ) {
            if(post.getId() > 0) {
                String update = "update post set title = ?, anons = ?, ful_text = ? where id = ?";
                getJdbcTemplate().update(update,
                        post.getTitle(),
                        post.getAnons(),
                        post.getFul_text(),
                        post.getId());
            }else {
                String insert = "insert into post (" +
                        " title, anons , ful_text ) " +
                        " VALUES('"+post.getTitle()+"','"
                        +post.getAnons()+"','"
                        +post.getFul_text()+"')";

                KeyHolder keyHolder = new GeneratedKeyHolder();
                getJdbcTemplate().update(
                        con -> {
                            PreparedStatement pst = con.prepareStatement(insert,new String[]{"id"});
                            return pst;
                        },
                        keyHolder
                );
                post.setId(keyHolder.getKey().intValue());
            }
        }
    }

    @Override
    public void deletePost(Integer Id) {
        String sql = "delete from post where id = ?";
        getJdbcTemplate().update(sql, new Object[]{Id});
    }


}



