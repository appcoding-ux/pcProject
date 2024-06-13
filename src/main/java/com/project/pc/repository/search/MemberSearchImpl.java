package com.project.pc.repository.search;

import com.project.pc.domain.Member;
import com.project.pc.domain.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MemberSearchImpl extends QuerydslRepositorySupport implements MemberSearch {

    public MemberSearchImpl(){
        super(Member.class);
    }

    @Override
    public Page<Member> searchWithAll(String keyword, Pageable pageable) {
        QMember member = QMember.member;
        JPQLQuery<Member> query = from(member);

        if(keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            booleanBuilder.or(member.name.contains(keyword));

            query.where(booleanBuilder).fetch();
        }

        this.getQuerydsl().applyPagination(pageable, query);

        List<Member> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
