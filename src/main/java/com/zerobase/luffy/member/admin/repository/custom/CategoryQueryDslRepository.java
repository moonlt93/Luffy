package com.zerobase.luffy.member.admin.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zerobase.luffy.member.admin.entity.Category;
import com.zerobase.luffy.member.admin.entity.QCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryQueryDslRepository {

    private final JPAQueryFactory query;
    public List<Category> findAllWithQuerydsl() {
        QCategory parent = new QCategory("parent");
        QCategory child = new QCategory("children");

        return query.selectFrom(parent)
                .distinct()
                .leftJoin(parent.children,child)
                .fetchJoin()
                .where(
                        parent.parent.isNull()
                )
                .orderBy(parent.sortValue.asc(), child.sortValue.asc())
                .fetch();

    }



}
